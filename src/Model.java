//John Metcalfe
//EECS 448
//4-1-2015

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Model 
{
	private ArrayList<Book> shoppingCart;
	private Map<String, ArrayList<Book>> catalog;
	
	Model()
	{
		catalog = LoadCatalog("catalog.txt");
		shoppingCart = new ArrayList<Book>();
	}
	
	//Adds a book the the cart and returns the new cart
	ArrayList<Book> addBookToCart(Book b)
	{
		shoppingCart.add(b);
		
		return shoppingCart;
	}
	
	//Removes a book from the cart and returns the new cart
	ArrayList<Book> removeBookFromCart(Book b)
	{
		shoppingCart.remove(b);
		
		return shoppingCart;
	}
	
	//Calculate the final price of the shopping cart and returns the price
	double calculateFinalPrice()
	{
		double total = 0;
		
		for(int i = 0; i < shoppingCart.size(); i++)
		{
			total += shoppingCart.get(i).GetPrice();
		}
		
		return total;
	}
	
	//Returns an array of strings corresponding to the types of books (pass these values to getBooksOfType())
	String[] getTypesOfBooks()
	{
		return catalog.keySet().toArray(new String[catalog.keySet().size()]);
	}
		
	//Returns an ArrayList of the books from the given category
	ArrayList<Book> getBooksOfType(String identifier)
	{
		String[] keys = catalog.keySet().toArray(new String[catalog.keySet().size()]);
		
		for(int i = 0; i < keys.length; i++)
		{
			if(keys[i].contains(identifier))
			{
				return catalog.get(identifier);
			}
		}
		
		return new ArrayList<Book>();
	}
	
	//Reads in the catalog from a .txt file
	private Map<String, ArrayList<Book>> LoadCatalog(String filename)
	{
		Map<String, ArrayList<Book>> cat = new HashMap<String, ArrayList<Book>>();
		
		//Set up the reader
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename), charset))
		{	
			//Read the first line (assuming its the first category)
		    String line = reader.readLine();
		    String categoryName = null;
		    double catPrice = 0;
		    while (true) 
		    {
		    	//If the reader didn't read anything stop looping
		    	if(line == null)
		    		break;
		    	
		    	//Break off the category name
		    	categoryName = line.substring(0, (line.indexOf("(")-1));
		    	//Do string magic to find the price assuming it starts with '$' and has a space after it
		    	catPrice = Double.parseDouble(line.substring( (line.indexOf("$")+1) , (line.indexOf("$")+1)+(line.substring( (line.indexOf("$")+1) , (line.length()-1) ).indexOf(' ') ) ) );
		    	ArrayList<Book> category = new ArrayList<Book>();
		    	
		    	line = reader.readLine();
		    	
		    	//If the line isn't a category line
		    	do
		    	{	
			    	//Peel off the author
		    		String author = line.substring( (line.indexOf(".")+1) , (line.indexOf(',')-1) );
		    		//Peel off the title
		    		String title = line.substring( (line.indexOf(',')+1) , (line.length()) );
		    		double price = catPrice;
		    		
		    		Book b = new Book(title, author, price);
		    		
		    		//Add book
		    		category.add(b);
		    		
			    	line = reader.readLine();
		    		
		    	}while((line != null) && !(line.contains("$")));
		    	
		    	//Put ArrayList of books into map
		    	cat.put(categoryName, category);
		    }
		} catch (IOException x) 
		{
		    System.err.format("IOException: %s%n", x);
		}
		
		return cat;
	}
}
