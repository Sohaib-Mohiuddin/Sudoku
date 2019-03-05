import javax.swing.*;
import java.awt.*;
public class Homepage {
    public JFrame frame;
    public JPanel panel1,panel2,panel3;
    public JButton button1, button2, button3, button4;
    public JLabel label1, label2, label3;
    public JMenuBar menu;

    public Homepage(){
        Gui();

    }

    public void Gui() {
        frame = new JFrame();
        frame.setSize(new Dimension(1000, 1000));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sudoku");
        frame.setVisible(true);
        frame.setLayout(new GridLayout(3,1));




        panel1 = new JPanel();
        panel1.setBackground(Color.cyan);
        panel1.setLayout(new FlowLayout());

        panel2 = new JPanel();
        panel2.setBackground(Color.cyan);
        panel2.setLayout(new FlowLayout());

        panel2.setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        GridBagConstraints c2 = new GridBagConstraints();
        GridBagConstraints c3 = new GridBagConstraints();
        GridBagConstraints c4 = new GridBagConstraints();

        c1.gridy = 0;
        c1.gridx = 0;
        c1.insets = new Insets(20,20,0,0);

        c2.gridy = 0;
        c2.gridx = 1;
        c2.insets = new Insets(20,20,0,0);

        c3.gridy = 1;
        c3.gridx = 0;
        c3.insets = new Insets(20,20,0,0);

        c4.gridy = 1;
        c4.gridx = 1;
        c4.insets = new Insets(20,20,0,0);

        panel3 = new JPanel();
        panel3.setBackground(Color.cyan);
        panel3.setLayout(new FlowLayout());






        button1 = new JButton("Play");
        button2 = new JButton("Options");
        button3 = new JButton("Help");
        button4 = new JButton("Quit");

        button1.setPreferredSize(new Dimension(150,40));
        button2.setPreferredSize(new Dimension(150,40));
        button3.setPreferredSize(new Dimension(150,40));
        button4.setPreferredSize(new Dimension(150,40));

        button1.setFont(new Font("Comic Sans", Font.BOLD, 30));
        button2.setFont(new Font("Comic Sans", Font.BOLD, 30));
        button3.setFont(new Font("Comic Sans", Font.BOLD, 30));
        button4.setFont(new Font("Comic Sans", Font.BOLD, 30));

        button1.setMargin(new Insets(10, 10, 10, 10));
        button2.setMargin(new Insets(10, 10, 10, 10));
        button3.setMargin(new Insets(10, 10, 10, 10));
        button4.setMargin(new Insets(10, 10, 10, 10));








        label1 = new JLabel("", JLabel.CENTER);
        label1.setText("Welcome to Sudoku-Sama");
        label1.setFont( new Font("Wanderlust",Font.BOLD, 75));



        frame.getContentPane().add(panel1);
        frame.getContentPane().add(panel2);
        frame.getContentPane().add(panel3);

        panel1.add(label1);
        panel2.add(button1,c1);
        panel2.add(button2,c2);
        panel2.add(button3,c3);
        panel2.add(button4,c4);
        frame.add(panel3);


    }

    public static void main(String[] args) {

        new Homepage();
    }


}


