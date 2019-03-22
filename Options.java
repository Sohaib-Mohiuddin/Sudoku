
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import sun.audio.*;


//@SuppressWarnings("serial")
public class Options extends JFrame {

    public JFrame frame;
    public JButton Beginner, Intermediate, Expert, Return;
    public JToggleButton soundButton;
    public JLabel pageTitle, modeLabel, soundLabel, bgimg;
    public JMenuBar menubar;
    public JMenu menu_file, submenu;
    public JMenuItem item_home, item_quit;

    private int gamemodepicked;
    public int gmode;
    private Play start;

    public static final int GRID_SIZE = 9;

    //private boolean[][] mask = maskGenerator();

    public static final Color BACKGROUND_COLOUR = new Color(238, 200, 150);

    public static final Font TITLE_FONTS = new Font("Comic Sans MS", Font.BOLD, 50);
    public static final Font MENU_FONTS = new Font("Comic Sans MS", Font.BOLD, 20);
    public static final Font FONT_BUTTONS = new Font("Comic Sans MS", Font.BOLD, 20);
    public static final Font SUBHEADING_FONTS = new Font("Comic Sans MS", Font.BOLD, 30);

    public String gongFile = "Resources/Music.wav";
    public File musicPath = new File(gongFile);
    public AudioInputStream audioInput;
    public Clip clip;
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

    Image img;
    {
        try {
            img = ImageIO.read(getClass().getResource("Resources/noo.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Image img2;
    {
        try {
            img2 = ImageIO.read(getClass().getResource("Resources/images.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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

    public Options() {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(1500, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Options");
        frame.setLayout(null);
        frame.setResizable(false);
        

        bgimg = new JLabel("", BGIMG, JLabel.CENTER);
        bgimg.setBounds(0, 0, 1500, 1000);

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
        
        item_quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to close?", "Close?",  JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        });
        item_home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Homepage homepage = new Homepage();
                frame.dispose();
            }
        });
        menu_file.add(item_home); menu_file.add(item_quit);
        menubar.add(menu_file);

        Beginner = new JButton("Beginner (Kouhai)");
        Intermediate = new JButton("Intermediate (Senpai)");
        Expert = new JButton("Expert (Sensei)");
        Return = new JButton("Return");
        soundButton = new JToggleButton();

        modeLabel = new JLabel("Mode:");
        modeLabel.setFont(SUBHEADING_FONTS);
        modeLabel.setBounds(100, 200, 300, 120);
        soundLabel = new JLabel("Sound:");
        soundLabel.setFont(SUBHEADING_FONTS);
        soundLabel.setBounds(700, 200, 300, 120);

        Beginner.setBounds(100, 300, 250, 50);
        Intermediate.setBounds(100, 360, 250, 50);
        Expert.setBounds(100, 420, 250, 50);
        Return.setBounds(1250, 860, 250, 50);
        Return.setBackground(BACKGROUND_COLOUR);
        Return.setBorder(new EmptyBorder(0,0,0,0));
        soundButton.setBounds(700, 300, 150, 150);
        soundButton.setIcon(new ImageIcon(img));

        Beginner.setFont(FONT_BUTTONS);
        Intermediate.setFont(FONT_BUTTONS);
        Expert.setFont(FONT_BUTTONS);
        Return.setFont(new Font("Comic Sans", Font.BOLD, 30));
        soundButton.setFont(FONT_BUTTONS);

        Return.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Homepage homepage = new Homepage();
                frame.dispose();
            }
        });
        Beginner.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                gamemodepicked = 1;
                Play play = new Play(gamemodepicked);
                play.maskGenerator();
                frame.dispose();
            }
        });
        Intermediate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                gamemodepicked = 2;
                Play play = new Play(gamemodepicked);
                play.maskGenerator();
                frame.dispose();
            }
        });
        Expert.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                gamemodepicked = 3;
                Play play = new Play(gamemodepicked);
                play.maskGenerator();
                frame.dispose();
            }
        });
        soundButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    soundButton.setIcon(new ImageIcon(img2));
                    try {
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    } catch(Exception ev) {
                        System.out.println("Not working");
                        ev.printStackTrace();
                    }
                } else {
                    soundButton.setIcon(new ImageIcon(img));
                    try {
                        clip.stop();
                    } catch(Exception ev) {
                        System.out.println("Not working");
                        ev.printStackTrace();
                    }
                }
            }
        });

        
        frame.setJMenuBar(menubar);
        frame.add(pageTitle);
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

    public void playSound() {
        try {
            if (musicPath.exists()){
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }

        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
        
    }

    public static void main(String[] args) {
        new Options();
    }
}
// class ContentPanel extends JPanel {
//     Image bgimage = null;
  
//     ContentPanel() {
//       MediaTracker mt = new MediaTracker(this);
//       bgimage = Toolkit.getDefaultToolkit().getImage("Resources/background_image.png");
//       mt.addImage(bgimage, 0);
//       try {
//         mt.waitForAll();
//       } catch (InterruptedException e) {
//         e.printStackTrace();
//       }
//     }
  
//     protected void paintComponent(Graphics g) {
//       super.paintComponent(g);
//       int imwidth = bgimage.getWidth(null);
//       int imheight = bgimage.getHeight(1000);
//       g.drawImage(bgimage, 1, 1, null);
//     }
//   }
