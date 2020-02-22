package main;

public class ProgrammingTextbook extends main.contracts.Textbook {
	private String language;
	
	public ProgrammingTextbook() {
		
	}

	public ProgrammingTextbook(String title, String author, int numberOfPages, String language) {
		super(title, author, numberOfPages);
		setLanguage(language);
	}
	
	public ProgrammingTextbook setLanguage(String language) {
		this.language = language;
		
		return this;
	}
	
	public String getLanguage() {
		return language;
	}
	
	@Override
	public String toString() {
		return String.format("%s, Language: %s", super.toString(), getLanguage());
	}
}
