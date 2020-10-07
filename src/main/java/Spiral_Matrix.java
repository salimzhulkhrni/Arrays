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

54. Spiral Matrix


Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

*/

import java.util.*;

public class Spiral_Matrix {

    /**
     * @param args the command line arguments
     */
    
    /*
    Time Complexity: O(N) - N = Number of elements in the matrix (n*N)
    Space Comlexity: O(N) - N = Number of elements in the matrix (n*N)
    */
    
    public List<Integer> spiralOrder(int[][] matrix) {
        
        if(matrix == null || matrix.length == 0)
            return new ArrayList<>();
        
        int startRow = 0, endRow = matrix.length - 1;
        int startColumn = 0, endColumn = matrix[0].length - 1;
        
        List<Integer> list = new ArrayList<>();
        
        while(startRow <= endRow && startColumn <= endColumn ){
            
            //traverse right (boundary)
            
            // How? row = constant(basically we are trying to include all values from startRow) & column keep changing during iteration to include the elements
            
            for(int i=startColumn; i<=endColumn; i++){
                
                list.add(matrix[startRow][i]);
            }
            
            startRow++;  // why? because we have to move inwards, so next time we will not include this row(from startRow) whch was already included above to avoid duplicates
            
            // traverse down (boundary)
            
            // how? column = constant(basically we are trying to include all values from endColumn) & row keep changing during iteration to include the elements 
            
            for(int i=startRow; i<=endRow; i++){
                
                list.add(matrix[i][endColumn]);
            }
            
            endColumn--; // why? because we have to move inwards, so next time we will not include this column(from endColumn) whch was already included above to avoid duplicates
            
            
            if(startRow > endRow || startColumn > endColumn)
                break;           // why is this required?
                                 /*
                                  1. the while condition is startRow <= endRow && startColumn <= endColumn; while moving inward we do startRow++ & endColumn-- from above two for loops, so recheck is required
                                  2. At any point,
                                     a) If startRow > endRow, it means that row which endRow points to, need not be considered again since it was already included when startRow was moved to 1 ie on doing startRow++ (on traversing down)
                                        Ex: [ [4, 5, 6]]
                                          it spirals towards right - printing 4, 5, 6
                                          startRow initially pointing to [0] will now point to [1](after moving downwwards by startRow++). Endrow will continue pointing to [0].
                                          At this point, startRow > EndRow but startColumn <= endColumn will stil hold true
                                         "If the condition is not present" - then 3rd loop will execute and print [5, 4] again as duplicates since the loop condition endColumn >= startColumn still holds true, whihc is wrong. Also, 4th loop will not execute because loop condition of endRow >=  startRow will not hold true.
                                          So output will be - [4, 5, 6, 5, 4]
                                          
            
                                     b) If startColumn > endColumn, it means that column which startColumn points to, need be considered again since it was already included when endColumn was moved to 1 ie on doing endColumn--(ie on traversing left)
                                        Ex:   [  [4]
                                                 [5]
                                                 [6]
                                            ]
                                       it spirals towards right - printing 4, towards down printing 5, 6,
                                       startRow & endRow will point to 5, holding startRow <= endRow
                                       but, endColumn initially pointing to [0] will move towards left pointing to [-1], startColumn will continue to point to [0]
                                       "If the condition is not present", then 3 rd loop will not execute because conditio is checked as false. But 4th loop will execute since endRow <= startRow, printing [5] again which is wrong
                                       So output will be - [4, 5, 6, 5]
                                 
                                 */
            // traverse left (boundary)
            
            //how? row = constant(basically we are trying to include all values from endRow) & column keep changing during iteration to include the elements
            
            for(int i=endColumn; i >= startColumn; i--){
                
                list.add(matrix[endRow][i]);
            }
                
            endRow--;  //why? because we have to move inwards, so next time we will not include this row(from endRow) whch was already included above to avoid duplicates
            
            // traverse up (boundary)
            
            //how? column = constant(basically we are trying to include all values from startColumn) & row keep changing during iteration to include the elements
            for(int i=endRow; i >= startRow; i--){
                
                list.add(matrix[i][startColumn]);
            }
            
            startColumn++; //why? because we have to move inwards, so next time we will not include this column(from startColumn) whch was already included above to avoid duplicates
        }
        
        return list;
    }
    
    
    public static void main(String args[]) {
        // TODO code application logic here
        
        Spiral_Matrix obj = new Spiral_Matrix();
        
        
        int[][] arr1 = new int[][]{  { 1, 2, 3 },
                                     { 4, 5, 6 },
                                     { 7, 8, 9 }
                                  };
        
        int[][] arr2 = new int[][] {  {1, 2, 3, 4},
                                      {5, 6, 7, 8},
                                      {9,10,11,12}
                                };
        
        Integer[] arr1_expected_ans = new Integer[]{ 1,2,3,6,9,8,7,4,5 };
        Integer[] arr2_expected_ans = new Integer[]{ 1,2,3,4,8,12,11,10,9,5,6,7 };
        
        List<Integer> ans1 = obj.spiralOrder(arr1);
        
        Integer[] arr1_ans = new Integer[arr1.length * arr1[0].length];
        arr1_ans = ans1.toArray(arr1_ans);
        
        
        List<Integer> ans2 = obj.spiralOrder(arr2);
        
        Integer[] arr2_ans = new Integer[arr2.length * arr2[0].length];
        arr2_ans = ans2.toArray(arr2_ans);
        
        
        System.out.println("arr1 : " + Arrays.equals(arr1_ans, arr1_expected_ans));
        System.out.println("arr2 : " + Arrays.equals(arr2_ans, arr2_expected_ans));
        
        
        
        
        
    }
}
