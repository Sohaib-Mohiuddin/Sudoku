package Sudoku.src;

import java.awt.*; import java.awt.event.*;
import java.awt.font.TextAttribute;

import javax.swing.*;
import java.util.Random; import java.util.Map; import java.util.HashMap;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;

import static javax.management.timer.Timer.ONE_SECOND;

public class Play extends JFrame {

    public JFrame frame;
    public JPanel panel1, num_panel, timer_panel;
    public JButton return_button, help, one, two, three, four, five, six, seven, eight, nine;
    public JToggleButton hint, start;
    public JLabel title_play, timer1;
    public JMenuBar menubar;
    public JMenu menu_file, submenu;
    public JMenuItem save, item_options, item_quit;

    public static final int GRID_SIZE = 9;
    public static final int SUBGRID_SIZE = 3;
    public static final int CELL_SIZE = 60;

    public static final Color RIGHT_ANSWER = Color.GREEN;
    public static final Color WRONG_ANSWER = Color.RED;
    public static final Color UNCLICKED_BOX = Color.white;
    public static final Color CLICKED_BOX = Color.CYAN;
    public static final Font FONT_NUMBERS = new Font("Comic Sans MS", Font.BOLD, 20);
    public static final Font BUTTON_FONTS = new Font("Comic Sans MS", Font.BOLD, 15);
    public static final Font TITLE_FONTS = new Font("Comic Sans MS", Font.BOLD, 50);

    private int previousRowPicked;
    private int previousColPicked;
    boolean[][] hidden;

    gameGenerator newPuzzle = new gameGenerator();
	private int[][] puzzle = newPuzzle.getPuzzle();

    private boolean[][] mask = maskGenerator();

    private JTextField[][] cells = new JTextField[GRID_SIZE][GRID_SIZE];
    private JButton[][] nums = new JButton[SUBGRID_SIZE][SUBGRID_SIZE];
    JPanel cell_panels = new JPanel();

    private static int cnt;
    private Timer timer;

    //final String propertyName = "text";

    public Play() {
        GUI();
    }

    public void GUI() {
        frame = new JFrame();
        frame.setSize(1500, 1000);
        frame.setTitle("Play");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.cyan);

        menubar = new JMenuBar();
        menu_file = new JMenu("File");
        item_options = new JMenuItem("Options");
        item_quit = new JMenuItem("Quit");
        
