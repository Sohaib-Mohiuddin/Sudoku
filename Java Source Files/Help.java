/**
 * Authors: Sohaib Mohiuddin, Umar Riaz, Jan O'Hanlon, Sailajan Sivalingam
 * Course: Principles of Software and Requirements (Winter 2019)
 * Due Date: March 27, 2019
 * Version 1
 * Github Link: https://github.com/sm131/Sudoku
 * 
 * 
 * Help.java 
 * This class is the Help page where instructions on how to play the game are shown to the user.
 */

// THESE IMPORTS ARE REQUIRED FOR THE CODE TO RUN
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// CREATING THE CLASS THAT EXTENDS JFRAME
public class Help extends JFrame {

    public JFrame frame;
    public JButton returnButton, button6;
    public JLabel pageTitle, line1, line2, line3, line4, line5, line6, bgimg, label2;
    public JMenuBar menubar;
    public JMenu menu_file, submenu;
    public JMenuItem item_home, item_quit;

    public static final Color BACKGROUND_COLOUR = new Color(238, 200, 150);
    public static final Font FONT_HELP = new Font("Comic Sans MS", Font.BOLD, 20);
    public static final Font TITLE_FONTS = new Font("Comic Sans MS", Font.BOLD, 50);

    // GETTING THE BACKGROUND IMAGE FOR THE JFRAME FROM THE RESOURCES FOLDER
    Image Background;
    {
        try {
            Background = ImageIO.read(getClass().getResource("Resources/background_image.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Image Background_image = Background.getScaledInstance(1500, 1000, Image.SCALE_DEFAULT);
    ImageIcon BGIMG = new ImageIcon(Background_image);

    // CREATING THE CONSTRUCTOR THAT INITIATES THE JFRAME AND ALL COMPONENTS CONTAINED IN THE JFRAME
    public Help() {

        // CREATING THE NEW FRAME THAT HAS A SET SIZE, TITLE, CLOSEOPERATION, AND LAYOUT
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(1500, 1000));  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Help");
        frame.setLayout(null);
        frame.setResizable(false);

        // CREATING A LABEL FOR THE BACKGROUND IMAGE TO BE PUT IN
        bgimg = new JLabel("", BGIMG, JLabel.CENTER);
        bgimg.setBounds(0, 0, 1500, 1000);

        // A COPYRIGHT LABEL BECAUSE WHY NOT
        label2 = new JLabel("© A product of JUSS Games Inc.");
        label2.setBounds(650, 880, 200, 50);

        menubar = new JMenuBar();
        menu_file = new JMenu("File");
        item_home = new JMenuItem("Home");
        item_quit = new JMenuItem("Quit");
        menu_file.setFont(FONT_HELP);
        item_home.setFont(FONT_HELP);
        item_quit.setFont(FONT_HELP);

        // ACTIONLISTENER FOR THE MENU ITEM QUIT
        item_quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to close?", "Close?",  JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        });

        // ACTIONLISTENER FOR THE MENU ITEM HOME
        item_home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Homepage homepage = new Homepage();
                frame.setVisible(false);
            }
        });
        menu_file.add(item_quit); menu_file.add(item_home);
        menubar.add(menu_file);

        returnButton = new JButton("Return");
        returnButton.setFont(new Font("Comic Sans", Font.BOLD, 30));
        returnButton.setBounds(1250, 860, 200, 50);
        returnButton.setBackground(BACKGROUND_COLOUR);
        returnButton.setBorder(new EmptyBorder(0,0,0,0));

        // SETTING THE TITLE OF THE FRAME 
        pageTitle = new JLabel("How to Play");
        pageTitle.setFont(TITLE_FONTS);
        pageTitle.setBounds(550, 100, 400, 70);
        Map<TextAttribute, Object> attributes = new HashMap<>(TITLE_FONTS.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        pageTitle.setFont(TITLE_FONTS.deriveFont(attributes));

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Homepage homepage = new Homepage();
                frame.setVisible(false);
            }
        });

        line1 = new JLabel("To play, you first pick a blue box and enter the number you think it is,");
        line2 = new JLabel("then you press enter and see if you are correct. If the box turns green");
        line3 = new JLabel("you are correct, if it is red then you are incorrect. The objective");
        line4 = new JLabel("of Sudoku is to fill up the boxes with a number between 1-9 that ");
        line5 = new JLabel("doesn't repeat in the rows, columns, or subgrids. To return to the menu");
        line6 = new JLabel("while playing, press the esc button.");

        line1.setBounds(400,210,800,60);
        line2.setBounds(400,250,800,60);
        line3.setBounds(400,290,800,60);
        line4.setBounds(400,330,800,60);
        line5.setBounds(400,370,800,60);
        line6.setBounds(400,410,800,60);

        line1.setForeground(Color.white);
        line2.setForeground(Color.white);
        line3.setForeground(Color.white);
        line4.setForeground(Color.white);
        line5.setForeground(Color.white);
        line6.setForeground(Color.white);
        line1.setFont(FONT_HELP); line2.setFont(FONT_HELP); line3.setFont(FONT_HELP);
        line4.setFont(FONT_HELP); line5.setFont(FONT_HELP); line6.setFont(FONT_HELP);

        // ADDING ALL COMPONENTS TO THE FRAME AND BACKGROUND LABEL
        frame.setJMenuBar(menubar);
        bgimg.add(returnButton);
        frame.add(pageTitle);
        bgimg.add(label2);
        bgimg.add(line1); 
        bgimg.add(line2); 
        bgimg.add(line3); 
        bgimg.add(line4); 
        bgimg.add(line5); 
        bgimg.add(line6);
        frame.add(bgimg);
 
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    // MAIN METHOD THAT RUNS THE PROGRAM
    public static void main(String[] args) {
        new Help();
        
    }
}
