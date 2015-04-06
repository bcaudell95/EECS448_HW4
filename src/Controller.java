import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;


public class Controller {
	
	private View view;
	private Model model;
    private ArrayList<Book> selectedBooks;
	
	public Controller() {
		model = new Model();
		view = new View(this);
		view.setVisible(true);

		//populate inventory on initial load
        onCategoriesDropDownChange("Science Fiction");
	}

	//user changes category
	public void onCategoriesDropDownChange(String item) {
        this.selectedBooks = model.getBooksOfType(item + " Books");
        view.populateInventory(getArrayOfBookNames(selectedBooks));
	}

	//create array to pass into JList later on
    private String[] getArrayOfBookNames(ArrayList<Book> selectedBooks) {
        String[] bookNames = new String[selectedBooks.size()];

        for(int i = 0; i < bookNames.length; i++) {
            bookNames[i] = selectedBooks.get(i).GetTitle() + " by" + selectedBooks.get(i).GetAuthor();
        }

        return bookNames;
    }

    public String[] onAddButtonClick(int[] selectedIndices) {
        for (int selectedIndex : selectedIndices) {
            model.addBookToCart(selectedBooks.get(selectedIndex));
        }

        return getArrayOfBookNames(model.getShoppingCart());
	}
	
	public String[] onRemoveButtonClick(int[] selectedIndices) {
		
		//removes books in reverse order; removing books earlier in the list first messes up the idices of the books later in the list
        for(int i = selectedIndices.length; i > 0; i--) {
            model.removeBookFromCart(model.getShoppingCart().get(selectedIndices[i - 1]));
        }

        return getArrayOfBookNames(model.getShoppingCart());
	}
	
	public double onCheckoutButtonClick() {
		return model.calculateFinalPrice();
	}

	public List<Book> getShoppingCart() {
		return model.getShoppingCart();
	}
	
}
