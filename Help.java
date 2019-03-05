import javax.swing.*;
import java.awt.*;

public class Help {
    public JFrame frame;
    public JPanel panel4,panel5,panel6;
    public JButton button5, button6;
    public JLabel label, label1;

    public Help(){
        Gui();
    }
    public void Gui(){

        frame = new JFrame();
        frame.setSize(new Dimension(1000,1000));
        frame.setTitle("Help");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(3,1));
        frame.setVisible(true);

        panel4 = new JPanel();
        panel4.setBackground(Color.cyan);
        panel4.setLayout(new FlowLayout());

        panel5 = new JPanel();
        panel5.setBackground(Color.cyan);
        panel5.setLayout(new FlowLayout());

        panel6 = new JPanel();
        panel6.setBackground(Color.cyan);
        panel6.setLayout(new FlowLayout());

        panel4.setLayout(new GridBagLayout());

        GridBagConstraints c1 = new GridBagConstraints();
        GridBagConstraints c2 = new GridBagConstraints();


        c1.gridx = 0;
        c1.gridy = 0;
        c1.insets = new Insets(0, 0, 0, 0);
        c1.anchor = GridBagConstraints.NORTHEAST;
        c1.weightx = 1;
        c1.weighty = 1;

        c2.gridx = 0;
        c2.gridy = 0;
        c2.insets = new Insets(0,0,0,0);
        c2.weightx = 1;
        c2.weighty = 1;




        button5 = new JButton("Return");
        button5.setPreferredSize(new Dimension(150,40));
        button5.setFont(new Font("Comic Sans", Font.BOLD, 30));
        button5.setLocation(0,900);






        String how = "The goal of sudoku is simple:"+ "fill in the numbers 1-9 exactly once"+ "in every row, column, and 3x3 region";
        label = new JLabel("<html>" + how + "<html>",JLabel.CENTER);
        label.setFont( new Font("Wanderlust", Font.PLAIN, 35));

        label1 = new JLabel("How to Play", JLabel.CENTER);
        label1.setFont(new Font("Arial",Font.BOLD,75));

        frame.getContentPane().add(panel4);
        frame.getContentPane().add(panel5);
        frame.getContentPane().add(panel6);
        panel4.add(button5,c1);
        panel5.add(label);
        panel4.add(label1);

    }
    public static void main(String[] args) {

        new Help();
    }

}
