using System;
using System.Data;
using System.Configuration;
using System.Collections.Generic;

namespace LoginUseWebService
{
    public class ServiceManager
    {
        private static ServiceManager instance;  
        
        private List<string> extensions = new List<string>();

        private ServiceManager() 
        {
            this.extensions = new List<string>();
            this.extensions.Add(".ZIP");
            this.extensions.Add(".TXT");
        }

        public static ServiceManager getInstance()
        {
            if (ServiceManager.instance == null)
                ServiceManager.instance = new ServiceManager();
            return ServiceManager.instance;
        }

        public bool isExtensionOk(string ext)
        {
            return this.extensions.Contains(ext.ToUpper());
        }

        public void starProcess()
        { 
        }
    }
}
