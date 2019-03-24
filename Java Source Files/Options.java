/**
 * Authors: Sohaib Mohiuddin, Umar Riaz, Jan O'Hanlon, Sailajan Sivalingam
 * Course: Principles of Software and Requirements (Winter 2019)
 * Due Date: March 27, 2019
 * Version 1
 * Github Link: https://github.com/sm131/Sudoku
 * 
 * 
 * Options.java 
 * This class is the options page where you have access to which difficulty you would like to play as well as the option to turn 
 * music on and off at user discretion
 */

 // THESE IMPORTS ARE REQUIRED FOR THE CODE TO RUN
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

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

    // GETTING THE MUSIC FILE FROM THE Resources FOLDER AND USING AUDIOINPUTSTREAM TO PLAY IT
    public String gongFile = "Resources/Music.wav";
    public File musicPath = new File(gongFile);
    public AudioInputStream audioInput;
    public static Clip clip;
    {
        try {
            audioInput = AudioSystem.getAudioInputStream(musicPath);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
        } catch(Exception e) {
            System.out.println("Error playing music");
            e.printStackTrace();
        }
    }

    // GETTING THE PICTURE FOR THE MUTE TOGGLE FROM THE Resources FOLDER
    Image img;
    {
        try {
            img = ImageIO.read(getClass().getResource("Resources/mute.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // GETTING THE PICTURE FOR THE UNMUTE TOGGLE FROM THE Resources FOLDER
    Image img2;
    {
        try {
            img2 = ImageIO.read(getClass().getResource("Resources/speaker.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // GETTING THE BACKGROUND IMAGE FOR THE JFRAME FROM THE Resources FOLDER
    Image Background;
    {
        try {
            Background = ImageIO.read(getClass().getResource("Resources/background_image.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // SCALING THE BACKGROUND IMAGE TO THE FRAME SIZE
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

        // CREATING THE MENUBAR
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

        // ACTIONLISTENER FOR THE MENU ITEM HOME
        item_home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Homepage homepage = new Homepage();
                frame.setVisible(false);
            }
        });
        menu_file.add(item_home); menu_file.add(item_quit);
        menubar.add(menu_file);

        // CREATING THE BUTTONS TO SELECT BEGINNER, INTERMEDIATE OR EXPERT
        Beginner = new JButton("Beginner (Kouhai)");
        Intermediate = new JButton("Intermediate (Senpai)");
        Expert = new JButton("Expert (Sensei)");
        Return = new JButton("Return");
        soundButton = new JToggleButton();

        // LABEL FOR WHICH MODE TO SELECT
        modeLabel = new JLabel("Mode:");
        modeLabel.setFont(SUBHEADING_FONTS);
        modeLabel.setBounds(100, 200, 300, 120);
        soundLabel = new JLabel("Sound:");
        soundLabel.setFont(SUBHEADING_FONTS);
        soundLabel.setBounds(700, 200, 300, 120);

        Beginner.setBounds(100, 300, 250, 50);
        Intermediate.setBounds(100, 360, 250, 50);
        Expert.setBounds(100, 420, 250, 50);
        Return.setBounds(1250, 860, 200, 50);
        Return.setBackground(BACKGROUND_COLOUR);
        Return.setBorder(new EmptyBorder(0,0,0,0));
        soundButton.setBounds(700, 300, 150, 150);

        // IF STATEMENT TO SET THE SOUND BUTTON ICON
        if (clip.isRunning()) {
            soundButton.setIcon(new ImageIcon(img2));
            soundButton.setSelected(true);
        } else {
            soundButton.setIcon(new ImageIcon(img));
            soundButton.setSelected(false);
        }

        Beginner.setFont(FONT_BUTTONS);
        Intermediate.setFont(FONT_BUTTONS);
        Expert.setFont(FONT_BUTTONS);
        Return.setFont(new Font("Comic Sans", Font.BOLD, 30));
        soundButton.setFont(FONT_BUTTONS);
        soundButton.setContentAreaFilled(false);
        soundButton.setFocusPainted(false);
        soundButton.setBorderPainted(false);

        Return.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Homepage homepage = new Homepage();
                frame.setVisible(false);
            }
        });

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

        // ACTION LISTENER FOR WHEN SOUND TOGGLE BUTTON IS CLICKED
        soundButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // IF STATEMENT THAT LOOKS AT ALL THE VARIATIONS OF BUTTON STATES, CLIP STATES AND PLAY.FRAME STATES
                if (e.getStateChange() == ItemEvent.SELECTED && clip.isRunning() && Play.frame != null) {
                    soundButton.setIcon(new ImageIcon(img2));
                    Play.sound.setSelected(true);
                } 
                else if (e.getStateChange() == ItemEvent.SELECTED && clip.isRunning() && Play.frame == null) {
                    soundButton.setIcon(new ImageIcon(img2));
                }
                else if (e.getStateChange() == ItemEvent.SELECTED && !clip.isRunning() && Play.frame != null) {
                    soundButton.setIcon(new ImageIcon(img2));
                    Play.sound.setSelected(true);
                    playSound();
                } 
                else if (e.getStateChange() == ItemEvent.SELECTED && !clip.isRunning() && Play.frame == null) {
                    soundButton.setIcon(new ImageIcon(img2));
                    playSound();
                }
                else if (e.getStateChange() == ItemEvent.DESELECTED && clip.isRunning() && Play.frame != null) {
                    soundButton.setIcon(new ImageIcon(img));
                    Play.sound.setSelected(false);
                    stopSound();
                }
                else if (e.getStateChange() == ItemEvent.DESELECTED && clip.isRunning() && Play.frame == null) {
                    soundButton.setIcon(new ImageIcon(img));
                    stopSound();
                }
                else if (e.getStateChange() == ItemEvent.DESELECTED && !clip.isRunning() && Play.frame != null) {
                    soundButton.setIcon(new ImageIcon(img));
                    Play.sound.setSelected(false);
                }
                else if (e.getStateChange() == ItemEvent.DESELECTED && !clip.isRunning() && Play.frame == null) {
                    soundButton.setIcon(new ImageIcon(img));
                }
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
        bgimg.add(soundLabel);
        bgimg.add(Return);
        bgimg.add(soundButton);
        frame.add(bgimg);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * THIS METHOD IS TO START THE MUSIC CLIP
     */
    public static void playSound() {
        try {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
        
    }

    /**
     * THIS METHOD IS TO STOP THE MUSIC CLIP
     */
    public static void stopSound() {
        clip.stop();   
    }

    /**
     * MAIN METHOD TO RUN PROGRAM
     * 
     * @param args
     */
    public static void main(String[] args) {
        new Options();
    }
}
