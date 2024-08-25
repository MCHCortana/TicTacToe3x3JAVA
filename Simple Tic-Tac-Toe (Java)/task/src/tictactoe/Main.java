package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void printGameBoard(char[][] grid, String input, int[] charCount) {

        int charIndex = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == 0 || i == 4) {
                    grid[i][j] = '-';
                } else if (j == 0 || j == 8) {
                    grid[i][j] = '|';
                } else if (j % 2 != 0) {
                    grid[i][j] = (' ');
                } else {
                    grid[i][j] = input.charAt(charIndex);
                    ;
                    switch (input.charAt(charIndex)) {
                        case 'X':
                            charCount[0]++;
                            break;
                        case 'O':
                            charCount[1]++;
                            break;
                        case '_':
                            charCount[2]++;
                            break;
                        default:
                            System.out.println("Not working in printGameBoard!");
                    }
                    charIndex++;
                }
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    public static char checkRows(String input) {
        char[] winners = {'0', '0'}; //wins {X, O}
        int numOptions = 6;

        int[][] winningOptions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, //Columns
                {0, 4, 8}, {2, 4, 6} //Diagonals
        };

        for (int[] pattern : winningOptions) {
            if (input.charAt(pattern[0]) == input.charAt(pattern[1]) &&
                    input.charAt(pattern[0]) == input.charAt(pattern[2])) {
                System.out.println(Arrays.toString(pattern));
                if (input.charAt(pattern[0]) == 'X') {
                    winners[0] = 'X';
                } else if (input.charAt(pattern[0]) == 'O') {
                    winners[1] = 'O';
                }
            }
        }

        char winner = 'N';
        if (winners[0] == 'X' && winners[1] == 'O') {
            winner = '!';
        } else if (winners[0] == 'X') {
            winner = 'X';
        } else if (winners[1] == 'O') {
            winner = 'O';
        }
        return winner;
    }

    public static String findWinner(String input, int[] charCount) {
        String result = "";
        char winner = checkRows(input);
        System.out.println(winner);

        if (winner == 'X' || winner == 'O') {
            result = winner + " wins";
        } else if (charCount[2] == 0) {
            result = "Draw";
        } else if (winner == '!' || Math.abs(charCount[0] - charCount[1]) >= 1) {
            result = "Impossible";
        } else {
            result = "Game not finished";
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        char[][] grid = new char[5][9];
        int[] charCount = {0, 0, 0}; //number of Xs, Os, _


        printGameBoard(grid, input, charCount);

        System.out.println(findWinner(input, charCount));
    }
}
