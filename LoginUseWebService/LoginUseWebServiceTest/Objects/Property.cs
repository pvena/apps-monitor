using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace LoginUseWebServiceTest
{
    public class Property
    {
        public string name { get; set; }
        public string type { get; set; }

        public string FullName { get { return this.type + "-" + this.name; } }
    }
}
