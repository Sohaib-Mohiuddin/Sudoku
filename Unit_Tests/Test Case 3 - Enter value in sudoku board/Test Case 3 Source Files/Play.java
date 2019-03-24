/**
 * Author: Sohaib Mohiuddin
 * Course: Principles of Software and Requirements (Winter 2019)
 * Due Date: March 27, 2019
 * Test Case 3
 * Github Link: https://github.com/sm131/Sudoku
 * 
 * 
 * Play.java 
 * This Test Case is to test whether values can be input into the board by the user
 */

 // IMPORTS FOR Play.java TO WORK
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

// CREATING THE CLASS THAT EXTENDS JFRAME
public class Play extends JFrame {

    public static JFrame frame;
    public JPanel panel1;
    public JLabel title_play, bgimg, label2;
    public JTextArea highscore_text;
    public JMenuBar menubar;
    public JMenu menu_file, submenu;
    public JMenuItem save, item_options, item_quit;

    // FINAL VARIABLE FOR GRID SIZE
    public static final int GRID_SIZE = 9;
    
    // FINAL VARIABLE FOR THE SUBGRID SIZE
    public static final int SUBGRID_SIZE = 3;

    //FINAL VARIABLE FOR EACH CELL SIZE IN THE GRID
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

    // GETTING THE BACKGROUND IMAGE FOR THE JFRAME FROM THE RESOURCES FOLDER
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

    /**
     * CREATING THE CONSTRUCTOR THAT INITIATES THE JFRAME AND ALL COMPONENTS CONTAINED IN THE JFRAME
     * 
     * @param gmode
     */
    public Play(int gmode) {

        // CREATING THE NEW FRAME THAT HAS A SET SIZE, TITLE, CLOSEOPERATION, AND LAYOUT
        frame = new JFrame();
        frame.setSize(1500, 1000);
        frame.setTitle("Play");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        
        // CREATING A LABEL FOR THE BACKGROUND IMAGE TO BE PUT IN
        bgimg = new JLabel("", BGIMG, JLabel.CENTER);
        bgimg.setBounds(0, 0, 1500, 1000);

        // A COPYRIGHT LABEL BECAUSE WHY NOT
        label2 = new JLabel("© A product of JUSS Games Inc.");
        label2.setBounds(650, 880, 200, 50);

        // INITIATING GAMEMODE TO 0
        gamemode = 0;
        this.gamemode = gmode;

        // SETTING THE VARIABLE MASK AS THE METHOD maskGenerator()
        mask = maskGenerator();

        menubar = new JMenuBar();
        menu_file = new JMenu("File");
        item_options = new JMenuItem("Options");
        item_quit = new JMenuItem("Quit");
        menu_file.setFont(FONT_NUMBERS);
        item_options.setFont(FONT_NUMBERS);
        item_quit.setFont(FONT_NUMBERS);
        
        // ACTIONLISTENER FOR THE MENU ITEM QUIT
        item_quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to close?", "Close?",  JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        });

        save = new JMenuItem("Save");
        save.setFont(FONT_NUMBERS);
        menu_file.add(save); menu_file.add(item_options); menu_file.add(item_quit);
        menubar.add(menu_file);

        // SETTING THE TITLE OF THE FRAME 
        title_play = new JLabel("Sudoku-sama");
        title_play.setBounds(610, 100, 340, 70);
        title_play.setFont(TITLE_FONTS);
        Map<TextAttribute, Object> attributes = new HashMap<>(TITLE_FONTS.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        title_play.setFont(TITLE_FONTS.deriveFont(attributes));

        // CREATING THE PANEL THAT WILL CONTAIN THE SUDOKU BOARD
        panel1 = new JPanel();
        panel1.setBackground(Color.PINK);
        panel1.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        panel1.setBounds(430, 180, CELL_SIZE*GRID_SIZE+100, CELL_SIZE*GRID_SIZE+100);
        panel1.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));

        // CREATING AN ABSTRACTACTION FOR THE TEXTFIELDS WHERE THE USER WILL INPUT THE NUMBER
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowPicked = -1;
                int colPicked = -1;

                // SOURCE OF THE ACTION IN THE JTEXTFIELD
                JTextField source = (JTextField)e.getSource();

                boolean found = false;
                for (int row = 0; row < GRID_SIZE && !found; ++row) {
                    for (int col = 0; col < GRID_SIZE && !found; ++col) {
                        if (cells[row][col] == source) {
                            rowPicked = row;
                            colPicked = col;
                            found = true;  // IF THE SOURCE IS FOUND, THE LOOP IS LEFT
                        }
                    }
                }

                // NESTED IF STATEMENT TO SET THE BACKGROUND COLOUR OF THE CELLS ONCE ANOTHER CELL IS SELECTED
                if (previousRowPicked != -1 && previousColPicked != -1) {
                    if(mask[previousRowPicked][previousColPicked]) {
                        cells[previousRowPicked][previousColPicked].setBackground(UNCLICKED_BOX);
                    } else {
                        cells[previousRowPicked][previousColPicked].setBackground(UNCLICKED_BOX);
                    }
                }

