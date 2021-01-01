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
1010. Pairs of Songs With Total Durations Divisible by 60


You are given a list of songs where the ith song has a duration of time[i] seconds.

Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.

 

Example 1:

Input: time = [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60
Example 2:

Input: time = [60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 

Constraints:

1 <= time.length <= 6 * 104
1 <= time[i] <= 500


*/

public class Pairs_Of_Songs {

    /*
        Approach 1 : Brute Force
           - take each pair and check for %60, keep count of pairs
           - Time: O(n^2) - space - O(1)
    
        Approach 2: use map[60]
          - Store the numbers whose remainders (ie num % 60) in a map
          - To get the pairs:
            
            1. Cross- pair: 
                Ex: map[1] - 5  means  there are 5 numbers whose remainder is 1
                    map[59] - 3 means  there are 3 numbers whose remainder is 59
                   ie map[1]'s numbers can be paired with map[60 - 1] = map[59] to get total pairs
                So total pairs - 5 * 3 = 15 .  because numbers with remainder 1 + remainder 59 = one whole number whose remainder is 60 that is divisible by 60
            
            2. Self-pair:
                    Ex: a) map[0]- 4 means there are 4 numbers whose remainder is 0. Ex: [60, 120, 180, 240]. How many pairs are possible from 4 numbers it 4C2 => (4 * 3) / 2 = 6 pairs
                            map[0]'s numbers can be paired with map[60 - 0] = map[60] to get total pairs. But since map[60] is not there and it implictly means it will fall in map[0] bucket. Since remainder of 60 is not possible and will be placed in remainder of 0 ie map[0] bucket
    
                        b) map[30]- 3 means there are 3 numbers whose remainder is 30. Ex: [30, 90, 150]. How many pairs are possible from 3 numbers it 3C2 => (3 * 2) / 2 = 3 pairs
                            map[30]'s numbers can be paired with map[60 - 30] = map[30] itself to get total pairs
                - Time: O(N), Space - O(1)
    
    Source: https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/discuss/296138/Java-solution-from-combination-perspective-with-best-explanation
    
    */
    
    public int numPairsDivisibleBy60(int[] time){
        
        if(time == null || time.length == 0)
            return 0;
        
        int pairCount = 0;
        int[] map = new int[60];
        
        for(int t : time){
            
            int remainder = t % 60;
            map[remainder]++;
        }
        
        pairCount+= map[0] * (map[0] - 1) / 2;  // self-pair
        pairCount+= map[30] * (map[30] - 1) / 2;  // self-pair
        
        for(int i = 1; i < 30; i++){
            pairCount+= map[i] * map[60 - i];
        }
        
        return pairCount;
    }
    
    public static void main(String args[]) {
        // TODO code application logic here
        
        Pairs_Of_Songs obj = new Pairs_Of_Songs();
        int[] arr1 = new int[] { 30,20,150,100,40 };
        int[] arr2 = new int[] {60, 60, 60};
        
        System.out.println("arr1: " + (obj.numPairsDivisibleBy60(arr1) == 3));
        System.out.println("arr2: " + (obj.numPairsDivisibleBy60(arr2) == 3));
    }
}
