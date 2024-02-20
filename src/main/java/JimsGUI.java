import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class JimsGUI extends JFrame {

    public JimsGUI() {
        // Naming the Window
        super("Inventory Management System");

        // Exit on close
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Window Size
        setSize(1200,800);

        // Start Position
        setLocationRelativeTo(null);

        setLayout(null);

        // Stop Resizing
        setResizable(false);
        addGuiComponents();

    }

    private void addGuiComponents() {

        // Login Text
        JLabel loginText = new JLabel("Admin Login");
        loginText.setBounds(185,200,300,35);
        loginText.setFont(new Font("Dialog", Font.BOLD, 40));
        add(loginText);

        // Add Username text field
        JTextField usernameTextField = new JTextField();
        usernameTextField.setBounds(150,290,300,35);
        usernameTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        usernameTextField.setHorizontalAlignment(JTextField.CENTER);
        add(usernameTextField);

        // Add Password text field
        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setBounds(150,340,300,35);
        passwordTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        passwordTextField.setHorizontalAlignment(JTextField.CENTER);
        add(passwordTextField);

        // Add Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBounds(260, 400, 80, 45);
        loginButton.setBackground(new Color(240, 240, 241));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Jims.authenticateUser(usernameTextField.getText(),passwordTextField.getText())) {
                    JOptionPane.showMessageDialog(JimsGUI.this, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(JimsGUI.this, "Login failed. Invalid credentials.");
                }
            }
        });
        add(loginButton);

        // Add Login Background
        JPanel loginAdmin = new JPanel();
        Color fargeLoginAdmin = new Color(102, 167,197);
        loginAdmin.setBackground(fargeLoginAdmin);
        loginAdmin.setBounds(0, 0, getWidth() / 2, getHeight());
        add(loginAdmin);

        // Add Image to login
        JLabel imageLogin = new JLabel(new ImageIcon("out/production/jims/assets/login.jpg"));
        imageLogin.setBounds(600,0,getWidth()/2,getHeight());
        add(imageLogin);

        // Continue

    }

}




