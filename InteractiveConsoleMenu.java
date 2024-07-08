package edu.sdccd.cisc191.template;

import java.util.Arrays;

public class InteractiveConsoleMenu {

    private char[][] board;

    public InteractiveConsoleMenu(int rows, int cols) {
        this.board = new char[rows][cols];
        initializeBoard();
    }

    private void initializeBoard() {
        for (char[] row : board) {
            Arrays.fill(row, '_');
        }
    }

    public char getAtIndex(int row, int col) {
        return board[row][col];
    }

    public void setAtIndex(int row, int col, char value) {
        board[row][col] = value;
    }

    public int findIndexOf(char value) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == value) {
                    return i * board[0].length + j;
                }
            }
        }
        return -1; // Not found
    }

    public void printAll() {
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void deleteAtIndex(int row, int col) {
        board[row][col] = '_';
    }

    public void expand(int newRows, int newCols) {
        char[][] newBoard = new char[newRows][newCols];
        for (int i = 0; i < Math.min(board.length, newRows); i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, Math.min(board[0].length, newCols));
        }
        board = newBoard;
    }

    public void shrink(int newRows, int newCols) {
        char[][] newBoard = new char[newRows][newCols];
        for (int i = 0; i < newRows; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, newCols);
        }
        board = newBoard;
    }
}
