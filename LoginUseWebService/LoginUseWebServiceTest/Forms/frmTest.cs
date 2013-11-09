using System;
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
            this.inicTypes();
            this.inicProperties();
            this.inicUsers();
        }

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

        private void UploadFile(string filename)
        {
            try
            {
                String strFile = System.IO.Path.GetFileName(filename);
                LoginUseService.LoginUse srv = new LoginUseService.LoginUse();
 
                FileInfo fInfo = new FileInfo(filename);
 
                long numBytes = fInfo.Length;
                double dLen = Convert.ToDouble(fInfo.Length / 1000000);
 
                if (dLen < 4 && this.cbxPhones.Text.Length > 0)
                {
                    FileStream fStream = new FileStream(filename, FileMode.Open, FileAccess.Read);
                    BinaryReader br = new BinaryReader(fStream); 
                    byte[] data = br.ReadBytes((int)numBytes);
                    br.Close();

                    string data64 = Convert.ToBase64String(data);

                    string sTmp = srv.UploadFile(data64, this.cbxPhones.Text);
                    fStream.Close();
                    fStream.Dispose();

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

        private void inicTypes()
        {
            try
            {
                LoginUseService.LoginUse srv = new LoginUseService.LoginUse();

                string[] types = srv.getTypes(null);

                for (int i = 0; i < types.Length; i++)
                    this.chbTypes.Items.Add(types[i], true);
            }
            catch (Exception ex) { }
        }

        private void inicProperties()
        {
            try
            {
                LoginUseService.LoginUse srv = new LoginUseService.LoginUse();

                string[] properties = srv.getProperties(null);

                for (int i = 0; i < properties.Length; i++)
                    this.chbProperties.Items.Add(properties[i], true);
            }
            catch (Exception ex) { }
        }

        private void inicUsers()
        {
            try
            {
                LoginUseService.LoginUse srv = new LoginUseService.LoginUse();

                string[] users = srv.getUsers(null);
                this.cbxUser.DataSource = users;

                string[] phoneIds = srv.getPhoneIds(null);
                this.cbxPhones.DataSource = phoneIds;
            }
            catch (Exception ex) { }
        }
    }
}
