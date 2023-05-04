import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateProduct extends JFrame implements ActionListener
{

    private JLabel NameLabel, PriceLabel;
    private JTextField NameField, PriceField;
    private JButton addButton, disposeButton;  
    public CreateProduct()
    {
        // Set window title
        super("Add a Product to the database");

        // Set window layout
        setLayout(new GridLayout(8, 2));

        // Create form components
        NameLabel = new JLabel("Name of product:");
        PriceLabel = new JLabel("Price Per item:");

        // putting the Field names into TextFields
        NameField = new JTextField(10);
        PriceField = new JTextField(10);
        // the create button.
        addButton = new JButton("Create");
        // the dispose button.
        disposeButton = new JButton("Dispose");

        // Add components to window
        add(NameLabel);
        add(NameField);
        add(PriceLabel);
        add(PriceField);
        add(new JLabel(""));
        add(addButton);
        add(new JLabel(""));
        add(disposeButton);

        // Add button action listener
        addButton.addActionListener(this);
        disposeButton.addActionListener(this);

        // Set window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        // check if dispose button was clicked
        if (e.getSource() == disposeButton) {
            dispose();
            return;
        }

        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/javaproject";
        Connection connection = null;
        PreparedStatement pstat = null;
        String Name = NameField.getText();
        String Price = PriceField.getText();

        int i = 0;
        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "@12July98");
            // create Prepared Statement for inserting data into table
            pstat = connection.prepareStatement("INSERT INTO product (Name, Price) VALUES (?,?)");
            pstat.setString(1, Name);
            pstat.setString(2, Price);

            // insert data into table
            i = pstat.executeUpdate();
            JOptionPane.showMessageDialog(this, i + " record successfully added to the database table.");
        } catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + sqlException.getMessage());
        } finally
        {
            try {
                if (pstat != null)
                {
                    pstat.close();
                }
                if (connection != null)
                {
                    connection.close();
                }
            } catch (SQLException ex) 
            {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) 
    {
        new CreateProduct();
    }
}
