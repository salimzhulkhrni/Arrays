/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*

Given a map Map<String, List<String>> userSongs with user names as keys and a list of all the songs that the user has listened to as values.

Also given a map Map<String, List<String>> songGenres, with song genre as keys and a list of all the songs within that genre as values. The song can only belong to only one genre.

The task is to return a map Map<String, List<String>>, where the key is a user name and the value is a list of the user's favorite genre(s). Favorite genre is the most listened to genre. A user can have more than one favorite genre if he/she has listened to the same number of songs per each of the genres.

Example 1:

Input:
userSongs = {  
   "David": ["song1", "song2", "song3", "song4", "song8"],
   "Emma":  ["song5", "song6", "song7"]
},
songGenres = {  
   "Rock":    ["song1", "song3"],
   "Dubstep": ["song7"],
   "Techno":  ["song2", "song4"],
   "Pop":     ["song5", "song6"],
   "Jazz":    ["song8", "song9"]
}

Output: {  
   "David": ["Rock", "Techno"],
   "Emma":  ["Pop"]
}

Explanation:
David has 2 Rock, 2 Techno and 1 Jazz song. So he has 2 favorite genres.
Emma has 2 Pop and 1 Dubstep song. Pop is Emma's favorite genre.
Example 2:

Input:
userSongs = {  
   "David": ["song1", "song2"],
   "Emma":  ["song3", "song4"]
},
songGenres = {}

Output: {  
   "David": [],
   "Emma":  []
}


*/

import java.util.*;

public class Favourite_Genres {
    
    // Time: O(U * (S + G)) -- see comments below for explanation
    // Space: O(S + G + (U * G)) but dominant is O(S + (U * G)) since O(U * G) grows faster than O(G)

    public Map<String, List<String>> favoritegenre(Map<String, List<String>> userMap, Map<String, List<String>> genreMap){
    
        Map<String, String> songsToGenre = new HashMap<>();
        Map<String, List<String>> res = new HashMap<>();
        
        // 1. map => songs -> genre
        
        for(Map.Entry<String, List<String>> entry : genreMap.entrySet()){    // Time: Each song belongs to only one genre. At most, traverse all genres = O(G) + Traverse all songs(worst case) => O(S) => Total complexity here => O(G + S)
                                                                             // Space: O(S) => map to store all songs mapped to genre
            String genre = entry.getKey();
            List<String> songs = entry.getValue();
            
            for(String song : songs)
                songsToGenre.put(song, genre);
            
        }
        
        Map<String, Integer> genreCountMap;
        List<String> favGenresForEachUser;
        
        for(Map.Entry<String, List<String>> entry : userMap.entrySet()){ // Time: for each user => O(U)
            
            String user = entry.getKey();
            List<String> songs = entry.getValue();
            int maxGenreCountForEachUser = 0;
            genreCountMap = new HashMap<>();                            // space: map for genre count => O(G)
            favGenresForEachUser = new ArrayList<>();                   // space => fav genre list for each user => O(U * G) . => Total space = O(S + G + (U * G))
            
            for(String song : songs){           //Time => iterate through songs => O(S)
                
                // 2. find which genre this song belongs to
                String genre = songsToGenre.get(song);
                
                // if genre is found, put in the genreCountMap
                if(genre != null){  // this is required to avoid adding null into genreCountMap 
                    
                    genreCountMap.put(genre, genreCountMap.getOrDefault(genre, 0) + 1);
                    int currentGenreCount = genreCountMap.get(genre);
                    maxGenreCountForEachUser = Math.max(maxGenreCountForEachUser, currentGenreCount);
                    
                }
                
            }
            
            res.put(user, favGenresForEachUser);   // this is required so that when there no mapping from genre -> songs, genreCountMap will be empty, so we ll have a user mapped to empty list of fav genres
            
            // find max genre for this user from genreCountMap & add it to the corresponding user
            
            for(Map.Entry<String, Integer> genre_entry : genreCountMap.entrySet()){    // Time => iterate through genres => O(G) => Total time=> O(S + G) + O(U * (S + G)) => O(U * (S + G)) (dominant) 
                
                String genre = genre_entry.getKey();
                int count = genre_entry.getValue();
                
                if(maxGenreCountForEachUser == count){
                    List<String> favGenresForEachUserList = res.get(user);
                    favGenresForEachUserList.add(genre);
                }
                    
            }
            
        }
        
        return res;
        
    }
    
    public void printMap(Map<String, List<String> > map){
    
        for(Map.Entry<String, List<String> > entry : map.entrySet()){
            
            String user = entry.getKey();
            List<String> genres = entry.getValue();
            
            System.out.println("user: " + user);
            System.out.print("genres: " );
            
            for(String genre : genres)
                System.out.print("" + genre + " ");
            
            System.out.println("");
            
        }
    }
    
    public static void main(String args[]) {
        
        Favourite_Genres obj = new Favourite_Genres();
        
        Map<String, List<String>> userSongs1 = new HashMap<>();
        Map<String, List<String>> songGenres1 = new HashMap<>();
        
        userSongs1.put("David", new ArrayList<String>(Arrays.asList("song1", "song2", "song3", "song4", "song8")));
        userSongs1.put("Emma", new ArrayList<String>(Arrays.asList("song5", "song6", "song7")));
        
        songGenres1.put("Rock", new ArrayList<String>(Arrays.asList("song1", "song3")));
        songGenres1.put("Dubstep", new ArrayList<String>(Arrays.asList("song7")));
        songGenres1.put("Techno", new ArrayList<String>(Arrays.asList("song2", "song4")));
        songGenres1.put("Pop", new ArrayList<String>(Arrays.asList("song5", "song6")));
        songGenres1.put("Jazz", new ArrayList<String>(Arrays.asList("song8", "song9")));
        
        
        Map<String, List<String>> userSongs2 = new HashMap<>();
        Map<String, List<String>> songGenres2 = new HashMap<>();
        
        userSongs2.put("David", new ArrayList<String>(Arrays.asList("song1", "song2")));
        userSongs2.put("Emma", new ArrayList<String>(Arrays.asList("song3", "song4")));
        
        
        Map<String, List<String>> res1 = obj.favoritegenre(userSongs1, songGenres1);
        Map<String, List<String>> res2 = obj.favoritegenre(userSongs2, songGenres2);
        
        System.out.println("----Res 1: ----");
        obj.printMap(res1);
        System.out.println("----Res 2: ----");
        obj.printMap(res2);
        
    }
}
