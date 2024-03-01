import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Comparator;
import java.util.Vector;

public class InventoryGUI extends JFrame {
    private JTextField nameText;
    private JTextField priceText;
    private JTextField descText;
    private JTextField qtyText;
    private static JTable table;

    public InventoryGUI() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        addSortingButtons();
        addDeleteButton();
        displayTable();
        addButtons();
        textField();
        addHeader();
        addOverviewPanel();
        addTableSelectionListener();

        table.setAutoCreateRowSorter(true);
        setTitle("Inventory Overview");
        setVisible(false);
    }

    private void addTableSelectionListener() {
        // Add selection listener to the table
        table.getSelectionModel().addListSelectionListener(e -> {
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
        });
    }

    public void displayTable() {
        table = Jims.getTable();  // Update the reference to the JTable
        // Wrap the table in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 75, 1090, 300);
        // table.setBackground(Color.BLACK);
        add(scrollPane);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(58, 58, 58));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setReorderingAllowed(false);
        tableHeader.setBounds(600, 75, 570, 20);
        add(tableHeader);
        table.setGridColor(new Color(72, 72, 72));
    }

    private void addHeader() {
        // Return BTN
        JButton returnButton = new JButton("Back");
        returnButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        returnButton.setBounds(10, 15, 100, 45);
        returnButton.setBackground(Color.WHITE);
        add(returnButton);
        returnButton.addActionListener(e -> {
            dispose();
            new OverviewGUI().setVisible(true);
        });

        // Add Header text
        JLabel headerText = new JLabel("Inventory");
        headerText.setBounds(500, 20, 500, 45);
        headerText.setFont(new Font("Dialog", Font.BOLD, 40));
        headerText.setForeground(Color.WHITE);
        add(headerText);

        // Add second Header text
        JLabel SecondHeaderText = new JLabel("Control Panel");
        SecondHeaderText.setBounds(470, 385, 500, 45);
        SecondHeaderText.setFont(new Font("Dialog", Font.BOLD, 40));
        SecondHeaderText.setForeground(Color.WHITE);
        add(SecondHeaderText);

        // Add header panel
        JPanel header = new JPanel();
        Color fargeHeader = new Color(51, 51, 51);
        header.setBackground(fargeHeader);
        header.setBounds(0, 0, 1200, 75);
        add(header);

        // Add second header panel
        JPanel secondHeader = new JPanel();
        Color secondHeaderFarge = new Color(32, 32, 32);
        secondHeader.setBackground(secondHeaderFarge);
        secondHeader.setBounds(0, 375, 1200, 75);
        add(secondHeader);

        // Add item header
        JPanel addItemHeader = new JPanel();
        Color addItemHeaderColor = new Color(41, 41, 41);
        addItemHeader.setBackground(addItemHeaderColor);
        addItemHeader.setBounds(0, 400, 350, 500);
        add(addItemHeader);

        JPanel addItemHeader2 = new JPanel();
        Color addItemHeaderColor2 = new Color(30, 30, 30);
        addItemHeader2.setBackground(addItemHeaderColor2);
        addItemHeader2.setBounds(250, 400, 350, 500);
        add(addItemHeader2);

    }

    public void textField() {
        //Text fields
        nameText = new JTextField();
        nameText.setBounds(100, 470, 150, 35);
        nameText.setBackground(new Color(69, 69, 69));
        nameText.setForeground(Color.WHITE);
        add(nameText);
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 470, 150, 35);
        nameLabel.setForeground(Color.WHITE);
        add(nameLabel);

        priceText = new JTextField();
        priceText.setBounds(100, 520, 150, 35);
        priceText.setBackground(new Color(69, 69, 69));
        priceText.setForeground(Color.WHITE);
        add(priceText);
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(50, 520, 150, 35);
        priceLabel.setForeground(Color.WHITE);
        add(priceLabel);

        descText = new JTextField();
        descText.setBounds(100, 570, 150, 35);
        descText.setBackground(new Color(69, 69, 69));
        descText.setForeground(Color.WHITE);
        add(descText);
        JLabel descLabel = new JLabel("Description:");
        descLabel.setBounds(20, 570, 150, 35);
        descLabel.setForeground(Color.WHITE);
        add(descLabel);

        qtyText = new JTextField();
        qtyText.setBounds(100, 620, 150, 35);
        qtyText.setBackground(new Color(69, 69, 69));
        qtyText.setForeground(Color.WHITE);
        add(qtyText);
        JLabel qtyLabel = new JLabel("Qty:");
        qtyLabel.setBounds(50, 620, 150, 35);
        qtyLabel.setForeground(Color.WHITE);
        add(qtyLabel);
        table.setFont(new Font("Dialog", Font.PLAIN, 15));
    }

    public void addButtons() {
        // Add item Button
        JButton addItemBtn = new JButton("Add Item");
        // TODO Placeholder spot
        addItemBtn.setBounds(100, 670, 150, 45);
        addItemBtn.setBackground(new Color(105, 105, 105));
        addItemBtn.setForeground(Color.WHITE);
        addItemBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(addItemBtn);
        addItemBtn.addActionListener(e -> {
            String itemName = nameText.getText();
            double price = Double.parseDouble(priceText.getText());
            String desc = descText.getText();
            int qty = Integer.parseInt(qtyText.getText());

            Jims.insertItemIntoMongoDB(itemName, price, desc, qty);

            // Use the existing table variable
            DefaultTableModel updatedModel = Jims.getTableModel();
            table.setModel(updatedModel);
            table.repaint();

        });

        // Add Refresh button
        JButton refreshBtn = new JButton("Refresh");
        // TODO Placeholder spot
        refreshBtn.setBounds(400, 470, 150, 45);
        refreshBtn.setBackground(new Color(105, 105, 105));
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(refreshBtn);
        refreshBtn.addActionListener(e -> {
            DefaultTableModel updatedModel = Jims.getTableModel();
            table.setModel(updatedModel);
            table.repaint();
            System.out.println("Number of rows in the model: " + Jims.getTable().getModel().getRowCount());
        });
    }

    private void addDeleteButton() {
        // Add Delete button
        JButton deleteItemBtn = new JButton("Delete selected item");
        deleteItemBtn.setBounds(400, 520, 150, 45);
        deleteItemBtn.setBackground(new Color(105, 105, 105));
        deleteItemBtn.setForeground(Color.WHITE);
        deleteItemBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(deleteItemBtn);
        deleteItemBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();

            if (selectedRow != -1) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.removeRow(selectedRow);

                Jims.deleteItemFromMongoDB(selectedRow);

                System.out.println("Row deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void addSortingButtons() {
        // Add Sort by Price button
        JButton sortByPriceBtn = new JButton("Sort by Price");
        sortByPriceBtn.setBounds(400, 570, 150, 45);
        sortByPriceBtn.setBackground(new Color(105, 105, 105));
        sortByPriceBtn.setForeground(Color.WHITE);
        sortByPriceBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(sortByPriceBtn);
        sortByPriceBtn.addActionListener(e -> sortTableByColumn("Price"));

        // Add Sort A-Z button
        JButton sortAZBtn = new JButton("Sort A-Z");
        sortAZBtn.setBounds(400, 620, 150, 45);
        sortAZBtn.setBackground(new Color(105, 105, 105));
        sortAZBtn.setForeground(Color.WHITE);
        sortAZBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(sortAZBtn);
        sortAZBtn.addActionListener(e -> sortTableByColumn("Name"));

        // Add Sort by Quantity button
        JButton sortByQtyBtn = new JButton("Sort by Quantity");
        sortByQtyBtn.setBounds(400, 670, 150, 45);
        sortByQtyBtn.setBackground(new Color(105, 105, 105));
        sortByQtyBtn.setForeground(Color.WHITE);
        sortByQtyBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(sortByQtyBtn);
        sortByQtyBtn.addActionListener(e -> sortTableByColumn("Quantity"));
    }

    private void sortTableByColumn(String columnName) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int columnIndex = model.findColumn(columnName);

        Vector<Vector> data = model.getDataVector();

        data.sort((Comparator<? super Vector>) (row1, row2) -> {
            String value1 = row1.get(columnIndex).toString();
            String value2 = row2.get(columnIndex).toString();

            // Compare the values
            return value1.compareTo(value2);
        });

        model.fireTableDataChanged();
    }

    private void addOverviewPanel() {
        // Add background panel
        JPanel overView = new JPanel();
        Color fargeOverview = new Color(30, 30, 30);
        overView.setBackground(fargeOverview);
        overView.setBounds(0, 0, getWidth(), getHeight());
        add(overView);
    }
}
