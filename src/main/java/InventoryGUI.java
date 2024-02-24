import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InventoryGUI extends JFrame {
    private JTextField nameText;
    private JTextField priceText;
    private JTextField descText;
    private JTextField qtyText;
    private JTable table;
    public InventoryGUI() {
        // Naming the Window
        super("Overview");

        // Exit on close
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Window Size
        setSize(1200, 800);

        // Start Position
        setLocationRelativeTo(null);

        setLayout(null);

        // Stop Resizing
        setResizable(false);

        // Add components
        addDeleteButton();
        displayTable();
        addButtons();
        textField();
        addHeader();
        addOverviewPanel();
        addTableSelectionListener();


    }
    private void addTableSelectionListener() {
        // Add selection listener to the table
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = table.getSelectedRow();

                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();

                    // Retrieve data from the selected row
                    String itemName = model.getValueAt(selectedRow, 0).toString();
                    String price = model.getValueAt(selectedRow, 1).toString();
                    String desc = model.getValueAt(selectedRow, 2).toString();
                    String qty = model.getValueAt(selectedRow, 3).toString();

                    // Set the text fields with the retrieved data
                    nameText.setText(itemName);
                    priceText.setText(price);
                    descText.setText(desc);
                    qtyText.setText(qty);
                }
            }
        });
    }

    public void displayTable() {
        table = Jims.getTable();  // Update the reference to the JTable
        add(table);
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(Color.GRAY);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setReorderingAllowed(false);
        tableHeader.setBounds(600, 75, 570, 20);
        add(tableHeader);
    }

    private void addHeader() {
        // Return BTN
        JButton returnButton = new JButton("Back");
        returnButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        returnButton.setBounds(10, 15, 100, 45);
        returnButton.setBackground(Color.WHITE);
        add(returnButton);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new OverviewGUI().setVisible(true);
            }
        });

        // Add Header text
        JLabel headerText = new JLabel("Inventory");
        headerText.setBounds(500, 20, 500, 45);
        headerText.setFont(new Font("Dialog", Font.BOLD, 40));
        add(headerText);

        // Add header panel
        JPanel header = new JPanel();
        Color fargeHeader = new Color(124, 199, 194);
        header.setBackground(fargeHeader);
        header.setBounds(0, 0, 1200, 75);
        add(header);
    }
    public void textField() {
        //Text fields
        nameText = new JTextField();
        nameText.setBounds(200, 200, 150, 35);
        add(nameText);
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(150, 200, 150, 35);
        add(nameLabel);

        priceText = new JTextField();
        priceText.setBounds(200, 250, 150, 35);
        add(priceText);
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(150, 250, 150, 35);
        add(priceLabel);

        descText = new JTextField();
        descText.setBounds(200, 300, 150, 35);
        add(descText);
        JLabel descLabel = new JLabel("Description:");
        descLabel.setBounds(120, 300, 150, 35);
        add(descLabel);

        qtyText = new JTextField();
        qtyText.setBounds(200, 350, 150, 35);
        add(qtyText);
        JLabel qtyLabel = new JLabel("Qty:");
        qtyLabel.setBounds(150, 350, 150, 35);
        add(qtyLabel);
    }
    public void addButtons() {
        // Add item Button
        JButton addItemBtn = new JButton("Add Item");
        // TODO Placeholder spot
        addItemBtn.setBounds(50, 80, 150, 45);
        addItemBtn.setBackground(new Color(240, 240, 241));
        addItemBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(addItemBtn);
        addItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName = nameText.getText();
                double price = Double.parseDouble(priceText.getText());
                String desc = descText.getText();
                int qty = Integer.parseInt(qtyText.getText());

                Jims.insertItemIntoMongoDB(itemName, price, desc, qty);

                // Use the existing table variable in your InventoryGUI class
                DefaultTableModel updatedModel = Jims.getTableModel();
                table.setModel(updatedModel);
                table.repaint();

            }
        });

        // Add Refresh button
        JButton refreshBtn = new JButton("Refresh");
        // TODO Placeholder spot
        refreshBtn.setBounds(200, 80, 150, 45);
        refreshBtn.setBackground(new Color(240, 240, 241));
        refreshBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(refreshBtn);
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Jims.displayDatabase();
                Jims.updateJTable(Jims.getTable());
                Jims.getTable().setModel(Jims.getTable().getModel());
                System.out.println("Number of rows in the model: " + Jims.getTable().getModel().getRowCount());
            }
        });
    }
    private void addDeleteButton() {
        // Add Delete button
        JButton deleteItemBtn = new JButton("Delete Item");
        deleteItemBtn.setBounds(350, 80, 150, 45);
        deleteItemBtn.setBackground(new Color(240, 240, 241));
        deleteItemBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(deleteItemBtn);
        deleteItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();

                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.removeRow(selectedRow);

                    // You may want to add logic to delete the corresponding item from the database
                    // For simplicity, let's assume you have a method in Jims class for this purpose
                    Jims.deleteItemFromMongoDB(selectedRow);

                    System.out.println("Row deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
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