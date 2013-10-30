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
    [WebService(Namespace = "http://tesis.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [ToolboxItem(false)]
    public class LoginUse : System.Web.Services.WebService
    {
        
        [WebMethod]
        public string UploadFile(string data, string userName)
        {            
            try
            {
                string filePath = System.Web.Hosting.HostingEnvironment.MapPath("~/FileReceiver/") + userName + ".zip";

               File.WriteAllBytes(filePath, Convert.FromBase64String(data));

                return "OK";
            }
            catch (Exception ex)
            {          
                return "Error: " + ex.Message.ToString();
            }
        }
    }
}
