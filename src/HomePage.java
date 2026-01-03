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

    //add new notes button
    JButton add = new JButton("+");

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
        menu.add(done);
        menu.addSeparator();
        menu.add(item2);


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
        setting.setBounds(20,20,100,30);
        exit.setBounds(20, 300, 100, 30);
        add.setBounds(200, 300, 100, 30);
    }

    public void addComponent(){
        this.add(setting);
        this.add(done);
        this.add(exit);
        this.add(add);
    }

    public void addAction(){
        setting.addActionListener(this);
        done.addActionListener(this);
        exit.addActionListener(this);
        add.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == setting) {
            menu.show(setting, 0, setting.getHeight());

        } else if(e.getSource() == done){

            String userColour = colour.getText().toUpperCase();

            userColour = adjustColour(userColour);

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
        } else if(e.getSource() == add){

            //create text area
            JTextArea noteArea = new JTextArea(10, 20);
            noteArea.setLineWrap(true);
            noteArea.setWrapStyleWord(true);

            JScrollPane scrollPane = new JScrollPane(noteArea);

            //show the popup dialog
            int result = JOptionPane.showConfirmDialog(this, scrollPane, "New Notes for " + this.userName, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if(result == JOptionPane.OK_OPTION){
                //save to database
                DataBaseManager.addNotes(noteArea.getText(), this.userName);
            }
        }
    }

    public void setColour(String colour){
        setBackground(Color.decode(colour));
    }

    public String adjustColour(String input){
        StringBuilder result = new StringBuilder();

        result.append('#');

        for(int i = 1; i<=6; i++){
            char curr = input.charAt(i);

            //if the current character is a number then skip
            if(!(curr >= 48 && curr <= 57)){
                //check to see if it's above F
                if(curr > 70){
                    curr = 'F';
                } else if(curr <65){
                    curr = 'A';
                }
            }

            result.append(curr);
        }

        return result.toString();
    }
}
