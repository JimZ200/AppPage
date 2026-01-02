import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame implements ActionListener{

    //declare component
    Container container = getContentPane();

    //label for username and password
    JLabel userLabel = new JLabel("UserName");
    JLabel passwordLabel = new JLabel("Password");

    //area to enter the username and password
    JTextField userTextField = new JTextField();
    JPasswordField passwordTextField = new JPasswordField();

    //buttons along with the label
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JCheckBox view = new JCheckBox("View Password");
    JButton createNewUser = new JButton("Create account");

    LoginPage(){
        //set up window
        setTitle("Welcome");
        setVisible(true);
        setBounds(10,10,370,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setLayoutManager();
        setLocationAndSize();
        addComponentToContainer();
        addActionEvent();
    }

    public void setLayoutManager(){
        container.setLayout(null);
    }

    //set the sizes and the position of each field
    public void setLocationAndSize(){
        userLabel.setBounds(50,150,100,30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordTextField.setBounds(150, 220, 150, 30);
        view.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);
        createNewUser.setBounds(100, 400, 150, 30);
    }

    //add all of the label and buttons to the container
    public void addComponentToContainer(){
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordTextField);
        container.add(view);
        container.add(loginButton);
        container.add(resetButton);
        container.add(createNewUser);
    }

    //make the button pressable (something happening after clicking)
    public void addActionEvent(){
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        view.addActionListener(this);
        createNewUser.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        //login logic
        String userName = userTextField.getText();

        String password = new String(passwordTextField.getPassword());

        //if the user press the login button
        if(e.getSource() == loginButton){
            //check to see if everything is good

            if(userName.isEmpty() || password.isEmpty()){
                JOptionPane.showMessageDialog(this, "Fields should not be empty");
            } else if(DataBaseManager.checkLogin(userName, password)){
                //JOptionPane.showMessageDialog(this, "Success");
                getContentPane().removeAll();

                add(new HomePage(userName));

                revalidate();
                repaint();

            } else {
                JOptionPane.showMessageDialog(this, "Password or username incorrect");
            }
        }

        else if(e.getSource() == resetButton) {
            userTextField.setText("");
            passwordTextField.setText("");
        } else if(e.getSource() == view){
            if(view.isSelected()){
                passwordTextField.setEchoChar((char) 0);
            } else {
                passwordTextField.setEchoChar('*');
            }
        } else if(e.getSource() == createNewUser){
            //take to the create user page
            new CreateUser();
        }

    }

    public void reset(){
        userTextField.setText("");
        passwordTextField.setText("");
    }
    public static void main(String[] args) {
        new LoginPage();
    }
}
