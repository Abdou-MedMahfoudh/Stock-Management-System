package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DAO.ProduitDao;
import Metier.Produit;

public class AddProductDialog extends JDialog {
    private JTextField nameField, priceField, stockField;
    private JButton addButton, cancelButton;
    private ProduitDao produitDAO;

    public AddProductDialog(JFrame parent) {
        super(parent, "Add New Product", true);
        produitDAO = new ProduitDao();

        setLayout(new GridLayout(5, 2));
       
       add(new JLabel("Product Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Price:"));
        priceField = new JTextField();
        add(priceField);

        add(new JLabel("Stock:"));
        stockField = new JTextField();
        add(stockField);

        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");
        add(addButton);
        add(cancelButton);

        addButton.addActionListener(
        		
        		new ActionListener() {
        			
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	// fetching the data 
            	int idproduit = 0 ;
                String name = nameField.getText();
                double prix = Double.parseDouble(priceField.getText());
                int stock = Integer.parseInt(stockField.getText());
                  
                try {
                	// creating the product object 
                    Produit newProduct = new Produit(idproduit, name, prix, stock );
                    //passing to the produit DAO
                    produitDAO.addProduit(newProduct);
                    
                    //shows message window ( for success or error ) 
                    JOptionPane.showMessageDialog(AddProductDialog.this, "Product added successfully.");
                    // to close the dialog window
                     dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AddProductDialog.this, "Error adding product.");
                    ex.printStackTrace();
                }
            }
        });
        // to use cancel 
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setSize(300, 200);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}