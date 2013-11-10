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
    public class LocationManager
    {
        public double GetDistance(decimal long1InDegrees, decimal lat1InDegrees, decimal long2InDegrees, decimal lat2InDegrees)
        {
            double lats = (double)(lat1InDegrees - lat2InDegrees);
            double lngs = (double)(long1InDegrees - long2InDegrees);

            //Paso a metros
            double latm = lats * 60 * 1852;
            double lngm = (lngs * Math.Cos((double)lat1InDegrees * Math.PI / 180)) * 60 * 1852;
            double distInMeters = Math.Sqrt(Math.Pow(latm, 2) + Math.Pow(lngm, 2));
            return distInMeters;
        }
    }
}
