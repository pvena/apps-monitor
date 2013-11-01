using System;
using System.Data;
using System.IO;
using ICSharpCode.SharpZipLib.Core;
using ICSharpCode.SharpZipLib.Zip;

namespace LoginUseWebService
{
    public class ZipManager
    {
        #region -----------------Zip Directorio------------------------
        private string getNombre(string carpeta,string path)
        {
            return ZipEntry.CleanName(path.Substring(carpeta.Length + (carpeta.EndsWith("\\") ? 0 : 1)));
        }
        private void guardarEnZip(string carpeta, string[] paths, ZipOutputStream zip, IZipLog l)
        {
            foreach (string path in paths)
            {
                l.Log = "[Info] Comprimiendo " + path + "...";
                ZipEntry nuevo = new ZipEntry(this.getNombre(carpeta, path));
                nuevo.Size = new FileInfo(path).Length;
                zip.PutNextEntry(nuevo);
                FileStream streamReader = File.OpenRead(path);
                StreamUtils.Copy(streamReader, zip, new byte[4096]);
                streamReader.Close();
                zip.Close();
            }
        }
        public bool comprimirDir(ref string archivo, string pass, string carpeta, string[] filtro, IZipLog l)
        {
            try
            {
                archivo += ".zip";
                l.Log = "[Info] Inicio Compresion...";
                string[] paths = Directory.GetFiles(carpeta, String.Join(";", filtro), SearchOption.TopDirectoryOnly);
                ZipOutputStream zip = new ZipOutputStream(File.Create(archivo));
                zip.SetLevel(3);
                zip.Password = pass;
                guardarEnZip(carpeta,paths, zip, l);
                zip.IsStreamOwner = true;
                zip.Close();
                l.Log = "[Info] Fin compresion...";
                return true;
            }
            catch (Exception e) 
            {
                l.Log = "[Error] " + e.Message;
                return false; 
            }
        }
         
        private void extraerArchivoZip(string carpeta, ZipFile zf, ZipEntry zip, IZipLog l)
        {
            if (zip.IsFile)
            {
                l.Log = "[Info] Descomprimiendo " + zip.Name + "...";
                byte[] buffer = new byte[4096];
                Stream zipStream = zf.GetInputStream(zip);
                String path = Path.Combine(carpeta, zip.Name);
                string dir = Path.GetDirectoryName(path);
                if (dir.Length > 0)
                    Directory.CreateDirectory(dir);
                FileStream streamWriter = File.Create(path);
                StreamUtils.Copy(zipStream, streamWriter, buffer);
                streamWriter.Close();
            }
        }
        public bool descomprimirDir(string archivo, string password, string carpeta, IZipLog l)
        {
            try
            {
                l.Log = "[Info] Inicio descompresion: " + Path.GetFileName(archivo);
                bool res = false;
                if (File.Exists(archivo))
                {
                    ZipFile zf = new ZipFile(File.OpenRead(archivo));
                    if (!String.IsNullOrEmpty(password))
                        zf.Password = password;
                    for (int i = 0; i < zf.Count; i++)
                        this.extraerArchivoZip(carpeta, zf, zf[i], l);
                    zf.IsStreamOwner = true;
                    zf.Close();
                    res = true;
                }
                l.Log = "[Info] Fin Descompresion: " + Path.GetFileName(archivo);
                
                return res;
            }
            catch (Exception e)
            {
                l.Log = e.Message;
                return false;
            }
        }
        #endregion
    }
}
