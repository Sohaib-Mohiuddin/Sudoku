import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//@SuppressWarnings("serial")
public class Homepage extends JFrame{

    public JFrame frame;

    public JButton play, options, help, quit;
    public JLabel title, label2;
    public JMenuBar menu;

    public Homepage() {
        Gui();

    }

    public void Gui() {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(1000, 1000));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sudoku");
        frame.setVisible(true);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.cyan);

        play = new JButton("Play");
        options = new JButton("Options");
        help = new JButton("Help");
        quit = new JButton("Quit");

        play.setBounds(300, 420, 150, 40);
        options.setBounds(300, 480, 150, 40);
        help.setBounds(510, 420, 150, 40);
        quit.setBounds(510, 480, 150, 40);

        title = new JLabel("Welcome to Sudoku-sama");
        title.setFont(new Font("Comic Sans", Font.BOLD, 30));
        title.setBounds(300, 100, 400, 50);

        label2 = new JLabel("© A product of JUSS Games Inc.");
        label2.setBounds(800, 900, 200, 50);

        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Help help = new Help();

                frame.setVisible(false);
            }
        });

        options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Options options = new Options();
                frame.setVisible(false);
            }
        });
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
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Play play = new Play();
                frame.setVisible(false);
            }
        });

        frame.add(play);
        frame.add(options);
        frame.add(help);
        frame.add(quit);
        frame.add(title);
        frame.add(label2);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void main(String[] args) {

        new Homepage();
    }


}
