using System;
using System.Collections.Generic;
using System.Text;
using System.Data;
using System.Data.SqlClient;

namespace LoginUseWebService
{
    public class DBManager : DBConfigManager
    {
        private InterfaseDB iDB;
        private bool isTransactionOpen;

        public DBManager()
        {
            this.iDB = new InterfaseDB();
            this.isTransactionOpen = false;
        }        

        public void beginTransaction(string name) 
        {
            this.connectIfNeed();
            this.iDB.newTransaction(name);
            this.isTransactionOpen = true;
        }
        public void endTransaction(string name,bool result)
        {
            this.isTransactionOpen = false;
            this.iDB.endTransaction(name, result);
            this.connectIfNeed();
            
        }

        private void connectIfNeed()
        {
            if (!this.isTransactionOpen && !this.iDB.isConected())
                this.iDB.connect(this.ServerDB, this.NameDB, this.UserId, this.PassDB);
        }
        private void disconnectIfNeed()
        {
            if (!this.isTransactionOpen && this.iDB.isConected())
                this.iDB.disconect();
        }

        #region -------------Transacciones--------------         
        public string saveUser(string phoneId,string name,string version, string phoneModel)
        {            
            try
            {
                SqlCommand cmd = new SqlCommand("InsertUser");
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@phoneId", phoneId);
                cmd.Parameters.AddWithValue("@name", name);
                cmd.Parameters.AddWithValue("@version", version);
                cmd.Parameters.AddWithValue("@phoneModel", phoneModel);
                this.connectIfNeed();
                this.iDB.executeInsert(cmd);
                this.disconnectIfNeed();
                return "OK.";
            }
            catch (Exception ex)
            {                                
                return "Fail.";
            }
        }
        public string saveFile(string phoneId,string name, bool process,bool isZip, long size)
        {
            try
            {
                SqlCommand cmd = new SqlCommand("InsertFile");
                cmd.CommandType = CommandType.StoredProcedure;                
                cmd.Parameters.AddWithValue("@phoneId", phoneId);
                cmd.Parameters.AddWithValue("@name", name);
                cmd.Parameters.AddWithValue("@process", process);
                cmd.Parameters.AddWithValue("@isZip", isZip);
                cmd.Parameters.AddWithValue("@size", size);
                this.connectIfNeed();
                int id = this.iDB.executeInsert(cmd);
                this.disconnectIfNeed();
                if (id > 0)
                    return "OK.";
                else
                    return "Fail.";
            }
            catch (Exception ex)
            {
                return "Fail.";
            }
        }
        public string saveLog(string fileName, DateTime dt, string type, string property, string value)
        {
            try
            {
                SqlCommand cmd = new SqlCommand("InsertLog");
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@file", fileName);
                cmd.Parameters.AddWithValue("@date", dt);
                cmd.Parameters.AddWithValue("@type", type);
                cmd.Parameters.AddWithValue("@property", property);
                cmd.Parameters.AddWithValue("@value", value);
                this.connectIfNeed();
                this.iDB.executeInsert(cmd);
                this.disconnectIfNeed();
                return "OK.";
            }
            catch (Exception ex)
            {
                return "Fail.";
            }
        }
        public string saveLocationGroup(string phoneId, LocationGroup lg)
        {
            try
            {
                SqlCommand cmd = new SqlCommand("InsertLocationGroup");
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@phoneId", phoneId);                
                cmd.Parameters.AddWithValue("@name", lg.Name);
                cmd.Parameters.AddWithValue("@latitud", lg.Latitud);
                cmd.Parameters.AddWithValue("@longitud", lg.Longitud);
                cmd.Parameters.AddWithValue("@count", lg.Count);
                if (lg.Id != int.MinValue)
                    cmd.Parameters.AddWithValue("@idLocationGroup", lg.Id);
                this.connectIfNeed();
                int id = this.iDB.executeInsert(cmd);
                this.disconnectIfNeed();
                if (id > 0)
                    return "OK.";
                else
                    return "Fail.";
            }
            catch (Exception ex)
            {
                return "Fail.";
            }
        }

        public string deleteLocationGroup(string phoneId)
        {
            try
            {
                SqlCommand cmd = new SqlCommand("deleteLocationGroups");
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@phoneId", phoneId); 
                this.connectIfNeed();
                this.iDB.execute(cmd);
                this.disconnectIfNeed();
                return "OK.";                
            }
            catch (Exception ex)
            {
                return "Fail.";
            }
        }

