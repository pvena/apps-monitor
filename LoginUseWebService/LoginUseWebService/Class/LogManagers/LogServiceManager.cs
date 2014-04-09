using System;
using System.Data;
using System.Configuration;
using System.Collections.Generic;
using System.IO;
using System.Threading;

namespace LoginUseWebService
{
    public class LogServiceManager
    {
        private delegate void processParameters(string folderPath, string filePath);

        private static LogServiceManager instance;  
        
        private LogServiceManager() {}



        public static LogServiceManager getInstance()
        {
            if (LogServiceManager.instance == null)
                LogServiceManager.instance = new LogServiceManager();
            return LogServiceManager.instance;
        } 

        #region ----------------Directory-------------------

        public void createFolder(string path) {
            DirectoryInfo dir = new DirectoryInfo(path);
            if (!dir.Exists)
                dir.Create();
        }

        public bool saveFile(string path, string base64Data)
        {
            File.WriteAllBytes(path, Convert.FromBase64String(base64Data));
            return true;
        }

        public string getBase64File(string fileZip,string folderPath, string filePath) 
        {
            try
            {
                ZipManager zm = new ZipManager();
                zm.comprimirDir(ref fileZip, null, folderPath, new string[] { "*.csv" }, new ZipLog());

                FileStream file = new FileStream(fileZip, FileMode.Open);
                byte[] buffer = new byte[file.Length];
                file.Read(buffer, 0, (int)file.Length);
                file.Close();
                File.Delete(filePath);
                File.Delete(fileZip);
                return Convert.ToBase64String(buffer);
            }
            catch (Exception ex) 
            {
                return "";
            }
        }

        #endregion

        #region --------------Process Data In---------------
 
        private void starProcess(string phoneId, string filePath)
        {
            string folderProcess = Path.GetDirectoryName(filePath);
            
            ZipLog log = new ZipLog();
            ZipManager zManager = new ZipManager();
            zManager.descomprimirDir(filePath, null, folderProcess, log);

            LogParserManager lParser = new LogParserManager();
            lParser.execute(phoneId,filePath,null);

            LocationManager lm = new LocationManager();
            lm.execute(phoneId);
        }

        public void executeProcess(string phoneId, string filePath)
        {
            Thread tr = new Thread(delegate() { starProcess(phoneId, filePath); });
            tr.Start();
        }

        private void starProcessRefreshLocationGroups(string phoneId)
        {
            LocationManager lm = new LocationManager();            
            lm.resetLocationGroups(phoneId);
        }

        public void executeResetLocationGroups(string phoneId)
        {
            Thread tr = new Thread(delegate() { starProcessRefreshLocationGroups(phoneId); });
            tr.Start();
        }

        #endregion

        #region ------------ProcessCSV Data Out----------------
        public bool executeProcessCSV(string phoneId, DateTime from, DateTime to, string typeNames, string propNames, string path)
        {
            LogCSVManager csvManager = new LogCSVManager();
            return csvManager.execute(phoneId, from, to, typeNames, propNames, path);
        }
        #endregion

        #region ---------ProcessGroups Data Out----------------
        public string createLocationGroupData(string phoneId)
        {
            DBManager dbm = new DBManager();
            DataTable dt = dbm.getLocationGroups(phoneId);            
            if (dt != null)
            {
                string data = "LocationGroups:";
                foreach (DataRow r in dt.Rows)                
                    data += r["name"] + "&" + r["latitud"] + "&" + r["longitud"] + "&" + r["count"] + "&";                
                return data.Substring(0,data.Length-1);
            }
            return "";
        }
        #endregion

        #region ---------ProcessRules Data Out----------------
        public string createRulesData(string phoneId)
        {
            DBManager dbm = new DBManager();
            DataTable dt = dbm.getRules(phoneId);
            if (dt != null)
            {
                string data = "Rules:";
                foreach (DataRow r in dt.Rows)                
                    data += r["idRule"] + "&" + r["commandKey"] + "&" + r["key"] + "&" + r["value"] + "&";                
                return data.Substring(0, data.Length - 1);
            }
            return "";
        }
        #endregion
              
    }
}
