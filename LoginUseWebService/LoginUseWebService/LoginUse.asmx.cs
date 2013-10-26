using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Configuration;
using System.Web.Services;
using System.Web.Services.Protocols;
using System.Xml.Linq;

namespace LoginUseWebService
{
    /// <summary>
    /// Summary description for Service1
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [ToolboxItem(false)]
    [System.Web.Script.Services.ScriptService]
    public class LoginUse : System.Web.Services.WebService
    {
        
        [WebMethod]
        public string UploadFile(string data, string username)
        {
            try
            {
                string filePath = System.Web.Hosting.HostingEnvironment.MapPath("~/FileReceiver/") + username + ".zip";

                File.WriteAllBytes(filePath, Convert.FromBase64String(data));

                return "OK";
            }
            catch (Exception ex)
            {               
                return ex.Message.ToString();
            }
        }
    }
}
