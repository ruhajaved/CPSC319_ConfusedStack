/**
 * @author  Ruha Javed
 * @version 1.7
 * @since   1.0
 */

 /**
  * Class used to run program, houses point of entry of program.
  */
 public class Runner
 {
     /**
      * Main function.
      * @param args arg[0] is the input file name and arg[1] is the name 
      of the output file to create or overwrite if it already exists.
      */
    public static void main (String[] args)
    {
        String inputFileName = args[0];                         // input file name

        IO pipe = new IO();                                     // create IO object

        IO.InputFile inputFile = pipe.new InputFile();          // create input file object
        inputFile.openInputFile(inputFileName);                 // open pipeline to input file

        String outputFileName = args[1];                        // output file name
        IO.OutputFile outputFile = pipe.new OutputFile();       // create output file object
        outputFile.openOutputFile(outputFileName);              // create pipe to output file

        Stack stack = new Stack();                              // create empty stack

        while(inputFile.checkForNextLine())                     // go through the input file, line by line
        {
            String s = inputFile.getNextLine();                 // get command from input file

            int key = inputFile.checkCommand(s);                // key from checkCommand to tell us what to do
            switch(key)                 
            {
                case -1:                                        // if command is not push, pop, or top
                {
                    outputErrorTerminate("Input error.", inputFile, outputFile); // write error to output and terminate
                }
                case -2:                                        // if push argument isn't natural #
                {
                    outputErrorTerminate("Imput error.", inputFile, outputFile); // write error to output and terminate
                }
                case -3:                                        // if a normal top call
                {
                    int data = stack.top();                                 // get data
                    if(data == 7)                                           // special case of top: if top is 7
                    {
                        stack.pop();                                                // pop, don't output
                    }
                    else                                                    // normal top operation
                    {
                        if(data == 666)                                     // special case of top: if top is 666
                        {
                        data = 999;
                        }
                        else if(data == 319)                                // special case of top: if top is 319
                        {
                            data = 666;
                        }
                        writeToOutput(data, outputFile);                  // write to output
                    }
                    break;
                }
                case -4:                                        // if a normal pop call - need to output the data we get
                {
                    if(stack.top() == 666)                                  // special case of pop: if top is 666
                    {
                        int data = stack.pop();                                     // get data
                        writeToOutput(data, outputFile);                 // write to output
                        stack.pop();                                                // pop next element if it exists, w/out outputing
                    }
                    else if(stack.top() == 7)                               // special case of pop: if top is 7
                    {
                        int data = 7;                                               // set data
                        writeToOutput(data, outputFile);                 // write to output
                    }
                    else if(stack.top() == 42)                              // special case of pop: if top is 42
                    {
                        int data = stack.pop();
                        writeToOutput(data, outputFile);                 // write 42 to output
                        while(!stack.isEmpty())                                     //empty stack, without outputting
                        {
                            stack.pop();
                        }
                    }
                    else
                    {
                        int data = stack.pop();                                     // get data
                        if(data == -5)                                              // pop done on empty stack
                        {
                            outputErrorTerminate("Error", inputFile, outputFile);   // write error to output and terminate
                        }
                        writeToOutput(data, outputFile);                 // write to output
                    }
                    break;  
                }
                default:                                        // if a normal push call, make default, because key can
                {                                               // can be any random natural number
                    if(key == 0 && !stack.isEmpty())                         // special case: push(0) and empty stack
                    {                                                        // if we push 0, but stack isn't empty, do nothing
                    }   
                    else if(key == 666)                                      // special case: push(666), push 666 3x times
                    {
                        stack.push(key);
                        stack.push(key);
                        stack.push(key);
                    } 
                    else if(key == 3)                                       // special case: push(3), push 7 instead
                    {
                        key = 7;
                        stack.push(key);
                    }
                    else if(key == 13)                                      // special case: push(13), empty, then push 13
                    {
                        while(!stack.isEmpty())                                         //empty stack
                        {
                            int data = stack.pop();
                            writeToOutput(data, outputFile);                 // write to output
                        }
                        stack.push(key);                                                //push 13
                    }
                    else
                    {
                        stack.push(key);
                    }
                }
            }
        }
        inputFile.closeInputFile();                             // close input file link
        outputFile.closeOuputFile();                            // close output file link
    }

    /**
     * Utility function used to write output to the output file.
     * @param data data to write to the output fule.
     * @param outputFile pipe to the output file.
     */
    public static void writeToOutput(int data, IO.OutputFile outputFile)
    {
        String output = outputFile.formatOutputString(data);    // format string to write to output file
        outputFile.writeToOutputFile(output);                   // write to output
    }

    /**
     * Used to write error message to output file and terminate program.
     * @param error error message to output.
     * @param inputFile link to input file - used to close.
     * @param outputFile link to output file - used to close.
     */
    public static void outputErrorTerminate(String error, IO.InputFile inputFile, IO.OutputFile outputFile)
    {
        outputFile.writeToOutputFile(error);                    // write error to output
        inputFile.closeInputFile();                             // close input file link
        outputFile.closeOuputFile();                            // close output file link
        System.exit(0);                                         // terminate
    }
 }