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
        private void starProcess(string folderPath, string filePath)
        {
            string folderProcess = folderPath + @"/process";
            this.createFolder(folderProcess);

            ZipLog log = new ZipLog();
            ZipManager zManager = new ZipManager();
            zManager.descomprimirDir(filePath, null, folderProcess, log);

            LogParserManager lParser = new LogParserManager();
            lParser.execute(folderProcess);
        }

        public void executeProces(string folderPath, string filePath)
        {
            Thread tr = new Thread(delegate() { starProcess(folderPath, filePath); });
            tr.Start();
        }
    }
}
