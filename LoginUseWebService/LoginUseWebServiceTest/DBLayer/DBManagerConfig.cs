using System;
using System.Data;
using System.Configuration;
using System.Linq;
using System.Xml.Linq;

namespace LoginUseWebServiceTest
{
    public class DBConfigManager
    {
        public string ServerDB
        {
            get { return Properties.Settings.Default.ServerDB; }
        }
        public string NameDB
        {
            get { return Properties.Settings.Default.NameDB; }
        }
        public string UserId
        {
            get { return Properties.Settings.Default.UserIdDB; }
        }
        public string PassDB
        {
            get { return Properties.Settings.Default.PassDB; }
        }
    }
}
