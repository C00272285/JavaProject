import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateCustomer extends JFrame implements ActionListener
{

    private JLabel  firstNameLabel, lastNameLabel, addressLabel, phoneLabel, emailLabel, idLabel;
    private JTextField  firstNameField, lastNameField, addressField, phoneField, emailField, idField;
    private JButton updateButton;
    public UpdateCustomer()
    {
        // Set window title
        super("Update a User");
    
        // Set window layout
        setLayout(new GridLayout(9, 2));
    
        // Create form components
        idLabel = new JLabel("Customer ID:");
        firstNameLabel = new JLabel("First Name:");
        lastNameLabel = new JLabel("Last Name:");
        addressLabel = new JLabel("Address");
        phoneLabel = new JLabel("Phone Number");
        emailLabel = new JLabel("Email");
    
        // putting the Field names into TextFields
        idField = new JTextField(10);
        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        addressField = new JTextField(10);
        phoneField = new JTextField(10);
        emailField = new JTextField(10);
        
        // the create button.
        updateButton = new JButton("Update");
    
        // Add components to window
        add(idLabel);
        add(idField);
        add(firstNameLabel);
        add(firstNameField);
        add(lastNameLabel);
        add(lastNameField);
        add(addressLabel);
        add(addressField);
        add(phoneLabel);
        add(phoneField);
        add(emailLabel);
        add(emailField);
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
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String address = addressField.getText();
        String phonenumber = phoneField.getText();
        String email = emailField.getText();
        String id = idField.getText();
    
        int i = 0;
        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "@12July98");
            // create Prepared Statement for updating data in table
            pstat = connection.prepareStatement("UPDATE customer SET FirstName =?, LastName =?, address =?, phonenumber =?, email =? WHERE CustomerID = ?");
            pstat.setString(1, firstName);
            pstat.setString(2, lastName);
            pstat.setString(3, address);
            pstat.setString(4, phonenumber);
            pstat.setString(5, email);
            pstat.setString(6, id);
    
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
        new UpdateCustomer();
    }
}
