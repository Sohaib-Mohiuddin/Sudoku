import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Random;
import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Play extends JFrame {

    public JFrame frame;
    public JPanel panel1, num_panel, timer_panel;
    public JButton return_button, help;
    public JToggleButton hint;
    public static JToggleButton sound;
    public JLabel title_play, timer1, remainingCells, finalscore_label, bgimg;
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
    private JButton[][] nums = new JButton[SUBGRID_SIZE][SUBGRID_SIZE];
    JPanel cell_panels = new JPanel();

    private static int cnt;
    private Timer timer;
    private int gamemodepicked;
    public int gamemode;
    private int remainingcells, initialcells = 0;
    public int score;
    private String username;

    public File file = new File("savefile.txt");
    
    Image musicOn;
    {
        try {
            musicOn = ImageIO.read(getClass().getResource("Resources/speaker.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Image MusicOff;
    {
        try {
            MusicOff = ImageIO.read(getClass().getResource("Resources/speaker_off.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Image speakerOnImage = musicOn.getScaledInstance(128, 128, Image.SCALE_DEFAULT);
    Image speakerOffImage = MusicOff.getScaledInstance(128, 128, Image.SCALE_DEFAULT);

    Image Background;
    {
        try {
            Background = ImageIO.read(getClass().getResource("Resources/background_image.png"));
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
                
                    if (Options.frame == null) {
                        new Options();
                        Options.Beginner.setVisible(false);
                        Options.Intermediate.setVisible(false);
                        Options.Expert.setVisible(false);
                        Options.modeLabel.setVisible(false);
                        Options.frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        Options.frame.setVisible(true);
                    } else {
                        if (Options.clip.isRunning()) {
                            Options.soundButton.setSelected(true);
                        } else {
                            Options.soundButton.setSelected(false);
                        }
                        Options.Beginner.setVisible(false);
                        Options.Intermediate.setVisible(false);
                        Options.Expert.setVisible(false);
                        Options.modeLabel.setVisible(false);
                        Options.frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        Options.frame.setVisible(true);
                    }
                    
                }
        });

        timer1 = new JLabel();
        timer1.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        timer1.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.black));
        
         ActionListener actListner = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
                //cnt = 0;
                cnt += 1;
                if (cnt < 10) {
                    timer1.setText("Timer: 00:0" + Integer.toString(cnt));
                }
                else if (cnt < 60){
					timer1.setText("Timer: 00:" + Integer.toString(cnt));
                }
                else if (cnt % 60 < 10) {
                    timer1.setText("Timer: " + Integer.toString(cnt/60) + ":0" + Integer.toString(cnt%60));
                }
                else if (cnt < 600){
                    timer1.setText("Timer: 0" + Integer.toString(cnt/60) + ":" + Integer.toString(cnt%60));
                }
                else {
                    timer1.setText("Timer: " + Integer.toString(cnt/60) + ":" + Integer.toString(cnt%60));
                }
                
                timer1.setBounds(50,50,250,40);
                timer1.setHorizontalAlignment(JLabel.CENTER);
            }
        };
        Timer timer = new Timer(1000, actListner);
        timer.start();
        //timer.setRepeats(false);
        // if (timer.isRunning()) {
        //     timer.stop();
        //     cnt = 0;
        //     timer.restart();
        // }
        
        

        save = new JMenuItem("Save");
        save.setFont(FONT_NUMBERS);
        menu_file.add(save); menu_file.add(item_options); menu_file.add(item_quit);
        menubar.add(menu_file);

        title_play = new JLabel("Sudoku-sama");
        title_play.setBounds(610, 100, 340, 70);
        title_play.setFont(TITLE_FONTS);
        Map<TextAttribute, Object> attributes = new HashMap<>(TITLE_FONTS.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        title_play.setFont(TITLE_FONTS.deriveFont(attributes));

        remainingCells = new JLabel("", JLabel.CENTER);
        remainingCells.setBounds(1100,50,300,40);
        remainingCells.setFont(BUTTON_FONTS);
        remainingCells.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.black));
        remainingCells.setText("Number of remaining boxes: --");

        return_button = new JButton("Return to Main Menu");
        return_button.setFont(new Font("Comic Sans", Font.BOLD, 30));
        return_button.setBounds(1150, 860, 300, 50);
        return_button.setBackground(BACKGROUND_COLOUR);
        return_button.setBorder(new EmptyBorder(0,0,0,0));
        return_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to go to Main Menu?" + "\n" + 
                                                        "You will lose your progress." , "Proceed to Main Menu?", 
                                                        JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION)
                {
                    cnt = 0;
                    timer.stop();
                    timer.restart();
                    
                    Homepage homepage = new Homepage();
                    frame.setVisible(false);
                }
            }
        });

        help = new JButton("Help");
        help.setFont(BUTTON_FONTS);
        help.setBounds(50, 450, 200, 50);
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                JOptionPane.showMessageDialog(null, "To play, you first pick a blue box and enter the number you think it is, \n" + 
                                                    "then you press enter and see if you are correct. If the box turns green \n" + 
                                                    "you are correct, if it is red then you are incorrect. The objective \n" + 
                                                    "of Sudoku is to fill up the boxes with a number between 1-9 that \n" + 
                                                    "doesn't repeat in the rows, columns, or subgrids. To return to the menu \n" + 
                                                    "while playing, press the esc button.");
            }
        });

        hint = new JToggleButton("Hints:ON");
        hint.setFont(BUTTON_FONTS);
        hint.setBounds(50, 550, 200, 50);
        hint.setSelected(true);

        sound = new JToggleButton();
        sound.setFont(BUTTON_FONTS);
        sound.setBounds(50, 650, 128, 128);
        // if (Options.clip.isRunning()) {
        //     sound.setIcon(new ImageIcon(speakerOnImage));
        //     sound.setSelected(true);
        // } else {
        //     sound.setIcon(new ImageIcon(speakerOffImage));
        //     sound.setSelected(false);
        // }
        sound.setIcon(new ImageIcon(speakerOffImage));
        

        finalscore_label = new JLabel("Final score: Finish to reveal", JLabel.CENTER);
        finalscore_label.setBounds(50,150,250,40);
        finalscore_label.setFont(BUTTON_FONTS);
        finalscore_label.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.black));

        highscore_text = new JTextArea();
        highscore_text.setBounds(50,200,250,200);
        highscore_text.setFont(BUTTON_FONTS);
        highscore_text.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));
        highscore_text.setEditable(false);
        JScrollPane scroller = new JScrollPane(highscore_text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setBounds(50,200,250,200);
        scroller.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));

        hint.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    hint.setText("Hints:ON");
                } else {
                    hint.setText("Hints:OFF");
                }
            }
        });
        sound.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                new Options();
                Options.frame.setVisible(false);
                if (e.getStateChange() == ItemEvent.SELECTED && Options.clip.isRunning()) {
                    sound.setIcon(new ImageIcon(speakerOnImage));
                    Options.soundButton.setSelected(true);
                } 
                else if (e.getStateChange() == ItemEvent.SELECTED && !Options.clip.isRunning()) {
                    sound.setIcon(new ImageIcon(speakerOnImage));
                    Options.soundButton.setSelected(true);
                    Options.playSound();
                }
                else if (e.getStateChange() == ItemEvent.DESELECTED && Options.clip.isRunning()) {
                    sound.setIcon(new ImageIcon(speakerOffImage));
                    Options.soundButton.setSelected(false);
                    Options.stopSound();
                } 
                else if (e.getStateChange() == ItemEvent.DESELECTED && !Options.clip.isRunning()) {
                    sound.setIcon(new ImageIcon(speakerOffImage));
                    Options.soundButton.setSelected(false);
                }
            }
        });

        num_panel = new JPanel();
        num_panel.setBackground(Color.PINK);
        num_panel.setLayout(new GridLayout(3, 3));
        num_panel.setBounds(1200, 400, CELL_SIZE*3, CELL_SIZE*3);
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
                        cells[previousRowPicked][previousColPicked].setBackground(UNCLICKED_BOX);
                    } else {
                        cells[previousRowPicked][previousColPicked].setBackground(UNCLICKED_BOX);
                    }
                }

                int user_input = Integer.parseInt(cells[rowPicked][colPicked].getText());
                if(hint.isSelected()) {
                    if (user_input == puzzle[rowPicked][colPicked]) {
                        cells[rowPicked][colPicked].setBackground(RIGHT_ANSWER);
                        cells[rowPicked][colPicked].setForeground(new Color(0, 0, 153));
                        cells[rowPicked][colPicked].setEditable(false);
                        remainingcells--;
                        score++;
                        remainingCells.setText("Number of remaining boxes: " +remainingcells);
                    } else {
                        cells[rowPicked][colPicked].setBackground(WRONG_ANSWER);
                        cells[rowPicked][colPicked].setText("");
                        score--;
                    }
                    if (remainingcells == 0) {
                        timer.stop();
                        int finalScore = (int)(((double)(score)/(double)(initialcells))*100);
                        finalscore_label.setText("Your final score is: " + finalScore + "%");
                        finalscore_label.setVisible(true);
                        username = JOptionPane.showInputDialog("What is your name?");
                        saveToFile(username + ":" + finalScore);
                        loadFromFile();
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
                            found_button = true;   
                        }
                    }
                }
                int button_user_input = Integer.parseInt(nums[rowPicked_button][colPicked_button].getText());
                System.out.println(button_user_input);
            }
        };

        int button_numbers = 1;
        
        for (int i = 0; i < SUBGRID_SIZE; ++i) {
            for (int j = 0; j < SUBGRID_SIZE; ++j) {
                nums[i][j] = new JButton(button_numbers + "");
                nums[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                nums[i][j].addActionListener(button_action);

                /**
                 * FIXME:
                 * Fix dragging values from number panel
                 *
                 *
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

                 */
                button_numbers++;
                num_panel.add(nums[i][j]);
            }
        }

        for (int i = 0; i < GRID_SIZE; ++i) {
            for (int j = 0; j < GRID_SIZE; ++j) {

                cells[i][j] = new JTextField();
                cells[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                //cells[i][j].setTransferHandler(new ValueImportTransferHandler());
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
                    cells[i][j].setForeground(new Color(255, 255, 255));
                    initialcells++;
                    
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
                 remainingcells = initialcells;
                 
            }
        }
        // for (int c = 0; c < 9; c++) {
        //     for (int d = 0; d < 9; d++) {
        //         System.out.print(puzzle[c][d]);
        //     }
        // }

        frame.getContentPane().add(title_play);
        frame.setJMenuBar(menubar);
        bgimg.add(return_button);
        bgimg.add(help);
        bgimg.add(hint);
        bgimg.add(sound);
        bgimg.add(remainingCells);
        bgimg.add(finalscore_label);
        bgimg.add(scroller);
        bgimg.add(panel1);
        bgimg.add(num_panel);
        bgimg.add(timer1);
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

    public void saveToFile(String finalScore) {
        try (FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {

            out.println(finalScore);
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        try {
            String token1;
            Scanner scanner = new Scanner(file);          
            List<String> temps = new ArrayList<String>();
            while (scanner.hasNext()) {
                token1 = scanner.next();
                temps.add(token1);
            }
            scanner.close();

            highscore_text.setText("Scores: \n");
            for (int s = 0; s < temps.size(); s++) {
                String label = temps.get(s);
                highscore_text.append((s+1) + ") " + label + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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

    /**
     * TODO:
     * fix classes to drag values from num panel in the future
     * 
     * 
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
    *
    *
    **/
}
