using OpenPop.Mime;
using OpenPop.Pop3;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Pop3UI
{
    class POP3
    {
        public Pop3Client _client;
        public Message Message;
        public int MessageNumber;
        public void Connect(string hostname, string username, string password, int port, bool isUseSsl)
        {
            this._client = new Pop3Client();
            this._client.Connect(hostname, port, isUseSsl);
            this._client.Authenticate(username, password);
        }

        public List<POP3> GetMail()
        {
            int messageCount = this._client.GetMessageCount();

            var allMessages = new List<POP3>(messageCount);

            for (int i = messageCount; i > 0; i--)
            {
                allMessages.Add(new POP3() { MessageNumber = i, Message = this._client.GetMessage(i) });
            }

            return allMessages;
        }

        public void Delete(int msgNumber)
        {
            this._client.DeleteMessage(msgNumber);
        }
    }
}
