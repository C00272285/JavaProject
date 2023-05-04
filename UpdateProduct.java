import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateProduct extends JFrame implements ActionListener
{

    private JLabel NameLabel, PriceLabel, idLabel;
    private JTextField NameField, PriceField, idField;
    private JButton UpdateButton;  
    public UpdateProduct()
    {
        // Set window title
        super("Update a Product");
    
        // Set window layout
        setLayout(new GridLayout(9, 2));
    
        // Create form components
        idLabel = new JLabel("Product ID:");
        NameLabel = new JLabel("Product Name:");
        PriceLabel = new JLabel("Price per Product :");
    
        // putting the Field names into TextFields
        idField = new JTextField(10);
        NameField = new JTextField(10);
        PriceField = new JTextField(10);
        
        // the create button.
        UpdateButton = new JButton("Update");
    
        // Add components to window
        add(idLabel);
        add(idField);
        add(NameLabel);
        add(NameField);
        add(PriceLabel);
        add(PriceField);
        add(new JLabel(""));
        add(UpdateButton);
    
        // Add button action listener
        UpdateButton.addActionListener(this);
    
        // Set window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/javaproject";
        Connection connection = null;
        PreparedStatement pstat = null;
        String ProductName = NameField.getText();
        String Price = PriceField.getText();
        String id = idField.getText();
    
        int i = 0;
        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "@12July98");
            // create Prepared Statement for updating data in table
            pstat = connection.prepareStatement("UPDATE product SET Name =?, Price =?, WHERE ProductID = ?");
            pstat.setString(1, ProductName);
            pstat.setString(2, Price);
            pstat.setString(3, id);
    
            // update data in table
            i = pstat.executeUpdate();
            JOptionPane.showMessageDialog(this, i + " record successfully updated in the database table.");
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
        new UpdateProduct();
    }
}
