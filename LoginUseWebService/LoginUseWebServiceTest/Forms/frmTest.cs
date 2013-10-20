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
                this.lblResult.Text = "";
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
 
                if (dLen < 4)
                {
                    FileStream fStream = new FileStream(filename, FileMode.Open, FileAccess.Read);
                    BinaryReader br = new BinaryReader(fStream); 
                    byte[] data = br.ReadBytes((int)numBytes);
                    br.Close();

                    string sTmp = srv.UploadFile(data, strFile);
                    fStream.Close();
                    fStream.Dispose();

                    this.lblResult.Text = sTmp;
                }
                else
                {
                    // Display message if the file was too large to upload
                    this.lblResult.Text = "The file selected exceeds the size limit for uploads.";
                }
            }
            catch (Exception ex)
            {
                // display an error message to the user
                this.lblResult.Text = ex.Message.ToString();
            }
        }
    }
}
