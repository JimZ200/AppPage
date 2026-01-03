import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.*;

public class HomePage extends JPanel implements ActionListener{

    JPopupMenu menu = new JPopupMenu();

    JLabel colourField = new JLabel("Enter colour");
    JTextField colour = new JTextField(10);
    JButton done = new JButton("Done");
    JMenuItem item2 = new JMenuItem("Close menu");


    //setting button
    JButton setting = new JButton("Setting");

    String userName;

    JButton exit = new JButton("Logout");


    HomePage(String userName){

        this.userName = userName;

        setVisible(true);
        setBounds(10,10,370,600);
        setBackground(Color.decode(DataBaseManager.getColour(this.userName)));

        setLayoutManager();;
        setLocationAndSize();
        addComponent();
        addAction();



        //add to menu
        menu.add(colourField);
        menu.add(colour);
        menu.add(item2);
        menu.add(done);
        menu.addSeparator();

        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
            }
        });


    }


    public void setLayoutManager(){
        setLayout(null);
    }

    public void setLocationAndSize(){
        setting.setBounds(20,20,50,15);
        exit.setBounds(20, 300, 100, 30);
    }

    public void addComponent(){
        this.add(setting);
        this.add(done);
        this.add(exit);
    }

    public void addAction(){
        setting.addActionListener(this);
        done.addActionListener(this);
        exit.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == setting) {
            menu.show(setting, 0, setting.getHeight());

        } else if(e.getSource() == done){

            String userColour = colour.getText();

            //if user did not enter anything then use white
            if(userColour.isEmpty()){
                setBackground(Color.WHITE);

                //OW get the user input then set the colour
            } else {
                DataBaseManager.setColour(userColour, this.userName);

                setColour(DataBaseManager.getColour(this.userName));
            }
        } else if(e.getSource() == exit){
            Window window = SwingUtilities.getWindowAncestor(this);

            window.dispose();

            new LoginPage();
        }
    }

    public void setColour(String colour){
        setBackground(Color.decode(colour));
    }

    public boolean checkColour(String input){
        return (input.length() == 7 && input.charAt(0) == '#');
    }
}
