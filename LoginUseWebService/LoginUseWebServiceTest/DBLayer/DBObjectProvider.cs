﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using System.Data.SqlClient;

namespace LoginUseWebServiceTest
{
    public class DBObjectProvider
    {
        #region -----------Get Data Base Info---------------

        public List<User> getUsers()
        {
            DBManager mdb = new DBManager();
            DataTable dt = mdb.getUsers(null);

            List<User> list = new List<User>();                
            if (dt != null)
                foreach (DataRow r in dt.Rows)
                {
                    User u = new User();
                    u.name = (string)r["Name"];
                    u.phoneId = (string)r["phoneId"];
                    u.phoneModel = (string)r["phoneModel"];
                    u.version = (string)r["version"];
                    u.maxLocation = (int)r["maxLocations"];
                    if (r["lastLocationProcess"] != DBNull.Value)
                        u.lastLocationProcess = (DateTime)r["lastLocationProcess"];
                    list.Add(u);
                }          
            return list;
        }

        public List<Property> getTypes()
        {
            DBManager mdb = new DBManager();
            DataTable dt = mdb.getType(null);

            List<Property> list = new List<Property>();

            if (dt != null)
                foreach (DataRow r in dt.Rows)
                {
                    Property p = new Property();
                    p.id = (int)r["idType"];
                    p.name = (string)r["name"];
                    list.Add(p);
                }
            return list;
        }

        public List<Property> getProperties(int idType)
        {
            DBManager mdb = new DBManager();
            DataTable dt = mdb.getProperties(null,idType);

            List<Property> list = new List<Property>();

            if (dt != null)
                foreach (DataRow r in dt.Rows)
                {
                    Property p = new Property();
                    p.id = (int)r["idProperty"];
                    p.name = (string)r["name"];
                    p.type = (string)r["type"];
                    list.Add(p);
                }
            return list;
        }

        public List<Property> getPropertyValues(int idProperty)
        {
            DBManager mdb = new DBManager();
            DataTable dt = mdb.getPropertyValues(idProperty);

            List<Property> list = new List<Property>();

            if (dt != null)
                foreach (DataRow r in dt.Rows)
                {
                    Property p = new Property();
                    p.id = (int)r["idPropertyValue"];
                    p.value = (string)r["value"];
                    list.Add(p);
                }
            return list;
        }

        public List<Property> getCommands(string phoneId)
        {
            DBManager mdb = new DBManager();
            DataTable dt = mdb.getCommands(phoneId);

            List<Property> list = new List<Property>();

            if (dt != null)
                foreach (DataRow r in dt.Rows)
                {
                    Property p = new Property();
                    p.id = (int)r["idRule"];
                    p.fId = (int)r["idCondition"];
                    p.name = (string)r["CommandKey"];
                    p.value = (string)r["condition"];
                    list.Add(p);
                }
            return list;
        }

        public List<File> getFiles(User u)
        {
            DBManager mdb = new DBManager();
            DataTable dt = mdb.getFile(u.phoneId,false);

            List<File> list = new List<File>();

            if (dt != null)
                foreach (DataRow r in dt.Rows)
                {
                    File f = new File();
                    f.name = (string)r["name"];
                    f.process = (bool)r["process"];
                    f.isZip = (bool)r["isZip"];
                    list.Add(f);
                }
            return list;
        }

        #endregion 
    }
}