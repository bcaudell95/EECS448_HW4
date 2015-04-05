import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.sound.midi.ControllerEventListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JComboBox;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale.Category;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;


public class View extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	
	private JComboBox categoryComboBox;
	private JButton addButton;
	private JButton removeButton;
	private JButton checkoutButton;
	
	private JList inventoryList;
	private JList shoppingCartList;
	
	public View(final Controller controller) {
		this();
		this.controller = controller;
		categoryComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onCategoriesDropDownChange(categoryComboBox.getSelectedItem().toString());
			}
		});
		this.addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] inventoryItems = controller.onAddButtonClick(inventoryList.getSelectedIndices());
				populateInventory(inventoryItems);
			}
		});
		this.removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] shoppingCartItems = controller.onAddButtonClick(shoppingCartList.getSelectedIndices());
				populateShoppingCart(shoppingCartItems);
			}
		});
		this.checkoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double totalPrice = controller.onCheckoutButtonClick();
				showTotalPrice(totalPrice);
			}
		});
	}
	
	public void populateInventory(String[] items) {
		this.inventoryList.setListData(items);
	}
	
	public void populateShoppingCart(String[] items) {
		this.shoppingCartList.setListData(items);
	}
	
	private void showTotalPrice(double price) {
		JOptionPane.showMessageDialog(this, "Total cost: $"+price);
	}
	
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{200, 0, 200, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 17, 97, 228, 34, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel categoriesLabel = new JLabel("Category:   ");
		GridBagConstraints gbc_categoriesLabel = new GridBagConstraints();
		gbc_categoriesLabel.insets = new Insets(0, 0, 5, 5);
		gbc_categoriesLabel.gridx = 0;
		gbc_categoriesLabel.gridy = 0;
		contentPane.add(categoriesLabel, gbc_categoriesLabel);
		
		this.categoryComboBox = new JComboBox();
		categoryComboBox.setModel(new DefaultComboBoxModel(new String[] {"Science Fiction", "Travel", "Software Engineering"}));
		GridBagConstraints gbc_categoryComboBox = new GridBagConstraints();
		gbc_categoryComboBox.gridwidth = 2;
		gbc_categoryComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_categoryComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_categoryComboBox.gridx = 1;
		gbc_categoryComboBox.gridy = 0;
		contentPane.add(categoryComboBox, gbc_categoryComboBox);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 3;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		contentPane.add(separator, gbc_separator);
		
		JLabel inventoryLabel = new JLabel("Inventory");
		GridBagConstraints gbc_inventoryLabel = new GridBagConstraints();
		gbc_inventoryLabel.insets = new Insets(0, 0, 5, 5);
		gbc_inventoryLabel.gridx = 0;
		gbc_inventoryLabel.gridy = 2;
		contentPane.add(inventoryLabel, gbc_inventoryLabel);
		
		JLabel lblShoppingCart = new JLabel("Shopping Cart");
		GridBagConstraints gbc_lblShoppingCart = new GridBagConstraints();
		gbc_lblShoppingCart.insets = new Insets(0, 0, 5, 0);
		gbc_lblShoppingCart.gridx = 2;
		gbc_lblShoppingCart.gridy = 2;
		contentPane.add(lblShoppingCart, gbc_lblShoppingCart);
		
		JScrollPane inventoryScrollPane = new JScrollPane();
		GridBagConstraints gbc_inventoryScrollPane = new GridBagConstraints();
		gbc_inventoryScrollPane.gridheight = 3;
		gbc_inventoryScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_inventoryScrollPane.fill = GridBagConstraints.BOTH;
		gbc_inventoryScrollPane.gridx = 0;
		gbc_inventoryScrollPane.gridy = 3;
		contentPane.add(inventoryScrollPane, gbc_inventoryScrollPane);
		
		inventoryList = new JList();
		inventoryScrollPane.setViewportView(inventoryList);
		
		JScrollPane shoppingCartScrollPane = new JScrollPane();
		GridBagConstraints gbc_shoppingCartScrollPane = new GridBagConstraints();
		gbc_shoppingCartScrollPane.gridheight = 3;
		gbc_shoppingCartScrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_shoppingCartScrollPane.fill = GridBagConstraints.BOTH;
		gbc_shoppingCartScrollPane.gridx = 2;
		gbc_shoppingCartScrollPane.gridy = 3;
		contentPane.add(shoppingCartScrollPane, gbc_shoppingCartScrollPane);
		
		shoppingCartList = new JList();
		shoppingCartScrollPane.setViewportView(shoppingCartList);
		
		addButton = new JButton(">>");
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.anchor = GridBagConstraints.SOUTH;
		gbc_addButton.insets = new Insets(0, 0, 5, 5);
		gbc_addButton.gridx = 1;
		gbc_addButton.gridy = 4;
		contentPane.add(addButton, gbc_addButton);
		
		removeButton = new JButton("<<");
		GridBagConstraints gbc_removeButton = new GridBagConstraints();
		gbc_removeButton.anchor = GridBagConstraints.NORTH;
		gbc_removeButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_removeButton.insets = new Insets(0, 0, 5, 5);
		gbc_removeButton.gridx = 1;
		gbc_removeButton.gridy = 5;
		contentPane.add(removeButton, gbc_removeButton);
		
		checkoutButton = new JButton("Checkout");
		GridBagConstraints gbc_btnCheckout = new GridBagConstraints();
		gbc_btnCheckout.gridx = 2;
		gbc_btnCheckout.gridy = 6;
		contentPane.add(checkoutButton, gbc_btnCheckout);
	}

}
