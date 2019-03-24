/**
 * Author: Sohaib Mohiuddin
 * Course: Principles of Software and Requirements (Winter 2019)
 * Due Date: March 27, 2019
 * Test Case 1
 * Github Link: https://github.com/sm131/Sudoku
 * 
 * 
 * Save.java 
 * This Test Case is to test whether the user name and score can be saved to a textfile
 */

// THESE IMPORTS ARE REQUIRED FOR THE CODE TO RUN
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

// NAME OF THE CLASS
public class Save {

    // CREATED THE FRAME, SAVE BUTTON AND LABEL THAT DISPLAYS A STATIC SCORE THAT WILL BE INPUT TO THE SAVEFILE
    // ALONG WITH THE USERS NAME
    private static JFrame frame;
    private static JButton saveValueButton;
    private static JLabel scoreLabel;

    // GETTING THE SAVEFILE
    private File file = new File("savefile2.txt");

    // THE STRING USED FOR USERNAME
    private String username;

    // THE STATIC SCORE VALUE THAT IS USED FOR TESTING THE SCORE BEING INPUT INTO THE SAVEFILE
    private int score = 10;

    // THE CONSTRUCTOR THAT INITIATES THE FRAME AND EVERYTHING IN THE FRAME
    private Save() {

        // CREATING THE NEW FRAME WITH SET SIZE, TITLE, CLOSEOPERATION AND LAYOUT
        frame = new JFrame();
        frame.setSize(500, 300);
        frame.setTitle("Play");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        // CREATING THE LABEL THAT DISPLAYS THE SCORE
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setBounds(200, 50, 100, 50);

        // THE BUTTON USED TO TAKE THE USERNAME AND SCORE AND CALL THE SAVETOFILE METHOD
        saveValueButton = new JButton("Save");
        saveValueButton.setBounds(200, 120, 100, 50);
        saveValueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                // A POPUP COMES UP AND THE USER IS ASKED FOR THEIR NAME 
                username = JOptionPane.showInputDialog("What is your name?");
                // THE INPUTTED USERNAME AND STATIC SCORE VALUE ARE SAVED TO THE FILE
                saveToFile(username + ":" + score);
            }
        });

        // THE FRAME ADDS ALL COMPONENTS THAT ARE INTERACTED WITH
        frame.add(scoreLabel);
        frame.add(saveValueButton);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    /**
     * THE METHOD THAT TAKES THE SCORE AND ENTERS IT INTO THE SAVEFILE
     * 
     * @param finalScore
     */
    private void saveToFile(String finalScore) {
        /**
         * TRY CATCH BLOCK NEEDED FOR ANY EXCEPTIONS
         * 
         * FILEWRITER CREATED, 
         * THEN BUFFEREDWRITER CREATED WITH THE FILEWRITER,
         * THEN PRINTWRITER CREATED WITH THE BUFFEREDWRITER 
         */
        try (FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(finalScore);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
        // CREATING AN NEW OBJECT OF SAVE TO RUN THE PROGRAM
        new Save();
    }
}