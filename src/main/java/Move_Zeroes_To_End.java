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

283. Move Zeroes

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.


*/

public class Move_Zeroes_To_End {
    
    
    /*
    1. Two pointer 
    
        - read = keeping reading until you find a non-zero
        - anchor = drop the anchor when you find a zero
        - swap read & anchor so that anchor holding 0 goes to the place where read is holding a non-zero
           - Ensure that read > anchor, because for example like : [1, 0] that is already correct - anchor = 0, read = 1, swapping becomes [0, 1] which is incorrect, so ensuring read > anchor checks this
    
    
    */
    
    public void moveZeroes(int[] nums) {
        
        if(nums == null || nums.length < 2)
            return;
        
        int read = 0, anchor = 0;
        
        while( read < nums.length){
            
            while(anchor < nums.length && nums[anchor] != 0)
                anchor++;
            
            while(read < nums.length && nums[read] == 0)
                read++;
            
            if(read < anchor)
                read = anchor + 1;
            
            else if(read < nums.length && anchor < nums.length)
                swap(nums, anchor, read);
            
            
        }
        
    }
    
    private void swap(int[] nums, int i, int j){
        
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public void print(int[] arr){
        
        for(int i=0; i<arr.length; i++){
            
            System.out.print("" + arr[i] + " ");
            
        }
        
        System.out.println("");
    }
    
    
    public static void main(String[] args){
    
        int[] arr1 = new int[]{0, 1, 0, 3, 2};
        int[] arr2 = new int[]{1, 0};
        
        Move_Zeroes_To_End obj = new Move_Zeroes_To_End();
        obj.moveZeroes(arr1);
        obj.moveZeroes(arr2);
        
        obj.print(arr1);
        obj.print(arr2);
        
        
    }
}
