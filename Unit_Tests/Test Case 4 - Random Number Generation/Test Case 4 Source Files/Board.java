/**
 * @Author: Umar Riaz
 *
 * Description: This test case generates the board and also generates random numbers from one to nine and inputs them
 * according to sudoku rules
 */

import javax.swing.*;
import java.awt.*;


public class Board {
    //Variables that are set initially to use throughout the code

    public static JFrame frame;
    public JPanel panel1;

    public static final Font FONT_NUMBERS = new Font("Comic Sans MS", Font.BOLD, 20);

    //setting grid size anf cell size parameters
    private JTextField[][] cells = new JTextField[GRID_SIZE][GRID_SIZE];
    public static final int GRID_SIZE = 9;
    public static final int CELL_SIZE = 60;

    //calling from class gamegenerator.java to implement in board
    gameGenerator random = new gameGenerator();
    private int[][] puzzle = random.getPuzzle();

    //Constructor
    public Board() {
        frame = new JFrame();
        frame.setSize(1500, 1000);
        frame.setTitle("Play");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        //Setting a panel and its parameters for the board
        panel1 = new JPanel();
        panel1.setBackground(Color.PINK);
        panel1.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        panel1.setBounds(430, 180, CELL_SIZE * GRID_SIZE + 100, CELL_SIZE * GRID_SIZE + 100);
        panel1.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));
        /**
         * For loop that creates a  sudoku board and also adds random numbers from 1-9 in the boxes under sudoku rules
         */
        for (int i = 0; i < GRID_SIZE; ++i) {
            for (int j = 0; j < GRID_SIZE; ++j) {
                //creates the board
                cells[i][j] = new JTextField();
                cells[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                if (i % 3 == 0 && i != 0) {
                    cells[i][j].setBorder(BorderFactory.createMatteBorder(4, 1, 1, 1, Color.black));
                }
                if (j % 3 == 0 && j != 0) {
                    cells[i][j].setBorder(BorderFactory.createMatteBorder(1, 4, 1, 1, Color.black));
                }
                if (i % 3 == 0 && j % 3 == 0 && j != 0 && i != 0) {
                    cells[i][j].setBorder(BorderFactory.createMatteBorder(4, 4, 1, 1, Color.black));
                }

                panel1.add(cells[i][j]);
                //Enters random numbers in the cells
                for (int x = 0; x < GRID_SIZE; x++) {
                    for (int y = 0; y < GRID_SIZE; y++) {
                        cells[i][j].setText(puzzle[i][j] + "");
                    }
                }
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(FONT_NUMBERS);
            }
        }
        // Adds the board to the frame
        frame.add(panel1);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    /**
     * Main method calls methods from gamegenerator class
     * @param args
     */
    public static void main(String[] args){
        Board board = new Board();
        board.random.fillValues();
        board.random.printSudoku();
    }
}
