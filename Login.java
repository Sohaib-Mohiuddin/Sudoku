import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {

    public JFrame frame;
    public JButton login, clear;
    public JLabel title, username, password;
    public JTextField username_input;
    public JPasswordField password_input;
    public JMenuBar menubar;
    public JMenu menu_file;
    public JMenuItem item_quit;

    public static final Color BACKGROUND_COLOUR = new Color(238, 200, 150);

    public static final Font TITLE_FONTS = new Font("Comic Sans MS", Font.BOLD, 50);
    public static final Font FONT_BUTTONS = new Font("Comic Sans MS", Font.BOLD, 20);

    public static final String LOGIN1 = "admin", PASSWORD1 = "principles", LOGIN2 = "sohaib", PASSWORD2 = "software", LOGIN3 = "umar", 
                                PASSWORD3 = "requirements";

    public Login() {
        
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(1500, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Login Page");
        frame.setLayout(null);
        frame.getContentPane().setBackground(BACKGROUND_COLOUR);

        menubar = new JMenuBar();
        menu_file = new JMenu("File");
        item_quit = new JMenuItem("Quit");
        menu_file.setFont(FONT_BUTTONS);
        item_quit.setFont(FONT_BUTTONS);

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

        username = new JLabel("Username");
        password = new JLabel("Password");
        username.setBounds(200, 200, 100, 40);
        password.setBounds(200, 270, 100, 40);
        username.setFont(FONT_BUTTONS);
        password.setFont(FONT_BUTTONS);

        username_input = new JTextField();
        password_input = new JPasswordField();
        username_input.setBounds(350, 200, 200, 40);
        password_input.setBounds(350, 270, 200, 40);
        username_input.setFont(FONT_BUTTONS);
        password_input.setFont(FONT_BUTTONS);

        login = new JButton("Login");
        clear = new JButton("Clear");
        login.setBounds(560, 480, 200, 50);
        clear.setBounds(770, 480, 200, 50);
        login.setFont(FONT_BUTTONS);
        clear.setFont(FONT_BUTTONS);

        login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               String usernameValue = username_input.getText();
               char[] c = password_input.getPassword();
               String passwordValue = new String(c);

               if ((usernameValue.equals(LOGIN1) && passwordValue.equals(PASSWORD1)) || (usernameValue.equals(LOGIN2) && 
               passwordValue.equals(PASSWORD2)) || (usernameValue.equals(LOGIN3) && passwordValue.equals(PASSWORD3))) {
                    JOptionPane.showMessageDialog(null, "You did it");
                    Homepage homepage = new Homepage();
                    frame.setVisible(false);
               } else {
                    JOptionPane.showMessageDialog(null, "Try again");
               }
            }
         });
         clear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               username_input.setText("");
               password_input.setText("");
            }
         });

        frame.add(login);
        frame.add(clear);
        frame.add(username);
        frame.add(password);
        frame.add(username_input);
        frame.add(password_input);

        frame.setJMenuBar(menubar);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main (String[] args) {
        new Login();
    }
}