        item_quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to close?", "Close?",  JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        });
        timer1 = new JLabel();
        timer1.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        timer1.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.black));
         ActionListener actListner = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                cnt += 1;

                timer1.setText("Timer: " + Integer.toString(cnt));
                timer1.setBounds(150,100,200,40);
                timer1.setHorizontalAlignment(JLabel.CENTER);
            }
        };
        Timer timer = new Timer(1000, actListner);
        timer.start();


        save = new JMenuItem("Save");
        menu_file.add(save); menu_file.add(item_options); menu_file.add(item_quit);
        menubar.add(menu_file);

        title_play = new JLabel("Sudoku-sama");
        title_play.setBounds(610, 100, 340, 70);
        title_play.setFont(TITLE_FONTS);
        Map<TextAttribute, Object> attributes = new HashMap<>(TITLE_FONTS.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        title_play.setFont(TITLE_FONTS.deriveFont(attributes));

        return_button = new JButton("Return to Main Menu");
        return_button.setFont(BUTTON_FONTS);
        return_button.setBounds(1250, 860, 200, 50);

        help = new JButton("Help");
        help.setFont(BUTTON_FONTS);
        help.setBounds(115, 450, 200, 50);

        hint = new JToggleButton("Hint: Default -> ON");
        hint.setFont(BUTTON_FONTS);
        hint.setBounds(115, 550, 200, 50);
        hint.setSelected(true);

        num_panel = new JPanel();
        num_panel.setBackground(Color.PINK);
        num_panel.setLayout(new GridLayout(3, 3));
        num_panel.setBounds(1100, 200, CELL_SIZE*3, CELL_SIZE*3);
        num_panel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));

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
                        cells[previousRowPicked][previousColPicked].setBackground(CLICKED_BOX);
                    } else {
                        cells[previousRowPicked][previousColPicked].setBackground(UNCLICKED_BOX);
                    }
                }

                int user_input = Integer.parseInt(cells[rowPicked][colPicked].getText());
                if(hint.isSelected()) {
                    if (user_input == puzzle[rowPicked][colPicked]) {
                        cells[rowPicked][colPicked].setBackground(RIGHT_ANSWER);
                        cells[rowPicked][colPicked].setEditable(false);
                    } else {
                        cells[rowPicked][colPicked].setBackground(WRONG_ANSWER);
                    }
                }

                previousRowPicked = rowPicked;
                previousColPicked = colPicked;
            }
        };

        Action button_action = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowPicked_button = -1;
                int colPicked_button = -1;

                JButton button_source = (JButton)e.getSource();

                boolean found_button = false;

                for (int i = 0; i < SUBGRID_SIZE && !found_button; ++i) {
                    for (int j = 0; j < SUBGRID_SIZE && !found_button; ++j) {
                        if(nums[i][j] == button_source) {
                            rowPicked_button = i;
                            colPicked_button = j;
                            //System.out.println(nums[i][j].getText());
                            //cells[i][j].setText(nums[i][j].getText());
                            
                            found_button = true;   
                        }
                    }
                }
            }
        };

        int button_numbers = 1;
        
        for (int i = 0; i < SUBGRID_SIZE; ++i) {
            for (int j = 0; j < SUBGRID_SIZE; ++j) {
                nums[i][j] = new JButton(button_numbers + "");
                nums[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                
                /**
                 * FIXME:
                 * Fix dragging values from number panel
                 */
                nums[i][j].addActionListener(button_action);
                int button_user_input = Integer.parseInt(nums[i][j].getText());
                nums[i][j].setTransferHandler(new ValueExportTransferHandler(Integer.toString(button_user_input)));

                nums[i][j].addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        JButton button = (JButton) e.getSource();
                        TransferHandler handle = button.getTransferHandler();
                        handle.exportAsDrag(button, e, TransferHandler.COPY);
                    }
                });
                button_numbers++;
                num_panel.add(nums[i][j]);

            }
        }

        for (int i = 0; i < GRID_SIZE; ++i) {
            for (int j = 0; j < GRID_SIZE; ++j) {

                cells[i][j] = new JTextField();
                cells[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                cells[i][j].setTransferHandler(new ValueImportTransferHandler());
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

        frame.getContentPane().add(title_play);
        frame.setJMenuBar(menubar);
        frame.add(return_button);
        frame.add(help);
        frame.add(hint);
        frame.getContentPane().add(panel1);
        frame.getContentPane().add(num_panel);
        frame.getContentPane().add(timer1);


        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void setSelected(boolean b) {
        b = true;
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
        new Play();

        
    }

    public static class ValueExportTransferHandler extends TransferHandler {

        public static final DataFlavor SUPPORTED_DATE_FLAVOR = DataFlavor.stringFlavor;
        private String value;

        public ValueExportTransferHandler(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public int getSourceActions(JComponent c) {
            return DnDConstants.ACTION_COPY_OR_MOVE;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            Transferable t = new StringSelection(getValue());
            return t;
        }

        @Override
        protected void exportDone(JComponent source, Transferable data, int action) {
            super.exportDone(source, data, action);
            // Decide what to do after the drop has been accepted
        }

    }

    public static class ValueImportTransferHandler extends TransferHandler {

        public static final DataFlavor SUPPORTED_DATE_FLAVOR = DataFlavor.stringFlavor;

        public ValueImportTransferHandler() {
        }

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            return support.isDataFlavorSupported(SUPPORTED_DATE_FLAVOR);
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            boolean accept = false;
            if (canImport(support)) {
                try {
                    Transferable t = support.getTransferable();
                    Object value = t.getTransferData(SUPPORTED_DATE_FLAVOR);
                    if (value instanceof String) {
                        Component component = support.getComponent();
                        if (component instanceof JLabel) {
                            ((JLabel) component).setText(value.toString());
                            accept = true;
                        }
                    }
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
            return accept;
        }
    }
}
