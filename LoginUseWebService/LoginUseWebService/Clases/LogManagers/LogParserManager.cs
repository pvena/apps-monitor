using System;
using System.Data;
using System.Configuration;
using System.Linq;
using System.IO;

namespace LoginUseWebService
{
    public class LogParserManager
    {

        public void execute(string path) 
        {
            string[] files = Directory.GetFiles(path, "*.txt", SearchOption.TopDirectoryOnly);


        }
    }
}
