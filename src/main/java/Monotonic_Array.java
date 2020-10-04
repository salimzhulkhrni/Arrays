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

896. Monotonic Array 

An array is monotonic if it is either monotone increasing or monotone decreasing.

An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone decreasing if for all i <= j, A[i] >= A[j].

Return true if and only if the given array A is monotonic.

Example 1:

Input: [1,2,2,3]
Output: true
Example 2:

Input: [6,5,4,4]
Output: true
Example 3:

Input: [1,3,2]
Output: false
Example 4:

Input: [1,2,4,5]
Output: true
Example 5:

Input: [1,1,1]
Output: true
 

Note:

1 <= A.length <= 50000
-100000 <= A[i] <= 100000

*/
public class Monotonic_Array {
    
    /*
        1. Detemrine the direction
        2. Check if the subsequent numbers follow the direction
    
    Time: O(n)
    Space: O(1)
    */
    public boolean isMonotonic_Solution_1(int[] arr){
        
        if(arr == null)
            return false;
        
        if(arr.length <= 2)
            return true;
        
        int direction = 0;    // 0 = yet to determine the direction
                              // 1 = increasing (non-decreasing)
                              // 2 = decreasing(non-increasing)
                              
        for(int i=0; i<arr.length - 1; i++){
            
            if(direction == 0){
            
                if(arr[i] < arr[i + 1])  // increasing
                    direction = 1;  
                
                else if(arr[i] > arr[i + 1]) // decreasing
                    direction = 2;
                
                // value are equal, do nothing - continue with the loop
            }
            
            else if(direction == 1 && arr[i] > arr[i + 1])   // direction is increasing, check if direction is violated by decraeasing
                return false;
        
            else if(direction == 2 && arr[i] < arr[i + 1])  // direction is decreasing, check if direction is violated by increasing
                return false;
        }
        
        return true;
        
    }
    
    /*
       1. At a time, an array can be either non-increasing(decreasing) or non-decreasing(increasing)
       2. If its both, then its not a monotonic array
       3. Logic: For every two elements comparison, check for non-decreasing or non-increasing
                 1. If the array is non-increasing(decreasing), only non-decreasing will be set to false.
                 2. If the array is non-decreasing(increasing), only non-increasing will be set to false.
                 3. a) If is "both" non-increasing and non-decreasing, then 'OR' of both, non-increasing & non-decreasing will be false.
                    b) If is "either" non-increasing "or" non-decreasing, then 'OR' of both, non-increasing & non-decreasing will be true.
                    c) If is "neither" non-increasing "nor" "non-decreasing(ie. all elements are equal), then 'OR' of both, non-increasing & non-decreasing will be true.
    */
    
    public boolean isMonotonic_Solution_2(int[] arr){
    
        if(arr == null)
            return false;
        
        if(arr.length <= 2)
            return true;
        
        
        boolean nonIncreasing = true; // decreasing - going down
        boolean nonDecreasing = true; // increasing - going up
        
        for(int i=0; i<arr.length - 1; i++){
            
            if(arr[i] < arr[i + 1]) // going up; increasing
                nonIncreasing = false;
            
            if(arr[i] > arr[i + 1]) // going down; decreasing
                nonDecreasing = false;
                
        }
        
        return nonIncreasing || nonDecreasing;
    
    }
    
    
    public static void main(String[] args){
    
        int[] arr1= new int[] {1, 2, 2, 3};
        int[] arr2 = new int[]{6, 5, 4, 4};
        int[] arr3 = new int[]{1, 3, 2};
        int[] arr4 = new int[]{1, 2, 4, 5};
        int[] arr5 = new int[]{1, 1, 1};
        
        Monotonic_Array obj = new Monotonic_Array();
        
        System.out.println("sln1 - arr1: " + (obj.isMonotonic_Solution_1(arr1) == true));
        System.out.println("sln1 - arr2: " + (obj.isMonotonic_Solution_1(arr2) == true));
        System.out.println("sln1 - arr3: " + (obj.isMonotonic_Solution_1(arr3) == false));
        System.out.println("sln1 - arr4: " + (obj.isMonotonic_Solution_1(arr4) == true));
        System.out.println("sln1 - arr5: " + (obj.isMonotonic_Solution_1(arr5) == true));
        
        
        System.out.println("sln2 - arr1: " + (obj.isMonotonic_Solution_2(arr1) == true));
        System.out.println("sln2 - arr2: " + (obj.isMonotonic_Solution_2(arr2) == true));
        System.out.println("sln2 - arr3: " + (obj.isMonotonic_Solution_2(arr3) == false));
        System.out.println("sln2 - arr4: " + (obj.isMonotonic_Solution_2(arr4) == true));
        System.out.println("sln2 - arr5: " + (obj.isMonotonic_Solution_2(arr5) == true));
    }
    
    
}
