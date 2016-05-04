using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

class Solution {

    static void Main(String[] args) {
        var T = Int32.Parse(Console.ReadLine());
        var s = String.Empty;
        int l = 0, max = 0;
        var sb = new StringBuilder();
        for(int p = 1; p <= T; p++)
        {
            max = 0;
            sb.Clear();
            s = Console.ReadLine();
            l = s.Length;            
            
            for(int i = 0; i < l; i++)
            {
                var c = s[i];
                var cVal = (int)(c);
                
                if(max < cVal)
                {
                    max = cVal;
                }
                
                if(cVal >= max)
                {
                    sb.Insert(0, c);
                }
                else
                {
                    sb.Append(c);
                }
            }
            Console.Write(string.Format("Case #{0}: ", p));
            Console.WriteLine(sb.ToString());
        }
    }
}