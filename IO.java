import java.io.*;
import java.util.Scanner;

/**
 * Used to handle IO in the program.
 */
public class IO 
{
    /**
     * handles dealing with the input file.
     */
    public class InputFile
    {
        private Scanner sc; // link to input file
        
        /**
         * Setter method for sc field.
         * @param sc Scanner object for input file that we want to create a pipe to.
         */
        private void setsc(Scanner sc)
        {
            this.sc = sc;
        }

        /**
         * Getter method for sc field.
         * @return returns Scanner object that links to the input file.
         */
        private Scanner getsc()
        {
            return this.sc;
        }

        /**
         * Opens a file. Throws an exception and quite the program if input file
         * cannot be opened.
         * @param inputFileName Name of file to open as input.
         */
        public void openInputFile(String inputFileName)
        {
            try
            {
                File inputFile = new File(inputFileName);    // create input file object
                Scanner sc = new Scanner(inputFile);         // open pipeline to input file
                this.setsc(sc);                             // set sc field
            }catch(Exception e)
            {
                System.out.println("Input file could not be opened. Terminating.");
                System.exit(0);
            }
        }

        /**
         * Checks of there is a next line in the file being read.
         * @return Returns true if there is more to read. Otherwise false.
         */
        public boolean checkForNextLine()
        {
            Scanner sc = this.getsc();
            return sc.hasNextLine();

        }

        /**
         * Get's next line in the file being read.
         * @return Returns the next line.
         */
        public String getNextLine()
        {
            Scanner sc = this.getsc();
            return sc.nextLine();

        }

        /**
         * check if the current line read from the input file is a valid stack command,
         * return an approriate key according to what's found.
         * @param s the current line from the input file to check
         * @return  return keys defined below, otherwise argument of push.
         */
        public int checkCommand(String s)
        {        
            int notACommand = -1;
            int invalidArgument = -2;
            int topCommand = -3;
            int popCommand = -4;

            if(s.startsWith("push(") && s.endsWith(")"))    // check if strings starts with "push(" and ends with ")"
            {
                if(s.length() < 7)                          // check if there's an argument in the push command
                {
                    return invalidArgument;                         // if there's no argument
                }
                
                String argumentAsString = s.substring(5, s.length()-1); // get argument as a string
                try                                                     // try to convert argument to integer
                {
                    if(argumentAsString.startsWith("0") && argumentAsString.length() > 1) // if argument has leading zeros
                    {                                                                     // and it's not JUST 0 (account for
                        throw new NumberFormatException();                                // push(0) case)
                    }
                    int argument = Integer.parseInt(argumentAsString, 10);      // convert to int, base 10
                    if (argument < 0)
                    {
                        throw new NumberFormatException();  // if argument is not a natural number
                    }
                    return argument;                        // if valid push command w/ valid argument
                }
                catch(NumberFormatException e)                         
                {
                    return invalidArgument;                 // if argument isn't an integer                     
                }
            }
            else if(s.equals("top()"))                      // if command is top
            {
                return topCommand;
            }
            else if(s.equals("pop()"))                      // if command is pop
            {
                return popCommand;
            }
            return notACommand;                             // if command isn't push, pop, or top
        }

        /**
         * closes link to input file.
         */
        public void closeInputFile()
        {
            Scanner sc = this.getsc();
            sc.close();
        }
    }

    /**
     * Handles the output file.
     */
    public class OutputFile
    {
        private BufferedWriter bw; // create pipe to output file
        private boolean outputNewlineFlag = false;                // used to determine if a new line is added or not

        /**
         * Setter method for bw field.
         * @param bw Object to point bw field to.
         */
        private void setbw(BufferedWriter bw)
        {
            this.bw = bw;
        }

        /**
         * Setter for outputNewlineFlag
         * @param outputNewlineFlag new flag value.
         */
        private void setOutputNewlineFlag(boolean outputNewlineFlag)
        {
            this.outputNewlineFlag = outputNewlineFlag;
        }

        /**
         * Getter method for bw field.
         * @return  Returns bw field.
         */
        private BufferedWriter getbw()
        {
            return this.bw;
        }

        /**
         * Creates a link to the output file; either it creates it or overwrites an
         * exiting file of the same name as ouputFileName.
         * @param outputFileName Name of the file to create or overwrite.
         */
        public void openOutputFile(String outputFileName)
        {
            try
            {
                File outputFile = new File(outputFileName); // create output file object
                BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile)); // create pipe to output file
                this.setbw(bw); // set bw field
            }catch(Exception e)
            {
                System.out.println("Output file could not be opened. Terminating.");
                System.exit(0);
            }
        }

        /**
         * Writes string output to the output file open.
         * @param output string to write in the output file.
         */
        public void writeToOutputFile(String output)//, boolean addNewLine)
        {
            try
            {
                BufferedWriter bw = this.getbw();
                if(!outputNewlineFlag)                         // check if first time outputting
                {
                    this.setOutputNewlineFlag(true); 
                }
                else                                            // if not, output /n before outputing the rest
                {
                    bw.newLine();                                   
                }
                bw.write(output);
            }catch(Exception e)
            {
                this.closeOuputFile();
                System.out.println("Could not write to output file. Terminating.");
                System.exit(0);
            }
        }

        /**
         * Utility function to appropriately format output string when top is called;
         * if called on empty stack, want to write null to stack.
         * @return returns formatted/converted string to be written to the output.
         */
        public String formatOutputString(int data)
        {
            if(data == -4)  // error key for top and empty stack
            {
                return "null";   // want to output
            }
            else
            {
                return Integer.toString(data);
            }
        }

        /**
         * Closes link to the output file.
         */
        public void closeOuputFile()
        {
            BufferedWriter bw = this.getbw();
            while(true)
            {
                try
                {
                    bw.close();
                    break;
                }catch(Exception e){}
            }
        }
    } 
}
