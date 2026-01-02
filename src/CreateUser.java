import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateUser extends JFrame implements ActionListener{

    Container container = getContentPane();

    //label for the information
    JLabel firstName = new JLabel("First name");
    JLabel lastName = new JLabel("Last Name");

    JLabel userName = new JLabel("User Name");
    JLabel password = new JLabel("Password");

    //text area
    JTextField fnameText = new JTextField();
    JTextField lnameText = new JTextField();

    JTextField usrnameText = new JTextField();
    JPasswordField pswText = new JPasswordField();

    //button when clicking done
    JButton finish = new JButton("Done");


    CreateUser(){
        setTitle("Create new user");
        setVisible(true);
        setBounds(10,10,370,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        container.setLayout(null);

        // Row 1: First Name
        firstName.setBounds(50, 50, 100, 30);
        fnameText.setBounds(150, 50, 150, 30);

        // Row 2: Last Name
        lastName.setBounds(50, 100, 100, 30);
        lnameText.setBounds(150, 100, 150, 30);

        // Row 3: User Name
        userName.setBounds(50, 150, 100, 30);
        usrnameText.setBounds(150, 150, 150, 30);

        // Row 4: Password
        password.setBounds(50, 200, 100, 30);
        pswText.setBounds(150, 200, 150, 30);

        // Done Button - Centered horizontally
        // Calculation: (Window Width 370 / 2) - (Button Width 100 / 2) = 135
        finish.setBounds(135, 300, 100, 40);

        //insert into container
        container.add(firstName);
        container.add(lastName);
        container.add(userName);
        container.add(password);
        container.add(fnameText);
        container.add(lnameText);
        container.add(usrnameText);
        container.add(pswText);
        container.add(finish);

        addActionEvent();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String firstName = fnameText.getText();
        String lastName = lnameText.getText();

        String userName = usrnameText.getText();
        String password = new String(pswText.getPassword());

        if(e.getSource() == finish){
            if(firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || password.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please fill every field");
            } else {
                //add the values into database
                DataBaseManager.addInfo(userName, password, firstName, lastName);

                JOptionPane.showMessageDialog(this, "Created Successful");

                this.dispose();
            }
        }
    }


    public void addActionEvent(){
        finish.addActionListener(this);
    }
}
