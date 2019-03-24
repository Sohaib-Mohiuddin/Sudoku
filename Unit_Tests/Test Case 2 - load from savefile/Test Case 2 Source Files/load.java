/**
 * @Author: Umar Riaz
 *
 * Description: This Test is for a load from file feature. The file contains all the saved scores of the player and displays the scores
 *              in a text box.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class load {

     // Setting Variables to be used throughout the code
    public JFrame frame;
    public JTextArea highscore_text;
    public static final Font BUTTON_FONTS = new Font("Comic Sans MS", Font.BOLD, 15);
    public JButton click;

    //Constructor Load
    public load(){
        //Creating a Frame to display the result
        frame = new JFrame();
        frame.setSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Load from file test");
        frame.setResizable(false);
        frame.setLayout(null);


        //Creating a textArea so the scores can be printed on to it
        highscore_text = new JTextArea();
        highscore_text.setFont(BUTTON_FONTS);
        highscore_text.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));
        highscore_text.setEditable(false);

        //Adding a scrollbar to the text Area
        JScrollPane scroller = new JScrollPane(highscore_text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setBounds(150,100,200,200);
        scroller.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));

        //Adding a Button to click so that the scores can be loaded from the file onto the textarea
        click = new JButton("Click to reveal scores");
        click.setBounds(150,320,200,40);
        click.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    loadFromFile();

            }
        });

        //Adding scrollbar that includes textarea and the click button to the frame
        frame.add(scroller);
        frame.add(click);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


    }
    //Getting the location of the file needed
    public File file = new File("savefile.txt");

    /**
     * Method to load scores from file
     */

    public void loadFromFile() {
        //try block that scans the file and puts the file contents in an array list
            try {
                String token1;
                Scanner scanner = new Scanner(file);
                List<String> temps = new ArrayList<String>();
                while (scanner.hasNext()) {
                    token1 = scanner.next();
                    temps.add(token1);
                }
                scanner.close();
                //Loop that displays the scores on the textfield
                highscore_text.setText("Scores: \n");
                for (int s = 0; s < temps.size(); s++) {
                    String label = temps.get(s);
                    highscore_text.append((s+1) + ") " + label + "\n");
                }
                //catch block if the file is not found
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    /**
     * Main method
     * @param args
     */
    public static void main (String[] args) {
            new load();
        }

    }


