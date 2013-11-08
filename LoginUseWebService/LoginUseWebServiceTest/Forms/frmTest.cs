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
        private byte[] data;

        public frmTest()
        {
            InitializeComponent();
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
 
                if (dLen < 4 && this.txtPhoneId.Text.Length > 0)
                {
                    FileStream fStream = new FileStream(filename, FileMode.Open, FileAccess.Read);
                    BinaryReader br = new BinaryReader(fStream); 
                    byte[] data = br.ReadBytes((int)numBytes);
                    br.Close();

                    string data64 = Convert.ToBase64String(data);

                    string sTmp = srv.UploadFile(data64, this.txtPhoneId.Text);
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
    }
}
