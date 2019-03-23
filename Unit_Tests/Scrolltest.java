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
    public JLabel pageTitle, line1, line2, line3, line4, line5, line6, timer1;
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

    private static int cnt;

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

        timer1 = new JLabel();
        timer1.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        timer1.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.black));
         ActionListener actListner = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
                cnt += 1;
                if (cnt < 10) {
                    timer1.setText("Timer: 00:0" + Integer.toString(cnt));
                }
                else if (cnt < 60){
					timer1.setText("Timer: 00:" + Integer.toString(cnt));
                }
                else if (cnt % 60 < 10) {
                    timer1.setText("Timer: " + Integer.toString(cnt/60) + ":0" + Integer.toString(cnt%60));
                }
                else if (cnt < 600){
                    timer1.setText("Timer: 0" + Integer.toString(cnt/60) + ":" + Integer.toString(cnt%60));
                }
                else {
                    timer1.setText("Timer: " + Integer.toString(cnt/60) + ":" + Integer.toString(cnt%60));
                }
                
                timer1.setBounds(1000,500,250,40);
                timer1.setHorizontalAlignment(JLabel.CENTER);
            }
        };
        Timer timer = new Timer(100, actListner);
        timer.start();

        texting = new JTextField();
        texting.setBounds(500,200,250,50);
        texting.setFont(BUTTON_FONTS);
        texting.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));

        button6.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                texting.setText("hello");
                cnt = 0;
                timer.restart();
            }
        });
        
        //frame.add(highscore_text);
        frame.getContentPane().add(scroller);
        frame.getContentPane().add(button6);
        frame.getContentPane().add(texting);
        frame.getContentPane().add(timer1);
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