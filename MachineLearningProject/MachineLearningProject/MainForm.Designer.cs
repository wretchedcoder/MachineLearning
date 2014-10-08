namespace MachineLearningProject
{
    partial class MainForm
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
            this.tabControl = new System.Windows.Forms.TabControl();
            this.tabDataSource = new System.Windows.Forms.TabPage();
            this.btnAdd = new System.Windows.Forms.Button();
            this.btnBrowse = new System.Windows.Forms.Button();
            this.txtDataSource = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.tabAlgorithms = new System.Windows.Forms.TabPage();
            this.tabExecute = new System.Windows.Forms.TabPage();
            this.listDataSources = new System.Windows.Forms.ListBox();
            this.btnDataToAlgorithms = new System.Windows.Forms.Button();
            this.tabControl.SuspendLayout();
            this.tabDataSource.SuspendLayout();
            this.SuspendLayout();
            // 
            // tabControl
            // 
            this.tabControl.Controls.Add(this.tabDataSource);
            this.tabControl.Controls.Add(this.tabAlgorithms);
            this.tabControl.Controls.Add(this.tabExecute);
            this.tabControl.Location = new System.Drawing.Point(12, 12);
            this.tabControl.Name = "tabControl";
            this.tabControl.SelectedIndex = 0;
            this.tabControl.Size = new System.Drawing.Size(522, 352);
            this.tabControl.TabIndex = 0;
            // 
            // tabDataSource
            // 
            this.tabDataSource.Controls.Add(this.btnDataToAlgorithms);
            this.tabDataSource.Controls.Add(this.listDataSources);
            this.tabDataSource.Controls.Add(this.btnAdd);
            this.tabDataSource.Controls.Add(this.btnBrowse);
            this.tabDataSource.Controls.Add(this.txtDataSource);
            this.tabDataSource.Controls.Add(this.label1);
            this.tabDataSource.Location = new System.Drawing.Point(4, 22);
            this.tabDataSource.Name = "tabDataSource";
            this.tabDataSource.Padding = new System.Windows.Forms.Padding(3);
            this.tabDataSource.Size = new System.Drawing.Size(514, 326);
            this.tabDataSource.TabIndex = 0;
            this.tabDataSource.Text = "Data Source";
            this.tabDataSource.UseVisualStyleBackColor = true;
            // 
            // btnAdd
            // 
            this.btnAdd.Location = new System.Drawing.Point(433, 8);
            this.btnAdd.Name = "btnAdd";
            this.btnAdd.Size = new System.Drawing.Size(75, 23);
            this.btnAdd.TabIndex = 3;
            this.btnAdd.Text = "Add";
            this.btnAdd.UseVisualStyleBackColor = true;
            this.btnAdd.Click += new System.EventHandler(this.btnAdd_Click);
            // 
            // btnBrowse
            // 
            this.btnBrowse.Location = new System.Drawing.Point(345, 8);
            this.btnBrowse.Name = "btnBrowse";
            this.btnBrowse.Size = new System.Drawing.Size(85, 23);
            this.btnBrowse.TabIndex = 2;
            this.btnBrowse.Text = "Browse";
            this.btnBrowse.UseVisualStyleBackColor = true;
            this.btnBrowse.Click += new System.EventHandler(this.btnBrowse_Click);
            // 
            // txtDataSource
            // 
            this.txtDataSource.Location = new System.Drawing.Point(81, 10);
            this.txtDataSource.Name = "txtDataSource";
            this.txtDataSource.Size = new System.Drawing.Size(262, 20);
            this.txtDataSource.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(6, 13);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(67, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Data Source";
            // 
            // tabAlgorithms
            // 
            this.tabAlgorithms.Location = new System.Drawing.Point(4, 22);
            this.tabAlgorithms.Name = "tabAlgorithms";
            this.tabAlgorithms.Padding = new System.Windows.Forms.Padding(3);
            this.tabAlgorithms.Size = new System.Drawing.Size(514, 326);
            this.tabAlgorithms.TabIndex = 1;
            this.tabAlgorithms.Text = "Algorithms";
            this.tabAlgorithms.UseVisualStyleBackColor = true;
            // 
            // tabExecute
            // 
            this.tabExecute.Location = new System.Drawing.Point(4, 22);
            this.tabExecute.Name = "tabExecute";
            this.tabExecute.Padding = new System.Windows.Forms.Padding(3);
            this.tabExecute.Size = new System.Drawing.Size(514, 326);
            this.tabExecute.TabIndex = 2;
            this.tabExecute.Text = "Execute";
            this.tabExecute.UseVisualStyleBackColor = true;
            // 
            // listDataSources
            // 
            this.listDataSources.FormattingEnabled = true;
            this.listDataSources.Location = new System.Drawing.Point(9, 37);
            this.listDataSources.Name = "listDataSources";
            this.listDataSources.Size = new System.Drawing.Size(499, 251);
            this.listDataSources.TabIndex = 4;
            // 
            // btnDataToAlgorithms
            // 
            this.btnDataToAlgorithms.Location = new System.Drawing.Point(423, 294);
            this.btnDataToAlgorithms.Name = "btnDataToAlgorithms";
            this.btnDataToAlgorithms.RightToLeft = System.Windows.Forms.RightToLeft.No;
            this.btnDataToAlgorithms.Size = new System.Drawing.Size(84, 26);
            this.btnDataToAlgorithms.TabIndex = 5;
            this.btnDataToAlgorithms.Text = "Next";
            this.btnDataToAlgorithms.UseVisualStyleBackColor = true;
            this.btnDataToAlgorithms.Click += new System.EventHandler(this.btnDataToAlgorithms_Click);
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(546, 376);
            this.Controls.Add(this.tabControl);
            this.Name = "MainForm";
            this.Text = "Machine Learning";
            this.tabControl.ResumeLayout(false);
            this.tabDataSource.ResumeLayout(false);
            this.tabDataSource.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tabControl;
        private System.Windows.Forms.TabPage tabDataSource;
        private System.Windows.Forms.TabPage tabAlgorithms;
        private System.Windows.Forms.TabPage tabExecute;
        private System.Windows.Forms.Button btnAdd;
        private System.Windows.Forms.Button btnBrowse;
        private System.Windows.Forms.TextBox txtDataSource;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button btnDataToAlgorithms;
        private System.Windows.Forms.ListBox listDataSources;
    }
}

