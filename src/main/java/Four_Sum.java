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

18. 4Sum


Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Notice that the solution set must not contain duplicate quadruplets.

 

Example 1:

Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
Example 2:

Input: nums = [], target = 0
Output: []


*/

import java.util.*;

public class Four_Sum {

    /*
    
    1. Approach 1: Brute -force
       - Take each and every number through 4 inner loops, check if their sum equals 0
    Time: O(n ^ 4) | Space: O(1)
    
    2. Approach 2: use sort
    
      1. Sort the numbers
      2. Keeping the first number & second number(ie. set to first number + 1) intact, set the  
        -left pointer = secondnummber + 1  (third number )
        - right pointer = last number     (fourth number)
      3.Move left & right pointer inwards
      - If sum > 0 
         - move the right by -1
      - If sum < 0
         - move left by + 1
      - if sum = 0,
         - we got those three numbers
      4. Remove duplicates by running a while loop - checking if we are taking the same number for each number, if not we will end up with the same set of quadruplets again
    
     Time: O(n ^ 3) | Space: O(1)
     Despite sorting being n log n, we have to set the first number in outer loop, second number in another inner loop & traverse the left & right through the other loop
    
    */
    
    public List<List<Integer>> fourSum(int[] nums, int target) {
        
        if(nums == null)
            return new ArrayList<>();
        
        List<List<Integer>> res = new ArrayList<>();
        
        Arrays.sort(nums);
        
        for(int i = 0; i < nums.length - 3; i++){
            
            for(int j = i + 1; j < nums.length - 2; j ++){
                
                int left = j + 1;
                int right = nums.length - 1;
                
                while(left < right){
                    
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    
                    if(sum == target){
                        
                        List<Integer> ans = new ArrayList<>();
                        
                        ans.add(nums[i]);
                        ans.add(nums[j]);
                        ans.add(nums[left]);
                        ans.add(nums[right]);
                        
                        res.add(ans);
                        
                        left++;   // why? nums[i] + nums[j] + (nums[left] + nums[right]) == 0, only one value of nums[left] + nums[right], this will be equal to 0. 
                        right--;  // If we move, either left++ or right--, sum can be > 0 or < 0, if  its again == 0, then we are having duplicate of nums[left], nums[right] again. To remove duplicates, see below.
                        
                        while(left < right && nums[left] == ans.get(2))   // remove duplicates for nums[left]
                            left++;
                        
                        while(left < right && nums[right] == ans.get(3)) // remove duplicates for nums[right]
                            right--;
                        
                    }
                    
                    else if(sum > target)
                        right--;
                    
                    else
                        left++;
                }
                
                // remove duplicates from the second number to prevent getting the same quadruplets
                
                
                while(j < nums.length - 1 && nums[j] == nums[j + 1])
                    j++;
                
                
            }
            
            // remove duplicates from the first number to prevent getting the same quadruplets
            
            while(i < nums.length -1 && nums[i] == nums[i + 1])
                i++;
            
        }
        
        return res;
        
    }
    
    public static void main(String args[]) {
        // TODO code application logic here
        
        Four_Sum obj = new Four_Sum();
        
        int[] arr1 = new int[]{1,0,-1,0,-2,2};
        int target = 0;
        
        List<List<Integer>> ans1 = new ArrayList<>();
        List<Integer> temp1 = new ArrayList<>();
        temp1.add(-2);
        temp1.add(-1);
        temp1.add(1);
        temp1.add(2);
        
        List<Integer> temp2 = new ArrayList<>();
        temp2.add(-2);
        temp2.add(0);
        temp2.add(0);
        temp2.add(2);
        
        List<Integer> temp3 = new ArrayList<>();
        temp3.add(-1);
        temp3.add(0);
        temp3.add(0);
        temp3.add(1);
        
        ans1.add(temp1);
        ans1.add(temp2);
        ans1.add(temp3);
        
        List<List<Integer>> res = obj.fourSum(arr1, target);
        
        boolean finalRes = true;
        
        for(int i = 0; i < ans1.size(); i++){
            
            finalRes = finalRes && (ans1.get(i).equals(res.get(i)));
        }
        
        System.out.println("final Result- matches: " + finalRes);
    }
}
