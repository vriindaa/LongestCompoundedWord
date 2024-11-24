# Steps to Execute the Java Code:
1. Copy and Paste the Code:
   Copy the provided Java code into any standard Java code editor or IDE.

2. Input File Setup:
   Ensure the input file is located in the same directory as the .java file.
   If the input file is stored elsewhere, provide the absolute file path as an argument in the takeInput method.

3. Compile and run the Code:
   
   1. If using a terminal:
      Open a terminal/command prompt, navigate to the directory containing the .java file, and compile it using the following command:
      javac LongestCompoundedWord.java

      After successful compilation, execute the program with:
      java LongestCompoundedWord

   2. If using an IDE:
      Click the Run button in the IDE toolbar.

# Design of the Code:

1. main Method:
   * Coordinates the execution of the program:
   * Reads the input file using takeInput.
   * Finds the longest and second-longest compounded words using findLongestCompoundedWord.
   * Logs the results along with the time taken to process the input file.
   * Complexity: Depends on the helper methods called; overall O(n * m^2) (explained below).

2. takeInput Method:
   * Reads words from a file and stores them in an ArrayList of Strings.
   * Uses a BufferedReader to read the file line by line.
   * Trims whitespace and adds each word to the list.
   * Complexity: O(n), where n is the number of words in the file.
   
3. findLongestCompoundedWord Method:
   * Identifies the longest and second-longest compounded words in the input list.
   * Stores the list of words into a HashSet of Strings for fast lookups.
   * Sorts the words in the ArrayList in descending order of length so that there is no need to evaluate rest of the words.
   * Iterates through the sorted list.
   * Removes the current word from the set to avoid using it itself.
   * Checks if the word is compounded using isCompoundedHelper.
   * If compounded, updates the longest and second-longest results.
   * Adds the word back to the set for further checks.
   * Stops when both longest and second-longest compounded words are found.
   * Complexity: Sorting: O(n log n), Word processing: O(n * m^2), where m is the average word length. Total: O(n * m^2).

4. isCompoundedHelper Method:
   Checks if a word can be split into two or more valid smaller words from the set. Its approach:
    
   # 1. Initial Brute Force Approach (Exponential Time Complexity) (commented out, not used)
  
   Initially, I used a brute force approach to check if each word could be formed by concatenating other words in the list. 
   For every word of length m, a prefix and suffix is generated iteratively. Now there are two main possibilites : Either the prefix is valid and the suffix is checked recursively or, the prefix is invalid and 
   the loop moves to next iteration.

   Specifically, for a word of length m,  the function has to check every possible substring of the word, which results in 2^m possible partitions because each character has two options: either part of the prefix 
   or suffix, and each substring call leads to more recursive checks for all remaining substrings, where the number of branches grows exponentially.
   As a result, the time complexity of this brute force solution is O(2^m).

   # 2. Optimized Approach (Dynamic Programming) (O(m^2) Time Complexity)
   
   To improve time complexity, I started finding alternate ways and realised that dynamic programming can be efficiently applied here because it breaks the problem into smaller subproblems ans store their results 
   avoiding repeated checks. For example, to check if a word of length m is compounded, we first check if the prefix of length m-1 is compounded. Similarly, for a prefix of length m-1, we check if the prefix of 
   length m-2 is compounded, and so on. This process continues until we reach the smallest subproblem, a prefix of length 0, which is the base case, and an empty string is always valid.

   To implement this efficiently, I used a dp array where dp[i] represents whether the word till length i can be split into valid words. We start by marking dp[0] as true because an empty string is always valid. 
   Then, for each position i from 1 to the length of the word m, we check all possible splits of the word at position j (where 0 ≤ j < i):
   If the prefix ending at j is compounded (dp[j] == true), and
   The substring from j to i exists in the set of valid words, we set dp[i] to true and stop checking further for that position.
   The time complexity of this method for a single word is O(m^2).

   By the end, dp[m]  tells us if the entire word is compounded. 
   This dynamic programming approach avoids repeating checks for the same subproblems and ensures an efficient solution compared to the brute force method.

# Overall Time Complexity : O(n*m^2) as total n words are processed and each word is processed in O(m^2) time.
