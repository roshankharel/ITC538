package main;

import java.util.ArrayList;

import main.contracts.Textbook;

public class TestTextbooks {
	private static ArrayList<Textbook> books = new ArrayList<Textbook>();

	public static void main(String[] args) {
		makeBooks();
	}

	private static void makeBooks() {
		ProgrammingTextbook programmingTextbook1 = new ProgrammingTextbook();
		ProgrammingTextbook programmingTextbook2 = new ProgrammingTextbook();
		EngineeringTextbook engineeringTextbook1 = new EngineeringTextbook();
		EngineeringTextbook engineeringTextbook2 = new EngineeringTextbook();

		programmingTextbook1.setTitle("Java for Dummies").setAuthor("John Doe").setNumberOfPages(111);
		programmingTextbook1.setLanguage("Java");

		programmingTextbook2.setTitle("Python for Dummies").setAuthor("John Doe").setNumberOfPages(112);
		programmingTextbook2.setLanguage("Python");

		engineeringTextbook1.setTitle("Roark's Formulas for Stress and Strain").setAuthor("Richard G. Budynas and Warren Clarence Young").setNumberOfPages(113);
		engineeringTextbook1.setSubject("Civil Engineering");
		
		engineeringTextbook2.setTitle("Shigley's Mechanical Engineering Design").setAuthor("J. Keith Nisbeth and Richard G. Budynas").setNumberOfPages(114);
		engineeringTextbook2.setSubject("Mechanical Engineering");
		
		books.add(programmingTextbook1);
		books.add(programmingTextbook2);
		books.add(engineeringTextbook1);
		books.add(engineeringTextbook2);
	}
	
	private static void testBooks() {
		//TODO test books
	}
}
