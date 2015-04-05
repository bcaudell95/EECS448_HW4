import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;


public class Controller {
	
	private View view;
	private Model model;
	
	public Controller() {
		model = new Model();
		view = new View(this);
		view.setVisible(true);
	}

	public void onCategoriesDropDownChange(String item) {
		System.out.println(item);
	}

	public String[] onAddButtonClick(int[] selectedIndices) {
		return null;
		
	}
	
	public String[] onRemoveButtonClick(int[] selectedIndices) {
		return null;
		
	}
	
	public double onCheckoutButtonClick() {
		return 0;
	}
	
}
