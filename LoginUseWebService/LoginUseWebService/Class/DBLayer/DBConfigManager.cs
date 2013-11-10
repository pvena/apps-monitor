using System;
using System.Data;
using System.Configuration;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Xml.Linq;

namespace LoginUseWebService
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
