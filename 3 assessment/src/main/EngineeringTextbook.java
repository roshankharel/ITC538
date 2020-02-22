package main;

public class EngineeringTextbook extends main.contracts.Textbook {
	private String subject;
	
	public EngineeringTextbook() {}
	
	public EngineeringTextbook(String title, String author, int numberOfPages, String subject) {
		super(title, author, numberOfPages);
		setSubject(subject);
	}
	
	public EngineeringTextbook setSubject(String subject) {
		this.subject = subject;
		
		return this;
	}
	
	public String getSubject() {
		return subject;
	}
	
	@Override
	public String toString() {
		return String.format("%s, Subject: %s", super.toString(), getSubject());
	}
}
