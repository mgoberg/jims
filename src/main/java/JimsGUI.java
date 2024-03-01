import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JimsGUI extends JFrame {

    public JimsGUI() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }


        // Exit on close
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Window Size
        setSize(800, 600);

        // Start Position
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);  // Use absolute layout
        mainPanel.setBackground(new Color(50, 50, 50));  // Set a dark background color

        // Add components

        addLoginComponent(mainPanel);

        setContentPane(mainPanel);
    }

    private void addLoginComponent(JPanel mainPanel) {
        // Login Text
        JLabel jimsText = new JLabel("JIMS");
        jimsText.setBounds(335, 20, 200, 45);
        jimsText.setFont(new Font("Dialog", Font.BOLD, 50));
        jimsText.setForeground(Color.WHITE);  // Set text color to white
        mainPanel.add(jimsText);

        // Load the image
        ImageIcon icon = new ImageIcon("images/logo.png");
        Image image = icon.getImage();

        // Scale the image
        int width = 160;  // Desired width
        int height = 160; // Desired height
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        // Create a new ImageIcon with the scaled image
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel imageLogin = new JLabel(scaledIcon);
        imageLogin.setBounds(313, 80, width, height);
        mainPanel.add(imageLogin);

        // Login text
        JLabel loginText = new JLabel("Admin Login");
        loginText.setBounds(300, 290, 200, 45);
        loginText.setFont(new Font("Dialog", Font.BOLD, 30));
        loginText.setForeground(Color.WHITE);  // Set text color to white
        mainPanel.add(loginText);

        // Add Username text field
        JTextField usernameTextField = createTextField();
        usernameTextField.setBounds(240, 350, 300, 35);
        mainPanel.add(usernameTextField);

        // Add Password text field
        JPasswordField passwordTextField = createPasswordField();
        passwordTextField.setBounds(240, 400, 300, 35);
        mainPanel.add(passwordTextField);

        // Add Login Button
        JButton loginButton = createButton("Login");
        loginButton.setBounds(340, 450, 100, 45);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Jims.authenticateUser(usernameTextField.getText(),passwordTextField.getText())) {
                    JOptionPane.showMessageDialog(JimsGUI.this, "Login successful!");
                    System.out.println("Login Successful!");
                    dispose();
                    OverviewGUI overviewGUI = new OverviewGUI();
                    overviewGUI.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(JimsGUI.this, "Login failed. Invalid credentials.");
                    System.out.println("Login failed, check credentials!");
                }
            }
        });
        mainPanel.add(loginButton);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Dialog", Font.PLAIN, 18));
        textField.setBackground(new Color(70, 70, 70));  // Set a dark background color
        textField.setForeground(Color.WHITE);  // Set text color to white
        return textField;
    }

    private JPasswordField createPasswordField() {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 18));
        passwordField.setBackground(new Color(70, 70, 70));  // Set a dark background color
        passwordField.setForeground(Color.WHITE);  // Set text color to white
        return passwordField;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFont(new Font("Dialog", Font.PLAIN, 18));
        button.setBackground(new Color(100, 100, 100));  // Set a dark background color
        button.setForeground(Color.WHITE);  // Set text color to white
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JimsGUI().setVisible(true);
        });
    }
}
