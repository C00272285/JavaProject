import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {

    private JButton CreateCustomer, CreateInvoice, CreateProduct;   //Jbuttons for the create
    private JButton DeleteCustomer, DeleteInvoice, DeleteProduct;   //Jbuttons for the Delete
    private JButton UpdateCustomer, UpdateInvoice, UpdateProduct;   //Jbuttons for the Update
    private JButton RetriveButton;    //Jbutton for Retriveing data from the database tables
    private JLabel Create, Delete, Update, Retrive ;

    public MainMenu() {
        super("Main Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Create = new JLabel("Below are the create Buttons");
        Delete = new JLabel("Below are the Delete Buttons");
        Update = new JLabel("Below are the Update Buttons");
        Retrive = new JLabel("Below is the retrive Button");



        // Create buttons
        CreateCustomer = new JButton("Create Customer Screen");
        CreateInvoice = new JButton("Create Invoice Screen");
        CreateProduct = new JButton("Create Product Screen");
        DeleteCustomer = new JButton("Delete Customer Screen");
        DeleteInvoice = new JButton("Delete Invoice Screen");
        DeleteProduct = new JButton("Delete Product Screen");
        UpdateCustomer = new JButton("Update Customer Screen");
        UpdateInvoice = new JButton("Update Invoice Screen");
        UpdateProduct = new JButton("Update Product Screen");
        RetriveButton = new JButton("Retrieve Screen");

        // Set window layout
        setLayout(new GridBagLayout());
        GridBagConstraints Screen = new GridBagConstraints();
        Screen.insets = new Insets(30, 30, 30, 30);
        Screen.anchor = GridBagConstraints.WEST;

        // Add components to window
        Screen.gridx = 2;
        Screen.gridy = 0;
        add(Create, Screen);

        Screen.gridx = 1;
        Screen.gridy = 1;
        add(CreateCustomer, Screen);

        Screen.gridx = 2;
        Screen.gridy = 1;
        add(CreateInvoice, Screen);

        Screen.gridx = 3;
        Screen.gridy = 1;
        add(CreateProduct, Screen);

        Screen.gridx = 2;
        Screen.gridy = 2;
        add(Delete, Screen);

        Screen.gridx = 1;
        Screen.gridy = 3;
        add(DeleteCustomer, Screen);

        Screen.gridx = 2;
        Screen.gridy = 3;
        add(DeleteInvoice, Screen);

        Screen.gridx = 3;
        Screen.gridy = 3;
        add(DeleteProduct, Screen);

        Screen.gridx = 2;
        Screen.gridy = 4;
        add(Update, Screen);

        Screen.gridx = 1;
        Screen.gridy = 5;
        add(UpdateCustomer, Screen);

        Screen.gridx = 2;
        Screen.gridy = 5;
        add(UpdateInvoice, Screen);

        Screen.gridx = 2;
        Screen.gridy = 5;
        add(UpdateProduct, Screen);

        Screen.gridx = 2;
        Screen.gridy = 6;
        add(Retrive, Screen);

        Screen.gridx = 2;
        Screen.gridy = 7;
        add(RetriveButton, Screen);

         // Add button action listener
         CreateCustomer.addActionListener(this);
         CreateInvoice.addActionListener(this);
         CreateProduct.addActionListener(this);
         DeleteCustomer.addActionListener(this);
         DeleteInvoice.addActionListener(this);
         DeleteProduct.addActionListener(this);
         UpdateCustomer.addActionListener(this);
         UpdateInvoice.addActionListener(this);
         UpdateProduct.addActionListener(this);
         RetriveButton.addActionListener(this);


        // Set window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }
  
    

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == CreateCustomer) {
            new CreateCustomer();
        }
        if (e.getSource() == CreateInvoice) {
            new CreateInvoice();
        }
        if (e.getSource() == CreateProduct) {
            new CreateProduct();
        }
        if (e.getSource() == UpdateCustomer) {
            new UpdateCustomer();
        }
        if (e.getSource() == UpdateInvoice) {
            new UpdateInvoice();
        }
        if (e.getSource() == UpdateProduct) {
            new UpdateProduct();
        }
        if (e.getSource() == DeleteCustomer) {
            new DeleteCustomer();
        }
        if (e.getSource() == DeleteInvoice) {
            new DeleteInvoice();
        }
        if (e.getSource() == RetriveButton)
        {
            new Retrive();
        }
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
