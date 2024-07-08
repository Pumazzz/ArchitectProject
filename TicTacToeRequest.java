package edu.sdccd.cisc191.template;

import java.io.Serializable;

public class TicTacToeRequest implements Serializable {
    private final int row;
    private final int col;

    public TicTacToeRequest(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}