using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Pop3UI
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }
        private List<POP3> messages;
        private POP3 Server;

        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                Server = new POP3();
                Server.Connect(textBox1.Text, textBox2.Text, textBox3.Text, 995, true);
                messages = Server.GetMail();
                foreach (var mail in messages)
                {
                    var subject = mail.Message.Headers.Subject;
                    var to = string.Join(",", mail.Message.Headers.To.Select(m => m.Address));
                    var from = mail.Message.Headers.From.Address;
                    var date = mail.Message.Headers.Date;
                    var message = UTF8Encoding.UTF8.GetString(mail.Message.MessagePart.MessageParts[0].Body);

                    ListViewItem lvi = new ListViewItem(mail.MessageNumber.ToString());
                    lvi.SubItems.Add(date);
                    lvi.SubItems.Add(to);
                    lvi.SubItems.Add(from);
                    lvi.SubItems.Add(subject);
                    lvi.SubItems.Add(message);
                    listView1.Items.Add(lvi);
                }
            }
            catch (Exception exp)
            {
                MessageBox.Show(exp.Message);
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            try
            {
                foreach (ListViewItem l in listView1.SelectedItems)
                {
                    Server.Delete(int.Parse(l.Text));
                    l.Remove();
                }
                Server._client.Disconnect();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
    }
}
