import java.awt.*;
import java.lang.*;
public class gameGenerator {

    public static final Font BUTTON_FONTS = new Font("Comic Sans MS", Font.BOLD, 15);
    int[][] puzzle;

    // Constructor
    gameGenerator(){ puzzle = new int[9][9]; }

    // Sudoku Generator
    public void fillValues(){
        // Fill the diagonal of 3 x 3 matrix
        fillDiagonal();

        // Fill remaining blocks
        fillRemaining(0, 3);
    }

    // Fill the diagonal of the 3 x 3 matrix
    void fillDiagonal(){

        for (int i = 0; i<9; i=i+3)
            // for diagonal box, start coordinates->i==j
            fillBox(i, i);
    }

    // Returns false if given 3 x 3 block contains num.
    boolean unUsedInBox(int rowStart, int colStart, int num){
        for (int i = 0; i<3; i++)
            for (int j = 0; j<3; j++)
                if (puzzle[rowStart+i][colStart+j]==num)
                    return false;

        return true;
    }

    // Fill a 3 x 3 matrix.
    void fillBox(int row,int col){
        int num;
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                do{
                    num = randomGenerator();
                }
                while (!unUsedInBox(row, col, num));

                puzzle[row+i][col+j] = num;
            }
        }
    }

    // Random generator
    private int randomGenerator(){
        return (int) Math.floor((Math.random()*9+1));
    }

    // Check if safe to put in cell
    boolean CheckIfSafe(int i,int j,int num){
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i-i%3, j-j%3, num));
    }

    // check in the row for existence
    boolean unUsedInRow(int i,int num){
        for (int j = 0; j<9; j++)
            if (puzzle[i][j] == num)
                return false;
        return true;
    }

    // check in the row for existence
    boolean unUsedInCol(int j,int num){
        for (int i = 0; i<9; i++)
            if (puzzle[i][j] == num)
                return false;
        return true;
    }

    // A recursive function to fill remaining
    // puzzlerix
    boolean fillRemaining(int i, int j){
        // System.out.println(i+" "+j);
        if (j>=9 && i<8){
            i = i + 1;
            j = 0;
        }
        if (i>=9 && j>=9)
            return true;

        if (i < 3){
            if (j < 3)
                j = 3;
        }else if (i < 6){
            if (j==(int)(i/3)*3)
                j = j + 3;
        }else{
            if (j == 6){
                i = i + 1;
                j = 0;
                if (i>=9)
                    return true;
            }
        }

        for (int num = 1; num<=9; num++){
            if (CheckIfSafe(i, j, num)){
                puzzle[i][j] = num;
                if (fillRemaining(i, j+1))
                    return true;

                puzzle[i][j] = 0;
            }
        }
        return false;
    }

    public int[][] getPuzzle(){
        fillValues();
        return puzzle;
    }


    // Print sudoku
    public void printSudoku(){
        for (int i = 0; i<9; i++){
            for (int j = 0; j<9; j++)
                System.out.print(puzzle[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
    // Driver code
    public static void main(String[] args){
        gameGenerator sudoku = new gameGenerator();
        sudoku.fillValues();
        sudoku.printSudoku();
    }










}
