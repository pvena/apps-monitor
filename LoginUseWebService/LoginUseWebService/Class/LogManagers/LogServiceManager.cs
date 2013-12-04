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

        private void setPropertyValue(Dictionary<string, string> values, string propName, string value)
        {
            string type = propName.Split('-')[0];
            string name = propName.Split('-')[1];
            List<string> keys = new List<string>(values.Keys);
            if ((type == "WIFI") && (name == "STATE") && (value == "0"))
                foreach (string key in keys)
                    if (key.Contains(type))
                        values[key] = "-";
            if ((type == "BLUETOOTH") && (name == "STATE") && (value == "0"))
                foreach (string key in keys)
                    if (key.Contains(type) && !key.Contains("STATE"))
                        values[key] = "-";
            values[propName] = value;
        }

        private Dictionary<string, string> inicPropValues(string[] properties)
        {
            Dictionary<string, string> values = new Dictionary<string, string>();
            for (int i = 0; i < properties.Length; i++)
                values.Add(properties[i], "");
            return values;
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

        #endregion

        #region ------------Process Data Out----------------
        private DateTime preProcessDate(Dictionary<string, string> values,DateTime date, DateTime lDate,out bool change)
        {
            change = (date != lDate);
            if (change)
            {                 
                values["year"] = date.ToString("yyyy");
                values["month"] = date.ToString("MM");
                values["day"] = date.ToString("dd");
                values["hour"] = date.ToString("hh");
                values["minute"] = date.ToString("mm");
                values["second"] = date.ToString("ss");
                values["isWeekDay"] = ((date.DayOfWeek == DayOfWeek.Sunday) || (date.DayOfWeek == DayOfWeek.Saturday)) ? "0" : "1";
                values["quarter"] = (date.Minute / 15).ToString();                
            }
            return date;
        }
        private string[] preProcessLocationGroup(List<LocationGroup> locationGroups, Dictionary<string, string> values, string[] location, LocationManager lm)
        {
            decimal lat = 0;
            decimal lng = 0;
            if (    (values["LOCATION-LAT"].Length > 0) && 
                    (values["LOCATION-LONG"].Length > 0) &&
                    (location[0] != values["LOCATION-LAT"]) && 
                    (location[1] != values["LOCATION-LONG"]) )
            {
                
                if (decimal.TryParse(values["LOCATION-LAT"], out lat) && decimal.TryParse(values["LOCATION-LONG"], out lng))
                    values["LocationGroup"] = lm.getContainGroup(locationGroups, lat, lng).Name;
                return new string[] { values["LOCATION-LAT"], values["LOCATION-LONG"] };
            }
            return location;
        }
        public bool createCSVFile(string phoneId, DateTime from, DateTime to, string typeNames, string propNames, string path)
        {
            try
            {
                DBManager dbm = new DBManager();
                LocationManager lm = new LocationManager();
                List<LocationGroup> locationGroups = lm.getLocationGroups(phoneId, dbm);
                DataTable data = dbm.getCsvData(phoneId, from, to, typeNames, propNames);

                string line = "year;month;day;hour;minute;second;isWeekDay;quarter;LocationGroup;" + propNames;

                string[] properties = line.Split(';');
                Dictionary<string, string> values = this.inicPropValues(properties);

                StreamWriter file = new StreamWriter(path, true);
                file.WriteLine(line);

                DateTime date = DateTime.Now;
                string[] location = new string[] { "", "" }; 
                bool change= false;

                foreach (DataRow r in data.Rows)
                {
                    this.setPropertyValue(values, (string)r["FullName"], (string)r["PropValue"]);
                    date = this.preProcessDate(values, (DateTime)r["date"], date,out change);
                    location = this.preProcessLocationGroup(locationGroups, values, location, lm);         
           
                    if (change)
                    {
                        line = "";
                        for (int i = 0; i < properties.Length; i++)
                            line += (string)values[properties[i]] + ";";
                        file.WriteLine(line.Substring(0, line.Length - 1));
                    }
                }
                file.Flush();
                file.Close();

                return true;
            }
            catch (Exception ex)
            { return false; }
        }

        public string createLocationGroupData(string phoneId)
        {
            DBManager dbm = new DBManager();
            DataTable dt = dbm.getLocationGroups(phoneId);            
            if (dt != null)
            {
                string data = "";
                foreach (DataRow r in dt.Rows)
                    data += r["name"] + "&" + r["latitud"] + "&" + r["longitud"] + "&";
                return data.Substring(0,data.Length-1);
            }
            return "";
        }
        #endregion
        
        #region -----------Get Data Base Info---------------

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

        
    }
}
