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

        #endregion

        #region ----------------Process Data In---------------------- 
 
        private void starProcess(string phoneId, string filePath)
        {
            string folderProcess = Path.GetDirectoryName(filePath);
            
            ZipLog log = new ZipLog();
            ZipManager zManager = new ZipManager();
            zManager.descomprimirDir(filePath, null, folderProcess, log);

            LogParserManager lParser = new LogParserManager();
            lParser.execute(phoneId,filePath,null);
        }

        public void executeProcess(string phoneId, string filePath)
        {
            Thread tr = new Thread(delegate() { starProcess(phoneId, filePath); });
            tr.Start();
        }

        #endregion

        #region -----------------Get Data Base Info------------------

        public string[] getUsers(string names)
        {
            DBManager mdb = new DBManager();
            DataTable dt = mdb.getUsers(names);

            if (dt != null)
            {
                string[] users = new string[dt.Rows.Count];
                int i = 0;
                foreach (DataRow r in dt.Rows)
                {
                    users[i] = (string)r["Name"];
                    i++;
                }
                return users;
            }
            return new string[0];
        }

        public string[] getPhoneIds(string names)
        {
            DBManager mdb = new DBManager();
            DataTable dt = mdb.getUsers(names);

            if (dt != null)
            {
                string[] users = new string[dt.Rows.Count];
                int i = 0;
                foreach (DataRow r in dt.Rows)
                {
                    users[i] = (string)r["phoneId"];
                    i++;
                }
                return users;
            }
            return new string[0];
        }

        public string[] getTypes(string names)
        {
            DBManager mdb = new DBManager();
            DataTable dt = mdb.getType(names);

            if (dt != null)
            {
                string[] types = new string[dt.Rows.Count];
                int i = 0;
                foreach (DataRow r in dt.Rows)
                {
                    types[i] = (string)r["Name"];
                    i++;
                }
                return types;
            }
            return new string[0];
        }

        public string[] getProperties(string names)
        {
            DBManager mdb = new DBManager();
            DataTable dt = mdb.getProperties(names);

            if (dt != null)
            {
                string[] properties = new string[dt.Rows.Count];
                int i = 0;
                foreach (DataRow r in dt.Rows)
                {
                    properties[i] = (string)r["FullName"];
                    i++;
                }
                return properties;
            }
            return new string[0];
        }

        #endregion 

        public bool createCSVFile(string phoneId,DateTime from, DateTime to, string typeNames, string propNames,string path)
        {
            try
            {
                DBManager dbm = new DBManager();
                DataTable data = dbm.getCsvData(phoneId, from, to, typeNames, propNames);

                string[] propertyes = propNames.Split(';');
                Dictionary<string, string> values = new Dictionary<string, string>();

                string line = "year;month;day,time;minute;second;isWeekDay;quarter;" + propNames;

                for (int i = 0; i < propertyes.Length; i++)
                    values.Add(propertyes[i], "Default");
                
                StreamWriter file = new StreamWriter(path, true);
                file.WriteLine(line);

                DateTime aux = DateTime.Now;

                foreach (DataRow r in data.Rows)
                {
                    values[(string)r["FullName"]] = (string)r["PropValue"];
                    if (((DateTime)r["date"]) != aux)
                    {
                        aux = ((DateTime)r["date"]);
                        line = ((DateTime)r["date"]).ToString("yyyy;");
                        line += ((DateTime)r["date"]).ToString("MM;");
                        line += ((DateTime)r["date"]).ToString("dd;");
                        line += ((DateTime)r["date"]).ToString("hh;");
                        line += ((DateTime)r["date"]).ToString("mm;");
                        line += ((DateTime)r["date"]).ToString("ss;");
                        line += ((((DateTime)r["date"]).DayOfWeek == DayOfWeek.Sunday) || (((DateTime)r["date"]).DayOfWeek == DayOfWeek.Saturday)) ? "0;" : "1;";
                        line += ((DateTime)r["date"]).Minute / 15;
                        for (int i = 0; i < propertyes.Length; i++)
                            line += ";" + (string)values[propertyes[i]];
                        file.WriteLine(line);
                    }
                }
                file.Flush();
                file.Close();

                return true;
            }
            catch (Exception ex) 
            { return false; }
        }


    }
}
