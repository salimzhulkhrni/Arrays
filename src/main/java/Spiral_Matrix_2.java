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

Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:

Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]


*/

import java.util.*;

public class Spiral_Matrix_2 {

    /**
     * @param args the command line arguments
     */
    
    /*
    Time Complexity: O(N) - N = Number of elements in the matrix (n*N)
    Space Comlexity: O(N) - N = Number of elements in the matrix (n*N)
    */
    
    public int[][] generateMatrix(int n) {
        
        if( n <= 0)
            return new int[0][0];
        
        int[][] matrix= new int[n][n];
        
        int startRow = 0, endRow = matrix.length - 1;
        int startColumn = 0,  endColumn = matrix[0].length - 1;
        
        int num = 1;
        
        while(startRow <= endRow && startColumn <= endColumn){
            
            
            //traverse right (boundary)
            
            // How? row = constant(basically we are trying to include all values from startRow) & column keep changing during iteration to include the elements
            
            for(int i=startColumn; i <= endColumn; i++)
                matrix[startRow][i] = num++;
            
            startRow++; // why? because we have to move inwards, so next time we will not include this row(from startRow) whch was already included above to avoid duplicates
            
            // traverse down (boundary)
            
            // how? column = constant(basically we are trying to include all values from endColumn) & row keep changing during iteration to include the elements 
            
            for(int i=startRow; i <= endRow; i++)
                matrix[i][endColumn] = num++;
            
            endColumn--; // why? because we have to move inwards, so next time we will not include this column(from endColumn) whch was already included above to avoid duplicates
            
            if(startRow > endRow || startColumn > endColumn)
                break;       // why is this required?
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
            
            for(int i=endColumn; i >= startColumn; i--)
                matrix[endRow][i] = num++;
            
            endRow--;    //why? because we have to move inwards, so next time we will not include this row(from endRow) whch was already included above to avoid duplicates
            
            // traverse up (boundary)
            
            //how? column = constant(basically we are trying to include all values from startColumn) & row keep changing during iteration to include the elements
            
            for(int i=endRow; i >= startRow; i--)
                matrix[i][startColumn] = num++;
            
            startColumn++;   //why? because we have to move inwards, so next time we will not include this column(from startColumn) whch was already included above to avoid duplicates
            
        }
        
        return matrix;
    }
    
    
    
    public static void main(String args[]) {
        // TODO code application logic here
        
        
        Spiral_Matrix_2 obj = new Spiral_Matrix_2();
        
        int n = 3;
        
        int[][] arr1 = new int[][]{  { 1, 2, 3 },
                                     { 8, 9, 4 },
                                     { 7, 6, 5 }
                                  };
    
        
        int[][] arr1_ans = obj.generateMatrix(n);
        
        for(int i=0; i<arr1_ans.length; i++){
            
            for(int j=0; j<arr1_ans[0].length; j++)
                System.out.print("" + arr1_ans[i][j] + " ");
            
            System.out.print(" -  " + Arrays.equals(arr1[i], arr1_ans[i]));
            System.out.println("");
        }
            
    }
}
