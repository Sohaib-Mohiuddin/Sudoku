import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.List.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class Scrolltest extends JFrame {

    public JFrame frame;
    public JButton returnButton, button6;
    public JLabel pageTitle, line1, line2, line3, line4, line5, line6;
    public JMenuBar menubar;
    public JTextArea highscore_text;
    public JMenu menu_file, submenu;
    public JMenuItem item_home, item_quit;
    public JTextField texting;

    public static final Color BACKGROUND_COLOUR = new Color(238, 200, 150);
    public static final Font FONT_NUMBERS = new Font("Comic Sans MS", Font.BOLD, 20);
    public static final Font BUTTON_FONTS = new Font("Comic Sans MS", Font.BOLD, 15);
    public static final Font TITLE_FONTS = new Font("Comic Sans MS", Font.BOLD, 50);
    public JScrollPane scroller;
    public File file = new File("savefile2.txt");

    public Scrolltest() {
        
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(1500, 1000));  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Help");
        frame.setLayout(null);
        frame.getContentPane().setBackground(BACKGROUND_COLOUR);

        highscore_text = new JTextArea();
        highscore_text.setBounds(50,200,250,200);
        highscore_text.setFont(BUTTON_FONTS);
        highscore_text.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));
        highscore_text.setEditable(false);
        scroller = new JScrollPane(highscore_text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scroller.setBounds(50,200,250,200);
        scroller.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));

        button6 = new JButton();
        button6.setBounds(50,600,250,50);
        button6.setFont(BUTTON_FONTS);
        button6.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));

        texting = new JTextField();
        texting.setBounds(500,200,250,50);
        texting.setFont(BUTTON_FONTS);
        texting.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));

        button6.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                texting.setText("hello");
            }
        });
        
        //frame.add(highscore_text);
        frame.getContentPane().add(scroller);
        frame.getContentPane().add(button6);
        frame.getContentPane().add(texting);
        frame.revalidate();

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void loadFromFile() {
        try {
            int token1;
            Scanner scanner = new Scanner(file);          
            List<Integer> temps = new ArrayList<Integer>();
            while (scanner.hasNext()) {
                token1 = scanner.nextInt();
                temps.add(token1);
            }
            scanner.close();

            highscore_text.setText("Scores: \n");
            for (int s = 0; s < temps.size(); s++) {
                String label = Integer.toString(temps.get(s));
                //String label2 = "<HTML>" + label + "<BR></HTML>";
                
                highscore_text.append((s+1) + ") " + label + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void main (String[] args) {
        Scrolltest test = new Scrolltest();
        test.loadFromFile();
    }
}