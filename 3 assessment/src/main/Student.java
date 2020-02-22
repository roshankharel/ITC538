package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Student {
	private int id;
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return String.format("%d, %s", id, name);
	}
	
	public boolean isValidId(String id) {
		return id.length() == 6 && id.matches("\\d+"); // or [0-9]+ as regex
	}
	
	public boolean isValidId(int id) {
		return isValidId(String.valueOf(id));
	}
	
	public boolean IdExists(String id) {
		boolean exists = false;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("./students.txt"));
			String line;
			
			while((line = reader.readLine()) != null) {
				if(line.contains(id)) {
					exists = false;
					break;
				}
			}
			
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return exists;
	}
	
	public boolean IdExists(int id) {
		return IdExists(String.valueOf(id));
	}
	
	
}






