# CPSC319_ConfusedStack

## Introduction

### Problem Statement

Design and implement a stack in Java, with additional requirements that transfrom it into a "confused" stack. Refer to attached description for more details on these additional requirements.

### Project Objective

The project objectives included:

1. Further understanding of Java and its capabilities.
2. Solidify understanding of linked list and stack data structures.
3. Practice and develop software design and architecture skills.

## Technologies

The technologies used include:

1. Java version 13.0.2 (note this is compatible with versions up to and including 1.8.0_161)

## Launch

To launch the project on your machine, first ensure that you hava a compatible Java compiler installed. 

Then ensure that all the files, namely Node.java, Stack.java, IO.java, and Runner.java, are in the same directory and at the same level - i.e. not below or above in reference to the current working directory. Also, while this is obvious, do not change the names of the source files.

To run the program, follow these steps:

1. Ensure the working directory contains all the source files (Node.java, Stack.java, IO.java, and Runner.java).
2. Execute the command "javac Runner.java".
3. Execute the command "java Runner myinput myoutput". Here, myinput is the name/address of the input file to read from, and myoutput is the name/address of the output file to either create or overwrite for program output. Note that you MUST provide 2 command line arguments, as specified in the command.

To be completely explicit, a set of successful commands to run the program would be:

javac Runner.java
java Runner input.txt output.txt

Upon execution, you can expect the program to appropriately output to the output file, as specified by the assignment handout. If there is any problem creating a connection with the input file (for example, if it doesn't exist) or the output file, an appropriate error will be printed to the terminal and the program will terminate.

## Things to Note

Note the following additional implicit requirements implemented:
1. No blank spaces in the input file are tolerated.
2. No empty lines in the input file are tolerated.
3. Arguments to push such as "01" will be considered an invalid argument - leading zeros are excluded from acceptable push arguments.
4. "Input error" takes priority over "Imput error." That is, a command like "push (12.0)," will terminate on a "Input error," rather than an "Imput error."
5. A test .txt file (test.txt) is included with example input, as well as the corresponding output file, output.txt, which demostrates the correct and expected output from the program.
