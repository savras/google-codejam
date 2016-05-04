using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace Template
{
    public enum Numbers
    {
        // Cascade
        ZERO = 0,   // Unique Z
        SIX = 6,    // Unique X
        TWO = 2,    // Unique W        
        EIGHT = 8,  // Unique G
        FOUR = 4,   // Unique U
        SEVEN = 7,  // Having processed the above, unique S
        FIVE = 5,   // ...unique V
        NINE = 9,   // ...unique N 
        THREE = 3,  // ...unique T, H & R
        ONE = 1    // Anything.        
    }

    public class Program
    {        
        static int caseNumber = 1;

        static void Main(string[] args)
        {
            var printList = new List<string>();
            int T;

            const string inputPath = @"GetDigits\A-large-practice.in";
            using (var file = new StreamReader(inputPath))
            {
                T = int.Parse(ReadNextLine(file));

                /*** Code solution here. ***/
                var numberStrings = new List<string>
                {
                    Numbers.ZERO.ToString(),
                    Numbers.SIX.ToString(),
                    Numbers.TWO.ToString(),
                    Numbers.FOUR.ToString(),
                    Numbers.EIGHT.ToString(),
                    Numbers.SEVEN.ToString(),
                    Numbers.FIVE.ToString(),
                    Numbers.NINE.ToString(),
                    Numbers.THREE.ToString(),
                    Numbers.ONE.ToString()
                };

                for (int i = 0; i < T; i++)
                {
                    /*** Tweak input here. ***/
                    var text = ReadNextLine(file);                    

                    var textString = text.ToCharArray().ToList();
                    var sb = new StringBuilder();

                    foreach (var num in numberStrings)
                    {
                        while (true)
                        {
                            if (textString.Count == 0)
                            {
                                break;
                            }

                            var numChars = num.ToCharArray().ToList();
                            string previousTextString = string.Join("", textString);

                            var found = true;
                            foreach(var c in numChars)
                            {
                                if(textString.Contains(c))
                                {
                                    textString.Remove(c);
                                }
                                else
                                {
                                    textString = previousTextString.ToCharArray().ToList();
                                    found = false;
                                    break;
                                }                                
                            }

                            if(!found)
                            {
                                break;
                            }
                            
                            
                            var digit = (int)Enum.Parse(typeof(Numbers), num);                            
                            sb.Append(digit);
                        }                
                    }

                    var result = sb.ToString().ToCharArray().OrderBy(c => c);
                    AddOutput(printList, string.Join("",result).ToString());
                    sb.Clear();
                }                
            }

            WriteToFile(printList);

            Console.WriteLine("Press any key to exit.");
            Console.ReadKey();
        }

        static string ReadNextLine(StreamReader file)
        {
            return file.ReadLine();
        }

        static void WriteToFile(List<string> outputList)
        {
            const string outputPath = @"GetDigits\A-large.out";
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
    }
}