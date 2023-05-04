import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateCustomer extends JFrame implements ActionListener {

    // Form components
    private JLabel firstNameLabel, lastNameLabel, addressLabel, phoneLabel, emailLabel, info;
    private JTextField firstNameField, lastNameField, addressField, phoneField, emailField;
    private JRadioButton maleRadio, femaleRadio;
    private JButton addButton, cancelButton;

    public CreateCustomer() {
        // Set window title
        super("Create a User");

        // Create form components
        firstNameLabel = new JLabel("First Name:");
        lastNameLabel = new JLabel("Last Name:");
        addressLabel = new JLabel("Address:");
        phoneLabel = new JLabel("Phone Number:");
        emailLabel = new JLabel("Email:");
        info = new JLabel("This screen allows you to add a new Customer to the database");

        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        addressField = new JTextField(10);
        phoneField = new JTextField(10);
        emailField = new JTextField(10);

        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        addButton = new JButton("Create");
        cancelButton = new JButton("Dispose");


        // Set window layout
        setLayout(new GridBagLayout());
        GridBagConstraints Screen = new GridBagConstraints();
        Screen.insets = new Insets(30, 30, 30, 30);
        Screen.anchor = GridBagConstraints.WEST;

        // Add components to window
        Screen.gridx = 0;
        Screen.gridy = 0;
        add(info, Screen);

        Screen.gridx = 0;
        Screen.gridy = 1;
        add(firstNameLabel, Screen);

        Screen.gridx = 1;
        Screen.gridy = 1;
        add(firstNameField, Screen);

        Screen.gridx = 0;
        Screen.gridy = 2;
        add(lastNameLabel, Screen);

        Screen.gridx = 1;
        Screen.gridy = 2;
        add(lastNameField, Screen);

        Screen.gridx = 0;
        Screen.gridy = 3;
        add(addressLabel, Screen);

        Screen.gridx = 1;
        Screen.gridy = 3;
        add(addressField, Screen);

        Screen.gridx = 0;
        Screen.gridy = 4;
        add(phoneLabel, Screen);

        Screen.gridx = 1;
        Screen.gridy = 4;
        add(phoneField, Screen);

        Screen.gridx = 0;
        Screen.gridy = 5;
        add(new JLabel("Gender:"), Screen);

        Screen.gridx = 1;
        Screen.gridy = 5;
        JPanel genderPanel = new JPanel();
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        add(genderPanel, Screen);

        Screen.gridx = 0;
        Screen.gridy = 6;
        add(emailLabel, Screen);

        Screen.gridx = 1;
        Screen.gridy = 6;
        add(emailField, Screen);

        Screen.gridx = 0;
        Screen.gridy = 7;
        Screen.gridwidth = 2;
        Screen.anchor = GridBagConstraints.CENTER;
        add(addButton,Screen);

        Screen.gridx = 1;
        Screen.gridy = 7;
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
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String address = addressField.getText();
        String phonenumber = phoneField.getText();
        String Gender = maleRadio.isSelected() ? "Male" : "Female";
        String email = emailField.getText();

        int i = 0;
        try {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "@12July98");
            // create Prepared Statement for inserting data into table
            pstat = connection.prepareStatement("INSERT INTO customer (FirstName, LastName, address, phonenumber, Gender, email) VALUES (?,?,?,?,?,?)");
            pstat.setString(1, firstName);
            pstat.setString(2, lastName);
            pstat.setString(3, address);
            pstat.setString(4, phonenumber);
            pstat.setString(5, Gender);
            pstat.setString(6, email);

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
        new CreateCustomer();
    }
}
