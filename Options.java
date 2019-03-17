import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


//@SuppressWarnings("serial")
public class Options extends JFrame{

    public JFrame frame;
    public JButton Beginner, Intermediate, Expert, Return;
    public JToggleButton soundButton;
    public JLabel modeLabel, soundLabel;
    public JMenuBar menu;

    public Options() {
        Gui();
    }

    public void Gui() {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(1500, 1000));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Options");
        
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN);


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
