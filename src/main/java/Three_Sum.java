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

15. 3Sum

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Notice that the solution set must not contain duplicate triplets.

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Example 2:

Input: nums = []
Output: []
Example 3:

Input: nums = [0]
Output: []



*/

import java.util.*;

public class Three_Sum {

    
    /*
    
    1. Approach 1: Brute -force
       - Take each and every number through 3 inner loops, check if their sum equals 0
    Time: O(n ^ 3) | Space: O(1)
    
    2. Approach 2: use sort
    
      1. Sort the numbers
      2. Keeping the first number intact, set the  
        -left pointer = first number + 1  (second number )
        - right pointer = last number     (third number)
      3.Move left & right pointer inwards
      - If sum > 0 
         - move the right by -1
      - If sum < 0
         - move left by + 1
      - if sum = 0,
         - we got those three numbers
      4. Remove duplicates by running a while loop - checking if we are taking the same number for each number, if not we will end up with the same set of triplets again
    
     Time: O(n ^ 2) | Space: O(1)
     Despite sorting being n log n, we have to set the first number in outer loop & traverse the left & right through the other loop
    
    */
    
    
  public List<List<Integer>> threeSum(int[] nums) {
      
      if(nums == null)
          return new ArrayList<>();
      
      List< List<Integer> > res = new ArrayList<>();
      
      Arrays.sort(nums);
      
      for(int i = 0; i < nums.length - 2; i++){
          
          int left = i + 1;
          int right = nums.length - 1;
          
          while(left < right){
              
              int sum = nums[left] + nums[right] + nums[i];
             
              
              if(sum < 0)
                  left++;
              
              else if(sum > 0)
                  right--;
              
              else{
                  
                  List<Integer> ans = new ArrayList<>();
                  
                  ans.add(nums[i]);
                  ans.add(nums[left]);
                  ans.add(nums[right]);
                  
                  res.add(ans);
                  
                  left++;       // why? nums[i] + (nums[left] + nums[right]) == 0, only one value of nums[left] + nums[right], this will be equal to 0. 
                  right--;      // If we move, either left++ or right--, sum can be > 0 or < 0, if  its again == 0, then we are having duplicate of nums[left], nums[right] again. To remove duplicates, see below.
                  
                  while(left < right && nums[left] == ans.get(1))    // remove duplicates. ex: first number -2   left: 0   right 2 , again prevent setting left as 0, if the numbers are repeated - -2 - 2 -2 0(left) 0(do not set again) 0 1(set to left in the next iteration) 1 1 2 2 2
                      left++;
                  
                  while(left < right && nums[right] == ans.get(2))
                      right--;
                  
              }
              
              
          }
          
          // prevent setting first number again to the same number that was set in the previous result. 
          // 1st iteration : Ex: -2(first number) - 2(first number) -2 0(left) 0 1 1 1 2 2 2(right)
          // second iteration: do not set, first number to -2 again, instead set it to 0 (the next unique number)
          
          while(i < nums.length - 1 && nums[i] == nums[ i + 1])
              i++;
          
      }
      
      return res;
  }
    
    
    public static void main(String args[]) {
        // TODO code application logic here
        
        Three_Sum obj = new Three_Sum();
        
        int[] arr1 = new int[]{-1,0,1,2,-1,-4};
        
        List<List<Integer>> ans1 = new ArrayList<>();
        List<Integer> temp1 = new ArrayList<>();
        temp1.add(-1);
        temp1.add(-1);
        temp1.add(2);
        
        List<Integer> temp2 = new ArrayList<>();
        temp2.add(-1);
        temp2.add(0);
        temp2.add(1);
        
        ans1.add(temp1);
        ans1.add(temp2);
        
        List<List<Integer>> res = obj.threeSum(arr1);
        
        boolean finalResult = true;
        
        for(int i = 0; i < ans1.size(); i++){
                
            finalResult = finalResult && (ans1.get(i).equals(res.get(i)));
        }
        
        System.out.println("final Result- matches: " + finalResult);
    }
}
