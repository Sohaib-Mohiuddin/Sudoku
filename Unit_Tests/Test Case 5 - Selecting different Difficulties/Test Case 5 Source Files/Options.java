/**
 * Author: Sohaib Mohiuddin
 * Course: Principles of Software and Requirements (Winter 2019)
 * Due Date: March 27, 2019
 * Test Case 5
 * Github Link: https://github.com/sm131/Sudoku
 * 
 * 
 * Options.java 
 * This Test Case is to test whether the user can select different difficulties and have those difficulties reflect
 * on the sudoku board 
 */

 //IMPORTS FOR Options.java TO WORK
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import java.io.*;

// CREATING THE CLASS THAT EXTENDS JFRAME
public class Options extends JFrame {

    public static JFrame frame;
    public static JButton Beginner, Intermediate, Expert, Return;
    public static JToggleButton soundButton;
    public JLabel pageTitle, soundLabel, bgimg, label2;
    public static JLabel modeLabel;
    public JMenuBar menubar;
    public JMenu menu_file, submenu;
    public JMenuItem item_home, item_quit;

    private int gamemodepicked;
    public int gmode;

    // FINAL VARIABLE FOR GRID SIZE
    public static final int GRID_SIZE = 9;

    public static final Color BACKGROUND_COLOUR = new Color(238, 200, 150);

    public static final Font TITLE_FONTS = new Font("Comic Sans MS", Font.BOLD, 50);
    public static final Font MENU_FONTS = new Font("Comic Sans MS", Font.BOLD, 20);
    public static final Font FONT_BUTTONS = new Font("Comic Sans MS", Font.BOLD, 20);
    public static final Font SUBHEADING_FONTS = new Font("Comic Sans MS", Font.BOLD, 30);

    // GETTING THE BACKGROUND IMAGE FOR THE JFRAME FROM THE RESOURCES FOLDER
    Image Background;
    {
        try {
            Background = ImageIO.read(getClass().getResource("background_image.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    Image Background_image = Background.getScaledInstance(1500, 1000, Image.SCALE_DEFAULT);
    ImageIcon BGIMG = new ImageIcon(Background_image);

    // CREATING THE CONSTRUCTOR THAT INITIATES THE JFRAME AND ALL COMPONENTS CONTAINED IN THE JFRAME
    public Options() {

        // CREATING THE NEW FRAME THAT HAS A SET SIZE, TITLE, CLOSEOPERATION, AND LAYOUT
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(1500, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Options");
        frame.setLayout(null);
        frame.setResizable(false);
        
        // CREATING A LABEL FOR THE BACKGROUND IMAGE TO BE PUT IN
        bgimg = new JLabel("", BGIMG, JLabel.CENTER);
        bgimg.setBounds(0, 0, 1500, 1000);

        // A COPYRIGHT LABEL BECAUSE WHY NOT
        label2 = new JLabel("© A product of JUSS Games Inc.");
        label2.setBounds(650, 880, 200, 50);

        // SETTING THE TITLE OF THE FRAME 
        pageTitle = new JLabel("Options");
        pageTitle.setBounds(550, 100, 400, 70);
        pageTitle.setFont(TITLE_FONTS);
        Map<TextAttribute, Object> attributes = new HashMap<>(TITLE_FONTS.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        pageTitle.setFont(TITLE_FONTS.deriveFont(attributes));

        menubar = new JMenuBar();
        menu_file = new JMenu("File");
        item_home = new JMenuItem("Home");
        item_quit = new JMenuItem("Quit");
        menu_file.setFont(MENU_FONTS);
        item_home.setFont(MENU_FONTS);
        item_quit.setFont(MENU_FONTS);
        
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
        menu_file.add(item_quit);
        menubar.add(menu_file);

        // CREATING THE BUTTONS TO SELECT BEGINNER, INTERMEDIATE OR EXPERT
        Beginner = new JButton("Beginner (Kouhai)");
        Intermediate = new JButton("Intermediate (Senpai)");
        Expert = new JButton("Expert (Sensei)");

        // LABEL FOR WHICH MODE TO SELECT
        modeLabel = new JLabel("Mode:");
        modeLabel.setFont(SUBHEADING_FONTS);
        modeLabel.setBounds(100, 200, 300, 120);

        Beginner.setBounds(100, 300, 250, 50);
        Intermediate.setBounds(100, 360, 250, 50);
        Expert.setBounds(100, 420, 250, 50);

        Beginner.setFont(FONT_BUTTONS);
        Intermediate.setFont(FONT_BUTTONS);
        Expert.setFont(FONT_BUTTONS);

        // ACTION LISTENER FOR WHEN Beginner BUTTON IS CLICKED
        Beginner.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // gamemode IS SET TO 1 BEING THE EASIEST AND A NEW GAME IS STARTED WITH THAT GAMEMODE
                gamemodepicked = 1;
                Play play = new Play(gamemodepicked);
                play.maskGenerator();
                frame.setVisible(false);
            }
        });
        // ACTION LISTENER FOR WHEN Intermediate BUTTON IS CLICKED
        Intermediate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // gamemode IS SET TO 2 BEING BETWEEN EASIEST AND HARDEST AND A NEW GAME IS STARTED WITH THAT GAMEMODE
                gamemodepicked = 2;
                Play play = new Play(gamemodepicked);
                play.maskGenerator();
                frame.setVisible(false);
            }
        });
        // ACTION LISTENER FOR WHEN Expert BUTTON IS CLICKED
        Expert.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // gamemode IS SET TO 3 BEING THE HARDEST AND A NEW GAME IS STARTED WITH THAT GAMEMODE
                gamemodepicked = 3;
                Play play = new Play(gamemodepicked);
                play.maskGenerator();
                frame.setVisible(false);
            }
        });

        // ADDING ALL COMPONENTS TO THE FRAME AND BACKGROUND LABEL
        frame.setJMenuBar(menubar);
        frame.add(pageTitle);
        bgimg.add(label2);
        bgimg.add(Beginner);
        bgimg.add(Intermediate);
        bgimg.add(Expert);
        bgimg.add(modeLabel);
        frame.add(bgimg);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // MAIN METHOD THAT RUNS THE PROGRAM
    public static void main(String[] args) {
        new Options();
    }
}
