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
                string filePath = folderPath + DateTime.Now.ToString("yyyyMMddHHmmss") + "-" + phoneId + ".zip";

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
        public string[] getUsers(string names)
        {
            try
            {
                return LogServiceManager.getInstance().getUsers(names);
            }
            catch (Exception ex)
            {
                return new string[0];
            }
        }

        [WebMethod]
        public string[] getPhoneIds(string names)
        {
            try
            {
                return LogServiceManager.getInstance().getPhoneIds(names);
            }
            catch (Exception ex)
            {
                return new string[0];
            }
        }
        
        [WebMethod]
        public string[] getTypes(string names)
        {
            try
            {
                return LogServiceManager.getInstance().getTypes(names);
            }
            catch (Exception ex)
            {
                return new string[0];
            }
        }

        [WebMethod]
        public string[] getProperties(string names)
        {
            try
            {
                return LogServiceManager.getInstance().getProperties(names);
            }
            catch (Exception ex)
            {
                return new string[0];
            }
        }

        [WebMethod]
        public double getPointDistance(decimal lat1,decimal long1,decimal lat2,decimal long2)
        {
            try
            {
                LocationManager lm = new LocationManager();
                return lm.GetDistance(long1, lat1, long2, lat2);
            }
            catch (Exception ex)
            {
                return -1;
            }
        }

        [WebMethod]
        public string getCSVData(string phoneId,DateTime from, DateTime to, string typeNames, string propNames)
        {
            try
            {
                string folderPath = System.Web.Hosting.HostingEnvironment.MapPath("~/FileReceiver/") + phoneId + @"_CSV\";
                string filePath = folderPath + DateTime.Now.ToString("yyyyMMddHHmmss") + "-" + phoneId + ".csv";
                string fileZip = folderPath + DateTime.Now.ToString("yyyyMMddHHmmss") + "-" + phoneId;

                LogServiceManager.getInstance().createFolder(folderPath);

                bool ok = LogServiceManager.getInstance().executeProcessCSV(phoneId, from, to, typeNames, propNames, filePath);
                
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
    }
}
