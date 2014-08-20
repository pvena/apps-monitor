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
        private void setPropertyValue(SortedDictionary<string, string> values, string propName, string value)
        {
            string type = propName.Split('-')[0];
            string name = propName.Split('-')[1];
            List<string> keys = new List<string>(values.Keys);
            if ((type == LogConstants.WIFI_STATE_TAG) && (name == LogConstants.STATE) && (value == "0"))
                foreach (string key in keys)
                    if (key.Contains(type))
                        values[key] = "?";
            if ((type == LogConstants.BLUETOOTH_STATE_TAG) && (name == LogConstants.STATE) && (value == "0"))
                foreach (string key in keys)
                    if (key.Contains(type) && !key.Contains(LogConstants.STATE))
                        values[key] = "?";
            values[propName] = value;
        }

        private void saveAtributes(StreamWriter f, DataTable dt) 
        {
            SortedDictionary<string, List<string>> attributes = new SortedDictionary<string, List<string>>();

            string attribute = null;
            string value = null;
            foreach (DataRow dr in dt.Rows)
            {
                attribute = (string)dr["FullName"];
                value = (string)dr["PropValue"];
                if (!attributes.ContainsKey(attribute))
                   attributes.Add(attribute, new List<string>());
                if (!attributes[attribute].Contains(value))
                   attributes[attribute].Add(value);
            }
            
            foreach (string at in attributes.Keys.ToList())
            {
                string line = "@attribute <" + at + "> {";
                foreach (string v in attributes[at].ToList())
                    line += v + ",";
                line = line.Substring(0,line.Length - 1);
                line += "}";
                f.WriteLine(line);
            }
        }
        
        public bool execute(string phoneId, DateTime from, DateTime to, string propNames, string path)
        {
            try
            {
                DBManager dbm = new DBManager();
                
                DataTable data = dbm.getCsvData(phoneId, from, to, propNames);

                //======================Obtengo los nombres de las properties================================
                SortedDictionary<string, string> values = new SortedDictionary<string,string>();
                foreach (DataRow dr in data.Rows)
                    if (!values.ContainsKey((string)dr["FullName"]))
                        values.Add((string)dr["FullName"], "?");

                string[] properties = values.Keys.ToArray<string>();

                //======================Obtengo la primer fecha==============================================
                DateTime date = (data.Rows.Count > 0) ? (DateTime)data.Rows[0]["date"] : DateTime.Now;

                string line = "";

                StreamWriter file = new StreamWriter(path, true);
                file.WriteLine("@RELATION Tesis");

                this.saveAtributes(file, data);

                file.WriteLine("@data");

                foreach (DataRow r in data.Rows)
                {
                    DateTime current = (DateTime)r["date"];
                    if (date != current)
                    {
                        date = current;
                        line = "";
                        for (int i = 0; i < properties.Length; i++)
                            line += (string)values[properties[i]] + ",";
                        file.WriteLine(line.Substring(0, line.Length - 1));
                    }
                    this.setPropertyValue(values, (string)r["FullName"], (string)r["PropValue"]);                    
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
