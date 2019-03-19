

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Help extends JFrame {

    public JFrame frame;
    public JButton returnButton, button6;
    public JLabel pageTitle, line1, line2, line3, line4, line5, line6;
    public JMenuBar menubar;
    public JMenu menu_file, submenu;
    public JMenuItem item_home, item_quit;

    public static final Color BACKGROUND_COLOUR = new Color(238, 200, 150);

    public static final Font FONT_HELP = new Font("Comic Sans MS", Font.BOLD, 20);
    public static final Font TITLE_FONTS = new Font("Comic Sans MS", Font.BOLD, 50);

    public Help() {
        Gui();
    }
    public void Gui() {

        frame = new JFrame();
        frame.setPreferredSize(new Dimension(1500, 1000));  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Help");
        frame.setLayout(null);
        frame.getContentPane().setBackground(BACKGROUND_COLOUR);

        menubar = new JMenuBar();
        menu_file = new JMenu("File");
        item_home = new JMenuItem("Home");
        item_quit = new JMenuItem("Quit");
        menu_file.setFont(FONT_HELP);
        item_home.setFont(FONT_HELP);
        item_quit.setFont(FONT_HELP);

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
                new Homepage();
                frame.setVisible(false);
            }
        });
        menu_file.add(item_quit); menu_file.add(item_home);
        menubar.add(menu_file);

        returnButton = new JButton("Return");
        returnButton.setFont(new Font("Comic Sans", Font.BOLD, 30));
        returnButton.setBounds(1250, 860, 200, 50);

        pageTitle = new JLabel("How to Play");
        pageTitle.setFont(TITLE_FONTS);
        pageTitle.setBounds(550, 100, 400, 70);

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Homepage();
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

        line1.setFont(FONT_HELP); line2.setFont(FONT_HELP); line3.setFont(FONT_HELP);
        line4.setFont(FONT_HELP); line5.setFont(FONT_HELP); line6.setFont(FONT_HELP);

        frame.setJMenuBar(menubar);
        frame.add(returnButton);
        frame.add(pageTitle);
        frame.add(line1); frame.add(line2); frame.add(line3); frame.add(line4); frame.add(line5); frame.add(line6);
 
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Help();
        
    }
}
