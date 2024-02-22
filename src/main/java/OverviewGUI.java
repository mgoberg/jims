import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OverviewGUI extends JFrame {

    public OverviewGUI() {
        // Naming the Window
        super("Overview");

        // Exit on close
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Window Size
        setSize(1200,800);

        // Start Position
        setLocationRelativeTo(null);

        setLayout(null);

        // Stop Resizing
        setResizable(false);
        addButtons();
        addHeader();
        addOverviewPanel();


    }

    private void addButtons() {
        // Add inventory button
        JButton innstillingerbutton = new JButton("Settings");
        innstillingerbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        innstillingerbutton.setBounds(300, 300, 150, 140);
        innstillingerbutton.setBackground(Color.gray);
        add(innstillingerbutton);

        // Add inventar button
        JButton inventarbutton = new JButton("Inventory");
        inventarbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        inventarbutton.setBounds(525, 300, 150, 140);
        inventarbutton.setBackground(Color.gray);
        inventarbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                InventoryGUI inventoryGUI = new InventoryGUI();
                new InventoryGUI().setVisible(true);
            }
        });

        add(inventarbutton);

        // Add håndter inventar button
        JButton håndterbutton = new JButton("Placeholder");
        håndterbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        håndterbutton.setBounds(750, 300, 150, 140);
        håndterbutton.setBackground(Color.gray);
        add(håndterbutton);

        // Add header text
        JLabel headerText = new JLabel("Inventory Management");
        headerText.setBounds(400,20,500,45);
        headerText.setFont(new Font("Dialog", Font.BOLD, 40));
        add(headerText);
    }

    private void addHeader() {
        // Add header panel
        JPanel header = new JPanel();
        Color fargeHeader = new Color(124, 199,194);
        header.setBackground(fargeHeader);
        header.setBounds(0, 0, 1200, 75);
        add(header);
    }

    private void addOverviewPanel() {
        // Add background panel
        JPanel overView = new JPanel();
        Color fargeOverview = new Color(213, 255,255);
        overView.setBackground(fargeOverview);
        overView.setBounds(0, 0, getWidth(), getHeight());
        add(overView);
    }


}