using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MachineLearningProject
{
    public partial class MainForm : Form
    {
        public MainForm()
        {
            InitializeComponent();
        }

        private void btnBrowse_Click(object sender, EventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            if (openFileDialog.ShowDialog() == DialogResult.OK)
            {
                string fileName = openFileDialog.FileName;
                this.txtDataSource.Text = fileName;
            }
            this.txtDataSource.Text = "";
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            string newDataSource = this.txtDataSource.Text;
            if (newDataSource == null || newDataSource == "")
            {
                // TODO
                // Popup notice stating data source is empty
                return;
            }
            if (this.listDataSources.Items.Contains(newDataSource))
            {
                // TODO
                // Popup notice stating data source already exists
            }
            // Save the Data Source
            this.listDataSources.Items.Add(newDataSource);
        }

        private void btnDataToAlgorithms_Click(object sender, EventArgs e)
        {
            // TODO
            // Put in logic to parse data source here?
            this.tabControl.SelectedTab = this.tabAlgorithms;
        }
    }
}
