import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateInvoice extends JFrame implements ActionListener
{

    private JLabel ProductNameLabel, QuantityLabel, TotalCostLabel, idLabel;
    private JTextField ProductNameField, QuantityField, TotalCostField, idField;
    private JButton updateButton;  
    public UpdateInvoice()
    {
        // Set window title
        super("Update an Invoice");
    
        // Set window layout
        setLayout(new GridLayout(9, 2));
    
        // Create form components
        idLabel = new JLabel("Invoice ID:");
        ProductNameLabel = new JLabel("Product Name:");
        QuantityLabel = new JLabel("Quantity:");
        TotalCostLabel = new JLabel("Total Cost");
    
        // putting the Field names into TextFields
        idField = new JTextField(10);
        ProductNameField = new JTextField(10);
        QuantityField = new JTextField(10);
        TotalCostField = new JTextField(10);
        
        // the create button.
        updateButton = new JButton("Update");
    
        // Add components to window
        add(idLabel);
        add(idField);
        add(ProductNameLabel);
        add(ProductNameField);
        add(QuantityLabel);
        add(QuantityField);
        add(TotalCostLabel);
        add(TotalCostField);
        add(new JLabel(""));
        add(updateButton);
    
        // Add button action listener
        updateButton.addActionListener(this);
    
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
        String ProductName = ProductNameField.getText();
        String Quantity = QuantityField.getText();
        String TotalCost = TotalCostField.getText();
        String id = idField.getText();
    
        int i = 0;
        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "@12July98");
            // create Prepared Statement for updating data in table
            pstat = connection.prepareStatement("UPDATE invoice SET ProductName =?, Quantity =?, TotalCost =? WHERE InvoiceID = ?");
            pstat.setString(1, ProductName);
            pstat.setString(2, Quantity);
            pstat.setString(3, TotalCost);
            pstat.setString(4, id);
    
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
        new UpdateInvoice();
    }
}
