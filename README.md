# TopWordFrequency Program - Shared State Style

## Overview
--------
The `Five` program is a shared-state Java application that:

- Reads a text file character-by-character into memory.
- Normalizes and cleans the input by removing non-alphanumeric characters and converting to lowercase.
- Splits the text into words.
- Removes stop words (from file and single-letter words).
- Tracks word frequencies using a list of [word, count] pairs.
- Sorts and displays the top 25 most frequent words.

Unlike the pipeline version, this implementation uses shared global variables to store data between processing steps, and all transformations operate on these shared structures.

## Constraints

- No long jumps.
- Complexity of control flow tamed by dividing the large problem into
smaller units using procedural abstraction. Procedures are pieces of functionality that may take input, but that don’t necessarily produce output that is relevant for the problem.
- Procedures may share state in the form of global variables.
- The larger problem is solved by applying the procedures, one after the
other, that change, or add to, the shared state.

## Project Folder Structure
------------------------
Place the following files in the same folder:

- Five.java             // The shared-state Java program
- test.txt              // Input text file for analysis
- stopwords.txt         // Comma-separated stop word list (e.g., "a,an,the,and,...")

## How to Compile and Run
-----------------------

1. Open Command Prompt or Terminal

2. Navigate to the source folder containing the Java and text files:

   Example:
   cd path\to\your\folder

3. Compile the Java program:
   javac Five.java

4. Run the program:
   java Five dummy test.txt

   (Note: The first argument is ignored; the second argument is the actual input file name.)

   Example:
   java Five x pride-prejudice.txt

## Expected Output
---------------
Top 25 most frequent non-stop words in the input file, printed in descending order:

Example:
elizabeth - 123  
darcy - 97  
love - 82  
time - 76  
...

## Notes
-----
- This implementation uses shared `static` lists for each processing stage:
    - `data`: List of characters from the input file
    - `words`: List of words after tokenization
    - `word_freq`: List of [word, count] pairs
- Data flows sequentially through a fixed order of operations, each updating the shared state.
- Stop words are loaded from `stopwords.txt`, and single-character words (a-z) are removed by default.
- Sorting is handled using `Collections.sort` with a custom comparator for frequency.

This structure makes it easy to trace how data evolves through each processing stage while maintaining all variables at the class level.

