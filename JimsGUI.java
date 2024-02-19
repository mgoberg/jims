import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JimsGUI extends JFrame {

    public JimsGUI() {
        // Setter navnet på vindu
        super("Inventory Management System");

        // Exit on close
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Setter størrelse på GUI
        setSize(1200,800);

        // Startposisjon 0
        setLocationRelativeTo(null);

        setLayout(null);

        // Stopp Resizing
        setResizable(false);

        addGuiComponents();

    }

    private void addGuiComponents() {
        //Component eksempel søkefelt
        JTextField searchTextField = new JTextField();
        // Setter koordinater for posisjon
        searchTextField.setBounds(15,15,450,45);
        //Her kan man sette font
        searchTextField.setFont(new Font("Dialog", Font.PLAIN, 24));

        //Legger til på progammet
        add(searchTextField);

        // Legg til ny komponent

    }
}