        public DataTable getFile(string phoneId, string fileName, bool isZip)
        {
            try
            {
                SqlCommand cmd = new SqlCommand("getFile");
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@phoneId", phoneId);
                cmd.Parameters.AddWithValue("@name", fileName);
                cmd.Parameters.AddWithValue("@isZip", isZip);
                this.connectIfNeed();
                DataTable dt = this.iDB.getTable(cmd);
                this.disconnectIfNeed();
                return dt;
            }
            catch (Exception ex)
            {
                return null;
            }
        }
        public DataTable getUsers(string names)
        {
            try
            {
                SqlCommand cmd = new SqlCommand("getUser");
                cmd.CommandType = CommandType.StoredProcedure;
                if (names != null)
                    cmd.Parameters.AddWithValue("@names", names);
                this.connectIfNeed();
                DataTable dt = this.iDB.getTable(cmd);
                this.disconnectIfNeed();
                return dt;
            }
            catch (Exception ex)
            {
                return null;
            }
        }
        public DataTable getType(string names)
        {
            try
            {
                SqlCommand cmd = new SqlCommand("getType");
                cmd.CommandType = CommandType.StoredProcedure;
                if (names != null)
                    cmd.Parameters.AddWithValue("@names", names);
                this.connectIfNeed();
                DataTable dt = this.iDB.getTable(cmd);
                this.disconnectIfNeed();
                return dt;
            }
            catch (Exception ex)
            {
                return null;
            }
        }
        public DataTable getProperties(string names)
        {
            try
            {
                SqlCommand cmd = new SqlCommand("getProperty");
                cmd.CommandType = CommandType.StoredProcedure;
                if (names != null)
                    cmd.Parameters.AddWithValue("@names", names);
                this.connectIfNeed();
                DataTable dt = this.iDB.getTable(cmd);
                this.disconnectIfNeed();
                return dt;
            }
            catch (Exception ex)
            {
                return null;
            }
        }
        public DataTable getLocationGroups(string phoneId)
        {
            try
            {
                SqlCommand cmd = new SqlCommand("getLocationGroup");
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@phoneId", phoneId);
                this.connectIfNeed();
                DataTable dt = this.iDB.getTable(cmd);
                this.disconnectIfNeed();
                return dt;
            }
            catch (Exception ex)
            {
                return null;
            }
        }
        public DataTable getLocations(string phoneId)
        {
            try
            {
                SqlCommand cmd = new SqlCommand("getLocation");
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@phoneId", phoneId);
                this.connectIfNeed();
                DataTable dt = this.iDB.getTable(cmd);
                this.disconnectIfNeed();
                return dt;
            }
            catch (Exception ex)
            {
                return null;
            }
        }
        public DataTable getRules(string phoneId)
        {
            try
            {
                SqlCommand cmd = new SqlCommand("getRules");
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@phoneId", phoneId);
                this.connectIfNeed();
                DataTable dt = this.iDB.getTable(cmd);
                this.disconnectIfNeed();
                return dt;
            }
            catch (Exception ex)
            {
                return null;
            }
        }

        public DataTable getCsvData(string phoneId,DateTime from, DateTime to,string typeNames,string propNames)
        {
            try
            {
                SqlCommand cmd = new SqlCommand("getCSVData");
                cmd.CommandType = CommandType.StoredProcedure;                
                cmd.Parameters.AddWithValue("@phoneId", phoneId);                
                cmd.Parameters.AddWithValue("@from", from);
                cmd.Parameters.AddWithValue("@to", to);
                if(typeNames != null)
                    cmd.Parameters.AddWithValue("@typeNames", typeNames);
                if (propNames != null)
                    cmd.Parameters.AddWithValue("@propNames", propNames);
                this.connectIfNeed();
                DataTable dt = this.iDB.getTable(cmd);
                this.disconnectIfNeed();
                return dt;
            }
            catch (Exception ex)
            {
                return null;
            }
        }
        #endregion


        public bool isFileProcess(string phoneId, string name, bool isZip)
        {
            DataTable dt = this.getFile(phoneId,name, isZip);

            if  ((dt.Rows.Count == 0) || ((dt.Rows.Count > 0) && !(bool)dt.Rows[0]["Process"]))                
                return false;
            return true;
        }
    }
}
