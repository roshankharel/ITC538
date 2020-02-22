package main.contracts;

public abstract class Textbook {
	private String title;
	private String author;
	private int numberOfPages;

	public Textbook() {

	}

	public Textbook(String title, String author, int numberOfPages) {
		setTitle(title);
		setAuthor(author);
		setNumberOfPages(numberOfPages);
	}
	
	public Textbook setTitle(String title) {
		this.title = title;
		
		return this;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Textbook setAuthor(String author) {
		this.author = author;
		
		return this;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public Textbook setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
		
		return this;
	}
	
	public int getNumberOfPages() {
		return numberOfPages;
	}
	
	public String toString() {
		return String.format("Title: %s, Author: %s, Number of Pages: %d", getTitle(), getAuthor(), getNumberOfPages());
	}
}
