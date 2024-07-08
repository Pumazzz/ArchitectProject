package edu.sdccd.cisc191.template;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private char[][] board;
    private char currentPlayer;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        this.board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '_';
            }
        }
        this.currentPlayer = 'X';
    }

    @Override
    public void run() {
        try (ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream())) {

            while (true) {
                TicTacToeRequest request = (TicTacToeRequest) input.readObject();
                System.out.println("Received request: " + request.getRow() + ", " + request.getCol());

                TicTacToeResponse response = processRequest(request);
                output.writeObject(response);

                if (response.getWinner() != '_') {
                    System.out.println("Game Over. Winner: " + response.getWinner());
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private TicTacToeResponse processRequest(TicTacToeRequest request) {
        TicTacToeResponse response = new TicTacToeResponse();
        int row = request.getRow();
        int col = request.getCol();

        if (isValidMove(row, col)) {
            board[row][col] = currentPlayer;
            if (checkForWinner()) {
                response.setWinner(currentPlayer);
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                response.setMessage("Move accepted.");
            }
        } else {
            response.setMessage("Invalid move. Please try again.");
        }
        response.setBoard(board);
        return response;
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '_';
    }

    private boolean checkForWinner() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }
}