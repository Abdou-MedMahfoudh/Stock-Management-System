package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DAO.ProduitDao;
import Metier.Produit;

public class EditProductDialog extends JDialog {
    private JTextField nameField, priceField, stockField;
    private JButton updateButton, cancelButton;
    private ProduitDao produitDAO;

    public EditProductDialog(JFrame parent, int productId) {
        super(parent, "Edit Product", true);
        produitDAO = new ProduitDao();

        setLayout(new GridLayout(5, 2));

        try {
            Produit product = produitDAO.getProduitbyid(productId);
            nameField = new JTextField(product.getNom());
            priceField = new JTextField(String.valueOf(product.getPrix()));
            stockField = new JTextField(String.valueOf(product.getQuantiteStock()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        add(new JLabel("Product Name:"));
        add(nameField);

        add(new JLabel("Price:"));
        add(priceField);

        add(new JLabel("Stock:"));
        add(stockField);

        updateButton = new JButton("Update");
        cancelButton = new JButton("Cancel");
        add(updateButton);
        add(cancelButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int stock = Integer.parseInt(stockField.getText());

                try {
                    Produit updatedProduct = new Produit(productId, name, price, stock);
                    produitDAO.updateProduit(updatedProduct);
                    JOptionPane.showMessageDialog(EditProductDialog.this, "Product updated successfully.");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(EditProductDialog.this, "Error updating product.");
                    ex.printStackTrace();
                }
            }
        });

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