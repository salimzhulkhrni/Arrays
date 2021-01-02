/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*

Here's the description for 5 star sellers.
Anyone has a good solution for this question?

Third-party companies that sell their products on abcd.com are able to analyze the customer reviews for their products in real time. Imagine that Amazon is creating a category called "five-star sellers" that will only display products sold by companies whose average percentage of five-star reviews per-product is at or above a certain threshold. Given the number of five-star and total reviews for each product a company sells, as well as the threshold percentage, what is the minimum number of additional fivestar reviews the company needs to become a five-star seller?

For example, let's say there are 3 products (n = 3) where productRatings = [[4,4], [1,2], [3, 6]], and the percentage ratings Threshold = 77. The first number for each product in productRatings denotes the number of fivestar reviews, and the second denotes the number of total reviews. Here is how we can get the seller to reach the threshold with the minimum number of additional five-star reviews:

Before we add more five-star reviews, the percentage for this seller is ((4 / 4) + (1/2) + (3/6))/3 = 66.66%
If we add a five-star review to the second product, the percentage rises to ((4 / 4) + (2/3) +(3/6))/3 = 72.22%
If we add another five-star review to the second product, the percentage rises to ((4 / 4) + (3/4) + (3/6))/3 = 75.00%
If we add a five-star review to the third product, the percentage rises to ((4/4) + (3/4) + (4/7))/3 = 77.38%
At this point, the threshold of 77% has been met. Therefore, the answer is 3 because that is the minimum number of additional five-star reviews the company needs to become a five-star seller.

Function Description

Complete the function fiveStarReviews in the editor below.

fiveStarReviews has the following parameters:

int productRatings[n][2]: a 2-dimensional array of integers where the ith element contains two values, the first one denoting fivestar[i] and the second denoting total[i]

int ratingsThreshold: the threshold percentage, which is the average percentage of five-star reviews the products need for the company to be considered a five-star seller

Returns:

int: the minimum number of additional five-star reviews the company needs to meet the threshold ratingsThreshold

Constraints

1<=n<=200
0 <= fivestar<total<=100
1<=ratingsThreshold<100
The array productRatings contains only non-negative integers.


*/


import java.util.*;

public class Five_Star_Sellers {

    /*
    
    Approach:
      1. Get the deficit part for each fraction ie (arr[0]/ arr[1]) - (arr[0] + 1) / (arr[1] + 1) and put it in the min heap
      2. Poll the max defcit part denoted by the smallest -ve number from the q and add it to the grand total. Increment the min operations count variable.
      3. For the polled max deficit part, we now have the new fraction part incremented by 1 on both numerator & denominator
            ie  If 2/3 was original, added the deficit part => ( 2 / 3 - 3 / 4) to grand total meaning we now have the fraction 3 / 4 by adding the deficit part
                Now 2 / 3 is no longer there instead we added the deficit part to make it 3 / 4. Now repeat Step 1 for the 3 / 4 part ie ( 3/ 4 - 4/ 5) & add the new deficit part to the q
      4. Repeat Steps 2, 3 until grand percentage reaches threshold
    
    Time: O(n log n) => log n for add/remove from q is log n we do it for n products
    Space: O(n) => store all n element's deficit in the priority queue
    
    spurce: https://leetcode.com/discuss/interview-question/983856/Amazon-or-OA-or-Five-Star-Sellers
    
    */
    
   public int fiveStarReviews(List<int[]> products, int threshold){
   
       if(products == null || threshold <= 0)
           return 0;
       
       int minCountForFiveStars = 0;
       float grandTotal = 0;
       float grandPercentage = 0;
       
       PriorityQueue<Float[]> q = new PriorityQueue<>(new Comparator<Float[]>(){
           
           public int compare(Float[] arr1, Float[] arr2){
               
               return arr1[1].compareTo(arr2[1]);
           }
       });
       
       // store in priority q, the deficit amount ie x/y - (x + 1) / (y + 1), then we can pick up from the q, the max value that would cause an increase in %
       
       for(int i = 0; i < products.size(); i++){
           
           int[] arr = products.get(i);
           float currentPercentagePart = getCurrentPercentage(arr);
           float incrementedPercentagePart = getIncrementedPercentage(arr);
           grandTotal+= currentPercentagePart;
           
           float deficitFromIncrementedPercentagePart =  currentPercentagePart - incrementedPercentagePart; 
           
           q.offer(new Float[]{ (float)i, deficitFromIncrementedPercentagePart} );
       }
       
       
       // from the q now, take the less deficit amount and then keep adding it to the grand total, until we reach the thershold
       // why less deficit amount? Ex: deficit amount = (-). So, if -0.5 & -0.9 are the in the q, then we should add -0.9 since that is the max deficit amount by magniture. But from number theory, -0.9 is the least deficit amount but max deficit amount by magnitude
       
       grandPercentage = (grandTotal / products.size()) * 100;
       
       while(grandPercentage < threshold){
           
           Float[] leastDeficit =  q.poll();                      // this will give the number ex. -0.9 which will increase the % by maximum
           grandTotal+= (-1) * leastDeficit[1];                   // add the least deficit part to grand total means we increased the part from 1/2 by adding the deficit amount (0.16) to get 2/3
           grandPercentage = (grandTotal / products.size()) * 100;
           
           int[] addedPart = products.get(leastDeficit[0].intValue());   // from Float type -> get the int value of it
           
           addedPart[0]+= 1;   // since we already added the deficit part to the grandTotal, now 1 / 2  should become 2 / 3 after adding 1 to both numerator and denominator 
           addedPart[1]+= 1; 
           
           // now we have new fraction part - 2/3, add the new deficit part ie 2 / 3 - 3 / 4 and put in the q
           
           float currentPercentagePart = getCurrentPercentage(addedPart);
           float incrementedPercentagePart = getIncrementedPercentage(addedPart);
           float newDeficitPart = currentPercentagePart - incrementedPercentagePart; 
           
           
           q.offer(new Float[] { leastDeficit[0], newDeficitPart});
           
           minCountForFiveStars++;
           
       } 
       
       return minCountForFiveStars;
       
   }
   
   private float getCurrentPercentage(int[] arr){
       
       return (float)arr[0] / (float)arr[1];
   }
   
   private float getIncrementedPercentage(int[] arr){
       
       return ((float)arr[0] + 1) / ((float)arr[1] + 1);
   }
   
    public static void main(String args[]) {
        
        Five_Star_Sellers obj = new Five_Star_Sellers();
        
        List<int[]> products = new ArrayList<>();
        
        products.add(new int[] {4, 4});
        products.add(new int[] {1, 2});
        products.add(new int[] {3, 6});
       
        int threshold = 77;
        
        System.out.println("res1: " + (obj.fiveStarReviews(products, threshold) ));
        
    }
}
