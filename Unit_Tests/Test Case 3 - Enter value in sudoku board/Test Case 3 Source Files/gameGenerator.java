
/**
 * Author: Sohaib Mohiuddin
 * Course: Principles of Software and Requirements (Winter 2019)
 * Due Date: March 27, 2019
 * Test Case 3
 * Github Link: https://github.com/sm131/Sudoku
 * 
 * 
 * gameGenerator.java 
 * This class generates the numbers for the sudoku board which will be entered by the user and checked with this class 
 * to see if the numbers match
 */
import java.lang.*;

public class gameGenerator {
    int[][] puzzle;

    // THE CONSTRUCTOR THAT INITIATES THE FRAME AND EVERYTHING IN THE FRAME
    gameGenerator(){
        puzzle = new int[9][9];
    }

    // THE SUDOKU GENERATOR
    public void fillValues(){
        // CALLS THE METHOD THAT FILLS THE DIAGONAL 3x3 SUBGRID
        fillDiagonal();

        // CALLS THE METHOD THAT FILLS THE REMAINING CELLS
        fillRemaining(0, 3);
    }

    // THE METHOD THAT FILLS THE DIAGONAL 3x3 SUBGRID
    void fillDiagonal(){

        for (int i = 0; i<9; i=i+3)
            // FOR THE DIAGONAL BOX, START THE COORDINATES AT i==j
            fillBox(i, i);
    }

    /**
     * RETURNS FALSE IF THE GIVEN 3x3 SUBGRID CONTAINS NUM (A NUMBER)
     * 
     * @param rowStart
     * @param colStart
     * @param num
     * @return
     */
    boolean unUsedInBox(int rowStart, int colStart, int num){
        // THIS NESTED FOR LOOP GOES THROUGH THE SUBGRIDS ITERATIVELY (ONE BY ONE - LINEARLY)
        for (int i = 0; i<3; i++)
            for (int j = 0; j<3; j++)
                if (puzzle[rowStart+i][colStart+j]==num)
                    return false;
        return true;
    }

    /**
     * METHOD THAT FILLS A 3x3 SUBGRID 
     * 
     * @param row
     * @param col
     */
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

    // THIS METHOD GENERATES RANDOM NUMBERS BETWEEN 1 AND 9 INCLUSIVE
    int randomGenerator(){
        return (int) Math.floor((Math.random()*9+1));
    }

    /**
     * THIS METHOD CHECKS TO SEE IF IT IS SAFE TO INPUT A VALUE IN THE GIVEN CELL
     * 
     * @param i
     * @param j
     * @param num
     * @return
     */
    boolean CheckIfSafe(int i,int j,int num){
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i-i%3, j-j%3, num));
    }

    /**
     * THIS METHOD CHECKS TO SEE IF THERE IS ALREADY A VALUE OR NOT IN THE ROW
     * 
     * @param i
     * @param num
     * @return
     */
    boolean unUsedInRow(int i,int num){
        for (int j = 0; j<9; j++)
            if (puzzle[i][j] == num)
                return false;
        return true;
    }

    /**
     * THIS METHOD CHECKS TO SEE IF THERE IS ALREADY A VALUE OR NOT IN THE COLUMN
     * 
     * @param j
     * @param num
     * @return
     */
    boolean unUsedInCol(int j,int num){
        for (int i = 0; i<9; i++)
            if (puzzle[i][j] == num)
                return false;
        return true;
    }

    /**
     * THIS METHOD IS A RECURSIVE FUNCTION THAT FILLS THE REMAINING CELLS
     * 
     * @param i
     * @param j
     * @return
     */
    boolean fillRemaining(int i, int j){
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
    
    /**
     * THIS METHOD CALLS THE fillValues() METHOD AND FILLS THE SUDOKU BOARD WITH VALUES (HIDDEN UNLESS Play.java  
     * DECIDES WHICH VALUES SHOULD BE SHOWN TO THE USER)
     * 
     * @return puzzle
     */
    public int[][] getPuzzle(){
        fillValues();
        return puzzle;
    }
    

    // THIS METHOD PRINTS THE VALUES FOR THE ENTIRE SUDOKU BOARD
    public void printSudoku(){
        for (int i = 0; i<9; i++){
            for (int j = 0; j<9; j++)
                System.out.print(puzzle[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    // MAIN METHOD TO CREATE gameGenerator THAT CALLS THE NECESSARY METHODS FOR THE SUDOKU BOARD VALUES
    public static void main(String[] args){
        gameGenerator sudoku = new gameGenerator();
        sudoku.fillValues();
        sudoku.printSudoku();
    }
}