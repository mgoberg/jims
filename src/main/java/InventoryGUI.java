import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Vector;
import java.util.stream.Collectors;


public class InventoryGUI extends JFrame {
    private JTextField nameText;
    private JTextField priceText;
    private JTextField descText;
    private JTextField qtyText;
    private static JTable table;
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
        addSortingButtons();
        addDeleteButton();
        displayTable();
        addButtons();
        textField();
        addHeader();
        addOverviewPanel();
        addTableSelectionListener();

        table.setAutoCreateRowSorter(true);

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
        // Wrap the table in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        Color scrollpanebg = new Color(124, 199, 194);
        scrollPane.setBounds(50, 75, 1090, 300);
       // table.setBackground(Color.BLACK);
        add(scrollPane);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(Color.GRAY);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setReorderingAllowed(false);
        tableHeader.setBounds(600, 75, 570, 20);
        add(tableHeader);
        table.setGridColor(Color.LIGHT_GRAY);
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

        // Add second Header text
        JLabel SecondHeaderText = new JLabel("Control Panel");
        SecondHeaderText.setBounds(470, 385, 500, 45);
        SecondHeaderText.setFont(new Font("Dialog", Font.BOLD, 40));
        add(SecondHeaderText);

        // Add header panel
        JPanel header = new JPanel();
        Color fargeHeader = new Color(124, 199, 194);
        header.setBackground(fargeHeader);
        header.setBounds(0, 0, 1200, 75);
        add(header);

        // Add second header panel
        JPanel secondHeader = new JPanel();
        Color secondHeaderFarge = new Color(50, 125, 198);
        secondHeader.setBackground(secondHeaderFarge);
        secondHeader.setBounds(0, 375, 1200, 75);
        add(secondHeader);

        // Add item header
        JPanel addItemHeader = new JPanel();
        Color addItemHeaderColor = new Color(99, 125, 200);
        addItemHeader.setBackground(addItemHeaderColor);
        addItemHeader.setBounds(0, 400, 350, 500);
        add(addItemHeader);

        JPanel addItemHeader2 = new JPanel();
        Color addItemHeaderColor2 = new Color(99, 99, 99);
        addItemHeader2.setBackground(addItemHeaderColor2);
        addItemHeader2.setBounds(250, 400, 350, 500);
        add(addItemHeader2);

    }
    public void textField() {
        //Text fields
        nameText = new JTextField();
        nameText.setBounds(100, 470, 150, 35);
        add(nameText);
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 470, 150, 35);
        add(nameLabel);

        priceText = new JTextField();
        priceText.setBounds(100, 520, 150, 35);
        add(priceText);
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(50, 520, 150, 35);
        add(priceLabel);

        descText = new JTextField();
        descText.setBounds(100, 570, 150, 35);
        add(descText);
        JLabel descLabel = new JLabel("Description:");
        descLabel.setBounds(20, 570, 150, 35);
        add(descLabel);

        qtyText = new JTextField();
        qtyText.setBounds(100, 620, 150, 35);
        add(qtyText);
        JLabel qtyLabel = new JLabel("Qty:");
        qtyLabel.setBounds(50, 620, 150, 35);
        add(qtyLabel);
        table.setFont(new Font("Dialog", Font.PLAIN, 15));
    }
    public void addButtons() {
        // Add item Button
        JButton addItemBtn = new JButton("Add Item");
        // TODO Placeholder spot
        addItemBtn.setBounds(100, 670, 150, 45);
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

                // Use the existing table variable
                DefaultTableModel updatedModel = Jims.getTableModel();
                table.setModel(updatedModel);
                table.repaint();

            }
        });

        // Add Refresh button
        JButton refreshBtn = new JButton("Refresh");
        // TODO Placeholder spot
        refreshBtn.setBounds(400, 470, 150, 45);
        refreshBtn.setBackground(new Color(240, 240, 241));
        refreshBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(refreshBtn);
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel updatedModel = Jims.getTableModel();
                table.setModel(updatedModel);
                table.repaint();
                System.out.println("Number of rows in the model: " + Jims.getTable().getModel().getRowCount());
            }
        });
    }
    private void addDeleteButton() {
        // Add Delete button
        JButton deleteItemBtn = new JButton("Delete selected item");
        deleteItemBtn.setBounds(400, 520, 150, 45);
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
    private void addSortingButtons() {
        // Add Sort by Price button
        JButton sortByPriceBtn = new JButton("Sort by Price");
        sortByPriceBtn.setBounds(400, 570, 150, 45);
        sortByPriceBtn.setBackground(new Color(240, 240, 241));
        sortByPriceBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(sortByPriceBtn);
        sortByPriceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortTableByColumn("Price");
            }
        });

        // Add Sort A-Z button
        JButton sortAZBtn = new JButton("Sort A-Z");
        sortAZBtn.setBounds(400, 620, 150, 45);
        sortAZBtn.setBackground(new Color(240, 240, 241));
        sortAZBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(sortAZBtn);
        sortAZBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortTableByColumn("Name");
            }
        });

        // Add Sort by Quantity button
        JButton sortByQtyBtn = new JButton("Sort by Quantity");
        sortByQtyBtn.setBounds(400, 670, 150, 45);
        sortByQtyBtn.setBackground(new Color(240, 240, 241));
        sortByQtyBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(sortByQtyBtn);
        sortByQtyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortTableByColumn("Quantity");
            }
        });
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
        Color fargeOverview = new Color(213, 255,255);
        overView.setBackground(fargeOverview);
        overView.setBounds(0, 0, getWidth(), getHeight());
        add(overView);
    }
}