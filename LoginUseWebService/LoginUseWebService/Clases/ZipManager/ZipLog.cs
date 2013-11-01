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
    public class ZipLog : IZipLog
    {
        private string log;


        public ZipLog() 
        {
            log = "";
        }

        public void initialize() 
        {
            this.log = "";
        }

        public string Log 
        {
            set { this.log += "/n" + value; }
            get { return this.log; }
        }
    }
}
