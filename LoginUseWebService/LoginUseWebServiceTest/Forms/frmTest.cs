﻿using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace LoginUseWebServiceTest
{
    public partial class frmTest : Form
    {
        public frmTest()
        {
            InitializeComponent();
            this.inicProperties();
            this.inicUsers();
        }

        #region -----------------------Btns Events-------------------------

        private void btnSelectFile_Click(object sender, EventArgs e)
        {
            if (this.ofdFile.ShowDialog() == DialogResult.OK)
            {                
                this.txtFile.Text = this.ofdFile.FileName;
            }
        }

        private void btnSend_Click(object sender, EventArgs e)
        {
            this.UploadFile(this.ofdFile.FileName);
        }

        private void btnFind_Click(object sender, EventArgs e)
        {
            this.find();
        }

        #endregion

        #region -------------------Properties ---------------------
        private User SelectedUser 
        {
            get 
            {
                return (User)this.cbxUser.SelectedItem;
            }
        }

        private string SelectedProperties
        {
            get
            {
                string values = "";
                foreach (object o in chbProperties.CheckedItems)
                    values += o.ToString() + ";";
                return values.Substring(0, values.Length - 1);
            }
        }
        #endregion

        #region  ------------------ Load Data -------------------------

        private void inicProperties()
        {
            try
            {
                List<Property> properties = new DBObjectProvider().getProperties(-1);

                foreach (Property p in properties)
                    this.chbProperties.Items.Add(p.FullName, true);
            }
            catch (Exception ex) { }
        }

        private void inicUsers()
        {
            try
            {
                List<User> users = new DBObjectProvider().getUsers();

                this.cbxUser.DataSource = users;
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        #endregion

        private void UploadFile(string filename)
        {
            try
            {
                String strFile = System.IO.Path.GetFileName(filename);
                LoginUseService.LoginUse srv = new LoginUseService.LoginUse();
 
                FileInfo fInfo = new FileInfo(filename);
 
                long numBytes = fInfo.Length;
                double dLen = Convert.ToDouble(fInfo.Length / 1000000);
 
                if (dLen < 4 && this.SelectedUser != null)
                {
                    FileStream fStream = new FileStream(filename, FileMode.Open, FileAccess.Read);
                    BinaryReader br = new BinaryReader(fStream); 
                    byte[] data = br.ReadBytes((int)numBytes);
                    br.Close();

                    string data64 = Convert.ToBase64String(data);

                    string sTmp = srv.UploadFile(data64, this.SelectedUser.phoneId);
                    fStream.Close();
                    fStream.Dispose();

                    MessageBox.Show(sTmp);

                }
                else
                {
                    if(dLen >= 4)
                        MessageBox.Show("The file selected exceeds the size limit for uploads.");
                    else
                        MessageBox.Show("Cargar un Valor en el campo PhoneId.");
                }
            }
            catch (Exception ex)
            {
                // display an error message to the user
                MessageBox.Show(ex.Message.ToString());
            }
        }

        private void find()
        {
            DateTime from = this.dtpFrom.Value;
            DateTime to = this.dtpTo.Value;

            LoginUseService.LoginUse srv = new LoginUseService.LoginUse();

            string base64Data = srv.getCSVData(this.SelectedUser.phoneId, from, to, SelectedProperties);

            if (base64Data != null && base64Data.Length > 0)
                if (this.sfdFile.ShowDialog() == DialogResult.OK)
                    System.IO.File.WriteAllBytes(this.sfdFile.FileName, Convert.FromBase64String(base64Data));
                else
                    MessageBox.Show("File not saved.");
            else
                MessageBox.Show("Response Problem.");

        }

        private void cbxUser_SelectedIndexChanged(object sender, EventArgs e)
        {            
            this.dgvFileInfo.DataSource = new DBObjectProvider().getFiles(this.SelectedUser);
            this.cbxType.DataSource = new DBObjectProvider().getTypes();
            this.dgvCommands.DataSource = new DBObjectProvider().getCommands(this.SelectedUser.phoneId);
            this.lblPhoneId.Text = "PhoneId: " + this.SelectedUser.phoneId;
            this.lblVersion.Text = "Version: " + this.SelectedUser.version;
            this.lblPhoneModel.Text = "Phone Model: " + this.SelectedUser.phoneModel;
            this.lblMaxLocation.Text = "Max Location: " + this.SelectedUser.maxLocation;
            this.lblLastProcess.Text = "Last Process: " + this.SelectedUser.lastLocationProcess.ToShortDateString();
        }

        private void cbxType_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (cbxType.SelectedItem != null) 
            {
                this.cbxProperty.DataSource = new DBObjectProvider().getProperties(((Property)cbxType.SelectedItem).id);
            }
        }

        private void cbxProperty_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (cbxProperty.SelectedItem != null)
            {
                this.cbxValue.DataSource = new DBObjectProvider().getPropertyValues(((Property)cbxProperty.SelectedItem).id);
            }
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            if ((dgvCommands.CurrentRow != null) &&
                (cbxType.SelectedItem != null) &&
                (cbxProperty.SelectedItem != null) &&
                (this.cbxValue.SelectedItem != null) &&
                !string.IsNullOrEmpty(cbxCommand.Text))
            {
                DBManager db = new DBManager();
                Property type = (Property)cbxType.SelectedItem;
                Property property = (Property)cbxProperty.SelectedItem;
                Property value = (Property)cbxValue.SelectedItem;
                Property command = (Property)dgvCommands.CurrentRow.DataBoundItem;
                db.InsertCommands(this.SelectedUser.phoneId, command.id, value.id, cbxCommand.Text);

            }
            this.dgvCommands.DataSource = new DBObjectProvider().getCommands(this.SelectedUser.phoneId);
        }

        private void btnDel_Click(object sender, EventArgs e)
        {
            if (dgvCommands.CurrentRow != null)
            {
                DBManager db = new DBManager();
                Property command = (Property)dgvCommands.CurrentRow.DataBoundItem;
                db.deleteCommands(this.SelectedUser.phoneId, command.id, command.fId);
            }
            this.dgvCommands.DataSource = new DBObjectProvider().getCommands(this.SelectedUser.phoneId);
        }

        private void btnNew_Click(object sender, EventArgs e)
        {
            if ((cbxType.SelectedItem != null) && 
                (cbxProperty.SelectedItem != null) && 
                (this.cbxValue.SelectedItem != null) && 
                !string.IsNullOrEmpty(cbxCommand.Text))
            {
                DBManager db = new DBManager();
                Property type = (Property)cbxType.SelectedItem;
                Property property = (Property)cbxProperty.SelectedItem;
                Property value = (Property)cbxValue.SelectedItem;
                db.InsertCommands(this.SelectedUser.phoneId, -1, value.id, cbxCommand.Text);
            }
            this.dgvCommands.DataSource = new DBObjectProvider().getCommands(this.SelectedUser.phoneId);
        }

        
    }
}
