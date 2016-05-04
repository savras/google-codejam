using System;
using System.Collections.Generic;
using System.IO;

public class Solution {
    private static int flipTimes;
    public static string Flip(int end, string patt) // end is an actual array index so it includes the 0 offset.
    {
        flipTimes++;
        //Console.WriteLine(string.Format("Flipping {0}, {1}", end, patt));
        
        var strArr = patt.ToCharArray();
        const int start = 0;

        for(int i = start; i <= end; i++)
        {
            if(patt[end -i] == '+')
            {
                strArr[i] = '-';
            }
            else
            {
                strArr[i] = '+';
            }
        }
        
        //Console.WriteLine("After flipping {0}", new string(strArr));
        return new string(strArr);
    }
    
    static void Main(String[] args) {
        int T;
        string patt = string.Empty;
        
        T = Int32.Parse(Console.ReadLine());
        //while(T-- > 0)
        for(int i = 1; i <= T; i++)
        {
            patt = Console.ReadLine();
            
            flipTimes = 0;
            int counter = 0;
            bool cont = true;
            
            // Scan for consecutive '+' from the front;                        
            while(cont)           
            {   
                if(patt[counter] == '+')
                { 
                    if(counter == patt.Length - 1)
                    {
                        cont = false;
                    }
                    
                    counter++;
                }
                else
                {
                    // i-1 is index of consecutive "+" from the front;
                    // Flip from front to i;                    
                    if(counter == 0)
                    {
                        // Scan for first - from the back
                        int p;
                        for(p = patt.Length - 1; p >= 0; p--)
                        {
                            if(patt[p] == '-')
                            {
                                patt = Flip(p, patt);
                                
                                break;
                            }                        
                        }
                    }
                    else
                    {
                        patt = Flip(counter - 1, patt);                        
                        
                        // Scan for first - from the back
                        int p;
                        for(p = patt.Length - 1; p > 0; p--)
                        {
                            if(patt[p] == '-')
                            {
                                patt = Flip(p, patt);                                
                                break;
                            }                        
                        }                        
                    }
                    counter = 0;
                }
            }
            Console.WriteLine(string.Format("Case #{0}: {1}", i, flipTimes));
        }
    }
}      
