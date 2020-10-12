/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author salim
 */

import java.util.*;

public class Largest_Range {
    
    /*
      Logic 1: Sort the array and then check if the difference between next & subsequent element is 1. Meanwhile, keep track of the longest length
               Time: O(n log n) | O(1) 
    
      Logic 2: 
             i) Store the array elements in hashmap, with key as element and value as a boolean indicating whether it was explored or not
             ii) If element is not explored-
                 a) Expand left and see how far it can go - this will be the left range  & as you go, mark the element as explored(true), this means that the explored elements already belonged to a range
                 b) Expand right and see how far it can go - this will be the right range & as you go, mark the element as explored(true), this means that the explored elements already belonged to a range
                 c) Keep track of the longest length meanwhile.
             iii) If the element is already explored skip it/
    
             Time: O(n) | Space: O(1) 
    */ 
    
    public int[] largestRange(int[] arr){
    
        if(arr == null)
            return new int[0];
        
        int[] ans = new int[2];
        HashMap<Integer, Boolean> map = new HashMap<>();
        int length = 0;
        
        for(int i=0; i<arr.length; i++)
            map.put(arr[i], false);
        
        for(int i=0; i<arr.length; i++){
            
            if(map.get(arr[i]))
                continue;     // already explored - skip. Also, if its a duplicate number already a part of the range, then it would not be explored. 
                              //Since, the question says - there can only be one largest range and ranges cannot overlap meaning one element from one range cannot overlap into the another range so as to get the largest range.
            
            int left = arr[i] - 1;
            int right = arr[i] + 1;
            
            while(map.containsKey(left)){
                
                map.put(left, true);
                left--;
            }
            
            while(map.containsKey(right)){
                
                map.put(right, true);
                right++;
            }
            
            int new_length = right - left - 1;   //   ans = [0, 7], left = -1, right = 8, so right - left + 1 = 8 - (-1) - 1 = 8
            
            if(new_length > length){
                
                length = new_length;
                ans[0] = left + 1;
                ans[1] = right - 1;
            }
            
        }
        
        return ans;
        
    }
    
    public static void main(String[] args){
        
        int[] arr1 = new int[]{ 1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6};
        int[] arr2 = new int[]{ 1, 1, 1, 3, 4};
        int[] arr3 = new int[]{ 2, 2, 2};
        
        int[] arr1_ans = new int[]{0, 7};
        int[] arr2_ans = new int[]{3, 4};
        int[] arr3_ans = new int[]{2, 2};
        
        Largest_Range obj = new Largest_Range();
        
        System.out.println("arr1: " + Arrays.equals(obj.largestRange(arr1), arr1_ans));
        System.out.println("arr2: " + Arrays.equals(obj.largestRange(arr2), arr2_ans));
        System.out.println("arr3: " + Arrays.equals(obj.largestRange(arr3), arr3_ans));
    }
}