                // VARIABLE user_input PARSES THE STRING VALUE OF THE CELLS TO INTEGER
                int user_input = Integer.parseInt(cells[rowPicked][colPicked].getText());
                
                    // IF STATEMENT FOR SETTING THE BACKGROUND COLOUR OF THE CELL IF THE ANSWER THE USER INPUT IS RIGHT OR WRONG
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

        // NESTED FOR LOOP TO CREATE THE JTEXTFIELDS FOR THE SUDOKU BOARD
        for (int i = 0; i < GRID_SIZE; ++i) {
            for (int j = 0; j < GRID_SIZE; ++j) {

                cells[i][j] = new JTextField();
                cells[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                // ADDING THE ACTION TO EACH CELL FROM THE ABSTRACTACTION
                cells[i][j].addActionListener(action);
                cells[i][j].addKeyListener(keyListener);
                
                // IF STATEMENT TO SET THE BORDERS FOR THE SUBGRIDS
                if (i % 3 == 0 && i != 0){
					cells[i][j].setBorder(BorderFactory.createMatteBorder(4, 1, 1, 1, Color.black));
				}
				if (j % 3 == 0 && j != 0){
					cells[i][j].setBorder(BorderFactory.createMatteBorder(1, 4, 1, 1, Color.black));
				}
				if (i % 3 == 0 && j % 3 == 0 && j != 0 && i != 0){
					cells[i][j].setBorder(BorderFactory.createMatteBorder(4, 4, 1, 1, Color.black));
                }
                
                // ADDING EACH CELL TO THE PANEL 
                panel1.add(cells[i][j]);

                // IF STATEMENT THAT SETS THE CELLS AS TRUE OR FALSE FOR IF VALUES ARE ALREADY AVAILABLE ON THE SUDOKU BOARD
                if (mask[i][j]) {
                    cells[i][j].setText("");
                    cells[i][j].setEditable(true);
                    cells[i][j].setBackground(CLICKED_BOX);
                    cells[i][j].setForeground(new Color(0, 0, 0));
                 } else {
                    
                    // FOR LOOP THAT SETS THE TEXT OF THE VALUES THAT ARE SHOWN TO THE USER AND SETS EDITABLE TO FALSE SO THE VALUE
                    // CANNOT BE CHANGED
                    for (int x = 0; x < GRID_SIZE; x++) {
                        for (int y = 0; y < GRID_SIZE; y++) {
                            cells[i][j].setText(puzzle[i][j] + "");
                        }
                    }
                    cells[i][j].setEditable(false);
                    cells[i][j].setBackground(Color.white);
                    cells[i][j].setForeground(new Color(0, 0, 153));
                 }

                 // ALIGNS ALL VALUES TO THE CENTER OF THE JTEXTFIELD FOR AESTHETICS
                 cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                 cells[i][j].setFont(FONT_NUMBERS);
            }
        }

        // ADDING ALL COMPONENTS TO THE FRAME AND BACKGROUND LABEL
        frame.getContentPane().add(title_play);
        frame.setJMenuBar(menubar);
        bgimg.add(label2);
        bgimg.add(panel1);
        frame.add(bgimg);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    // METHOD THAT CREATED THE MASK FOR THE SUDOKU BOARD
    public boolean[][] maskGenerator() {
        Random random = new Random();
        boolean[][] cover = new boolean[GRID_SIZE][GRID_SIZE];
        
        /**
         * SWITCH CASE FOR THE SELECTED GAMEMODE
         * case 1: gamemode = 2 --> Beginner
         * case 2: gamemode = 3 --> Intermediate
         * case 3: gamemode = 4 --> Expert
         * 
         * gamemode CORRESPONDS TO THE RANDOM VALUE 
         */
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
        /**
         * NESTED FOR LOOP THAT GENERATES RANDOM NUMBERS LESS THAN 
         * AND EQUAL TO THE GAMEMODE SELECTED
         */
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

    /**
     * A KEYLISTENER THAT LISTENS TO THE SOURCE AND LIMITS THE AMOUNT OF NUMBERS ENTERED IN EACH TEXTFIELD AND 
     * ALSO DOES NOT ALLOW CHARACTERS SUCH AS (A-Z) TO BE ENTERED IN THE TEXTFIELDS
     */
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

            // SOURCE OF THE ACTION
            JTextField source = (JTextField)keyEvent.getSource();

            boolean found = false;
            for (int row = 0; row < 9 && !found; ++row) {
                for (int col = 0; col < 9 && !found; ++col) {
                    if (cells[row][col] == source) {
                        rowPicked = row;
                        colPicked = col;
                        found = true;  // IF THE SOURCE IS FOUND, THE LOOP IS LEFT
                    }
                }
            }

            char c = keyEvent.getKeyChar();
            // LIMIT TEXTFIELD TO ONE NUMBER AND NO LETTER CHARACTERS
            if (cells[rowPicked][colPicked].getText().length() >= 1 || Character.isAlphabetic(c))
                keyEvent.consume();
        }

    };

    // MAIN METHOD THAT RUNS THE PROGRAM AT BEGINNER LEVEL
    public static void main (String[] args) {
        new Play(2);
    }
}
