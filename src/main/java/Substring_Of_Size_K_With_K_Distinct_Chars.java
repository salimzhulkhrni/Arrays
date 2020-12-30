/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author salim
 */

/*

Given a string s and an int k, return all unique substrings of s of size k with k distinct characters.

Example 1:

Input: s = "abcabc", k = 3
Output: ["abc", "bca", "cab"]
Example 2:

Input: s = "abacab", k = 3
Output: ["bac", "cab"]
Example 3:

Input: s = "awaglknagawunagwkwagl", k = 4
Output: ["wagl", "aglk", "glkn", "lkna", "knag", "gawu", "awun", "wuna", "unag", "nagw", "agwk", "kwag"]
Explanation: 
Substrings in order are: "wagl", "aglk", "glkn", "lkna", "knag", "gawu", "awun", "wuna", "unag", "nagw", "agwk", "kwag", "wagl" 
"wagl" is repeated twice, but is included in the output once.
Constraints:

The input string consists of only lowercase English letters [a-z]
0 ≤ k ≤ 26

*/

import java.util.*;

public class Substring_Of_Size_K_With_K_Distinct_Chars {

    
    public List<String> substringSizeK(String s, int k){
        
        if(k < 0)
            return new ArrayList<>();
        
        Set<String> set = new HashSet<>();
        int start = 0, end = 0;
        int[] map = new int[26];
        
        for(; end < s.length(); end++){
            
            if(end - start + 1 < k){                // until the window is reached
                
                if(map[s.charAt(end) - 'a'] == 0){   // check if new character can be added
                    
                    map[s.charAt(end) - 'a']++;      // if yes, then add it
                    
                }
                else{
                    
                    end--;                          // new character cannot be added, so shrink the window from start, until new character can be added
                    map[s.charAt(start) - 'a']--;
                    start++;
                }
                
                continue;
            }
            
            // window of size k reached
            if(map[s.charAt(end) - 'a'] == 0){      // check if new character can be added
                
                map[s.charAt(end) - 'a']++;          // add new character
                String soln = s.substring(start, end + 1);  // since we already window wit size k and having unique characters, this substring will be a solution
                set.add(soln);
            }
            else
                end--;                           // cannot add new character, can be re-tried to consider adding this character again after the window has shrunk
            
            // shrink window
            
            map[s.charAt(start) - 'a']--;
            start++;
            
        }
 
        return new ArrayList<>(set);
    }
    
    public static void main(String args[]) {
        // TODO code application logic here
        
        Substring_Of_Size_K_With_K_Distinct_Chars obj = new Substring_Of_Size_K_With_K_Distinct_Chars();
        String s1 = "abcabc"; int k1 = 3;
        String s2 = "abacab"; int k2 = 3;
        String s3 = "awaglknagawunagwkwagl"; int k3 = 4;
        
        List<String> res1 = obj.substringSizeK(s1, k1);
        List<String> res2 = obj.substringSizeK(s2, k2);
        List<String> res3 = obj.substringSizeK(s3, k3);
        
        System.out.println("----List 1 ----");
        for(String s : res1)
            System.out.println("" + s);
        
        System.out.println("----List 2 ----");
        for(String s : res2)
            System.out.println("" + s);
        
        System.out.println("----List 3 ----");
        for(String s : res3)
            System.out.println("" + s);
        
    }
}
