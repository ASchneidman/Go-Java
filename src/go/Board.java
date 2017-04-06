/* This file contains: Iterations, Aggregation (Board has two players), One array and one ArrayList, setPosition() passes a player object as an 
    an argument, multiple methods which pass arguments and return a value, getEntBoard() returns an array, this. notation is used, a Constructor,
    and Constants
 */
package go;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class Board {

    PrintWriter outputFile;
    private int size;
    private String[][] board = new String[0][0];
    ArrayList<Piece> pieces;
    private int pos = -1;
    private int SMALL_SIZE = 9;
    private int MEDIUM_SIZE = 13;
    private int LARGE_SIZE = 19;

    public Board(int size, Player player1, Player player2, PrintWriter outputFile) {

        pieces = new ArrayList<>();

        this.outputFile = outputFile;
        //this.size = size;

        switch (size) {
            case 9:
                this.size = SMALL_SIZE;
                break;
            case 13:
                this.size = MEDIUM_SIZE;
                break;
            case 19:
                this.size = LARGE_SIZE;
                break;

        }

        switch (this.size) {
            case 9:

                board = new String[9][9];
                break;
            case 13:

                board = new String[13][13];
                break;
            case 19:

                board = new String[19][19];
                break;
            default:
                System.out.println("You did not enter a valid number 1, 2, or 3.");
                System.exit(0);

        }
    }

    public void printBoard() {
        boolean doubDigit = false;
        for (int i = 0; i < size + 1; ++i) {
            for (int j = 0; j < size; ++j) {
                if (((j + 1) % 10) == 0) {
                    doubDigit = true;
                }
                if (i == 0) {
                    //formatting nonsense probably a bad way of doing this
                    if (!doubDigit) {
                        System.out.print((j + 1) + "        ");
                        outputFile.print((j + 1) + "        ");
                    } else {
                        System.out.print((j + 1) + "       ");
                        outputFile.print((j + 1) + "       ");
                    }

                } else {

                    System.out.print(board[i - 1][j]);
                    outputFile.print(board[i - 1][j]);
                }
            }
            if (i == 0) {
                System.out.println("");
                outputFile.println("");
            } else {
                System.out.println((i) + "\n");
                outputFile.println((i) + "\n");
            }

        }

    }

    public void setBoard() {

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                board[i][j] = "----     ";

            }

        }

    }

    public String getPosition(int row, int colomn) {

        return board[row - 1][colomn - 1];

    }

    public String[][] getEntBoard() {
        return board;
    }

    // Passes object as argument
    public void setPosition(int row, int colomn, Player player) {
        if (player.getPlayerType() == 1) {
            board[row - 1][colomn - 1] = "(@@)     ";
            ++pos;
            pieces.add(new Piece(row - 1, colomn - 1, player, pos));
        } else {
            ++pos;
            board[row - 1][colomn - 1] = "(##)     ";
            pieces.add(new Piece(row - 1, colomn - 1, player, pos));
        }
    }

    public void clearSpace(int row, int colomn) {
        board[row - 1][colomn - 1] = "----     ";
    }

}
