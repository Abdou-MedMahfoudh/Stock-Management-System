package Gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import DAO.*;
import Metier.*;

public class AdminView extends JFrame {
	// all the needed components 
    private JTabbedPane tabbedPane;
    private JPanel productPanel, orderPanel, stockPanel;
    private JTable productTable, orderTable, stockTable;
    private DefaultTableModel productTableModel, orderTableModel, stockTableModel;
    
    // the needed dao to work with 
    private ProduitDao produitDAO;
    private CommandeDao commandeDAO;

    //constructeur 
    public AdminView() {
    	
    	//jframe settings 
        setTitle("Admin Panel");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //the needed dao intialization 
        produitDAO = new ProduitDao();
        commandeDAO = new CommandeDao();

        // Set up TabbedPane
        tabbedPane = new JTabbedPane();
      
        
        // Product Management Panel
        
        productPanel = new JPanel(new BorderLayout());
        
        //creating the table model
        
        productTableModel = new DefaultTableModel();
        productTableModel.addColumn("ID");
        productTableModel.addColumn("Name");
        productTableModel.addColumn("Price");
        productTableModel.addColumn("Stock");
        	
        //passing the table model to jtable 
        productTable = new JTable(productTableModel);
        
        //loading the data to the Jtable 
        loadProductData();
        
        //adding the product table to the product panel and making scrollable 
        productPanel.add(new JScrollPane(productTable), BorderLayout.CENTER);
        
         //Panel at the bottom for the buttons 
        JPanel productControlsPanel = new JPanel();
        
        //the buttons 
        JButton addProductBtn = new JButton("Add Product");
        JButton editProductBtn = new JButton("Edit Product");
        JButton deleteProductBtn = new JButton("Delete Product");
        
        //adding the buttons to the panel 
        productControlsPanel.add(addProductBtn);
        productControlsPanel.add(editProductBtn);
        productControlsPanel.add(deleteProductBtn);
        
        //adding the buttons panel to the main panel 
        productPanel.add(productControlsPanel, BorderLayout.SOUTH);
        
        
        addProductBtn.addActionListener(
        		
        		new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                new AddProductDialog(AdminView.this);
                
                 Refreshtables();}
        });

        editProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = productTable.getSelectedRow();
                if (rowIndex >= 0) {
                    int productId = (int) productTable.getValueAt(rowIndex, 0);
                    new EditProductDialog(AdminView.this, productId);
                    Refreshtables ();
                } else {
                    JOptionPane.showMessageDialog(AdminView.this, "Please select a product to edit.");
                }
            }
        });

        deleteProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = productTable.getSelectedRow();
                if (rowIndex >= 0) {
                    int productId = (int) productTable.getValueAt(rowIndex, 0);
                    try {
                        produitDAO.deleteProduit(productId);
                        JOptionPane.showMessageDialog(AdminView.this, "Product deleted successfully.");
                        Refreshtables ();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(AdminView.this, "Error deleting product.");
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(AdminView.this, "Please select a product to delete.");
                }
            }
        });

        // Order Management Panel
        orderPanel = new JPanel(new BorderLayout());
        orderTableModel = new DefaultTableModel();
        orderTableModel.addColumn("Order ID");
        orderTableModel.addColumn("Client ID");
        orderTableModel.addColumn("Date");
        orderTableModel.addColumn("Status");
        orderTable = new JTable(orderTableModel);
        loadOrderData();
        
        orderPanel.add(new JScrollPane(orderTable), BorderLayout.CENTER);

        JPanel orderControlsPanel = new JPanel();
        JButton updateOrderStatusBtn = new JButton("Update Order Status");
        orderControlsPanel.add(updateOrderStatusBtn);
        orderPanel.add(orderControlsPanel, BorderLayout.SOUTH);

        updateOrderStatusBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//fetches the row index from the table ( if its returns -1 nothing was selected ) 
                int rowIndex = orderTable.getSelectedRow();
                // checking if its > -1 
                if (rowIndex >= 0) {
                	//fetching the value and casting it form string to int 
                    int orderId = (int) orderTable.getValueAt(rowIndex, 0);
                    //show input dialog that returns the input as a string 
                    String newStatus = JOptionPane.showInputDialog(AdminView.this, "Enter new status:");
                    try {
                    	
                        commandeDAO.updateCommandeStatut(orderId, newStatus);
                        
                        JOptionPane.showMessageDialog(AdminView.this, "Order status updated.");
                        
                        // ( very important ) to update the table 
                        Refreshtables ();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(AdminView.this, "Error updating order status.");
                        ex.printStackTrace();
                    }
                } else {
                	// if nothing was selected 
                    JOptionPane.showMessageDialog(AdminView.this, "Please select an order to update.");
                }
            }
        });

        // Stock Management Panel
        stockPanel = new JPanel(new BorderLayout());
        stockTableModel = new DefaultTableModel();
        stockTableModel.addColumn("Product ID");
        stockTableModel.addColumn("Name");
        stockTableModel.addColumn("Current Stock");
        stockTable = new JTable(stockTableModel);
        loadStockData();
        stockPanel.add(new JScrollPane(stockTable), BorderLayout.CENTER);

        JPanel stockControlsPanel = new JPanel();
        JButton updateStockBtn = new JButton("Update Stock");
        stockControlsPanel.add(updateStockBtn);
        stockPanel.add(stockControlsPanel, BorderLayout.SOUTH);
        
        updateStockBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = stockTable.getSelectedRow();
                if (rowIndex >= 0) {
                    int productId = (int) stockTable.getValueAt(rowIndex, 0);
                    int newStock = Integer.parseInt(JOptionPane.showInputDialog(AdminView.this, "Enter new stock value:"));
                    try {
                        produitDAO.updateStock(productId, newStock);
                        JOptionPane.showMessageDialog(AdminView.this, "Stock updated successfully.");
                        Refreshtables ();
                        
                        
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(AdminView.this, "Error updating stock.");
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(AdminView.this, "Please select a product to update stock.");
                }
            }
        });

      
        // Add TabbedPane to JFrame
        add(tabbedPane);
        setVisible(true);
    }

    private void loadProductData() {
        try {
        	//setting the number of rows to 0 
            productTableModel.setRowCount(0);
            for (Produit produit : produitDAO.getAllProduits()) {
            	//populating each row with data of each row in database 
                productTableModel.addRow(new Object[]{
                        produit.getIdProduit(),
                        produit.getNom(),
                        produit.getPrix(),
                        produit.getQuantiteStock()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadOrderData() {
        try {
            orderTableModel.setRowCount(0);
            for (Commande commande : commandeDAO.getAllCommandes()) {
                orderTableModel.addRow(new Object[]{
                        commande.getIdCommande(),
                        commande.getIdClient(),
                        commande.getDateCommande(),
                        commande.getStatut()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }}
        private void loadStockData() {
            try {
                stockTableModel.setRowCount(0);
                for (Produit produit :produitDAO.getAllProduits()) {
                    stockTableModel.addRow(new Object[]{
                            produit.getIdProduit(),
                            produit.getNom(),
                            produit.getQuantiteStock()                });
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
        
        
    
    
        // Add panels to TabbedPane
        tabbedPane.addTab("gestion des produit", productPanel);
        tabbedPane.addTab("Gestion stock", stockPanel);
        tabbedPane.addTab("Gestion des commandes", orderPanel);
       
        
        // Add TabbedPane to JFrame
        add(tabbedPane);
        setVisible(true);
        }

   public void Refreshtables () {
	   loadOrderData();
	   loadStockData();
	   loadProductData();
   }

    public static void main(String[] args) {
        new AdminView();
    }
}