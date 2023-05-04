import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateInvoice extends JFrame implements ActionListener {

    // Form components
    private JLabel ProductNameLabel, QuantityLabel, TotalCostLabel;
    private JTextField ProductNameField, QuantityField, TotalCostField;
    private JButton addButton, cancelButton;

    public CreateInvoice() {
        // Set window title
        super("Create a User");

        // Create form components
        ProductNameLabel = new JLabel("First Name:");
        QuantityLabel = new JLabel("Last Name:");
        TotalCostLabel = new JLabel("Address:");
    //   info = new JLabel("This screen allows you to add a new Customer to the database");

        ProductNameField = new JTextField(10);
        QuantityField = new JTextField(10);
        TotalCostField = new JTextField(10);

        addButton = new JButton("Create");
        cancelButton = new JButton("Dispose");


        // Set window layout
        setLayout(new GridBagLayout());
        GridBagConstraints Screen = new GridBagConstraints();
        Screen.insets = new Insets(30, 30, 30, 30);
        Screen.anchor = GridBagConstraints.WEST;

        // Add components to window
        /*Screen.gridx = 0;
        Screen.gridy = 0;
        add(info, Screen);*/

        Screen.gridx = 0;
        Screen.gridy = 1;
        add(ProductNameLabel, Screen);

        Screen.gridx = 1;
        Screen.gridy = 1;
        add(ProductNameField, Screen);

        Screen.gridx = 0;
        Screen.gridy = 2;
        add(QuantityLabel, Screen);

        Screen.gridx = 1;
        Screen.gridy = 2;
        add(QuantityField, Screen);

        Screen.gridx = 0;
        Screen.gridy = 3;
        add(TotalCostLabel, Screen);

        Screen.gridx = 1;
        Screen.gridy = 3;
        add(TotalCostField, Screen);

        Screen.gridx = 0;
        Screen.gridy = 4;
        Screen.gridwidth = 2;
        Screen.anchor = GridBagConstraints.CENTER;
        add(addButton,Screen);

        Screen.gridx = 1;
        Screen.gridy = 4;
        Screen.gridwidth = 2;
        Screen.anchor = GridBagConstraints.CENTER;
        add(cancelButton,Screen);

        // Add button action listener
        addButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // Set window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == cancelButton) {
            dispose();
            return;
        }
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/javaproject";
        Connection connection = null;
        PreparedStatement pstat = null;
        String ProductName = ProductNameField.getText();
        String Quantity = QuantityField.getText();
        String TotalCost = TotalCostField.getText();


        int i = 0;
        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "@12July98");
            // create Prepared Statement for inserting data into table
            pstat = connection.prepareStatement("INSERT INTO invoice (ProductName, Quantity, TotalCost) VALUES (?,?,?)");
            pstat.setString(1, ProductName);
            pstat.setString(2, Quantity);
            pstat.setString(3, TotalCost);


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
        new CreateInvoice();
    }
}
