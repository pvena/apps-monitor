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

        public DBManager()            
        {
            this.iDB = new InterfaseDB();
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
                this.iDB.connect(this.ServerDB, this.NameDB, this.UserId, this.PassDB);
                this.iDB.executeInsert(cmd);
                this.iDB.disconect();
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
                this.iDB.connect(this.ServerDB, this.NameDB, this.UserId, this.PassDB);
                int id = this.iDB.executeInsert(cmd);
                this.iDB.disconect();
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
                this.iDB.connect(this.ServerDB, this.NameDB, this.UserId, this.PassDB);
                this.iDB.executeInsert(cmd);
                this.iDB.disconect();
                return "OK.";
            }
            catch (Exception ex)
            {
                return "Fail.";
            }
        }

        public DataTable getFile(string fileName, bool isZip)
        {
            try
            {
                SqlCommand cmd = new SqlCommand("getFile");
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@name", fileName);
                cmd.Parameters.AddWithValue("@isZip", isZip);
                this.iDB.connect(this.ServerDB, this.NameDB, this.UserId, this.PassDB);
                DataTable dt = this.iDB.getTable(cmd);
                this.iDB.disconect();
                return dt;
            }
            catch (Exception ex)
            {
                return null;
            }
        }


        public DataTable getCsvData(int idUser,DateTime from, DateTime to)
        {
            try
            {
                SqlCommand cmd = new SqlCommand("getCSVData");
                cmd.CommandType = CommandType.StoredProcedure;                
                cmd.Parameters.AddWithValue("@idUser", idUser);
                cmd.Parameters.AddWithValue("@from", from);
                cmd.Parameters.AddWithValue("@to", to);
                this.iDB.connect(this.ServerDB, this.NameDB, this.UserId, this.PassDB);
                DataTable dt = this.iDB.getTable(cmd);
                this.iDB.disconect();
                return dt;
            }
            catch (Exception ex)
            {
                return null;
            }
        }
        #endregion


        public bool isFileProcess(string name, bool isZip)
        {
            DataTable dt = this.getFile(name, isZip);

            if  ((dt.Rows.Count == 0) || ((dt.Rows.Count > 0) && !(bool)dt.Rows[0]["Process"]))                
                return false;
            return true;
        }
    }
}
