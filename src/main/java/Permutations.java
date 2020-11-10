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

public class Permutations {

    /**
     * @param args the command line arguments
     */
    
    public List<String> permutationString(String str){
    
        if(str == null)
            return new ArrayList<>();
        
        List<String> res = new ArrayList<>();
        
        permute(str, res, "");
        
        return res;
        
    }
    
    private void permute(String s, List<String> list, String stringSoFar){
        
        
        
        if(s.length() == 0){
            
            list.add(stringSoFar);
            return;
        }
        
        // abc => extrat a, get leftpart = "", right part = bc, full string =  left part + right part = bc, generate permuations for 'bc'. Each time when given a call, extracted character will be attached to the string in the next recursive call.
        // 
        
        
        
        for(int i = 0; i < s.length(); i++){
            
            char ch = s.charAt(i);  //   extract 'b'
            String leftPart = s.substring(0, i);    // get left part = a
            String rightPart = s.substring(i + 1);  // get right part = c
            String restOfString = leftPart + rightPart; // recurse new string = ac
            
            permute(restOfString, list, stringSoFar + ch); // add the extracted char to the string before calling the recursive function for rest of the string
            
        }
        
    }
    
    /*
        1. Fix a number at one position by swapping 1,2,3,... n-1 positions
        2. After fixing the start position, permute the start + 1 positions
        3. Unswap the original swapping that was done during the fix-start action so that we can fix other number(other option) in the start position.
    
        Time: O(n * n!) => N! to generate the permuations. At each of the N! permuted string, we print that string that takes O(N) time
        Space: O(n!) => List to store n! permutated strings.

    */
    
    public List<List<Integer>> permuteArray(int[] nums){
        
        if(nums == null)
            return new ArrayList<>();
        
        List<List<Integer>> res = new ArrayList<>();
        permuteArr(nums, 0, res);
        
        return res;
    }
    
    private void permuteArr(int[] nums, int start, List<List<Integer>> res){
        
        if(start >= nums.length){
            
            List<Integer> soln = new ArrayList<>();
            for(int num : nums)
                soln.add(num);
            
            res.add(soln);
            
            return;
        }
        
        // we try to fix one start position. Ex: [1, 2]. At the 0th position, we try to fix 1 and then permute for the rest of the array. 
        // Then we explore other option of fixing 2 at 0th postion, then get permutations for the rest of the array
        
        for(int i = start; i < nums.length; i++){     
            
            swap(nums, i, start);   // start denotes the position where we we want to fix the elements and get permutation for the rest of the elements. [1, 2]. start = 0th index, swap with i = 0th index. For the next option, start = 0th index, swap with i = 1th index such that it becomes [2,1] 
            permuteArr(nums, start + 1, res); // once start is fixed, do the permuation for the rest of the array. So that we will get - [2] followed by rest of combinations of array starting with 2.
            swap(nums, i, start); // once we come back, we back track and undo the changes, since the original array is changed. First: [1, 2], Second: [2, 1]. 
                                  // Lets say we have [1, 2, 3]. After the last option we would have had the array as - [1, 3, 2]. To get the second set of permuations starting with [2]. We go back to original array by back tracking the swapping we did(from [1][3, 2] to [1][2, 3]), so that we get the original array - [1, 2, 3] and the next option to explore having [2] as fixed start so that we get all permutations starting with 2 after swapping 0th & 1th index. So this becomes : [2] permute the rest - [1, 3]
        }
        
        
        /* See below for the swapping chages
            option# 1 : fix 1 in first position - [1, 2, 3] --> original array
             
            swap -> [1] -> [1] => [1, 2, 3]
            rest permute: [2, 3] : gives [2, 3] & [3, 2] (internal recursive that unswaps to [2, 3]). At this point after swaps in internal recursive call,s  array will be => [1, 2, 3]
            once done with current option, unswap(backrack) => ([1] <=> [1]) => [1, 2, 3] --> original array from [1, 2, 3] 
        
            option# 2 : fix 2 in first position - [1, 2, 3] --> original array
             
            swap -> [1] -> [2] => [2, 1, 3]
            rest permute: [1, 3] : gives [1, 3] & [3, 1] (internal recursive that unswaps to [1, 3]). At this point after swaps in internal recursive call,s  array will be => [2, 1, 3]
            once done with current option, unswap(backrack) => ([1] <=> [2]) => [1, 2, 3] --> original array from [2, 1, 3]
        
            option# 3 : fix 3 in first position - [1, 2, 3] --> original array
             
            swap -> [1] -> [3] => [3, 2, 1]
            rest permute: [2, 1] : gives [2, 1] & [1, 2] (internal recursive that unswaps to [2, 1]). At this point after swaps in internal recursive call,s  array will be => [3, 2, 1]
            once done with current option, unswap(backrack) => ([1] <=> [3]) => [1, 2, 3] --> original array from [3,2,1]
            
        
        */
        
            
    }
    
    private void swap(int[] nums, int i, int j){
    
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        
    }
    
    
    
    public static void main(String args[]) {
        // TODO code application logic here
        
        Permutations obj = new Permutations();
        
        String s1 = "abc";
        
        
        List<String> res = obj.permutationString(s1);
        
        System.out.println("Total Permutations: " + res.size());
        
        for(String str : res){
            
            System.out.println("" + str);
        }
        
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> res1 = obj.permuteArray(nums);
        
        for(List<Integer> list : res1 ){
            
            for(int num : list)
                System.out.print("" + num);
            
            System.out.println("");
        }
        
    }
}
