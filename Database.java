package domi;

import java.util.*;

/**
 * Class to implement the multimedia database (DOME)
 * @author Barrie Kelly
 *
 */
public class Database {
	
	protected ArrayList database;
	private   Scanner   input = new Scanner(System.in);
	
	public Database() {
		
		database = new ArrayList();
	}
	
	protected ArrayList search(String name) {
		
		ArrayList foundList = new ArrayList();
		for (int j = 0; j < database.size(); j++) {
		   Item current = (Item) database.get(j);	
		   if (name.equalsIgnoreCase(current.getName()))
		     foundList.add(j);		   
		}
		return foundList;
	}
	
	public void add(Item item) {
		
		database.add(item);
	}
	
	public void remove(String name) {
		
		ArrayList foundList = search(name);
		int       choice;
		switch (foundList.size()) {
		
		  case 0  : System.out.println("No item found to remove!");
		            return;
		           
		  case 1  : choice = 0;
		            break;
		           
		  default : System.out.println("\nThe following items matched the " +
				                       "specified name for removal:");
		            for (int j = 0; j < foundList.size(); j++) {
		            	int index = (Integer) foundList.get(j);
		               	System.out.println("  " + j + ".  " + (Item) database.get(index));
		            }              
		            while (true) {
		              System.out.print("Which do you want to remove? ");
		              choice = input.nextInt();
		              if (choice < 0 || choice >= foundList.size())
		            	 System.out.println("Illegal choice - please retry");
		              else
		            	 break;
		            } 
		            input.nextLine();
		}
		int index = (Integer) foundList.get(choice);
		System.out.println(database.get(index) + " removed");
		database.remove(index);       		
	}
	
	public void list() {
		
		for (int j = 0; j < database.size(); j++)
			System.out.println(database.get(j));
	}
		

}
