using System;
using System.Data;
using System.Configuration;
using System.Linq;
using System.Collections.Generic;

namespace LoginUseWebService
{
    public class LocationManager
    {
        public double GetDistance(decimal long1InDegrees, decimal lat1InDegrees, decimal long2InDegrees, decimal lat2InDegrees)
        {
            double lats = (double)(lat1InDegrees - lat2InDegrees);
            double lngs = (double)(long1InDegrees - long2InDegrees);

            //Paso a metros
            double latm = lats * 60 * 1852;
            double lngm = (lngs * Math.Cos((double)lat1InDegrees * Math.PI / 180)) * 60 * 1852;
            double distInMeters = Math.Sqrt(Math.Pow(latm, 2) + Math.Pow(lngm, 2));
            return distInMeters;
        }

        private List<LocationGroup> getLocationGroups(string phoneId, DBManager dbm)
        {
            DataTable dt = dbm.getLocationGroups(phoneId);
            List<LocationGroup> list = new List<LocationGroup>();
            foreach (DataRow r in dt.Rows)
            {
                LocationGroup lg = new LocationGroup();
                lg.Id = (int)r["idLocationGroup"];
                lg.Name = (string)r["name"];
                lg.Longitud = Convert.ToDecimal(r["longitud"]);
                lg.Latitud = Convert.ToDecimal(r["latitud"]);
                lg.Count = (int)r["count"];
                list.Add(lg);
            }
            return list;
        }
        private List<Location> getLocations(string phoneId, DBManager dbm)
        {
            DataTable dt = dbm.getLocations(phoneId);
            List<Location> list = new List<Location>();
            foreach (DataRow r in dt.Rows)
            {
                Location l = new Location();
                l.Longitud = Convert.ToDecimal(r["longitud"]);
                l.Latitud = Convert.ToDecimal(r["latitud"]);                
                list.Add(l);
            }
            return list;
        }
        public void execute(string phoneId)
        {
            try
            {
                DBManager dbm = new DBManager();
                List<LocationGroup> groups = this.getLocationGroups(phoneId, dbm);
                List<Location> locations = this.getLocations(phoneId, dbm);

                foreach (Location l in locations)
                {
                    bool find = false;
                    for (int i = 0; (i < groups.Count) && !find; i++)
                    {
                        LocationGroup lg = groups[i];
                        if (this.GetDistance(lg.Longitud, lg.Latitud, l.Longitud, l.Latitud) < 400)
                        {
                            find = true;
                            lg.Latitud = ((lg.Latitud * lg.Count) + l.Latitud) / (lg.Count + 1);
                            lg.Longitud = ((lg.Longitud * lg.Count) + l.Longitud) / (lg.Count + 1);
                            lg.Count++;
                        }
                    }
                    if (!find)
                    {
                        LocationGroup lg = new LocationGroup();
                        lg.Name = "Group_" + (groups.Count + 1);
                        lg.Longitud = l.Longitud;
                        lg.Latitud = l.Latitud;
                        lg.Count = 1;
                        groups.Add(lg);
                    }
                }
                foreach (LocationGroup lg in groups)
                    dbm.saveLocationGroup(phoneId, lg);
            }
            catch (Exception ex) { }
        }
    }
}
