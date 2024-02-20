import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class InventoryGUI extends JFrame {

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
        displayDatabase();
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

    private void displayDatabase() {
        MongoClient client = MongoClients.create("mongodb+srv://martingoberg:root@jims.byniw4p.mongodb.net/?retryWrites=true&w=majority");
        System.out.println("Connected to Items > Items in MongoDB");
        MongoDatabase database = client.getDatabase("items");
        MongoCollection<Document> collection = database.getCollection("items");

        List<Document> documents = collection.find().into(new ArrayList<>());

        String[] columnNames = {"name", "Price", "Description", "Quantity"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Document document : documents) {
            Object[] rowData = {document.get("name"), document.get("price"), document.get("desc"), document.get("qty")};
            model.addRow(rowData);
        }

        JTable table = new JTable(model);
        table.setLayout(new BorderLayout());
        table.setBounds(600, 95, 570, 700);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(Color.BLUE);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setBounds(600, 75, 570, 20);

        add(tableHeader);
        add(table);

        //Text fields
        JTextField nameText = new JTextField();
        nameText.setBounds(200, 200, 150, 35);
        add(nameText);
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(150, 200, 150, 35);
        add(nameLabel);

        JTextField priceText = new JTextField();
        priceText.setBounds(200, 250, 150, 35);
        add(priceText);
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(150, 250, 150, 35);
        add(priceLabel);

        JTextField descText = new JTextField();
        descText.setBounds(200, 300, 150, 35);
        add(descText);
        JLabel descLabel = new JLabel("Description:");
        descLabel.setBounds(120, 300, 150, 35);
        add(descLabel);

        JTextField qtyText = new JTextField();
        qtyText.setBounds(200, 350, 150, 35);
        add(qtyText);
        JLabel qtyLabel = new JLabel("Qty:");
        qtyLabel.setBounds(150, 350, 150, 35);
        add(qtyLabel);

        JButton addItemBtn = new JButton("Add Item");
        // TODO Placeholder spot
        addItemBtn.setBounds(50, 80, 150, 45);
        addItemBtn.setBackground(new Color(240, 240, 241));
        addItemBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName = nameText.getText();
                double price = Double.parseDouble(priceText.getText());
                String desc = descText.getText();
                int qty = Integer.parseInt(qtyText.getText());

                Jims.insertItemIntoMongoDB(itemName,price,desc,qty);
                Jims.updateJTable(table);

            }
        });

        add(addItemBtn);

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