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
    public class Location
    {
        private decimal longitud;
        private decimal latitud;

        public decimal Longitud
        {
            set { this.longitud = value; }
            get { return this.longitud; }
        }
        public decimal Latitud
        {
            set { this.latitud = value; }
            get { return this.latitud; }
        }
    }
}
