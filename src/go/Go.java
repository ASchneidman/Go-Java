package go;

// This file contains: Iterations, Wrapper classes (Character and Integer), String methods (.equals(), .toString(), .charAt()), File I/O

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;

/**
 *
 * @author Alex
 */
public class Go {

    public static void main(String[] args) throws IOException {
        
        asdf("kek");
        
        PrintWriter outputFile = null;

        Board b = null;

        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("E MM.dd.yyyy 'at' hh:mm:ss a zzz");

        Player player1 = new Player(1);
        Player player2 = new Player(2);

        player1.setTurn(1);
        player2.setTurn(1);

        boolean gameOver = false;
        boolean pass = false;

        String pos = "";
        String fileChoice = "";
        String fileName = "";
        int currentPlayer = 1;

        int boardChoice = 0;
        int move = 0;
        int row = -30;
        int colomn = -30;
        int boardLim = 0;
        int deadStones = 0;
        int totStones = 0;
        int passCounter = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the java command line Go Board! It is recommended that you enlarge this window to fullscreen to get the best possible exerience.");
        System.out.println("This program will store a log of this game in a file of your choice.");
        System.out.println("If you would like to append to an existing file, enter y. Enter n to create a new file.");
        fileChoice = sc.nextLine();

        StringValidator choiceValid = new StringValidator(fileChoice, "y", "n");
        do {
            if (choiceValid.validateDoubleOpt()) {
                break;
            } else {
                System.out.println("Enter either y to append to an existing file or n to create a new file.");
                fileChoice = sc.nextLine();
                choiceValid.setInput(fileChoice);
            }
        } while (!(choiceValid.validateDoubleOpt()));

        if (fileChoice.equals("y")) {
            System.out.println("What is the name of the file you would like to append to (do not include the .txt extension)?");
            fileName = sc.nextLine();
            File file = new File(fileName + ".txt");
            
            if (file.exists()) {
                System.out.println("This game's data will be appended to the file " + fileName + ".");
                FileWriter fwriter = new FileWriter(fileName + ".txt", true);
                outputFile = new PrintWriter(fwriter);
            } else if (!(file.exists())) {
                System.out.println("There is no file with the name " + fileName + ". A new file with the name " + fileName + " will be created.");
                outputFile = new PrintWriter(fileName + ".txt");
            }

        } else if (fileChoice.equals("n")) {
            System.out.println("What would you like to name your new file (do not include the .txt extension)?");
            fileName = sc.nextLine();
            File file = new File(fileName + ".txt");

            outputFile = new PrintWriter(fileName + ".txt");
        }

        System.out.println("What size would you like your board to be? Type 1 for 9x9, 2 for 13x13, or 3 for 19x19");
        boardChoice = sc.nextInt();
        sc.nextLine();

        if (boardChoice < 1 || boardChoice > 3) {
            do {
                System.out.println("You must enter 1 for 9x9, 2 for 13x13, or 19x19.");
                boardChoice = sc.nextInt();
                sc.nextLine();
            } while (boardChoice < 1 || boardChoice > 3);

        }

        switch (boardChoice) {
            case 1:
                b = new Board(9, player1, player2, outputFile);
                boardLim = 9;
                break;
            case 2:
                b = new Board(13, player1, player2, outputFile);
                boardLim = 13;
                break;
            case 3:
                b = new Board(19, player1, player2, outputFile);
                boardLim = 19;
                break;
            default:
                System.out.println("You did not enter a valid number 1, 2, or 3.");
                System.exit(0);

        }

        System.out.println("Here's your board: ");

        outputFile.println("File " + fileName + ":");
        outputFile.println("Game played at " + ft.format(date));
        b.setBoard();
        b.printBoard();

        System.out.println("To pass your turn, simply type pass during the request for your next piece location.");
        do {

            ++move;
            outputFile.println("Move " + move + " for player " + currentPlayer + ".");
            pass = false;
            if (passCounter == 2) {
                break;
            }
            if (currentPlayer == 1) {

                System.out.println("Move " + move + ": " + "Player " + currentPlayer + "'s turn (@@)!");

            } else {

                System.out.println("Move " + move + ": " + "Player " + currentPlayer + "'s turn (##)!");
            }
            System.out.println("What position would you like to place your piece (format: row colomn e.x. 1 3)?");

            pos = sc.nextLine();

            if (pos.equals("pass")) {
                outputFile.println("Player " + currentPlayer + " passed!");
                pass = true;
                ++passCounter;
                System.out.println("Turn for player " + currentPlayer + " passed!");
                if (currentPlayer == 1) {
                    currentPlayer = 2;
                } else {
                    currentPlayer = 1;
                }
            }
            if (!pass) {
                do {
                    try {
                        try {
                            row = Integer.parseInt(Character.toString(pos.charAt(0)));
                            colomn = Integer.parseInt(Character.toString(pos.charAt(2)));

                            if (row > boardLim || colomn > boardLim && (!pass)) {
                                System.out.println("You must enter a position that is within your board of " + boardLim + "x" + boardLim + ".");
                                pos = sc.nextLine();
                            } else if (row <= 0 || colomn <= 0) {
                                System.out.println("You must enter a position that is within your board of " + boardLim + "x" + boardLim + ".");
                                pos = sc.nextLine();
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {

                            String inRow = Character.toString(pos.charAt(0)) + Character.toString(pos.charAt(1));
                            String inColomn = Character.toString(pos.charAt(3)) + Character.toString(pos.charAt(4));

                            /*
                        if (Character.toString(pos.charAt(0)).equals("-") || Character.toString(pos.charAt(3)).equals("-") || Character.toString(pos.charAt(4)).equals("-")) {
                            System.out.println("You must enter a position that is within your board of " + boardLim + "x" + boardLim + ".");
                            pos = sc.nextLine();
                        }*/
                            try {
                                row = Integer.parseInt(inRow);
                                colomn = Integer.parseInt(inColomn);
                            } catch (NumberFormatException r) {
                                System.out.println("You must enter a position that is within your board of " + boardLim + "x" + boardLim + ".");
                                pos = sc.nextLine();
                            }
                            if (row > boardLim || colomn > boardLim && (!pass)) {
                                System.out.println("You must enter a position that is within your board of " + boardLim + "x" + boardLim + ".");
                                pos = sc.nextLine();
                            } else if (row <= 0 || colomn <= 0 && (!pass)) {
                                System.out.println("You must enter a position that is within your board of " + boardLim + "x" + boardLim + ".");
                                pos = sc.nextLine();
                            } else {
                                break;
                            }

                        }
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("You must enter a number greater than zero and less than or equal to " + boardLim + " for your position.");
                        pos = sc.nextLine();

                    }
                } while (true);

                if (b.getPosition(row, colomn).equals("(@@)     ") || b.getPosition(row, colomn).equals("(##)     ")) {
                    System.out.println("There already is a piece there.");
                } else if (currentPlayer == 1) {
                    b.setPosition(row, colomn, player1);
                    ++totStones;
                    player1.nextTurn();
                    b.printBoard();

                    //remove stones
                    System.out.println("How many stones need to be removed? Enter zero for none.");
                    do {
                        try {
                            deadStones = sc.nextInt();
                            sc.nextLine();

                            if (deadStones < 0 || deadStones > totStones) {
                                do {
                                    System.out.println("You must enter either zero for no dead stones or a positive whole number that is smaller than the total amount of stones.");
                                    deadStones = sc.nextInt();
                                    sc.nextLine();
                                } while (deadStones < 0);
                                break;
                            }

                            if (deadStones > 0) {
                                for (int i = 0; i < deadStones; ++i) {
                                    do {
                                        System.out.println("What is the position of dead stone " + (i + 1) + "?");
                                        pos = sc.nextLine();

                                        row = Integer.parseInt(Character.toString(pos.charAt(0)));
                                        colomn = Integer.parseInt(Character.toString(pos.charAt(2)));

                                        if (b.getPosition(row, colomn).equals("----     ")) {
                                            System.out.println("There isn't a piece there. Enter another position.");
                                        } else {
                                            b.clearSpace(row, colomn);
                                            player1.increaseDeadStones();
                                            break;

                                        }
                                    } while (true);
                                }
                            }
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("You must enter either zero for no dead stones or a positive whole number that is smaller than the total amount of stones.");
                            System.out.println("How many stones need to be removed? Enter zero for none.");
                            sc.nextLine();
                        }
                    } while (true);

                    if (currentPlayer == 1) {
                        currentPlayer = 2;
                    } else {
                        currentPlayer = 1;
                    }
                } else if (currentPlayer == 2) {
                    b.setPosition(row, colomn, player2);
                    ++totStones;
                    player2.nextTurn();
                    b.printBoard();

                    //remove stones
                    System.out.println("How many stones need to be removed? Enter zero for none.");
                    deadStones = sc.nextInt();
                    sc.nextLine();

                    if (deadStones < 0) {
                        do {
                            System.out.println("You must enter either zero for no dead stones or a positive whole number.");
                            deadStones = sc.nextInt();
                            sc.nextLine();
                        } while (deadStones < 0);
                    }

                    if (deadStones > 0) {
                        for (int i = 0; i < deadStones; ++i) {
                            System.out.println("What is the position of dead stone " + (i + 1) + "?");
                            pos = sc.nextLine();

                            row = Integer.parseInt(Character.toString(pos.charAt(0)));
                            colomn = Integer.parseInt(Character.toString(pos.charAt(2)));

                            b.clearSpace(row, colomn);
                            player2.increaseDeadStones();
                        }
                    }

                    if (currentPlayer == 1) {
                        currentPlayer = 2;

                    } else {
                        currentPlayer = 1;
                    }
                }
                passCounter = 0;
            }
        } while (!gameOver);
        outputFile.println("Game Over! Both players passed.");
        System.out.println("Game Over! Both players passed.");
        b.printBoard();
        outputFile.println("Player 1 captured " + player1.getDeadStones() + " stones.");
        outputFile.println("Player 2 captured " + player2.getDeadStones() + " stones.");
        System.out.println("Player 1 captured " + player1.getDeadStones() + " stones.");
        System.out.println("Player 2 captured " + player2.getDeadStones() + " stones.");
        outputFile.println("\n\n\n\n\n\n");
        outputFile.close();
    }
    
    public static void asdf(String kek) {
        System.out.println(kek.length());
        
    }

}
