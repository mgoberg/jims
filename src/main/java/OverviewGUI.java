import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OverviewGUI extends JFrame {

    public OverviewGUI() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        addButtons();
        addHeader();
        addOverviewPanel();
    }

    private void addButtons() {
        // Add inventory button
        JButton settingsButton = createStyledButton("Settings");
        settingsButton.setBounds(300, 300, 150, 140);
        add(settingsButton);

        // Add inventory button
        JButton inventoryBtn = createStyledButton("Inventory");
        inventoryBtn.setBounds(525, 300, 150, 140);
        inventoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                InventoryGUI inventoryGUI = new InventoryGUI();
                inventoryGUI.setVisible(true);
            }
        });

        add(inventoryBtn);

        // Add placeholder button
        JButton placeholder = createStyledButton("Placeholder");
        placeholder.setBounds(750, 300, 150, 140);
        add(placeholder);

        // Add header text
        JLabel headerText = new JLabel("Inventory Management");
        headerText.setBounds(400, 20, 500, 45);
        headerText.setFont(new Font("Dialog", Font.BOLD, 40));
        headerText.setForeground(Color.WHITE); // Set text color to white
        add(headerText);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBackground(Color.GRAY);
        button.setForeground(Color.WHITE); // Set text color to white
        button.setFont(new Font("Dialog", Font.PLAIN, 18));
        return button;
    }

    private void addHeader() {
        // Add header panel
        JPanel header = new JPanel();
        header.setBackground(new Color(55, 50, 50));
        header.setBounds(0, 0, 1200, 75);
        add(header);
    }

    private void addOverviewPanel() {
        // Add background panel
        JPanel overView = new JPanel();
        overView.setBackground(new Color(50, 50, 50)); // Set dark background color
        overView.setBounds(0, 0, getWidth(), getHeight());
        add(overView);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new OverviewGUI().setVisible(true);
        });
    }
}
