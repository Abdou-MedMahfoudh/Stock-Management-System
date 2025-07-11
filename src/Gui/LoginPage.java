package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPage() {
        setTitle("Login Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        
        // Title
        JLabel titleLabel = new JLabel("bonjour! Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel(new GridLayout(5, 5, 5, 5));
        centerPanel.add(new JLabel("Username"));
        usernameField = new JTextField();
        centerPanel.add(usernameField);
        centerPanel.add(new JLabel("Password"));
        passwordField = new JPasswordField();
        centerPanel.add(passwordField);
        add(centerPanel, BorderLayout.CENTER);

        // Login Button
        loginButton = new JButton("Login");
        add(loginButton, BorderLayout.SOUTH);

        // Action Listener for Login Button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if ("admin".equals(username) && "admin".equals(password)) {
                    new AdminView();
                    dispose();
                } else if ("client".equals(username) && "client".equals(password)) {
                    
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginPage.this, "mot de passe ou utilisateur incorrecte.");
                }
            }
        });
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}