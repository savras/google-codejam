using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace Template
{   
    public class Program
    {        
        static int caseNumber = 1;

        static void Main(string[] args)
        {
            var printList = new List<string>();
            int T;
            
            const string inputPath = @"F:\Users\Xaero\Desktop\CodeJam\2016\1C\Evacuation\A-small-attempt2.in";
            
            using (var file = new StreamReader(inputPath))
            {
                T = int.Parse(ReadNextLine(file));

                /*** Code solution here. ***/
                const int asciiA = 65;
                var sb = new StringBuilder();

                for (int i = 0; i < T; i++)
                {
                    sb.Clear();
                    /*** Tweak input here. ***/
                    var n = int.Parse(ReadNextLine(file));
                    var parties = ReadNextLine(file).Split(' ');

                    var largest = -1;
                    var arr = new int[n];

                    for(var p = 0; p < n; p++)
                    {                        
                        arr[p] = int.Parse(parties[p]);

                        if(largest < arr[p])
                        {
                            largest = arr[p];
                        }
                    }
                    
                    var counter = 0;
                    var found = true;
                    while (largest != 0)
                    {
                        for (int p = 0; p < n && counter < 2; p++)
                        {
                            if(arr[p] == largest)
                            {
                                var cInt = asciiA + p;
                                var c = (char)cInt;
                                sb.Append(c);
                                counter++;
                                arr[p]--;
                                found = true;
                            }
                            else
                            {
                                found = false;
                            }
                        }

                        if (!found)
                        {
                            largest--;
                            continue;
                        }

                        counter = 0;
                        sb.Append(" ");
                    }
                    
                    var tmpStr = sb.ToString().Trim();
                    var len = tmpStr.Length;
                    var last = len - 1;
                    if (tmpStr[last - 1] == ' ')
                    {
                        var lastChar = tmpStr[last];
                        sb.Remove(last, 1);
                        sb.Insert(len - 4, lastChar.ToString() + " ");
                    }

                    AddOutput(printList, sb.ToString().Trim());
                }
            }

            WriteToFile(printList);

            Console.WriteLine("Press any key to exit.");
            Console.ReadKey();
        }

        #region IO Stuff
        static string ReadNextLine(StreamReader file)
        {
            return file.ReadLine();
        }

        static void WriteToFile(List<string> outputList)
        {
            const string outputPath = @"F:\Users\Xaero\Desktop\CodeJam\2016\1C\Evacuation\A-small.out";
            using (var file = new StreamWriter(outputPath, false))
            {
                foreach (var line in outputList)
                {
                    file.WriteLine(line);
                }
            }
        }

        static void AddOutput(List<string> outputList, string answer)
        {
            outputList.Add(string.Format("Case #{0}: {1}", caseNumber, answer));
            caseNumber++;
        }
        #endregion
    }
}