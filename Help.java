import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
//import java.util.Scanner;
import java.io.File;
import java.io.IOException;


//@SuppressWarnings("serial")
public class Help extends JFrame {

    public JFrame frame;
    public JButton returnButton, button6;
    public JLabel pageTitle, description, test;

    public Help() {
        //Gui();
        //readfile();
    }

    public void Gui() {

        frame = new JFrame();
        frame.setPreferredSize(new Dimension(1500, 1000));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Help");
        
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.cyan);

        returnButton = new JButton("Return");
        returnButton.setFont(new Font("Comic Sans", Font.BOLD, 30));
        returnButton.setBounds(1250, 860, 200, 50);

        pageTitle = new JLabel("How to Play");
        pageTitle.setFont(new Font("Comic Sans", Font.BOLD, 30));
        pageTitle.setBounds(400, 100, 400, 40);

        test = new JLabel();
        test.setBounds(300,100,1000,500);

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Homepage homepage = new Homepage();

                frame.setVisible(false);
            }
        });

        frame.add(returnButton);
        frame.add(pageTitle);
        frame.add(test);
 
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }



    public void readfile() {
        String line;
        File file = new File("help.txt");
        
        try(BufferedReader in = new BufferedReader(new FileReader(file))) {
            
            while ((line = in.readLine()) != null) {
                //line = in.readLine();
                test.setText(line);
                test.setText(test.getText()+line);
                line = in.readLine();
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }






    public static void main(String[] args) {
        Help h = new Help();
        h.Gui();
        h.readfile();
    }
}
