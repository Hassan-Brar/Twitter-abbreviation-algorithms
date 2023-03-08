import java.io.*;
import java.text.*;
import java.util.*;

public class CustomAlgorithm {

	static HashMap<String, String> map = new HashMap<String, String>(); //need map outside main to compare with tweets later
	static int changes=0;
	static long startTime;
	static long endTime;
	public static void main(String[] args) throws ParseException, IOException {
		
		String path = "/Users/Hassan/Desktop/Slang.csv";//change path to appropriate file location
	    String line =  null;
	    long startTime;
		long endTime;
	    
	    map = mapping(path);
	   
	    try {
	    	//change path to appropriate file location
		    BufferedReader reader = new BufferedReader(new FileReader("/Users/Hassan/Desktop/filtered_data.csv"));
	        reader.readLine();
	        String row;
	        startTime = System.currentTimeMillis();
	        while ((row = reader.readLine()) != null) {                  // read until end of file
	        	System.out.println(row);
	        	System.out.println("=");
	        	System.out.println(Replace(row));
	        	System.out.println("______________________________________________________________________________________________________________________________________________________________");
	        	
	        }
	        endTime = System.currentTimeMillis();
	        long Elapsed = endTime - startTime;
	        System.out.println("Time Elapsed in ms: "+Elapsed);
	        System.out.println("Number of changes: "+changes);
	    }catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
    } 
    public static String Replace(String tweet) {
    	String[] tweetSubstring = tweet.split(" ");  // tweet split on spaces
    	
    	
    	for(int i = 0; i < tweetSubstring.length;i++) { //Runtime of for loop is O(n)
    		if(tweetSubstring[i].length() < 7 || tweetSubstring[i].contains("'")) {
	    		if(map.containsKey(remove(tweetSubstring[i].toLowerCase()))){ //Runtime of this statement is O(1)
					tweetSubstring[i] = map.get(tweetSubstring[i]);
					changes++;
	    		}
    		}		
    	}
        String newTweet = String.join(" ", tweetSubstring);
    	return newTweet;
    }
    
//Putting the abbreviations in a key value hashmap
	public static HashMap<String, String> mapping(String csv) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		try {
            BufferedReader reader = new BufferedReader(new FileReader(csv));
            reader.readLine();                                          
            String row;

            while ((row = reader.readLine()) != null) {                 
                String[] split = row.split(",");                     
                map.put(split[0].toLowerCase(), split[1].toLowerCase()); //Value(abr), Key(word)
            }
            
		
		} catch(FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("Cannot find file");
        }
		return map;
	}
	
	public static String remove(String word) {
        if (word.length() < 2) //Need to add this so the word in within range
            return word;

        char Char = word.charAt(word.length() - 1);
        if (Char == '!')
            return word.substring(0, word.length() - 1);
        if(Char == ',')
        	return word.substring(0, word.length() - 1);
        if(Char == '.')
        	return word.substring(0, word.length() - 1);
        if(Char == '"')
        	return word.substring(0, word.length() - 1);
        
        
        return word;
    }
}
