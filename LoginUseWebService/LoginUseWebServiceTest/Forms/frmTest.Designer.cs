namespace LoginUseWebServiceTest
{
    partial class frmTest
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.btnSelectFile = new System.Windows.Forms.Button();
            this.tlp1 = new System.Windows.Forms.TableLayoutPanel();
            this.txtFile = new System.Windows.Forms.TextBox();
            this.lblSelectFile = new System.Windows.Forms.Label();
            this.btnSend = new System.Windows.Forms.Button();
            this.lblResult = new System.Windows.Forms.Label();
            this.ofdFile = new System.Windows.Forms.OpenFileDialog();
            this.lblPhoneId = new System.Windows.Forms.Label();
            this.txtPhoneId = new System.Windows.Forms.TextBox();
            this.tlp1.SuspendLayout();
            this.SuspendLayout();
            // 
            // btnSelectFile
            // 
            this.btnSelectFile.Location = new System.Drawing.Point(473, 3);
            this.btnSelectFile.Name = "btnSelectFile";
            this.btnSelectFile.Size = new System.Drawing.Size(42, 38);
            this.btnSelectFile.TabIndex = 0;
            this.btnSelectFile.Text = "...";
            this.btnSelectFile.UseVisualStyleBackColor = true;
            this.btnSelectFile.Click += new System.EventHandler(this.btnSelectFile_Click);
            // 
            // tlp1
            // 
            this.tlp1.ColumnCount = 3;
            this.tlp1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tlp1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tlp1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tlp1.Controls.Add(this.btnSelectFile, 2, 0);
            this.tlp1.Controls.Add(this.txtFile, 1, 0);
            this.tlp1.Controls.Add(this.lblSelectFile, 0, 0);
            this.tlp1.Controls.Add(this.btnSend, 1, 2);
            this.tlp1.Controls.Add(this.lblResult, 1, 3);
            this.tlp1.Controls.Add(this.lblPhoneId, 0, 1);
            this.tlp1.Controls.Add(this.txtPhoneId, 1, 1);
            this.tlp1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tlp1.Location = new System.Drawing.Point(0, 0);
            this.tlp1.Name = "tlp1";
            this.tlp1.RowCount = 4;
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 40F));
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 26.38889F));
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 73.61111F));
            this.tlp1.Size = new System.Drawing.Size(518, 374);
            this.tlp1.TabIndex = 1;
            // 
            // txtFile
            // 
            this.txtFile.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Left | System.Windows.Forms.AnchorStyles.Right)));
            this.txtFile.Location = new System.Drawing.Point(82, 11);
            this.txtFile.Name = "txtFile";
            this.txtFile.Size = new System.Drawing.Size(385, 22);
            this.txtFile.TabIndex = 1;
            // 
            // lblSelectFile
            // 
            this.lblSelectFile.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblSelectFile.AutoSize = true;
            this.lblSelectFile.Location = new System.Drawing.Point(3, 13);
            this.lblSelectFile.Name = "lblSelectFile";
            this.lblSelectFile.Size = new System.Drawing.Size(73, 17);
            this.lblSelectFile.TabIndex = 2;
            this.lblSelectFile.Text = "Select File";
            // 
            // btnSend
            // 
            this.btnSend.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.btnSend.Location = new System.Drawing.Point(237, 110);
            this.btnSend.Name = "btnSend";
            this.btnSend.Size = new System.Drawing.Size(75, 23);
            this.btnSend.TabIndex = 3;
            this.btnSend.Text = "Send";
            this.btnSend.UseVisualStyleBackColor = true;
            this.btnSend.Click += new System.EventHandler(this.btnSend_Click);
            // 
            // lblResult
            // 
            this.lblResult.AutoSize = true;
            this.lblResult.Dock = System.Windows.Forms.DockStyle.Fill;
            this.lblResult.Location = new System.Drawing.Point(82, 160);
            this.lblResult.Name = "lblResult";
            this.lblResult.Size = new System.Drawing.Size(385, 214);
            this.lblResult.TabIndex = 4;
            this.lblResult.Text = "Result";
            // 
            // lblPhoneId
            // 
            this.lblPhoneId.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblPhoneId.AutoSize = true;
            this.lblPhoneId.Location = new System.Drawing.Point(3, 55);
            this.lblPhoneId.Name = "lblPhoneId";
            this.lblPhoneId.Size = new System.Drawing.Size(60, 17);
            this.lblPhoneId.TabIndex = 5;
            this.lblPhoneId.Text = "PhoneId";
            // 
            // txtPhoneId
            // 
            this.txtPhoneId.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.txtPhoneId.Location = new System.Drawing.Point(82, 53);
            this.txtPhoneId.Name = "txtPhoneId";
            this.txtPhoneId.Size = new System.Drawing.Size(200, 22);
            this.txtPhoneId.TabIndex = 6;
            // 
            // frmTest
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(518, 374);
            this.Controls.Add(this.tlp1);
            this.Name = "frmTest";
            this.Text = "WebService Test";
            this.tlp1.ResumeLayout(false);
            this.tlp1.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button btnSelectFile;
        private System.Windows.Forms.TableLayoutPanel tlp1;
        private System.Windows.Forms.TextBox txtFile;
        private System.Windows.Forms.Label lblSelectFile;
        private System.Windows.Forms.OpenFileDialog ofdFile;
        private System.Windows.Forms.Button btnSend;
        private System.Windows.Forms.Label lblResult;
        private System.Windows.Forms.Label lblPhoneId;
        private System.Windows.Forms.TextBox txtPhoneId;
    }
}

