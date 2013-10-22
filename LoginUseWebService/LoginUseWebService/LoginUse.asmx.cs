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

    public class LoginUse : System.Web.Services.WebService
    {
        
        [WebMethod]
        public string UploadFile(byte[] bytes, string fileName)
        {
            try
            {
                string filePath = System.Web.Hosting.HostingEnvironment.MapPath("~/FileReceiver/") + fileName;
                if (ServiceManager.getInstance().isExtensionOk(Path.GetExtension(filePath)))
                {
                    MemoryStream ms = new MemoryStream(bytes);
                    FileStream fs = new FileStream(filePath, FileMode.Create);

                    ms.WriteTo(fs);

                    ms.Close();
                    fs.Close();
                    fs.Dispose();

                    return "File Upload Successful.";
                }
                else
                    return "Web Services not support this type of file.";
            }
            catch (Exception ex)
            {               
                return ex.Message.ToString();
            }
        }
        [WebMethod]
        public string test(int bytes, string fileName)
        {
            int d = bytes;
            return "OK";
        }
    }
}
