import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Vector;

public class InventoryGUI extends JFrame {
    private JTextField nameTextField;
    private JTextField priceTextField;
    private JTextField descTextField;
    private JTextField qtyTextField;
    private static JTable inventoryTable;

    public InventoryGUI() {
        // Set up the JFrame
        initializeFrame();

        // Add components to the JFrame
        displayTable();
        addButtons();
        addTextFields();
        addHeader();
        addOverviewPanel();
        addTableSelectionListener();

        // Enable table row sorting
        inventoryTable.setAutoCreateRowSorter(true);
    }

    private void initializeFrame() {
        setTitle("Overview");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
    }

    private void addTableSelectionListener() {
        // Add selection listener to the table
        inventoryTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = inventoryTable.getSelectedRow();

            if (selectedRow != -1) {
                DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();

                // Retrieve data from the selected row
                String itemName = model.getValueAt(selectedRow, 0).toString();
                String price = model.getValueAt(selectedRow, 1).toString();
                String desc = model.getValueAt(selectedRow, 2).toString();
                String qty = model.getValueAt(selectedRow, 3).toString();

                // Set the text fields with the retrieved data
                nameTextField.setText(itemName);
                priceTextField.setText(price);
                descTextField.setText(desc);
                qtyTextField.setText(qty);
            }
        });
    }

    private void displayTable() {
        inventoryTable = Jims.getTable();
        JScrollPane scrollPane = new JScrollPane(inventoryTable);
        scrollPane.setBounds(50, 75, 1090, 300);
        add(scrollPane);

        customizeTableHeader();
    }

    private void customizeTableHeader() {
        JTableHeader tableHeader = inventoryTable.getTableHeader();
        tableHeader.setBackground(Color.GRAY);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setReorderingAllowed(false);
        tableHeader.setBounds(600, 75, 570, 20);
        add(tableHeader);

        inventoryTable.setGridColor(Color.LIGHT_GRAY);
    }

    private void addHeader() {
        JButton returnButton = new JButton("Back");
        configureHeaderButton(returnButton, 10, 15, 100, 45);
        returnButton.addActionListener(e -> {
            dispose();
            new OverviewGUI().setVisible(true);
        });

        addHeaderLabel("Inventory", 500, 20, 40);
        addHeaderLabel("Control Panel", 470, 385, 40);

        addHeaderPanel(0, 0, 1200, 75, new Color(124, 199, 194));
        addHeaderPanel(0, 375, 1200, 75, new Color(50, 125, 198));
        addHeaderPanel(0, 400, 350, 500, new Color(99, 125, 200));
        addHeaderPanel(250, 400, 350, 500, new Color(99, 99, 99));
    }

    private void configureHeaderButton(JButton button, int x, int y, int width, int height) {
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBounds(x, y, width, height);
        button.setBackground(Color.WHITE);
        add(button);
    }

    private void addHeaderLabel(String text, int x, int y, int fontSize) {
        JLabel headerText = new JLabel(text);
        headerText.setBounds(x, y, 500, 45);
        headerText.setFont(new Font("Dialog", Font.BOLD, fontSize));
        add(headerText);
    }

    private void addHeaderPanel(int x, int y, int width, int height, Color color) {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(color);
        headerPanel.setBounds(x, y, width, height);
        add(headerPanel);
    }

    private void addOverviewPanel() {
        JPanel overView = new JPanel();
        overView.setBackground(new Color(213, 255, 255));
        overView.setBounds(0, 0, getWidth(), getHeight());
        add(overView);
    }

    private void addTextFields() {
        nameTextField = createAndConfigureTextField(100, 470, 150, 35);
        add(createLabel("Name:", 50, 470));

        priceTextField = createAndConfigureTextField(100, 520, 150, 35);
        add(createLabel("Price:", 50, 520));

        descTextField = createAndConfigureTextField(100, 570, 150, 35);
        add(createLabel("Description:", 20, 570));

        qtyTextField = createAndConfigureTextField(100, 620, 150, 35);
        add(createLabel("Qty:", 50, 620));

        inventoryTable.setFont(new Font("Dialog", Font.PLAIN, 15));
    }

    private JTextField createAndConfigureTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        add(textField);
        return textField;
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 35);
        add(label);
        return label;
    }

    private void addButtons() {
        addButton("Add Item", 100, 670, 150, 45, e -> {
            String itemName = nameTextField.getText();
            double price = Double.parseDouble(priceTextField.getText());
            String desc = descTextField.getText();
            int qty = Integer.parseInt(qtyTextField.getText());

            Jims.insertItemIntoMongoDB(itemName, price, desc, qty);

            DefaultTableModel updatedModel = Jims.getTableModel();
            inventoryTable.setModel(updatedModel);
            inventoryTable.repaint();
        });

        addButton("Refresh", 400, 470, 150, 45, e -> {
            DefaultTableModel updatedModel = Jims.getTableModel();
            inventoryTable.setModel(updatedModel);
            inventoryTable.repaint();
            System.out.println("Number of rows in the model: " + inventoryTable.getModel().getRowCount());
        });

        addButton("Delete selected item", 400, 520, 150, 45, e -> {
            int selectedRow = inventoryTable.getSelectedRow();

            if (selectedRow != -1) {
                DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();
                model.removeRow(selectedRow);

                Jims.deleteItemFromMongoDB(selectedRow);

                System.out.println("Row deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        addButton("Sort by Price", 400, 570, 150, 45, e -> sortTableByColumn("Price"));
        addButton("Sort A-Z", 400, 620, 150, 45, e -> sortTableByColumn("Name"));
        addButton("Sort by Quantity", 400, 670, 150, 45, e -> sortTableByColumn("Quantity"));
    }

    private void addButton(String text, int x, int y, int width, int height, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(new Color(240, 240, 241));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addActionListener(actionListener);
        add(button);
    }

    private void sortTableByColumn(String columnName) {
        DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();
        int columnIndex = model.findColumn(columnName);

        Vector<Vector> data = model.getDataVector();

        data.sort(Comparator.comparing(row -> row.get(columnIndex).toString()));

        model.fireTableDataChanged();
    }
    
}
