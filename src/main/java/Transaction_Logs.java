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

Hi All,
Recently I had an OA from a FAANG company. I wanted to share the questions and answers to those questions. Please let me know your opinions and code improvements.

The 1st question was Transaction logs.

A Company parses logs of online store user transactions/activity to flag fraudulent activity.

The log file is represented as an Array of arrays. The arrays consist of the following data:

[ <# of transactions>]

For example:

[345366 89921 45]

Note: the data is space delimited

So, the log data would look like:

[
[345366 89921 45],
[029323 38239 23]
...
]
Write a function to parse the log data to find distinct users that meet or cross a certain threshold.

The function will take in 2 inputs:
logData: Log data in form an array of arrays

threshold: threshold as an integer

Output:
It should be an array of userids that are sorted.

If same userid appears in the transaction as userid1 and userid2, it should count as one occurrence, not two.

Example:
Input:
logData:

[
[345366 89921 45],
[029323 38239 23],
[38239 345366 15],
[029323 38239 77],
[345366 38239 23],
[029323 345366 13],
[38239 38239 23]
...
]
threshold: 3

Output: [345366 , 38239, 029323]
Explanation:
Given the following counts of userids, there are only 3 userids that meet or exceed the threshold of 3.

345366 -4 , 38239 -5, 029323-3, 89921-1



*/

import java.util.*;

public class Transaction_Logs {

    public String[] transactionLogs(String[][] arr, int threshold){
        
        if(threshold < 0)
            return new String[0];
        
        Map<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        
        for(int i = 0; i < arr.length; i++){
            
            String str = arr[i][0];
            String[] users = str.split(" ");
            
            if(!users[0].equals(users[1])){
                
               map.put(users[0], map.getOrDefault(users[0], 0) + 1);
               map.put(users[1], map.getOrDefault(users[1], 0) + 1);
            }
            else
                map.put(users[0], map.getOrDefault(users[0], 0) + 1);
            
        }
        
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            
            if(entry.getValue() >= threshold){}
                list.add(entry.getKey());
                
            System.out.println("key: " + entry.getKey() + " count: " + entry.getValue());
        }
        
        String[] res = new String[list.size()];
        
        Collections.sort(list);
        
        for(int i = 0; i < list.size(); i++)
            res[i] = list.get(i);
        
        return res;
        
        
    }
    
    public static void main(String args[]) {
        // TODO code application logic here
        
        Transaction_Logs obj = new Transaction_Logs();
        
        String[][] arr = new String[][] { 
                                            {"345366 89921 45"},
                                            {"029323 38239 23"},
                                            {"38239 345366 15"},
                                            {"029323 38239 77"},
                                            {"345366 38239 23"},
                                            {"029323 345366 13"},
                                            {"38239 38239 23"}
        
                                   };
        
        String[] res = obj.transactionLogs(arr, 3);
        
        for(int i = 0; i < res.length; i++)
            System.out.println("" + res[i]);
    }
        
}
