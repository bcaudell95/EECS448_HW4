//John Metcalfe
//EECS 448
//4-1-2015

public class Book 
{
	private String title;
	private String author;
	private double price;
	
	Book(String inTitle, String inAuthor, double inPrice)
	{
		title = inTitle;
		author = inAuthor;
		price = inPrice;
	}
	
	public String GetTitle() { return title; }
	public String GetAuthor() { return author; }
	public double GetPrice() { return price; }
}
