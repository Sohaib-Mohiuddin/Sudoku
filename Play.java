import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Play extends JFrame {

    public JFrame frame;
    public JPanel panel1, panel2;
    public JButton button1, button2, button3;
    public JLabel label1, label2;
    public JMenu menu, submenu;
    public JMenuItem i1, i2, i3, i4, i5;

    public static final int GRID_SIZE = 9;
    public static final int SUBGRID_SIZE = 3;
    public static final int CELL_SIZE = 60;

    public static final Color RIGHT_ANSWER = Color.GREEN;
    public static final Color WRONG_ANSWER = Color.RED;
    public static final Color UNCLICKED_BOX = Color.white;
    public static final Color CLICKED_BOX = Color.CYAN;
    public static final Font FONT_NUMBERS = new Font("Comic Sans MS", Font.BOLD, 20);

    public int previousRowPicked;
    public int previousColPicked;
    boolean[][] hidden;

    gameGenerator newPuzzle = new gameGenerator();
	private int[][] puzzle = newPuzzle.getPuzzle();


    // private int[][] puzzle = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
    //                           {0, 0, 0, 0, 0, 0, 0, 0, 0},
    //                           {0, 0, 0, 0, 0, 0, 0, 0, 0},
    //                           {0, 0, 0, 0, 0, 0, 0, 0, 0},
    //                           {0, 0, 0, 0, 0, 0, 0, 0, 0},
    //                           {0, 0, 0, 0, 0, 0, 0, 0, 0},
    //                           {0, 0, 0, 0, 0, 0, 0, 0, 0},
    //                           {0, 0, 0, 0, 0, 0, 0, 0, 0},
    //                           {0, 0, 0, 0, 0, 0, 0, 0, 0}};
    // private boolean[][] mask = {{false, false, false, false, false, true, false, false, false},
    //                             {false, false, false, false, false, false, false, false, true},
    //                             {false, false, true, false, false, false, false, false, false},
    //                             {false, false, false, false, false, false, false, false, false},
    //                             {false, false, false, true, false, true, false, false, false},
    //                             {false, false, false, false, true, false, false, false, false},
    //                             {false, false, false, false, false, false, false, false, false},
    //                             {false, true, false, false, false, false, false, false, false},
    //                             {false, false, false, false, false, false, false, false, false}};

    private boolean[][] mask = maskGenerator();

    private JTextField[][] cells = new JTextField[GRID_SIZE][GRID_SIZE];
    JPanel cell_panels = new JPanel();

    public Play() {

    }

    public void GUI() {
        frame = new JFrame();
        frame.setSize(CELL_SIZE*GRID_SIZE+150, CELL_SIZE*GRID_SIZE+150);
        frame.setTitle("Play");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.cyan);

        panel1 = new JPanel();
        panel1.setBackground(Color.PINK);
        panel1.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        panel1.setBounds(0, 0, CELL_SIZE*GRID_SIZE+100, CELL_SIZE*GRID_SIZE+100);
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
                            cells[rowPicked][colPicked].setBackground(RIGHT_ANSWER);
                            found = true;  //Leaves the loop when found
                            if () {
                                
                            }
                        }
                    }
                }
            }
        };

        for (int i = 0; i < GRID_SIZE; ++i) {
            for (int j = 0; j < GRID_SIZE; ++j) {

                cells[i][j] = new JTextField();
                cells[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                cells[i][j].addActionListener(action);

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
                    cells[i][j].setBackground(Color.white);
                    cells[i][j].setForeground(new Color(0, 0, 153));
                    
                 } else {
                    
                    for (int x = 0; x < GRID_SIZE; x++) {
                        for (int y = 0; y < GRID_SIZE; y++) {
                            cells[i][j].setText(puzzle[i][j] + "");
                        }
                    }
                    cells[i][j].setEditable(false);
                    cells[i][j].setBackground(Color.LIGHT_GRAY);
                    cells[i][j].setForeground(new Color(0, 0, 153));
                 }

                 // Beautify all the cells
                 cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                 cells[i][j].setFont(FONT_NUMBERS);
            }
        }

        frame.getContentPane().add(panel1);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public boolean[][] maskGenerator() {
        Random random = new Random();
        
        boolean[][] cover = new boolean[GRID_SIZE][GRID_SIZE];

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int randomnum = random.nextInt(4) + 1;
                if (randomnum <= 3) {
                    cover[i][j] = true;
                } else {
                    cover[i][j] = false;
                }
            }
        }
        return cover;
    }

    public static void main(String[] args) {
        Play play = new Play();

        play.GUI();
    }
}
