using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Data;
using System.Threading;
using System.IO;

namespace LoginUseWebServiceTest
{
    public class DBInterfase
    {
        private SqlConnection coneccion;
        private SqlTransaction transaccion;
        private string transNombre;
        private bool conectado;

        public DBInterfase()
        {
            this.conectado = false;
        }

        #region -----------coneccion------------
        private string getConnectionString(string servidor, string nombreDB, string userId, string pass)
        {
            string cs = "Data Source=" + servidor + ";Initial Catalog=" + nombreDB;
            cs += ";uid=" + userId + ";Password=" + pass;
            cs += ";Integrated Security=False";
            return cs;
        }
        public void connect(string servidor, string nombreDB, string userId, string pass)
        {
            string conect = this.getConnectionString(servidor, nombreDB, userId, pass);
            this.coneccion = new SqlConnection(conect);
            this.conectado = false;
            this.coneccion.Open();
            this.conectado = (this.coneccion.State == ConnectionState.Open);
        }
        public void disconect()
        {
            lock (this.coneccion)
            {
                if (this.conectado)
                {
                    this.coneccion.Close();
                    this.coneccion.Dispose();
                    this.conectado = false;
                }
            }
        }
        public bool isConected()
        {
            return this.conectado;
        }
        #endregion

        #region --------------Publicos----------
        public void newTransaction(string nombre)
        {
            lock (this.coneccion)
            {
                if (this.transNombre == null)
                {
                    this.transNombre = nombre;
                    this.transaccion = coneccion.BeginTransaction();
                }
            }
        }
        public void endTransaction(string nombre, bool exito)
        {
            lock (this.coneccion)
            {
                if (this.transNombre == nombre)
                {
                    if (exito)
                        this.transaccion.Commit();
                    else
                        this.transaccion.Rollback();
                    this.transaccion = null;
                    this.transNombre = null;
                }
            }
        }

        #region --------------Consultas---------
        public DataTable getTable(SqlCommand cmd)
        {
            lock (this.coneccion)
            {
                cmd.Connection = coneccion;
                cmd.Transaction = this.transaccion;
                SqlDataAdapter da = new SqlDataAdapter(cmd);
                DataTable res = new DataTable();
                try
                {
                    da.Fill(res);
                }
                catch (Exception e) { }
                return res;
            }
        }
        public object getValue(SqlCommand cmd)
        {
            lock (this.coneccion)
            {
                cmd.Connection = coneccion;
                cmd.Transaction = this.transaccion;
                object res = null;
                try
                {
                    res = cmd.ExecuteScalar();
                }
                catch (Exception e) { }
                return res;
            }
        }
        public DataRow getRow(SqlCommand cmd)
        {
            lock (this.coneccion)
            {
                cmd.Connection = coneccion;
                DataTable t = this.getTable(cmd);
                if (t.Rows.Count > 0)
                    return t.Rows[0];
                return null;
            }
        }
        #endregion

        #region -----------------ABM------------
        public void execute(SqlCommand cmd)
        {
            lock (this.coneccion)
            {
                cmd.Connection = coneccion;
                cmd.Transaction = this.transaccion;
                cmd.ExecuteNonQuery();
            }
        }
        public int executeInsert(SqlCommand cmd)
        {
            lock (this.coneccion)
            {
                cmd.Connection = this.coneccion;
                cmd.Transaction = this.transaccion;
                int res = -1;
                res = Convert.ToInt32(this.getRow(cmd)["id"].ToString());
                return res;
            }
        }
        #endregion

        #region --------------Archivos----------
        public string backUp(string path, bool diferencial, string version)
        {
            lock (this.coneccion)
            {
                SqlCommand cmd = new SqlCommand("CrearBackupDB");
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@path", path);
                if ((version != null) && (version.Length > 0))
                    cmd.Parameters.AddWithValue("@version", version);
                return (string)this.getValue(cmd);
            }
        }
        #endregion

        #region ---------------Tablas-----------
        public DataTable getTable(string consulta)
        {
            SqlCommand cmd = new SqlCommand();
            cmd.CommandType = CommandType.Text;
            cmd.CommandText = consulta;
            return this.getTable(cmd);
        }
        public DataTable getTable(string sp, List<string> pNombres, List<Object> pValores)
        {
            SqlCommand cmd = new SqlCommand(sp, this.coneccion);
            cmd.CommandType = CommandType.StoredProcedure;
            for (int i = 0; (i < pNombres.Count) && (i < pValores.Count); i++)
                cmd.Parameters.AddWithValue(pNombres[i], pValores[i]);
            return this.getTable(cmd);
        }
        #endregion

        #endregion
    }
}

