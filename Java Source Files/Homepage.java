/**
 * Authors: Sohaib Mohiuddin, Umar Riaz, Jan O'Hanlon, Sailajan Sivalingam
 * Course: Principles of Software and Requirements (Winter 2019)
 * Due Date: March 27, 2019
 * Version 1
 * Github Link: https://github.com/sm131/Sudoku
 * 
 * 
 * Homepage.java 
 * This class is the homepage page where the user starts and have access to options, help and quit. 
 */

// THESE IMPORTS ARE REQUIRED FOR THE CODE TO RUN
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicBorders;

// CREATING THE CLASS THAT EXTENDS JFRAME
public class Homepage extends JFrame {

    public JFrame frame;
    public JButton play, options, help, quit, logout;
    public JLabel title, label2 ,bgimg;
    public JMenuBar menubar;
    public JMenu menu_file, submenu;
    public JMenuItem item_options, item_quit;

    public static final Color BACKGROUND_COLOUR = new Color(238, 200, 150);

    public static final Font TITLE_FONTS = new Font("Comic Sans MS", Font.BOLD, 50);
    public static final Font FONT_BUTTONS = new Font("Comic Sans MS", Font.BOLD, 20);

    // FINAL VARIABLE FOR GRID SIZE
    public static final int GRID_SIZE = 9;
    private int gamemodepicked;

    // GETTING THE BACKGROUND IMAGE FOR THE JFRAME FROM THE RESOURCES FOLDER
    Image Background;
    {
        try {
            Background = ImageIO.read(getClass().getResource("Resources/background_image.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    Image Background_image = Background.getScaledInstance(1500, 1000, Image.SCALE_DEFAULT);
    ImageIcon BGIMG = new ImageIcon(Background_image);

    // CREATING THE CONSTRUCTOR THAT INITIATES THE JFRAME AND ALL COMPONENTS CONTAINED IN THE JFRAME
    public Homepage() {

        // CREATING THE NEW FRAME THAT HAS A SET SIZE, TITLE, CLOSEOPERATION, AND LAYOUT
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(1500, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sudoku");
        frame.setLayout(null);
        frame.setResizable(false);
        
        // CREATING A LABEL FOR THE BACKGROUND IMAGE TO BE PUT IN
        bgimg = new JLabel("", BGIMG, JLabel.CENTER);
        bgimg.setBounds(0, 0, 1500, 1000);

        menubar = new JMenuBar();
        menu_file = new JMenu("File");
        item_quit = new JMenuItem("Quit");
        menu_file.setFont(FONT_BUTTONS);
        item_quit.setFont(FONT_BUTTONS);
        
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

        // CREATING THE BUTTONS FOR THE PAGE
        play = new JButton("Play");
        options = new JButton("Options");
        help = new JButton("Help");
        quit = new JButton("Quit");

        play.setBorder(new RoundedBorder(50));
        options.setBorder(new RoundedBorder(50));
        help.setBorder(new RoundedBorder(50));
        quit.setBorder(new RoundedBorder(50));

        play.setBounds(560, 420, 200, 50);
        options.setBounds(560, 480, 200, 50);
        help.setBounds(770, 420, 200, 50);
        quit.setBounds(770, 480, 200, 50);

        play.setFont(FONT_BUTTONS);
        options.setFont(FONT_BUTTONS);
        help.setFont(FONT_BUTTONS);
        quit.setFont(FONT_BUTTONS);

        // SETTING THE TITLE OF THE FRAME 
        title = new JLabel("Welcome to Sudoku-sama");
        title.setBounds(460, 100, 700, 70);
        title.setFont(TITLE_FONTS);
        Map<TextAttribute, Object> attributes = new HashMap<>(TITLE_FONTS.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        title.setFont(TITLE_FONTS.deriveFont(attributes));

        // A COPYRIGHT LABEL BECAUSE WHY NOT
        label2 = new JLabel("© A product of JUSS Games Inc.");
        label2.setBounds(650, 880, 200, 50);

        // ACTIONLISTENER FOR THE HELP BUTTON
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Help help = new Help();
                frame.setVisible(false);
            }
        });

        // ACTIONLISTENER FOR THE OPTIONS BUTTON
        options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Options options = new Options();
                frame.setVisible(false);
            }
        });

        // ACTIONLISTENER FOR THE QUIT BUTTON
        quit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to close?", "Close?",  JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        });

        // ACTIONLISTENER FOR THE PLAY BUTTON
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Options.frame == null) {
                    new Options();
                    frame.setVisible(false);
                } else {
                    Options.Beginner.setVisible(true);
                    Options.Intermediate.setVisible(true);
                    Options.Expert.setVisible(true);
                    Options.modeLabel.setVisible(true);
                    Options.frame.setVisible(true);
                    Options.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(false);
                }
            }
        });

        // ADDING ALL COMPONENTS TO THE FRAME AND BACKGROUND LABEL
        frame.setJMenuBar(menubar);
        bgimg.add(play);
        bgimg.add(options);
        bgimg.add(help);
        bgimg.add(quit);
        frame.add(title);
        frame.add(label2);
        frame.add(bgimg);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // MAIN METHOD THAT RUNS THE PROGRAM
    public static void main(String[] args) {
        Homepage homepage = new Homepage();
    }

    // CLASS THAT CREATES ROUNDED BORDERS FOR ANY COMPONENT IT IS ASSOCIATED WITH
    private static class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }
    
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }
    
        public boolean isBorderOpaque() {
            return true;
        }
    
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
}

