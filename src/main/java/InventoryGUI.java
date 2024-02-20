import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InventoryGUI extends JFrame {

    public InventoryGUI() {
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
        addHeader();
        addOverviewPanel();

    }

    private void addHeader() {
        // Return BTN
        JButton returnButton = new JButton("Back");
        returnButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        returnButton.setBounds(10, 15, 100, 45);
        returnButton.setBackground(Color.WHITE);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new OverviewGUI().setVisible(true);
            }
        });
        add(returnButton);
        // Add Header text
        JLabel headerText = new JLabel("Inventory");
        headerText.setBounds(500,20,500,45);
        headerText.setFont(new Font("Dialog", Font.BOLD, 40));
        add(headerText);

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