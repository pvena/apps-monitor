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
    public static class LogConstants
    {
        public static string INICIO = "INIC";

        public static string WIFI_STATE_TAG = "WIFI";

        public static string BATTERY_STATE_TAG = "BATTERY";

        public static string BLUETOOTH_STATE_TAG = "BLUETOOTH";

        public static string CONNECTION_STATE_TAG = "CONNECTION";

        public static string LOCATION_STATE_TAG = "LOCATION";

        public static string GPS_STATE_TAG = "GPS";

        public static string CURRENTACTIVITY = "ACTIVITY";        

        public static string SEP = "::";

        public static string PSEP = "|";

        public static string PERCENTAGE = "PCT";

        public static string PLUGGED_USB = "PLGUSB";

        public static string PLUGGED_AC = "PLGAC";

        public static string DISCHARGING = "DISCH";

        public static string DATEFORMAT = "kk:mm:ss";

        public static string FILEDATEFORMAT = "yyyyMMdd";

        public static string STATE = "STATE";

        public static string LATITUDE = "LAT";

        public static string LONGITUDE = "LONG";

        public static string ACTIVITY = "ACT";

        public static string CONFIDENCE = "CONF";

        public static string ALTITUDE = "ALT";

        public static string CATSEP1 = "[";

        public static string CATSEP2 = "]";

        public static string CATSEP = "&"; 	 
    }
}
