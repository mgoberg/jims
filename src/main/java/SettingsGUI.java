import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsGUI extends JFrame {

    public SettingsGUI() {
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
        addHeader();
    }

    private void addHeader() {
        // Return BTN
        JButton returnButton = new JButton("Back");
        returnButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        returnButton.setBounds(10, 15, 100, 45);
        returnButton.setBackground(new Color(50,50,50));
        add(returnButton);
        returnButton.addActionListener(e -> {
            dispose();
            new OverviewGUI().setVisible(true);
        });

        // Add Header text
        JLabel headerText = new JLabel("Settings");
        headerText.setBounds(500, 20, 500, 45);
        headerText.setFont(new Font("Dialog", Font.BOLD, 40));
        headerText.setForeground(Color.WHITE);
        add(headerText);


        // Add header panel
        JPanel header = new JPanel();
        Color fargeHeader = new Color(51, 51, 51);
        header.setBackground(fargeHeader);
        header.setBounds(0, 0, 1200, 75);
        add(header);



    }
}
