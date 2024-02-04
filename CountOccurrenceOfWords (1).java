/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practical_ii_q2;

/**
 *
 * @author User
 */
import java.util.*;

public class CountOccurrenceOfWords {
  public static void main(String[] args) {
    // Set text in a string
    String text = "Good day. Have a great class. " +
      "Have a good stay. Enjoy what you have. Have all fun!";

    // Create a TreeMap to hold words as key and count as value
    Map<String, Integer> map = new LinkedHashMap<String, Integer>();
    String pt = "([g.s.]\\!)(\\!)";
    String[] words = text.split("[\\s\n\t\r.,;:!?(){}]");
    System.out.println(text.replaceAll(pt, "$2")); 
    for (int i = 0; i < words.length; i++) {
      String key = words[i].toLowerCase();
      
      if (key.length() > 0) {
        if (!map.containsKey(key)) {
          map.put(key, 1);
        }
        else {
          int value = map.get(key);
          value++;
          map.put(key, value);
        }
      }
    }
   
    // Get all entries into a set
    Collection<Map.Entry<String, Integer>> entrySet = map.entrySet();
    
    List<Map.Entry<String, Integer>> list = new ArrayList(entrySet);
    list.sort(new CompareEntries());
    map.clear();
    
    for (Map.Entry<String, Integer> entry: list)
        map.put(entry.getKey(),entry.getValue());
     System.out.println(map);
   
  }
}