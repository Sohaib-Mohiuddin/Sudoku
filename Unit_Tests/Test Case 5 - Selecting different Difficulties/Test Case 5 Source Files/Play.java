/**
 * Authors: Sohaib Mohiuddin, Umar Riaz, Jan O'Hanlon, Sailajan Sivalingam
 * Course: Principles of Software and Requirements (Winter 2019)
 * Due Date: March 27, 2019
 * Version 1
 * Github Link: https://github.com/sm131/Sudoku
 * 
 * 
 * Play.java 
 * This class is the play page where the sudoku game occurs. The user is shown how many remaining boxes are there to fill, a highscore 
 * list for when the game is finished, a timer for the user to know how long the game has been going on for, [a number panel to drag
 * values into the sudoku board(IN PROGRESS)] and buttons for music, hints, help and return to main menu. 
 */

 //imports for Play.java to work
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicBorders;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Play extends JFrame {

    public static JFrame frame;
    public JPanel panel1;
    public JLabel title_play, bgimg, label2;
    public JTextArea highscore_text;
    public JMenuBar menubar;
    public JMenu menu_file, submenu;
    public JMenuItem save, item_options, item_quit;

    public static final int GRID_SIZE = 9;
    public static final int SUBGRID_SIZE = 3;
    public static final int CELL_SIZE = 60;

    public static final Color RIGHT_ANSWER = Color.GREEN;
    public static final Color WRONG_ANSWER = Color.RED;
    public static final Color UNCLICKED_BOX = Color.white;
    public static final Color CLICKED_BOX = new Color(100, 100, 255);
    public static final Color BACKGROUND_COLOUR = new Color(238, 200, 150);
    public static final Font FONT_NUMBERS = new Font("Comic Sans MS", Font.BOLD, 20);
    public static final Font BUTTON_FONTS = new Font("Comic Sans MS", Font.BOLD, 15);
    public static final Font TITLE_FONTS = new Font("Comic Sans MS", Font.BOLD, 50);

    private int previousRowPicked;
    private int previousColPicked;
    boolean[][] hidden;

    gameGenerator newPuzzle = new gameGenerator();
	private int[][] puzzle = newPuzzle.getPuzzle();
    private boolean[][] mask;

    private JTextField[][] cells = new JTextField[GRID_SIZE][GRID_SIZE];

    private int gamemodepicked;
    public int gamemode;
    public SimpleDateFormat sdf;

    public File file = new File("savefile.txt");

    //Getting the background image for the JFrame from the Resources folder
    Image Background;
    {
        try {
            Background = ImageIO.read(getClass().getResource("background_image.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    Image Background_image = Background.getScaledInstance(1500, 1000, Image.SCALE_DEFAULT);
    ImageIcon BGIMG = new ImageIcon(Background_image);

    public Play(int gmode) {

        frame = new JFrame();
        frame.setSize(1500, 1000);
        frame.setTitle("Play");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        

        bgimg = new JLabel("", BGIMG, JLabel.CENTER);
        bgimg.setBounds(0, 0, 1500, 1000);

        label2 = new JLabel("© A product of JUSS Games Inc.");
        label2.setBounds(650, 880, 200, 50);

        gamemode = 0;
        this.gamemode = gmode;
        mask = maskGenerator();

        menubar = new JMenuBar();
        menu_file = new JMenu("File");
        item_options = new JMenuItem("Options");
        item_quit = new JMenuItem("Quit");
        menu_file.setFont(FONT_NUMBERS);
        item_options.setFont(FONT_NUMBERS);
        item_quit.setFont(FONT_NUMBERS);
        
        item_quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to close?", "Close?",  JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        });
        item_options.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                
                new Options();
                    
            }
        });

        save = new JMenuItem("Save");
        save.setFont(FONT_NUMBERS);
        menu_file.add(item_options); menu_file.add(item_quit);
        menubar.add(menu_file);

        title_play = new JLabel("Sudoku-sama");
        title_play.setBounds(610, 100, 340, 70);
        title_play.setFont(TITLE_FONTS);
        Map<TextAttribute, Object> attributes = new HashMap<>(TITLE_FONTS.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        title_play.setFont(TITLE_FONTS.deriveFont(attributes));


        panel1 = new JPanel();
        panel1.setBackground(Color.PINK);
        panel1.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        panel1.setBounds(430, 180, CELL_SIZE*GRID_SIZE+100, CELL_SIZE*GRID_SIZE+100);
        panel1.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowPicked = -1;
                int colPicked = -1;

                //Source of the action
                JTextField source = (JTextField)e.getSource();

                boolean found = false;
                for (int row = 0; row < GRID_SIZE && !found; ++row) {
                    for (int col = 0; col < GRID_SIZE && !found; ++col) {
                        if (cells[row][col] == source) {
                            rowPicked = row;
                            colPicked = col;
                            found = true;  //Leaves the loop when found
                        }
                    }
                }

                if (previousRowPicked != -1 && previousColPicked != -1) {
                    if(mask[previousRowPicked][previousColPicked]) {
                        cells[previousRowPicked][previousColPicked].setBackground(UNCLICKED_BOX);
                    } else {
                        cells[previousRowPicked][previousColPicked].setBackground(UNCLICKED_BOX);
                    }
                }

                int user_input = Integer.parseInt(cells[rowPicked][colPicked].getText());
                
                    if (user_input == puzzle[rowPicked][colPicked]) {
                        cells[rowPicked][colPicked].setBackground(RIGHT_ANSWER);
                        cells[rowPicked][colPicked].setForeground(new Color(0, 0, 153));
                        cells[rowPicked][colPicked].setEditable(false);
                    } else {
                        cells[rowPicked][colPicked].setBackground(WRONG_ANSWER);
                        cells[rowPicked][colPicked].setText("");
                    }
                

                previousRowPicked = rowPicked;
                previousColPicked = colPicked;
            }
        };

        for (int i = 0; i < GRID_SIZE; ++i) {
            for (int j = 0; j < GRID_SIZE; ++j) {

                cells[i][j] = new JTextField();
                cells[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                cells[i][j].addActionListener(action);
                cells[i][j].addKeyListener(keyListener);
                

                if (i % 3 == 0 && i != 0){
					cells[i][j].setBorder(BorderFactory.createMatteBorder(4, 1, 1, 1, Color.black));
				}
				if (j % 3 == 0 && j != 0){
					cells[i][j].setBorder(BorderFactory.createMatteBorder(1, 4, 1, 1, Color.black));
				}
				if (i % 3 == 0 && j % 3 == 0 && j != 0 && i != 0){
					cells[i][j].setBorder(BorderFactory.createMatteBorder(4, 4, 1, 1, Color.black));
                }
                
                panel1.add(cells[i][j]);

                if (mask[i][j]) {
                    cells[i][j].setText("");
                    cells[i][j].setEditable(true);
                    cells[i][j].setBackground(CLICKED_BOX);
                    //cells[i][j].setForeground(new Color(0, 0, 153));
                    cells[i][j].setForeground(new Color(0, 0, 0));
                    
                 } else {
                    
                    for (int x = 0; x < GRID_SIZE; x++) {
                        for (int y = 0; y < GRID_SIZE; y++) {
                            cells[i][j].setText(puzzle[i][j] + "");
                        }
                    }
                    cells[i][j].setEditable(false);
                    cells[i][j].setBackground(Color.white);
                    cells[i][j].setForeground(new Color(0, 0, 153));
                 }

                 // Beautify all the cells
                 cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                 cells[i][j].setFont(FONT_NUMBERS);
                 
            }
        }

        frame.getContentPane().add(title_play);
        frame.setJMenuBar(menubar);
        //bgimg.add(return_button);
        //bgimg.add(help);
        bgimg.add(label2);
        //bgimg.add(hint);
        //bgimg.add(sound);
        //bgimg.add(remainingCells);
        //bgimg.add(finalscore_label);
        //bgimg.add(scroller);
        bgimg.add(panel1);
        //bgimg.add(num_panel);
        //bgimg.add(timer1);
        frame.add(bgimg);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public boolean[][] maskGenerator() {
        Random random = new Random();
        boolean[][] cover = new boolean[GRID_SIZE][GRID_SIZE];
        switch(gamemode) {
            case 1:
            gamemode = 2;
            break;

            case 2:
            gamemode = 3;
            break;

            case 3:
            gamemode = 4;
            break;
        }
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int randomnum = random.nextInt(5) + 1;
                if (randomnum <= gamemode) {
                    cover[i][j] = true;
                } else {
                    cover[i][j] = false;
                }
            }
        }
        return cover;
    }    

    public void setSelected(boolean b) {
        b = true;
    }

    KeyListener keyListener = new KeyListener() {
        public void keyPressed(KeyEvent keyEvent) {
        }
        public void keyReleased(KeyEvent keyEvent) {
        }

        public void keyTyped(KeyEvent keyEvent) {
            limit(keyEvent);
        }

        private void limit(KeyEvent keyEvent) {
            int rowPicked = -1;
            int colPicked = -1;

            //Source of the action
            JTextField source = (JTextField)keyEvent.getSource();

            boolean found = false;
            for (int row = 0; row < 9 && !found; ++row) {
                for (int col = 0; col < 9 && !found; ++col) {
                    if (cells[row][col] == source) {
                        rowPicked = row;
                        colPicked = col;
                        found = true;  //Leaves the loop when found
                    }
                }
            }
            char c = keyEvent.getKeyChar();
            if (cells[rowPicked][colPicked].getText().length() >= 1 || Character.isAlphabetic(c)) // limit textField to 1 character
                keyEvent.consume();
        }
    };
}
