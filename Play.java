import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Play extends JFrame {

    public static final int GRID_SIZE = 9;
    public static final int SUBGRID_SIZE = 3;

    public static final int CELL_SIZE = 60;
    public static final int CANVAS_WIDTH = CELL_SIZE * GRID_SIZE;
    public static final int CANVAS_HEIGHT = CELL_SIZE * GRID_SIZE;

    public static final Color OPEN_CELL_BGCOLOR = Color.CYAN;
    public static final Color OPEN_CELL_TEXT_YES = new Color(0, 255, 0);
    public static final Color OPEN_CELL_TEXT_NO = Color.RED;
    public static final Color CLOSED_CELL_BGCOLOR = new Color(240, 240, 240);
    public static final Color CLOSED_CELL_TEXT = Color.BLACK;
    public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);

    private JTextField[][] cells = new JTextField[GRID_SIZE][GRID_SIZE];

    private int[][] puzzle = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                              {0, 0, 0, 0, 0, 0, 0, 0, 0},
                              {0, 0, 0, 0, 0, 0, 0, 0, 0},
                              {0, 0, 0, 0, 0, 0, 0, 0, 0},
                              {0, 0, 0, 0, 0, 0, 0, 0, 0},
                              {0, 0, 0, 0, 0, 0, 0, 0, 0},
                              {0, 0, 0, 0, 0, 0, 0, 0, 0},
                              {0, 0, 0, 0, 0, 0, 0, 0, 0},
                              {0, 0, 0, 0, 0, 0, 0, 0, 0}};
    private boolean[][] mask = {{false, false, false, false, false, true, false, false, false},
                                {false, false, false, false, false, false, false, false, true},
                                {false, false, false, false, false, false, false, false, false},
                                {false, false, false, false, false, false, false, false, false},
                                {false, false, false, false, false, false, false, false, false},
                                {false, false, false, false, false, false, false, false, false},
                                {false, false, false, false, false, false, false, false, false},
                                {false, false, false, false, false, false, false, false, false},
                                {false, false, false, false, false, false, false, false, false}};

    public Play() {
        Container container = getContentPane();
        container.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        for (int i = 0; i < GRID_SIZE; ++i) {
            for (int j = 0; j < GRID_SIZE; ++j) {
                cells[i][j] = new JTextField();
                container.add(cells[i][j]);

                if (mask[i][j]) {
                    cells[i][j].setText("");
                    cells[i][j].setEditable(true);
                    cells[i][j].setBackground(OPEN_CELL_BGCOLOR);

                    // Add ActionEvent listener to process the input

                 } else {
                    cells[i][j].setText(puzzle[i][j] + "");
                    cells[i][j].setEditable(false);
                    cells[i][j].setBackground(CLOSED_CELL_BGCOLOR);
                    cells[i][j].setForeground(CLOSED_CELL_TEXT);
                 }
                 // Beautify all the cells
                 cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                 cells[i][j].setFont(FONT_NUMBERS);
            }
        }

        container.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sudoku");
        setVisible(true);
    }

    public static void main (String[] args) {
        Play game = new Play();
    }

    private class InputListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int row_selected = -1;
            int col_selected = -1;

            JTextField source = (JTextField)e.getSource();

            boolean found = false;

            for (int i = 0; i < GRID_SIZE && !found; ++i) {
                for (int j = 0; j < GRID_SIZE && !found; ++j) {
                    if (cells[i][j] == source) {
                        row_selected = i;
                        col_selected = j;
                        found = true;
                    }
                }
            }
            /**
             * TODO:
             * 1. Get the input String via tfCells[rowSelected][colSelected].getText()
             * 2. Convert the String to int via Integer.parseInt().
             * 3. Assume that the solution is unique. Compare the input number with
             *    the number in the puzzle[rowSelected][colSelected].  If they are the same,
             *    set the background to green (Color.GREEN); otherwise, set to red (Color.RED).
             *
             * TODO:
             * Check if the player has solved the puzzle after this move.
             * You could update the masks[][] on correct guess, and check the masks[][] if
             * any input cell pending.
             */
        }
    }
}
