
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


//@SuppressWarnings("serial")
public class Options extends JFrame{

    public JFrame frame;
    public JButton Beginner, Intermediate, Expert, Return;
    public JToggleButton soundButton;
    public JLabel modeLabel, soundLabel;
    public JMenuBar menubar;
    public JMenu menu_file, submenu;
    public JMenuItem item_home, item_quit;

    public static final Color BACKGROUND_COLOUR = new Color(238, 200, 150);

    public static final Font TITLE_FONTS = new Font("Comic Sans MS", Font.BOLD, 50);
    public static final Font MENU_FONTS = new Font("Comic Sans MS", Font.BOLD, 20);

    public Options() {
        Gui();
    }

    public void Gui() {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(1500, 1000));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Options");
        
        frame.setLayout(null);
        frame.getContentPane().setBackground(BACKGROUND_COLOUR);

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
                frame.setVisible(false);
            }
        });
        menu_file.add(item_home); menu_file.add(item_quit);
        menubar.add(menu_file);

        Beginner = new JButton("Beginner (Kouhai)");
        Intermediate = new JButton("Intermediate (Senpai)");
        Expert = new JButton("Expert (Sensei)");
        Return = new JButton("Return");
        soundButton = new JToggleButton("ON/OFF");

        modeLabel = new JLabel("Mode:");
        modeLabel.setFont(new Font("Comic Sans", Font.BOLD, 30));
        modeLabel.setBounds(100, 200, 300, 120);
        soundLabel = new JLabel("Sound:");
        soundLabel.setFont(new Font("Comic Sans", Font.BOLD, 30));
        soundLabel.setBounds(700, 200, 300, 120);

        Beginner.setBounds(70, 300, 160, 40);
        Intermediate.setBounds(70, 360, 160, 40);
        Expert.setBounds(70, 420, 160, 40);
        Return.setBounds(1250, 860, 200, 50);
        soundButton.setBounds(700, 300, 160, 40);

        Return.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Homepage homepage = new Homepage();
                frame.setVisible(false);
            }
        });

        frame.setJMenuBar(menubar);
        frame.add(Beginner);
        frame.add(Intermediate);
        frame.add(Expert);
        frame.add(modeLabel);
        frame.add(soundLabel);
        frame.add(Return);
        frame.add(soundButton);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        new Options();
    }


}
