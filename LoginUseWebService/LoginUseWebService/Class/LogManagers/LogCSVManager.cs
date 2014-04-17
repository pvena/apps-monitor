using System;
using System.Data;
using System.Configuration;
using System.Linq;
using System.Collections.Generic;
using System.IO;

namespace LoginUseWebService
{
    public class LogCSVManager
    {
        private string extraColumns = "YEAR;MONTH;DAY;HOUR;MINUTE;SECOND;ISWEEKDAY;QUARTER;LOCATIONGROUP";

        private void setPropertyValue(Dictionary<string, string> values, string propName, string value)
        {
            string type = propName.Split('-')[0];
            string name = propName.Split('-')[1];
            List<string> keys = new List<string>(values.Keys);
            if ((type == LogConstants.WIFI_STATE_TAG) && (name == LogConstants.STATE) && (value == "0"))
                foreach (string key in keys)
                    if (key.Contains(type))
                        values[key] = "-";
            if ((type == LogConstants.BLUETOOTH_STATE_TAG) && (name == LogConstants.STATE) && (value == "0"))
                foreach (string key in keys)
                    if (key.Contains(type) && !key.Contains(LogConstants.STATE))
                        values[key] = "-";
            values[propName] = value;
        }
        
        private Dictionary<string, string> inicPropertiesValues(string[] properties)
        {
            Dictionary<string, string> values = new Dictionary<string, string>();
            for (int i = 0; i < properties.Length; i++)
                values.Add(properties[i], "");
            return values;
        }

        private void preProcessDate(Dictionary<string, string> values, DateTime date)
        {
            values["YEAR"] = date.ToString("yyyy");
            values["MONTH"] = date.ToString("MM");
            values["DAY"] = date.ToString("dd");
            values["HOUR"] = date.ToString("hh");
            values["MINUTE"] = date.ToString("mm");
            values["SECOND"] = date.ToString("ss");
            values["ISWEEKDAY"] = ((date.DayOfWeek == DayOfWeek.Sunday) || (date.DayOfWeek == DayOfWeek.Saturday)) ? "0" : "1";
            values["QUARTER"] = (date.Minute / 15).ToString();
        }
        
        private void preProcessLocationGroup(List<LocationGroup> locationGroups, Dictionary<string, string> values, LocationManager lm)
        {
            decimal lat = 0;
            decimal lng = 0;

            string latitud = LogConstants.LOCATION_STATE_TAG + "-" + LogConstants.LATITUDE;
            string longitud = LogConstants.LOCATION_STATE_TAG + "-" + LogConstants.LONGITUDE;

            if (values.ContainsKey(latitud) && values.ContainsKey(longitud))
                if ((values[latitud].Length > 0) && (values[longitud].Length > 0))
                    if (decimal.TryParse(values[latitud], out lat) && decimal.TryParse(values[longitud], out lng))
                        values["LOCATIONGROUP"] = lm.getContainGroup(locationGroups, lat, lng).Name;            
        }
        
        public bool execute(string phoneId, DateTime from, DateTime to, string propNames, string path)
        {
            try
            {
                DBManager dbm = new DBManager();
                LocationManager lm = new LocationManager();

                List<LocationGroup> locationGroups = lm.getLocationGroups(phoneId, dbm);                
                DataTable data = dbm.getCsvData(phoneId, from, to, propNames);

                string line = this.extraColumns + ";" + propNames;

                string[] properties = line.Split(';');
                Dictionary<string, string> values = this.inicPropertiesValues(properties);

                DateTime date = (data.Rows.Count > 0) ? (DateTime)data.Rows[0]["date"] : DateTime.Now;
                string[] location = new string[] { "", "" };
                bool change = false;

                StreamWriter file = new StreamWriter(path, true);
                file.WriteLine(line);
                
                foreach (DataRow r in data.Rows)
                {
                    DateTime current = (DateTime)r["date"];
                    if (date != current)
                    {
                        date = current;
                        line = "";
                        for (int i = 0; i < properties.Length; i++)
                            line += (string)values[properties[i]] + ";";
                        file.WriteLine(line.Substring(0, line.Length - 1));
                    }
                    this.preProcessDate(values, current);
                    this.setPropertyValue(values, (string)r["FullName"], (string)r["PropValue"]);
                    this.preProcessLocationGroup(locationGroups, values, lm);                    
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
