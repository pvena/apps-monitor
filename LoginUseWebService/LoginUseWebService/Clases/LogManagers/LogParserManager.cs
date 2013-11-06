using System;
using System.Data;
using System.Configuration;
using System.Linq;
using System.IO;

namespace LoginUseWebService
{
    public class LogParserManager
    {
        private void processProperty(string property, string type, DateTime dt,DBManager dbm) 
        {
            string[] pValue = property.Split(LogConstants.SEP.ToCharArray());

            string prop = pValue[0];
            string value = pValue[1];

            dbm.saveLog(dt, type, prop, value);
        }
        
        private void processProperties(string properties,string type,DateTime dt,DBManager dbm) 
        {
            string[] values = properties.Split(LogConstants.PSEP.ToCharArray());
            for (int i = 0; i < values.Length; i++)
            {
                this.processProperty(values[i],type,dt,dbm);
            }
        }

        private DateTime getDatetime(string dTime)
        {
            string[] time = dTime.Substring(1, dTime.Length - 1).Split(':');

            int year = Convert.ToInt16(dTime.Substring(0, 3));
            int month = Convert.ToInt16(dTime.Substring(3, 5));
            int day = Convert.ToInt16(dTime.Substring(5, 7));
            int hh = Convert.ToInt16(time[0]);
            int mm = Convert.ToInt16(time[1]);
            int ss = Convert.ToInt16(time[2]);

            return new DateTime(year, month, day, hh, mm, ss); 
        }
        private void processLine(string line,string date,DBManager dbm)
        {
            string[] parts = line.Split(LogConstants.CATSEP.ToCharArray());

            DateTime dTime = this.getDatetime(parts[0]);            
            string type = parts[1].Substring(1, parts[1].Length - 1);

            for (int i = 2; i < parts.Length; i++)
            {
                parts[i] = parts[i].Substring(1, parts[i].Length - 1);
                this.processProperty(parts[i],type,dTime,dbm);
            }
        }

        private void processFile(string path,DBManager dbm) 
        {
            string date = Path.GetFileNameWithoutExtension(path).Substring(0, 7);
            
            StreamReader sr = new StreamReader(path);

            string line = null;
            while (!sr.EndOfStream)
            {
                this.processLine(sr.ReadLine(),date,dbm);
            }
        }

        public void execute(string phoneId,string zipPath,string pass) 
        {
            DBManager dbM = new DBManager();

            try
            {                
                string res = dbM.saveFile(phoneId, Path.GetFileName(zipPath), false, true, new FileInfo(zipPath).Length);

                if (res == "OK.")
                {

                    ZipManager zm = new ZipManager();
                    zm.descomprimirDir(zipPath, pass, Path.GetDirectoryName(zipPath), new ZipLog());


                    string[] files = Directory.GetFiles(Path.GetDirectoryName(zipPath), "*.txt", SearchOption.TopDirectoryOnly);

                    for (int i = 0; (i < files.Length) && (res == "OK."); i++)
                    {
                        this.processFile(files[i], dbM);
                        File.Delete(files[i]);
                        dbM.saveFile(phoneId, Path.GetFileName(files[i]), true, false, new FileInfo(files[i]).Length);
                    }

                    if (res == "OK.")
                        dbM.saveFile(phoneId, Path.GetFileName(zipPath), true, true, new FileInfo(zipPath).Length);
                }
            }
            catch (Exception ex)
            {
                dbM.saveFile(phoneId, Path.GetFileName(zipPath), false, true, new FileInfo(zipPath).Length);
            }
        }
    }
}
