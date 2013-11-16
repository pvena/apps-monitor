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
    public class LocationGroup : Location
    {
        private int id;
        private int count; 
        private string name;

        public LocationGroup()
        {
            this.id = int.MinValue;
        }

        public int Id
        {
            set { this.id = value; }
            get { return this.id; }
        }
        public int Count
        {
            set { this.count = value; }
            get { return this.count; }
        }
        public string Name
        {
            set { this.name = value; }
            get { return this.name; }
        }
    }
}
