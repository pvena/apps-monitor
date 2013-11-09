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


        /// <summary>
        /// Descomprime los Archivos recividos y los guarda en la base de datos.
        /// </summary>
        /// <param name="root">Carpeta Raiz para el usuario</param>
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
                    properties[i] = (string)r["Name"];
                    i++;
                }
                return properties;
            }
            return new string[0];
        }


    }
}
