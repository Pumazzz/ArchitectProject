package edu.sdccd.cisc191.template;

import java.io.Serializable;


public class TicTacToeResponse implements Serializable {
    private char[][] board;
    private char winner;
    private String message;

    public TicTacToeResponse() {
        this.board = new char[3][3];
        this.winner = '_';
        this.message = "";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public char getWinner() {
        return winner;
    }

    public void setWinner(char winner) {
        this.winner = winner;
    }

    public boolean isMoveAccepted() {
        return message != null && message.equals("Move accepted.");
    }
}
