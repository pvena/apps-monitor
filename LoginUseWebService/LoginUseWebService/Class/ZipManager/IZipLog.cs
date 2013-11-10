using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace LoginUseWebService
{
    public interface IZipLog
    {
        void initialize();

        string Log { set; }

    }
}