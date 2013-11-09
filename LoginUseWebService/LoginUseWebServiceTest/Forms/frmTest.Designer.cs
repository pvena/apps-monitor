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
            this.lblPhoneId = new System.Windows.Forms.Label();
            this.lblCSV = new System.Windows.Forms.Label();
            this.lblUser = new System.Windows.Forms.Label();
            this.lblType = new System.Windows.Forms.Label();
            this.lblProperty = new System.Windows.Forms.Label();
            this.lblFrom = new System.Windows.Forms.Label();
            this.cbxUser = new System.Windows.Forms.ComboBox();
            this.chbTypes = new System.Windows.Forms.CheckedListBox();
            this.chbProperties = new System.Windows.Forms.CheckedListBox();
            this.dtpFrom = new System.Windows.Forms.DateTimePicker();
            this.lblTo = new System.Windows.Forms.Label();
            this.dtpTo = new System.Windows.Forms.DateTimePicker();
            this.btnFind = new System.Windows.Forms.Button();
            this.lblSend = new System.Windows.Forms.Label();
            this.ofdFile = new System.Windows.Forms.OpenFileDialog();
            this.cbxPhones = new System.Windows.Forms.ComboBox();
            this.tlp1.SuspendLayout();
            this.SuspendLayout();
            // 
            // btnSelectFile
            // 
            this.btnSelectFile.Location = new System.Drawing.Point(791, 23);
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
            this.tlp1.Controls.Add(this.btnSelectFile, 2, 1);
            this.tlp1.Controls.Add(this.txtFile, 1, 1);
            this.tlp1.Controls.Add(this.lblSelectFile, 0, 1);
            this.tlp1.Controls.Add(this.btnSend, 1, 3);
            this.tlp1.Controls.Add(this.lblPhoneId, 0, 2);
            this.tlp1.Controls.Add(this.lblCSV, 1, 4);
            this.tlp1.Controls.Add(this.lblUser, 0, 5);
            this.tlp1.Controls.Add(this.lblType, 0, 6);
            this.tlp1.Controls.Add(this.lblProperty, 0, 7);
            this.tlp1.Controls.Add(this.lblFrom, 0, 8);
            this.tlp1.Controls.Add(this.cbxUser, 1, 5);
            this.tlp1.Controls.Add(this.chbTypes, 1, 6);
            this.tlp1.Controls.Add(this.chbProperties, 1, 7);
            this.tlp1.Controls.Add(this.dtpFrom, 1, 8);
            this.tlp1.Controls.Add(this.lblTo, 0, 9);
            this.tlp1.Controls.Add(this.dtpTo, 1, 9);
            this.tlp1.Controls.Add(this.btnFind, 1, 10);
            this.tlp1.Controls.Add(this.lblSend, 1, 0);
            this.tlp1.Controls.Add(this.cbxPhones, 1, 2);
            this.tlp1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tlp1.Location = new System.Drawing.Point(0, 0);
            this.tlp1.Name = "tlp1";
            this.tlp1.RowCount = 12;
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 20F));
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 20F));
            this.tlp1.Size = new System.Drawing.Size(836, 458);
            this.tlp1.TabIndex = 1;
            // 
            // txtFile
            // 
            this.txtFile.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Left | System.Windows.Forms.AnchorStyles.Right)));
            this.txtFile.Location = new System.Drawing.Point(82, 31);
            this.txtFile.Name = "txtFile";
            this.txtFile.Size = new System.Drawing.Size(703, 22);
            this.txtFile.TabIndex = 1;
            // 
            // lblSelectFile
            // 
            this.lblSelectFile.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblSelectFile.AutoSize = true;
            this.lblSelectFile.Location = new System.Drawing.Point(3, 33);
            this.lblSelectFile.Name = "lblSelectFile";
            this.lblSelectFile.Size = new System.Drawing.Size(73, 17);
            this.lblSelectFile.TabIndex = 2;
            this.lblSelectFile.Text = "Select File";
            // 
            // btnSend
            // 
            this.btnSend.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.btnSend.Location = new System.Drawing.Point(396, 98);
            this.btnSend.Name = "btnSend";
            this.btnSend.Size = new System.Drawing.Size(75, 23);
            this.btnSend.TabIndex = 3;
            this.btnSend.Text = "Send";
            this.btnSend.UseVisualStyleBackColor = true;
            this.btnSend.Click += new System.EventHandler(this.btnSend_Click);
            // 
            // lblPhoneId
            // 
            this.lblPhoneId.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblPhoneId.AutoSize = true;
            this.lblPhoneId.Location = new System.Drawing.Point(3, 71);
            this.lblPhoneId.Name = "lblPhoneId";
            this.lblPhoneId.Size = new System.Drawing.Size(60, 17);
            this.lblPhoneId.TabIndex = 5;
            this.lblPhoneId.Text = "PhoneId";
            // 
            // lblCSV
            // 
            this.lblCSV.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblCSV.AutoSize = true;
            this.lblCSV.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblCSV.Location = new System.Drawing.Point(376, 124);
            this.lblCSV.Name = "lblCSV";
            this.lblCSV.Size = new System.Drawing.Size(115, 18);
            this.lblCSV.TabIndex = 7;
            this.lblCSV.Text = "CSV Generate";
            // 
            // lblUser
            // 
            this.lblUser.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblUser.AutoSize = true;
            this.lblUser.Location = new System.Drawing.Point(3, 148);
            this.lblUser.Name = "lblUser";
            this.lblUser.Size = new System.Drawing.Size(38, 17);
            this.lblUser.TabIndex = 8;
            this.lblUser.Text = "User";
            // 
            // lblType
            // 
            this.lblType.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblType.AutoSize = true;
            this.lblType.Location = new System.Drawing.Point(3, 208);
            this.lblType.Name = "lblType";
            this.lblType.Size = new System.Drawing.Size(40, 17);
            this.lblType.TabIndex = 9;
            this.lblType.Text = "Type";
            // 
            // lblProperty
            // 
            this.lblProperty.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblProperty.AutoSize = true;
            this.lblProperty.Location = new System.Drawing.Point(3, 298);
            this.lblProperty.Name = "lblProperty";
            this.lblProperty.Size = new System.Drawing.Size(62, 17);
            this.lblProperty.TabIndex = 10;
            this.lblProperty.Text = "Property";
            // 
            // lblFrom
            // 
            this.lblFrom.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblFrom.AutoSize = true;
            this.lblFrom.Location = new System.Drawing.Point(3, 357);
            this.lblFrom.Name = "lblFrom";
            this.lblFrom.Size = new System.Drawing.Size(40, 17);
            this.lblFrom.TabIndex = 12;
            this.lblFrom.Text = "From";
            // 
            // cbxUser
            // 
            this.cbxUser.FormattingEnabled = true;
            this.cbxUser.Location = new System.Drawing.Point(82, 145);
            this.cbxUser.Name = "cbxUser";
            this.cbxUser.Size = new System.Drawing.Size(439, 24);
            this.cbxUser.TabIndex = 13;
            // 
            // chbTypes
            // 
            this.chbTypes.Dock = System.Windows.Forms.DockStyle.Fill;
            this.chbTypes.FormattingEnabled = true;
            this.chbTypes.Location = new System.Drawing.Point(82, 175);
            this.chbTypes.MultiColumn = true;
            this.chbTypes.Name = "chbTypes";
            this.chbTypes.Size = new System.Drawing.Size(703, 72);
            this.chbTypes.TabIndex = 14;
            // 
            // chbProperties
            // 
            this.chbProperties.Dock = System.Windows.Forms.DockStyle.Fill;
            this.chbProperties.FormattingEnabled = true;
            this.chbProperties.Location = new System.Drawing.Point(82, 265);
            this.chbProperties.MultiColumn = true;
            this.chbProperties.Name = "chbProperties";
            this.chbProperties.Size = new System.Drawing.Size(703, 72);
            this.chbProperties.TabIndex = 15;
            // 
            // dtpFrom
            // 
            this.dtpFrom.Location = new System.Drawing.Point(82, 355);
            this.dtpFrom.Name = "dtpFrom";
            this.dtpFrom.Size = new System.Drawing.Size(200, 22);
            this.dtpFrom.TabIndex = 16;
            // 
            // lblTo
            // 
            this.lblTo.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblTo.AutoSize = true;
            this.lblTo.Location = new System.Drawing.Point(3, 385);
            this.lblTo.Name = "lblTo";
            this.lblTo.Size = new System.Drawing.Size(25, 17);
            this.lblTo.TabIndex = 11;
            this.lblTo.Text = "To";
            // 
            // dtpTo
            // 
            this.dtpTo.Location = new System.Drawing.Point(82, 383);
            this.dtpTo.Name = "dtpTo";
            this.dtpTo.Size = new System.Drawing.Size(200, 22);
            this.dtpTo.TabIndex = 17;
            // 
            // btnFind
            // 
            this.btnFind.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.btnFind.Location = new System.Drawing.Point(396, 411);
            this.btnFind.Name = "btnFind";
            this.btnFind.Size = new System.Drawing.Size(75, 23);
            this.btnFind.TabIndex = 18;
            this.btnFind.Text = "Find";
            this.btnFind.UseVisualStyleBackColor = true;
            // 
            // lblSend
            // 
            this.lblSend.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblSend.AutoSize = true;
            this.lblSend.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblSend.Location = new System.Drawing.Point(356, 1);
            this.lblSend.Name = "lblSend";
            this.lblSend.Size = new System.Drawing.Size(155, 18);
            this.lblSend.TabIndex = 19;
            this.lblSend.Text = "Send File Simulator";
            // 
            // cbxPhones
            // 
            this.cbxPhones.FormattingEnabled = true;
            this.cbxPhones.Location = new System.Drawing.Point(82, 67);
            this.cbxPhones.Name = "cbxPhones";
            this.cbxPhones.Size = new System.Drawing.Size(216, 24);
            this.cbxPhones.TabIndex = 20;
            // 
            // frmTest
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(836, 458);
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
        private System.Windows.Forms.Label lblPhoneId;
        private System.Windows.Forms.Label lblUser;
        private System.Windows.Forms.Label lblType;
        private System.Windows.Forms.Label lblProperty;
        private System.Windows.Forms.Label lblFrom;
        private System.Windows.Forms.ComboBox cbxUser;
        private System.Windows.Forms.CheckedListBox chbTypes;
        private System.Windows.Forms.CheckedListBox chbProperties;
        private System.Windows.Forms.DateTimePicker dtpFrom;
        private System.Windows.Forms.Label lblTo;
        private System.Windows.Forms.DateTimePicker dtpTo;
        private System.Windows.Forms.Button btnFind;
        private System.Windows.Forms.Label lblCSV;
        private System.Windows.Forms.Label lblSend;
        private System.Windows.Forms.ComboBox cbxPhones;
    }
}

