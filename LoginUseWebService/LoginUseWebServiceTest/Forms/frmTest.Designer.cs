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
            this.components = new System.ComponentModel.Container();
            this.btnSelectFile = new System.Windows.Forms.Button();
            this.tlp1 = new System.Windows.Forms.TableLayoutPanel();
            this.txtFile = new System.Windows.Forms.TextBox();
            this.lblSelectFile = new System.Windows.Forms.Label();
            this.btnSend = new System.Windows.Forms.Button();
            this.btnFind = new System.Windows.Forms.Button();
            this.lblSend = new System.Windows.Forms.Label();
            this.lblCSV = new System.Windows.Forms.Label();
            this.lblFrom = new System.Windows.Forms.Label();
            this.dtpFrom = new System.Windows.Forms.DateTimePicker();
            this.lblProperty = new System.Windows.Forms.Label();
            this.lblTo = new System.Windows.Forms.Label();
            this.chbProperties = new System.Windows.Forms.CheckedListBox();
            this.dtpTo = new System.Windows.Forms.DateTimePicker();
            this.lblUser = new System.Windows.Forms.Label();
            this.cbxUser = new System.Windows.Forms.ComboBox();
            this.userBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.ofdFile = new System.Windows.Forms.OpenFileDialog();
            this.sfdFile = new System.Windows.Forms.SaveFileDialog();
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.tabPage2 = new System.Windows.Forms.TabPage();
            this.dgvFileInfo = new System.Windows.Forms.DataGridView();
            this.nameDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.processDataGridViewCheckBoxColumn = new System.Windows.Forms.DataGridViewCheckBoxColumn();
            this.isZipDataGridViewCheckBoxColumn = new System.Windows.Forms.DataGridViewCheckBoxColumn();
            this.fileBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.tabPage3 = new System.Windows.Forms.TabPage();
            this.tabPage4 = new System.Windows.Forms.TabPage();
            this.tableLayoutPanel2 = new System.Windows.Forms.TableLayoutPanel();
            this.lblType = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.cbxType = new System.Windows.Forms.ComboBox();
            this.prTypeBS = new System.Windows.Forms.BindingSource(this.components);
            this.cbxProperty = new System.Windows.Forms.ComboBox();
            this.prPRBS = new System.Windows.Forms.BindingSource(this.components);
            this.cbxValue = new System.Windows.Forms.ComboBox();
            this.prPVBS = new System.Windows.Forms.BindingSource(this.components);
            this.dgvCommands = new System.Windows.Forms.DataGridView();
            this.id = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.fId = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.nameDataGridViewTextBoxColumn1 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.valueDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.prCMBS = new System.Windows.Forms.BindingSource(this.components);
            this.lblCommand = new System.Windows.Forms.Label();
            this.btnAdd = new System.Windows.Forms.Button();
            this.btnDel = new System.Windows.Forms.Button();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.lblPhoneModel = new System.Windows.Forms.Label();
            this.lblVersion = new System.Windows.Forms.Label();
            this.lblPhoneId = new System.Windows.Forms.Label();
            this.lblMaxLocation = new System.Windows.Forms.Label();
            this.lblLastProcess = new System.Windows.Forms.Label();
            this.btnNew = new System.Windows.Forms.Button();
            this.cbxCommand = new System.Windows.Forms.ComboBox();
            this.tlp1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.userBindingSource)).BeginInit();
            this.tabControl1.SuspendLayout();
            this.tabPage1.SuspendLayout();
            this.tabPage2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvFileInfo)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.fileBindingSource)).BeginInit();
            this.tabPage4.SuspendLayout();
            this.tableLayoutPanel2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.prTypeBS)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.prPRBS)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.prPVBS)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgvCommands)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.prCMBS)).BeginInit();
            this.tableLayoutPanel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // btnSelectFile
            // 
            this.btnSelectFile.Location = new System.Drawing.Point(527, 21);
            this.btnSelectFile.Name = "btnSelectFile";
            this.btnSelectFile.Size = new System.Drawing.Size(42, 24);
            this.btnSelectFile.TabIndex = 0;
            this.btnSelectFile.Text = "...";
            this.btnSelectFile.UseVisualStyleBackColor = true;
            this.btnSelectFile.Click += new System.EventHandler(this.btnSelectFile_Click);
            // 
            // tlp1
            // 
            this.tlp1.BackColor = System.Drawing.SystemColors.Control;
            this.tlp1.ColumnCount = 3;
            this.tlp1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tlp1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tlp1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tlp1.Controls.Add(this.btnSelectFile, 2, 1);
            this.tlp1.Controls.Add(this.txtFile, 1, 1);
            this.tlp1.Controls.Add(this.lblSelectFile, 0, 1);
            this.tlp1.Controls.Add(this.btnSend, 1, 2);
            this.tlp1.Controls.Add(this.btnFind, 1, 7);
            this.tlp1.Controls.Add(this.lblSend, 1, 0);
            this.tlp1.Controls.Add(this.lblCSV, 1, 3);
            this.tlp1.Controls.Add(this.lblFrom, 0, 4);
            this.tlp1.Controls.Add(this.dtpFrom, 1, 4);
            this.tlp1.Controls.Add(this.lblProperty, 0, 6);
            this.tlp1.Controls.Add(this.lblTo, 0, 5);
            this.tlp1.Controls.Add(this.chbProperties, 1, 6);
            this.tlp1.Controls.Add(this.dtpTo, 1, 5);
            this.tlp1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tlp1.Location = new System.Drawing.Point(3, 3);
            this.tlp1.Name = "tlp1";
            this.tlp1.RowCount = 9;
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 92F));
            this.tlp1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 40F));
            this.tlp1.Size = new System.Drawing.Size(572, 503);
            this.tlp1.TabIndex = 1;
            // 
            // txtFile
            // 
            this.txtFile.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Left | System.Windows.Forms.AnchorStyles.Right)));
            this.txtFile.Location = new System.Drawing.Point(82, 22);
            this.txtFile.Name = "txtFile";
            this.txtFile.Size = new System.Drawing.Size(439, 22);
            this.txtFile.TabIndex = 1;
            // 
            // lblSelectFile
            // 
            this.lblSelectFile.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblSelectFile.AutoSize = true;
            this.lblSelectFile.Location = new System.Drawing.Point(3, 24);
            this.lblSelectFile.Name = "lblSelectFile";
            this.lblSelectFile.Size = new System.Drawing.Size(73, 17);
            this.lblSelectFile.TabIndex = 2;
            this.lblSelectFile.Text = "Select File";
            // 
            // btnSend
            // 
            this.btnSend.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.btnSend.Location = new System.Drawing.Point(264, 51);
            this.btnSend.Name = "btnSend";
            this.btnSend.Size = new System.Drawing.Size(75, 23);
            this.btnSend.TabIndex = 3;
            this.btnSend.Text = "Send";
            this.btnSend.UseVisualStyleBackColor = true;
            this.btnSend.Click += new System.EventHandler(this.btnSend_Click);
            // 
            // btnFind
            // 
            this.btnFind.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.btnFind.Location = new System.Drawing.Point(264, 450);
            this.btnFind.Name = "btnFind";
            this.btnFind.Size = new System.Drawing.Size(75, 23);
            this.btnFind.TabIndex = 18;
            this.btnFind.Text = "Find";
            this.btnFind.UseVisualStyleBackColor = true;
            this.btnFind.Click += new System.EventHandler(this.btnFind_Click);
            // 
            // lblSend
            // 
            this.lblSend.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblSend.AutoSize = true;
            this.lblSend.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblSend.Location = new System.Drawing.Point(224, 0);
            this.lblSend.Name = "lblSend";
            this.lblSend.Size = new System.Drawing.Size(155, 18);
            this.lblSend.TabIndex = 19;
            this.lblSend.Text = "Send File Simulator";
            // 
            // lblCSV
            // 
            this.lblCSV.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.lblCSV.AutoSize = true;
            this.lblCSV.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblCSV.Location = new System.Drawing.Point(244, 77);
            this.lblCSV.Name = "lblCSV";
            this.lblCSV.Size = new System.Drawing.Size(115, 18);
            this.lblCSV.TabIndex = 7;
            this.lblCSV.Text = "CSV Generate";
            // 
            // lblFrom
            // 
            this.lblFrom.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblFrom.AutoSize = true;
            this.lblFrom.Location = new System.Drawing.Point(3, 100);
            this.lblFrom.Name = "lblFrom";
            this.lblFrom.Size = new System.Drawing.Size(40, 17);
            this.lblFrom.TabIndex = 12;
            this.lblFrom.Text = "From";
            // 
            // dtpFrom
            // 
            this.dtpFrom.CustomFormat = "yyyy/MM/dd hh:mm:ss";
            this.dtpFrom.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.dtpFrom.Location = new System.Drawing.Point(82, 98);
            this.dtpFrom.Name = "dtpFrom";
            this.dtpFrom.Size = new System.Drawing.Size(187, 22);
            this.dtpFrom.TabIndex = 16;
            // 
            // lblProperty
            // 
            this.lblProperty.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblProperty.AutoSize = true;
            this.lblProperty.Location = new System.Drawing.Point(3, 275);
            this.lblProperty.Name = "lblProperty";
            this.lblProperty.Size = new System.Drawing.Size(62, 17);
            this.lblProperty.TabIndex = 10;
            this.lblProperty.Text = "Property";
            // 
            // lblTo
            // 
            this.lblTo.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblTo.AutoSize = true;
            this.lblTo.Location = new System.Drawing.Point(3, 128);
            this.lblTo.Name = "lblTo";
            this.lblTo.Size = new System.Drawing.Size(25, 17);
            this.lblTo.TabIndex = 11;
            this.lblTo.Text = "To";
            // 
            // chbProperties
            // 
            this.chbProperties.CheckOnClick = true;
            this.chbProperties.Dock = System.Windows.Forms.DockStyle.Fill;
            this.chbProperties.FormattingEnabled = true;
            this.chbProperties.Location = new System.Drawing.Point(82, 154);
            this.chbProperties.MultiColumn = true;
            this.chbProperties.Name = "chbProperties";
            this.chbProperties.Size = new System.Drawing.Size(439, 259);
            this.chbProperties.TabIndex = 15;
            // 
            // dtpTo
            // 
            this.dtpTo.CustomFormat = "yyyy/MM/dd hh:mm:ss";
            this.dtpTo.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.dtpTo.Location = new System.Drawing.Point(82, 126);
            this.dtpTo.Name = "dtpTo";
            this.dtpTo.Size = new System.Drawing.Size(187, 22);
            this.dtpTo.TabIndex = 17;
            // 
            // lblUser
            // 
            this.lblUser.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblUser.AutoSize = true;
            this.lblUser.Location = new System.Drawing.Point(3, 12);
            this.lblUser.Name = "lblUser";
            this.lblUser.Size = new System.Drawing.Size(38, 17);
            this.lblUser.TabIndex = 8;
            this.lblUser.Text = "User";
            // 
            // cbxUser
            // 
            this.cbxUser.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.cbxUser.DataSource = this.userBindingSource;
            this.cbxUser.DisplayMember = "name";
            this.cbxUser.FormattingEnabled = true;
            this.cbxUser.Location = new System.Drawing.Point(88, 9);
            this.cbxUser.Name = "cbxUser";
            this.cbxUser.Size = new System.Drawing.Size(277, 24);
            this.cbxUser.TabIndex = 13;
            this.cbxUser.SelectedIndexChanged += new System.EventHandler(this.cbxUser_SelectedIndexChanged);
            // 
            // userBindingSource
            // 
            this.userBindingSource.DataSource = typeof(LoginUseWebServiceTest.User);
            // 
            // sfdFile
            // 
            this.sfdFile.FileName = "csvLogData.zip";
            // 
            // tabControl1
            // 
            this.tableLayoutPanel1.SetColumnSpan(this.tabControl1, 3);
            this.tabControl1.Controls.Add(this.tabPage1);
            this.tabControl1.Controls.Add(this.tabPage2);
            this.tabControl1.Controls.Add(this.tabPage3);
            this.tabControl1.Controls.Add(this.tabPage4);
            this.tabControl1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tabControl1.Location = new System.Drawing.Point(3, 135);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(769, 538);
            this.tabControl1.SizeMode = System.Windows.Forms.TabSizeMode.FillToRight;
            this.tabControl1.TabIndex = 2;
            // 
            // tabPage1
            // 
            this.tabPage1.BackColor = System.Drawing.Color.Transparent;
            this.tabPage1.Controls.Add(this.tlp1);
            this.tabPage1.Location = new System.Drawing.Point(4, 25);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(578, 509);
            this.tabPage1.TabIndex = 0;
            this.tabPage1.Text = "Data Send & CSV";
            this.tabPage1.UseVisualStyleBackColor = true;
            // 
            // tabPage2
            // 
            this.tabPage2.BackColor = System.Drawing.Color.Transparent;
            this.tabPage2.Controls.Add(this.dgvFileInfo);
            this.tabPage2.Location = new System.Drawing.Point(4, 25);
            this.tabPage2.Name = "tabPage2";
            this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage2.Size = new System.Drawing.Size(578, 509);
            this.tabPage2.TabIndex = 1;
            this.tabPage2.Text = "User File Send Info.";
            this.tabPage2.UseVisualStyleBackColor = true;
            // 
            // dgvFileInfo
            // 
            this.dgvFileInfo.AllowUserToResizeColumns = false;
            this.dgvFileInfo.AllowUserToResizeRows = false;
            this.dgvFileInfo.AutoGenerateColumns = false;
            this.dgvFileInfo.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvFileInfo.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.nameDataGridViewTextBoxColumn,
            this.processDataGridViewCheckBoxColumn,
            this.isZipDataGridViewCheckBoxColumn});
            this.dgvFileInfo.DataSource = this.fileBindingSource;
            this.dgvFileInfo.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgvFileInfo.Location = new System.Drawing.Point(3, 3);
            this.dgvFileInfo.MultiSelect = false;
            this.dgvFileInfo.Name = "dgvFileInfo";
            this.dgvFileInfo.ReadOnly = true;
            this.dgvFileInfo.RowHeadersVisible = false;
            this.dgvFileInfo.RowTemplate.Height = 24;
            this.dgvFileInfo.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvFileInfo.Size = new System.Drawing.Size(572, 503);
            this.dgvFileInfo.TabIndex = 0;
            // 
            // nameDataGridViewTextBoxColumn
            // 
            this.nameDataGridViewTextBoxColumn.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.Fill;
            this.nameDataGridViewTextBoxColumn.DataPropertyName = "name";
            this.nameDataGridViewTextBoxColumn.HeaderText = "name";
            this.nameDataGridViewTextBoxColumn.Name = "nameDataGridViewTextBoxColumn";
            this.nameDataGridViewTextBoxColumn.ReadOnly = true;
            // 
            // processDataGridViewCheckBoxColumn
            // 
            this.processDataGridViewCheckBoxColumn.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.AllCells;
            this.processDataGridViewCheckBoxColumn.DataPropertyName = "process";
            this.processDataGridViewCheckBoxColumn.HeaderText = "process";
            this.processDataGridViewCheckBoxColumn.Name = "processDataGridViewCheckBoxColumn";
            this.processDataGridViewCheckBoxColumn.ReadOnly = true;
            this.processDataGridViewCheckBoxColumn.Width = 64;
            // 
            // isZipDataGridViewCheckBoxColumn
            // 
            this.isZipDataGridViewCheckBoxColumn.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.AllCells;
            this.isZipDataGridViewCheckBoxColumn.DataPropertyName = "isZip";
            this.isZipDataGridViewCheckBoxColumn.HeaderText = "isZip";
            this.isZipDataGridViewCheckBoxColumn.Name = "isZipDataGridViewCheckBoxColumn";
            this.isZipDataGridViewCheckBoxColumn.ReadOnly = true;
            this.isZipDataGridViewCheckBoxColumn.Width = 44;
            // 
            // fileBindingSource
            // 
            this.fileBindingSource.DataSource = typeof(LoginUseWebServiceTest.File);
            // 
            // tabPage3
            // 
            this.tabPage3.BackColor = System.Drawing.Color.Transparent;
            this.tabPage3.Location = new System.Drawing.Point(4, 25);
            this.tabPage3.Name = "tabPage3";
            this.tabPage3.Size = new System.Drawing.Size(578, 509);
            this.tabPage3.TabIndex = 2;
            this.tabPage3.Text = "Log History";
            this.tabPage3.UseVisualStyleBackColor = true;
            // 
            // tabPage4
            // 
            this.tabPage4.Controls.Add(this.tableLayoutPanel2);
            this.tabPage4.Location = new System.Drawing.Point(4, 25);
            this.tabPage4.Name = "tabPage4";
            this.tabPage4.Size = new System.Drawing.Size(761, 509);
            this.tabPage4.TabIndex = 3;
            this.tabPage4.Text = "Commands";
            this.tabPage4.UseVisualStyleBackColor = true;
            // 
            // tableLayoutPanel2
            // 
            this.tableLayoutPanel2.ColumnCount = 3;
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel2.Controls.Add(this.btnAdd, 1, 5);
            this.tableLayoutPanel2.Controls.Add(this.btnDel, 2, 5);
            this.tableLayoutPanel2.Controls.Add(this.btnNew, 0, 5);
            this.tableLayoutPanel2.Controls.Add(this.dgvCommands, 0, 4);
            this.tableLayoutPanel2.Controls.Add(this.lblType, 0, 0);
            this.tableLayoutPanel2.Controls.Add(this.label2, 0, 1);
            this.tableLayoutPanel2.Controls.Add(this.label3, 0, 2);
            this.tableLayoutPanel2.Controls.Add(this.lblCommand, 0, 3);
            this.tableLayoutPanel2.Controls.Add(this.cbxType, 1, 0);
            this.tableLayoutPanel2.Controls.Add(this.cbxProperty, 1, 1);
            this.tableLayoutPanel2.Controls.Add(this.cbxValue, 1, 2);
            this.tableLayoutPanel2.Controls.Add(this.cbxCommand, 1, 3);
            this.tableLayoutPanel2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel2.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel2.Name = "tableLayoutPanel2";
            this.tableLayoutPanel2.RowCount = 6;
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanel2.Size = new System.Drawing.Size(761, 509);
            this.tableLayoutPanel2.TabIndex = 0;
            // 
            // lblType
            // 
            this.lblType.AutoSize = true;
            this.lblType.Location = new System.Drawing.Point(3, 0);
            this.lblType.Name = "lblType";
            this.lblType.Size = new System.Drawing.Size(40, 17);
            this.lblType.TabIndex = 0;
            this.lblType.Text = "Type";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(3, 30);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(62, 17);
            this.label2.TabIndex = 1;
            this.label2.Text = "Property";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(3, 60);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(44, 17);
            this.label3.TabIndex = 2;
            this.label3.Text = "Value";
            // 
            // cbxType
            // 
            this.tableLayoutPanel2.SetColumnSpan(this.cbxType, 2);
            this.cbxType.DataSource = this.prTypeBS;
            this.cbxType.DisplayMember = "Name";
            this.cbxType.FormattingEnabled = true;
            this.cbxType.Location = new System.Drawing.Point(80, 3);
            this.cbxType.Name = "cbxType";
            this.cbxType.Size = new System.Drawing.Size(382, 24);
            this.cbxType.TabIndex = 3;
            this.cbxType.SelectedIndexChanged += new System.EventHandler(this.cbxType_SelectedIndexChanged);
            // 
            // prTypeBS
            // 
            this.prTypeBS.DataSource = typeof(LoginUseWebServiceTest.Property);
            // 
            // cbxProperty
            // 
            this.tableLayoutPanel2.SetColumnSpan(this.cbxProperty, 2);
            this.cbxProperty.DataSource = this.prPRBS;
            this.cbxProperty.DisplayMember = "Name";
            this.cbxProperty.FormattingEnabled = true;
            this.cbxProperty.Location = new System.Drawing.Point(80, 33);
            this.cbxProperty.Name = "cbxProperty";
            this.cbxProperty.Size = new System.Drawing.Size(382, 24);
            this.cbxProperty.TabIndex = 4;
            this.cbxProperty.SelectedIndexChanged += new System.EventHandler(this.cbxProperty_SelectedIndexChanged);
            // 
            // prPRBS
            // 
            this.prPRBS.DataSource = typeof(LoginUseWebServiceTest.Property);
            // 
            // cbxValue
            // 
            this.tableLayoutPanel2.SetColumnSpan(this.cbxValue, 2);
            this.cbxValue.DataSource = this.prPVBS;
            this.cbxValue.DisplayMember = "value";
            this.cbxValue.FormattingEnabled = true;
            this.cbxValue.Location = new System.Drawing.Point(80, 63);
            this.cbxValue.Name = "cbxValue";
            this.cbxValue.Size = new System.Drawing.Size(382, 24);
            this.cbxValue.TabIndex = 5;
            // 
            // prPVBS
            // 
            this.prPVBS.DataSource = typeof(LoginUseWebServiceTest.Property);
            // 
            // dgvCommands
            // 
            this.dgvCommands.AutoGenerateColumns = false;
            this.dgvCommands.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvCommands.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.id,
            this.fId,
            this.nameDataGridViewTextBoxColumn1,
            this.valueDataGridViewTextBoxColumn});
            this.tableLayoutPanel2.SetColumnSpan(this.dgvCommands, 3);
            this.dgvCommands.DataSource = this.prCMBS;
            this.dgvCommands.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgvCommands.Location = new System.Drawing.Point(3, 124);
            this.dgvCommands.Name = "dgvCommands";
            this.dgvCommands.RowHeadersVisible = false;
            this.dgvCommands.RowTemplate.Height = 24;
            this.dgvCommands.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvCommands.Size = new System.Drawing.Size(755, 336);
            this.dgvCommands.TabIndex = 7;
            // 
            // id
            // 
            this.id.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.AllCells;
            this.id.DataPropertyName = "id";
            this.id.HeaderText = "Rule";
            this.id.Name = "id";
            this.id.Width = 62;
            // 
            // fId
            // 
            this.fId.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.AllCells;
            this.fId.DataPropertyName = "fId";
            this.fId.HeaderText = "Condition";
            this.fId.Name = "fId";
            this.fId.Width = 92;
            // 
            // nameDataGridViewTextBoxColumn1
            // 
            this.nameDataGridViewTextBoxColumn1.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.AllCells;
            this.nameDataGridViewTextBoxColumn1.DataPropertyName = "name";
            this.nameDataGridViewTextBoxColumn1.HeaderText = "name";
            this.nameDataGridViewTextBoxColumn1.Name = "nameDataGridViewTextBoxColumn1";
            this.nameDataGridViewTextBoxColumn1.Width = 68;
            // 
            // valueDataGridViewTextBoxColumn
            // 
            this.valueDataGridViewTextBoxColumn.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.Fill;
            this.valueDataGridViewTextBoxColumn.DataPropertyName = "value";
            this.valueDataGridViewTextBoxColumn.HeaderText = "value";
            this.valueDataGridViewTextBoxColumn.Name = "valueDataGridViewTextBoxColumn";
            // 
            // prCMBS
            // 
            this.prCMBS.DataSource = typeof(LoginUseWebServiceTest.Property);
            // 
            // lblCommand
            // 
            this.lblCommand.AutoSize = true;
            this.lblCommand.Location = new System.Drawing.Point(3, 90);
            this.lblCommand.Name = "lblCommand";
            this.lblCommand.Size = new System.Drawing.Size(71, 17);
            this.lblCommand.TabIndex = 8;
            this.lblCommand.Text = "Command";
            // 
            // btnAdd
            // 
            this.btnAdd.Location = new System.Drawing.Point(80, 466);
            this.btnAdd.Name = "btnAdd";
            this.btnAdd.Size = new System.Drawing.Size(62, 40);
            this.btnAdd.TabIndex = 9;
            this.btnAdd.Text = "Add";
            this.btnAdd.UseVisualStyleBackColor = true;
            this.btnAdd.Click += new System.EventHandler(this.btnAdd_Click);
            // 
            // btnDel
            // 
            this.btnDel.Location = new System.Drawing.Point(148, 466);
            this.btnDel.Name = "btnDel";
            this.btnDel.Size = new System.Drawing.Size(60, 40);
            this.btnDel.TabIndex = 10;
            this.btnDel.Text = "Del";
            this.btnDel.UseVisualStyleBackColor = true;
            this.btnDel.Click += new System.EventHandler(this.btnDel_Click);
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 3;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 11.04557F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 47.93566F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 41.01876F));
            this.tableLayoutPanel1.Controls.Add(this.lblUser, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.tabControl1, 0, 4);
            this.tableLayoutPanel1.Controls.Add(this.cbxUser, 1, 0);
            this.tableLayoutPanel1.Controls.Add(this.lblPhoneModel, 2, 2);
            this.tableLayoutPanel1.Controls.Add(this.lblVersion, 1, 2);
            this.tableLayoutPanel1.Controls.Add(this.lblPhoneId, 1, 1);
            this.tableLayoutPanel1.Controls.Add(this.lblMaxLocation, 1, 3);
            this.tableLayoutPanel1.Controls.Add(this.lblLastProcess, 2, 3);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 5;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 42F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 30F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 30F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 30F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 20F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(775, 676);
            this.tableLayoutPanel1.TabIndex = 14;
            // 
            // lblPhoneModel
            // 
            this.lblPhoneModel.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblPhoneModel.AutoSize = true;
            this.lblPhoneModel.Location = new System.Drawing.Point(459, 78);
            this.lblPhoneModel.Name = "lblPhoneModel";
            this.lblPhoneModel.Size = new System.Drawing.Size(91, 17);
            this.lblPhoneModel.TabIndex = 16;
            this.lblPhoneModel.Text = "Phone Model";
            // 
            // lblVersion
            // 
            this.lblVersion.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblVersion.AutoSize = true;
            this.lblVersion.Location = new System.Drawing.Point(88, 78);
            this.lblVersion.Name = "lblVersion";
            this.lblVersion.Size = new System.Drawing.Size(56, 17);
            this.lblVersion.TabIndex = 15;
            this.lblVersion.Text = "Version";
            // 
            // lblPhoneId
            // 
            this.lblPhoneId.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblPhoneId.AutoSize = true;
            this.lblPhoneId.Location = new System.Drawing.Point(88, 48);
            this.lblPhoneId.Name = "lblPhoneId";
            this.lblPhoneId.Size = new System.Drawing.Size(60, 17);
            this.lblPhoneId.TabIndex = 14;
            this.lblPhoneId.Text = "PhoneId";
            // 
            // lblMaxLocation
            // 
            this.lblMaxLocation.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblMaxLocation.AutoSize = true;
            this.lblMaxLocation.Location = new System.Drawing.Point(88, 108);
            this.lblMaxLocation.Name = "lblMaxLocation";
            this.lblMaxLocation.Size = new System.Drawing.Size(91, 17);
            this.lblMaxLocation.TabIndex = 17;
            this.lblMaxLocation.Text = "Max Location";
            // 
            // lblLastProcess
            // 
            this.lblLastProcess.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.lblLastProcess.AutoSize = true;
            this.lblLastProcess.Location = new System.Drawing.Point(459, 108);
            this.lblLastProcess.Name = "lblLastProcess";
            this.lblLastProcess.Size = new System.Drawing.Size(90, 17);
            this.lblLastProcess.TabIndex = 18;
            this.lblLastProcess.Text = "Last Process";
            // 
            // btnNew
            // 
            this.btnNew.Location = new System.Drawing.Point(3, 466);
            this.btnNew.Name = "btnNew";
            this.btnNew.Size = new System.Drawing.Size(71, 38);
            this.btnNew.TabIndex = 11;
            this.btnNew.Text = "New";
            this.btnNew.UseVisualStyleBackColor = true;
            this.btnNew.Click += new System.EventHandler(this.btnNew_Click);
            // 
            // cbxCommand
            // 
            this.tableLayoutPanel2.SetColumnSpan(this.cbxCommand, 2);
            this.cbxCommand.FormattingEnabled = true;
            this.cbxCommand.Items.AddRange(new object[] {
            "SynchLogFile\t\t",
            "WifiEnabled",
            "WifiDisabled",
            "BlueToothEnabled",
            "BlueToothDisabled",
            "ConnectionEnabled",
            "ConnectionDisabled",
            "BrightnessDisabled"});
            this.cbxCommand.Location = new System.Drawing.Point(80, 93);
            this.cbxCommand.Name = "cbxCommand";
            this.cbxCommand.Size = new System.Drawing.Size(382, 24);
            this.cbxCommand.TabIndex = 12;
            // 
            // frmTest
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(775, 676);
            this.Controls.Add(this.tableLayoutPanel1);
            this.Name = "frmTest";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "WebService Test";
            this.tlp1.ResumeLayout(false);
            this.tlp1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.userBindingSource)).EndInit();
            this.tabControl1.ResumeLayout(false);
            this.tabPage1.ResumeLayout(false);
            this.tabPage2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dgvFileInfo)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.fileBindingSource)).EndInit();
            this.tabPage4.ResumeLayout(false);
            this.tableLayoutPanel2.ResumeLayout(false);
            this.tableLayoutPanel2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.prTypeBS)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.prPRBS)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.prPVBS)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgvCommands)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.prCMBS)).EndInit();
            this.tableLayoutPanel1.ResumeLayout(false);
            this.tableLayoutPanel1.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button btnSelectFile;
        private System.Windows.Forms.TableLayoutPanel tlp1;
        private System.Windows.Forms.TextBox txtFile;
        private System.Windows.Forms.Label lblSelectFile;
        private System.Windows.Forms.OpenFileDialog ofdFile;
        private System.Windows.Forms.Button btnSend;
        private System.Windows.Forms.Label lblUser;
        private System.Windows.Forms.Label lblProperty;
        private System.Windows.Forms.Label lblFrom;
        private System.Windows.Forms.ComboBox cbxUser;
        private System.Windows.Forms.CheckedListBox chbProperties;
        private System.Windows.Forms.DateTimePicker dtpFrom;
        private System.Windows.Forms.Label lblCSV;
        private System.Windows.Forms.Label lblSend;
        private System.Windows.Forms.SaveFileDialog sfdFile;
        private System.Windows.Forms.Button btnFind;
        private System.Windows.Forms.Label lblTo;
        private System.Windows.Forms.DateTimePicker dtpTo;
        private System.Windows.Forms.BindingSource userBindingSource;
        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TabPage tabPage2;
        private System.Windows.Forms.TabPage tabPage3;
        private System.Windows.Forms.DataGridView dgvFileInfo;
        private System.Windows.Forms.BindingSource fileBindingSource;
        private System.Windows.Forms.DataGridViewTextBoxColumn nameDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewCheckBoxColumn processDataGridViewCheckBoxColumn;
        private System.Windows.Forms.DataGridViewCheckBoxColumn isZipDataGridViewCheckBoxColumn;
        private System.Windows.Forms.Label lblPhoneId;
        private System.Windows.Forms.Label lblVersion;
        private System.Windows.Forms.Label lblPhoneModel;
        private System.Windows.Forms.Label lblMaxLocation;
        private System.Windows.Forms.Label lblLastProcess;
        private System.Windows.Forms.TabPage tabPage4;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel2;
        private System.Windows.Forms.Label lblType;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.ComboBox cbxType;
        private System.Windows.Forms.ComboBox cbxProperty;
        private System.Windows.Forms.ComboBox cbxValue;
        private System.Windows.Forms.DataGridView dgvCommands;
        private System.Windows.Forms.Label lblCommand;
        private System.Windows.Forms.Button btnAdd;
        private System.Windows.Forms.Button btnDel;
        private System.Windows.Forms.BindingSource prTypeBS;
        private System.Windows.Forms.BindingSource prPRBS;
        private System.Windows.Forms.BindingSource prPVBS;
        private System.Windows.Forms.BindingSource prCMBS;
        private System.Windows.Forms.DataGridViewTextBoxColumn id;
        private System.Windows.Forms.DataGridViewTextBoxColumn fId;
        private System.Windows.Forms.DataGridViewTextBoxColumn nameDataGridViewTextBoxColumn1;
        private System.Windows.Forms.DataGridViewTextBoxColumn valueDataGridViewTextBoxColumn;
        private System.Windows.Forms.Button btnNew;
        private System.Windows.Forms.ComboBox cbxCommand;
    }
}

