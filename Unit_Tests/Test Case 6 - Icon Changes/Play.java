/**
 * @Author Umar Riaz
 *
 * Description: This Test case adds icons as buttons to the Jframe and also implements listeners for when the button is
 * pressed
 */
//ALL imports required for the test case
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Play extends JFrame {

    //Stating initial variables that will be used in the code

    public static JFrame frame;
    public JToggleButton hint;
    public JButton help;
    public static JToggleButton sound;

    public static final Font BUTTON_FONTS = new Font("Comic Sans MS", Font.BOLD, 15);

    //Getting music on image from the Resources folder
    Image musicOn;

    {
        try {
            musicOn = ImageIO.read(getClass().getResource("Resources/speaker.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Getting music off icon image from the Resources folder
    Image MusicOff;

    {
        try {
            MusicOff = ImageIO.read(getClass().getResource("Resources/mute.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Scaling the images to look better in on the frame
    Image speakerOnImage = musicOn.getScaledInstance(120, 100, Image.SCALE_DEFAULT);
    Image speakerOffImage = MusicOff.getScaledInstance(120, 100, Image.SCALE_DEFAULT);

    //Getting the hint off image for the JFrame from the Resources folder

    Image hint_off;

    {
        try {
            hint_off = ImageIO.read(getClass().getResource("Resources/lightbulboff.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Getting the hint on image for the JFrame from the Resources folder
    Image hint_on;

    {
        try {
            hint_on = ImageIO.read(getClass().getResource("Resources/lightbulbon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Getting the help image for the JFrame from the Resources folder for the help button
    Image help_popup;

    {
        try {
            help_popup = ImageIO.read(getClass().getResource("Resources/Help_me.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Scaling all icon images to make them look better on the page
    Image hint_off_image = hint_off.getScaledInstance(120, 100, Image.SCALE_DEFAULT);
    ImageIcon hint_off_icon = new ImageIcon(hint_off_image);

    Image hint_on_image = hint_on.getScaledInstance(120, 100, Image.SCALE_DEFAULT);
    ImageIcon hint_on_icon = new ImageIcon(hint_on_image);

    Image help_popup_image = help_popup.getScaledInstance(120, 100, Image.SCALE_DEFAULT);
    ImageIcon help_popup_icon = new ImageIcon(help_popup_image);
//Constructor
    public Play(int gmode) {

        //Setting parameters of the frame

        frame = new JFrame();
        frame.setSize(1500, 1000);
        frame.setTitle("Play");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        //Setting the parameters of help button
        help = new JButton();
        help.setIcon(help_popup_icon);
        help.setFont(BUTTON_FONTS);
        help.setBounds(0, 800, 120, 100);
        help.setContentAreaFilled(false);
        help.setFocusPainted(false);
        help.setBorderPainted(false);

        //Adding an action listener that shows a popup with game instructions
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                JOptionPane.showMessageDialog(null, "To play, you first pick a blue box and enter the number you think it is, \n" +
                        "then you press enter and see if you are correct. If the box turns green \n" +
                        "you are correct, if it is red then you are incorrect. The objective \n" +
                        "of Sudoku is to fill up the boxes with a number between 1-9 that \n" +
                        "doesn't repeat in the rows, columns, or subgrids.");
            }
        });
        //setting parameters for the hint button
        hint = new JToggleButton("");
        hint.setIcon(hint_on_icon);
        hint.setFont(BUTTON_FONTS);
        hint.setBounds(150, 800, 120, 100);
        hint.setContentAreaFilled(false);
        hint.setFocusPainted(false);
        hint.setBorderPainted(false);
        hint.setSelected(true);

        /**
         * Adding an Item Listener that checks the state of the hint Jtoggle button and changes the icon accordingly
         * There are 2 pictures and they change to show a lightbulb turning on and off
         */
        hint.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.DESELECTED) {
                    hint.setIcon(hint_off_icon);
                } else {
                    hint.setIcon(hint_on_icon);
                }
            }
        });
        //Setting the parameters for the sound button
        sound = new JToggleButton();
        sound.setFont(BUTTON_FONTS);
        sound.setBounds(300, 800, 120, 100);
        sound.setContentAreaFilled(false);
        sound.setFocusPainted(false);
        sound.setBorderPainted(false);

        /**
         * Adding an Item Listener that checks the state of the sound Jtoggle button and changes the icon accordingly
         * There are 2 pictures and they change to show a sound button either muted or turned on
         */
        sound.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.DESELECTED) {
                    sound.setIcon(new ImageIcon(speakerOnImage));
                } else {
                    sound.setIcon(new ImageIcon(speakerOffImage));
                }
            }
            }
        );

        //Adding the buttons to the frame
        frame.add(hint);
        frame.add(help);
        frame.add(sound);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    /**
     * Main Method
     * @param args
     */
    public static void main(String[] args){
        new Play(1);

    }
}


