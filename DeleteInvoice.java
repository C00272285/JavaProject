/*Notes
 * The user can only delete an invoice by entering in an InvoiceID number
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DeleteInvoice extends JFrame implements ActionListener
{

    private JLabel InvoiceIDLabel;
    private JTextField InvoiceIDField;
    private JButton deleteButton;

    public DeleteInvoice()
    {
        // Set window title
        super("Delete an Invoice");

        // Set window layout
        setLayout(new GridLayout(8, 2));

        // Create form components
        InvoiceIDLabel = new JLabel("Invoice ID:");

        // putting the Field names into TextFields
        InvoiceIDField = new JTextField(10);
        
        // the create button.
        deleteButton = new JButton("Delete");

        // Add components to window
        add(InvoiceIDLabel);
        add(InvoiceIDField);

        add(new JLabel(""));
        add(deleteButton);

        // Add button action listener
        deleteButton.addActionListener(this);

        // Set window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setVisible(true);
    }

    
    public void actionPerformed(ActionEvent e)
    {
        // database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/javaproject";
        Connection connection = null;
        PreparedStatement pstat = null;
        String InvoiceID = InvoiceIDField.getText();


        int i = 0;

        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "@12July98");
            // create Prepared Statement for inserting data into table
            pstat = connection.prepareStatement("Delete From invoice Where InvoiceID=?" );
            pstat.setString(1, InvoiceID);

            // insert data into table
            i = pstat.executeUpdate();
            JOptionPane.showMessageDialog(this, i + " record successfully Deleted From the database table.");
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
        new DeleteInvoice();
    }
}
