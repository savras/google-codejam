using System;
using System.Collections.Generic;
using System.IO;

public class Solution {
    static Dictionary<char, char> dict; 
    
    public static bool ProcessDigits(int value, int counter)
    {
        //Console.WriteLine(string.Format("Processing {0}, {1}.", value, counter));
        char result;        
        var dString = value.ToString();
        
        for(int i = 0; i < dString.Length; i++)
        {
            var ch = dString[i];
            if(!dict.TryGetValue(ch, out result))
            {
                dict.Add(ch, ch);
            }
        }
        
        var count = dict.Count;
   
        /*
        if(count < 10 && counter > 500)
        {
            throw new Exception();
        }
     */
        
        var re = count == 10 ? false : true;
       
        /*
        foreach(var s in dict.Keys )
        {
            Console.WriteLine("Key = {0}", s);
        }
        */
        
        return re;        
        
    }
    
    static void Main(String[] args) {
        dict = new Dictionary<char, char>();
        int T;
        
        T = Int32.Parse(Console.ReadLine());
        
        int n;
        bool isInsomnia = false;
        for(int i = 1; i <= T; i++)
        {
            isInsomnia = false;
            n = Int32.Parse(Console.ReadLine());
            
            var cont = true;
            var counter = 1;
            while(cont)
            {                
                cont = ProcessDigits(n * counter, counter);
                
                if(cont && counter > 600)
                {                    
                    cont = false;
                    isInsomnia = true;
                }
                
                counter++;
            }            
            dict.Clear();
            
            if(isInsomnia)
            {
                Console.WriteLine("Case #" + i + ": " + "INSOMNIA");
            }
            else 
            {
                Console.WriteLine("Case #" + i + ": " + n * (counter - 1));
            }
        }
    }
}      
