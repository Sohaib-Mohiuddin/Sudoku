
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import java.util.Scanner;
import javax.swing.*;

public class Save {

    private static JFrame frame;
    private static JButton saveValueButton;
    private static JLabel scoreLabel;


    private File file = new File("savefile2.txt");
    private String username;
    private int score = 10;

    private Save() {
        frame = new JFrame();
        frame.setSize(500, 300);
        frame.setTitle("Play");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setBounds(200, 50, 100, 50);

        saveValueButton = new JButton("Save");
        saveValueButton.setBounds(200, 120, 100, 50);
        saveValueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                username = JOptionPane.showInputDialog("What is your name?");
                saveToFile(username + ":" + score);
            }
        });

        frame.add(scoreLabel);
        frame.add(saveValueButton);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void saveToFile(String finalScore) {
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
        new Save();
    }
}