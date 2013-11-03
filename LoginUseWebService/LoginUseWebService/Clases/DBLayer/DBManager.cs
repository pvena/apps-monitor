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
        #endregion
    }
}
