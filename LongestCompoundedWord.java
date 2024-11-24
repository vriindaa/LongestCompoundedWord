import java.util.*;
import java.io.*;

public class LongestCompoundedWord{
    public static void main(String[] args) throws IOException{ //O(n*m^2)  (m=wordLength)

        //Taking input from file
        ArrayList<String> words = takeInput("Input_02.txt"); //O(n)

        //Finding Longest and Second Longest Compounded Word
        Long startTime = System.currentTimeMillis();
        ArrayList<String> ans = findLongestCompoundedWord(words); //O(n*m^2)
        Long endTime = System.currentTimeMillis();

        //logging out result
        System.out.println("Longest Compunded Word : " + ans.get(0));
        System.out.println("Second Longest Compunded Word : " + ans.get(1));
        System.out.println("Time Taken to process the input file : " + (endTime - startTime) + " ms");

    }

    public static ArrayList<String> takeInput(String fileName) throws IOException {//O(n)
        ArrayList<String> words = new ArrayList<>(); 

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        String line = reader.readLine();
        while(line!=null){
            words.add(line.trim());
            line = reader.readLine();
        }
        reader.close();

        return words;

    }

    public static ArrayList<String> findLongestCompoundedWord(ArrayList<String> words){//O(n*m^2)
        
        //Storing all unique words in a hashset
        HashSet<String> set = new HashSet<>(words);

        //sorting words from longest length to shortest length
        Collections.sort(words, (a,b) -> b.length()-a.length()); //O(nlogn)

        //checking if a word is compounded
        ArrayList<String> ans = new ArrayList<>();
        String longest  = "", secLongest = "";

        for(String word : words){ //O(n)
            set.remove(word);

            if(isCompoundedHelper(word, set)){ //O(m^2)
               if(longest.length()==0){
                 longest = word;
               }else if(secLongest.length()==0){
                 secLongest = word;
                 break;
               }
            }

            set.add(word);
        }
        
        ans.add(longest);
        ans.add(secLongest);

        return ans;
    }
    
    /* OPTIMIZED APPROACH */
    public static boolean isCompoundedHelper(String word, HashSet<String> set){//O(m^2)
        int n = word.length();
        boolean dp[] = new boolean[n+1];

        //base case
        dp[0] = true; //empty string is a valid prefix

        //performing tabulation 
        for(int i=1; i<=n; i++){ //O(m^2)
            for(int j=0; j<i; j++){

                if(dp[j] && set.contains(word.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
    
    
    /*  BRUTE FORCE APPROACH */

    // public static boolean isCompoundedHelper(String  word, HashSet<String> set){ //O(2^m)
    //     if(set.contains(word)){
    //         return true;
    //     }
        
    //     for(int i=0; i<word.length(); i++){
    //         String prefix = word.substring(0, i);
    //         String suffix = word.substring(i);

    //         if(set.contains(prefix) && isCompoundedHelper(suffix, set)){
    //             return true;
    //         }
    //     }

    //     return false;
    // }
    

}