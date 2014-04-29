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
        public string UploadFile(string data, string phoneId)
        {            
            try
            {
                string folderPath = System.Web.Hosting.HostingEnvironment.MapPath("~/FileReceiver/") + @"/" + phoneId + @"/";
                string filePath = folderPath + DateTime.Now.ToString("yyyyMMdd") + "-" + phoneId + ".zip";

                LogServiceManager.getInstance().createFolder(folderPath);
                LogServiceManager.getInstance().saveFile(filePath, data);
                LogServiceManager.getInstance().executeProcess(phoneId, filePath);

                return "OK";
            }
            catch (Exception ex)
            {          
                return "Error: " + ex.Message.ToString();
            }
        }
        
        [WebMethod]
        public string RegisterUser(string phoneId,string name,string version,string phoneModel)
        {
            try 
            {
                DBManager mDB = new DBManager();                
                return mDB.saveUser(phoneId, name, version, phoneModel);
            }
            catch(Exception ex)
            {
                return "Problem."; 
            }
        }

        [WebMethod]
        public string getCSVData(string phoneId, DateTime from, DateTime to, string propNames)
        {
            try
            {
                string folderPath = System.Web.Hosting.HostingEnvironment.MapPath("~/FileReceiver/") + phoneId + @"_ARFF\";
                string filePath = folderPath + DateTime.Now.ToString("yyyyMMddHHmmss") + "-" + phoneId + ".arff";
                string fileZip = folderPath + DateTime.Now.ToString("yyyyMMddHHmmss") + "-" + phoneId;

                LogServiceManager.getInstance().createFolder(folderPath);

                bool ok = LogServiceManager.getInstance().executeProcessCSV(phoneId, from, to, propNames, filePath);

                if (ok)
                    return LogServiceManager.getInstance().getBase64File(fileZip, folderPath, filePath);
                return "";
            }
            catch (Exception ex)
            {
                return "";
            }
        }

        [WebMethod]
        public string getSynchInfo(string phoneId)
        {
            try
            {
                string data = LogServiceManager.getInstance().createLocationGroupData(phoneId) + "|";
                data += LogServiceManager.getInstance().createRulesData(phoneId);
                return data;
            }
            catch (Exception ex)
            {
                return "Fail";
            }
        }

        [WebMethod]
        public string ResetLocationGroups(string phoneId)
        {
            try
            {
                LogServiceManager.getInstance().executeResetLocationGroups(phoneId);
                return "OK.";
            }
            catch (Exception ex)
            {
                return "Fail";
            }
        }
        
    }
}
