using System;
using System.Collections.Generic;
using System.Text;
using System.Data;
using System.Data.SqlClient;

namespace LoginUseWebServiceTest
{
    public class DBManager : DBConfigManager
    {
        private DBInterfase iDB;
        private bool isTransactionOpen;

        public DBManager()
        {
            this.iDB = new DBInterfase();
            this.isTransactionOpen = false;
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

        public DataTable getFile(string phoneId,bool isZip)
        {
            try
            {
                SqlCommand cmd = new SqlCommand("getFile");
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@phoneId", phoneId);                
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
        public DataTable getProperties(string names,int idType)
        {
            try
            {
                SqlCommand cmd = new SqlCommand("getProperty");
                cmd.CommandType = CommandType.StoredProcedure;
                if (names != null)
                    cmd.Parameters.AddWithValue("@names", names);
                if (idType != -1)
                    cmd.Parameters.AddWithValue("@type", idType);
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
        public DataTable getPropertyValues(int idProperty)
        {
            try
            {
                SqlCommand cmd = new SqlCommand("getPropertyValue");
                cmd.CommandType = CommandType.StoredProcedure;
                if (idProperty != -1)
                    cmd.Parameters.AddWithValue("@property", idProperty);
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
        public DataTable getCommands(string phoneId)
        {
            try
            {
                SqlCommand cmd = new SqlCommand("getCommands");
                cmd.CommandType = CommandType.StoredProcedure;
                if (phoneId != null)
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

        public void InsertCommands(string phoneId, int idRule, int idPropertyValue, string commandKey)
        {
            try
            {
                SqlCommand cmd = new SqlCommand("InsertCommand");
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@phoneId", phoneId);
                if(idRule != -1)
                    cmd.Parameters.AddWithValue("@idRule", idRule);
                cmd.Parameters.AddWithValue("@idPropertyValue", idPropertyValue);
                cmd.Parameters.AddWithValue("@commandKey", commandKey);
                this.connectIfNeed();
                this.iDB.execute(cmd);
                this.disconnectIfNeed();
            }
            catch (Exception ex)
            {
                string s = ex.Message;
            }
        }

        public void deleteCommands(string phoneId, int idRule, int idCondition)
        {
            try
            {
                SqlCommand cmd = new SqlCommand("DeleteCommand");
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@phoneId", phoneId);
                cmd.Parameters.AddWithValue("@idRule", idRule);
                cmd.Parameters.AddWithValue("@idCondition", idCondition);
                this.connectIfNeed();
                this.iDB.execute(cmd);
                this.disconnectIfNeed();
            }
            catch (Exception ex)
            {
                string s = ex.Message;
            }
        }

        public DataTable getCsvData(string phoneId, DateTime from, DateTime to, string typeNames, string propNames)
        {
            try
            {
                SqlCommand cmd = new SqlCommand("getCSVData");
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@phoneId", phoneId);
                cmd.Parameters.AddWithValue("@from", from);
                cmd.Parameters.AddWithValue("@to", to);
                if (typeNames != null)
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
    }
}
