using System;
using System.IO;
using System.Windows.Forms;

namespace CS322_DZ02_3860_JovanVujovic
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string info = "";

            info += "Ime: " + textBox1.Text + "; ";
            info += "Prezime: " + textBox2.Text + "; ";
            info += "Adresa: " + textBox3.Text + "; ";

            MessageBox.Show(info);

            using (StreamWriter sw = new StreamWriter("out.txt")) {
                sw.Write(info);
            }
        }
    }
}