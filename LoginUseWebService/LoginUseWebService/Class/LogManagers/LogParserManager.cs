using System;
using System.Data;
using System.Configuration;
using System.Linq;
using System.IO;
using System.Data;

namespace LoginUseWebService
{
    public class LogParserManager
    {
        private DateTime getDatetime(string date, string dTime)
        {
            string[] time = dTime.Substring(1, dTime.Length - 2).Split(':');

            int year = Convert.ToInt16(date.Substring(0, 4));
            int month = Convert.ToInt16(date.Substring(4, 2));
            int day = Convert.ToInt16(date.Substring(6, 2));
            int hh = Convert.ToInt16(time[0]);
            int mm = Convert.ToInt16(time[1]);
            int ss = Convert.ToInt16(time[2]);

            return new DateTime(year, month, day, hh, mm, ss);
        }
        
        
        private void processProperty(string property,string fileName, string type, DateTime dt,DBManager dbm) 
        {
            string[] pValue = property.Split(LogConstants.SEP.ToCharArray());

            string prop = pValue[0];
            string value = pValue[pValue.Length-1];

            dbm.saveLog(fileName, dt, type, prop, value);
        }

        private void processProperties(string properties, string fileName, string type, DateTime dt, DBManager dbm) 
        {
            string[] values = properties.Split(LogConstants.PSEP.ToCharArray());
            for (int i = 0; i < values.Length; i++)
            {
                this.processProperty(values[i], fileName ,type, dt, dbm);
            }
        }
        
        private void processLine(string line,string fileName, string date,DBManager dbm)
        {
            string[] parts = line.Split(LogConstants.CATSEP.ToCharArray());

            DateTime dTime = this.getDatetime(date, parts[0]);            
            string type = parts[1].Substring(1, parts[1].Length - 2);

            for (int i = 2; i < parts.Length; i++)
            {
                parts[i] = parts[i].Substring(1, parts[i].Length - 2);
                this.processProperties(parts[i], fileName, type, dTime, dbm);
            }
        }

        private void processFile(string phoneId, string path, DBManager dbm) 
        {
            string fileName = Path.GetFileName(path);

            if (!dbm.isFileProcess(fileName, false))
            {
                dbm.saveFile(phoneId, fileName, false, false, new FileInfo(path).Length);

                string date = Path.GetFileNameWithoutExtension(path).Substring(0, 8);

                StreamReader sr = new StreamReader(path);

                string line = null;
                while (!sr.EndOfStream)
                    this.processLine(sr.ReadLine(), fileName, date, dbm);

                sr.Close();
                dbm.saveFile(phoneId, fileName, true, false, new FileInfo(path).Length);                
            }            
        }

        public void execute(string phoneId,string zipPath,string pass) 
        {
            DBManager dbm = new DBManager();

            try
            {
                if (!dbm.isFileProcess(Path.GetFileName(zipPath), true))
                {
                    string res = dbm.saveFile(phoneId, Path.GetFileName(zipPath), false, true, new FileInfo(zipPath).Length);

                    if (res == "OK.")
                    {

                        ZipManager zm = new ZipManager();
                        zm.descomprimirDir(zipPath, pass, Path.GetDirectoryName(zipPath), new ZipLog());

                        string[] files = Directory.GetFiles(Path.GetDirectoryName(zipPath), "*.txt", SearchOption.TopDirectoryOnly);

                        for (int i = 0; (i < files.Length) && (res == "OK."); i++)
                        {
                            this.processFile(phoneId, files[i], dbm);
                            File.Delete(files[i]);
                        }

                        if (res == "OK.")
                            dbm.saveFile(phoneId, Path.GetFileName(zipPath), true, true, new FileInfo(zipPath).Length);
                    }
                }
            }
            catch (Exception ex)
            {
                dbm.saveFile(phoneId, Path.GetFileName(zipPath), false, true, new FileInfo(zipPath).Length);
            }
        }
    }
}
