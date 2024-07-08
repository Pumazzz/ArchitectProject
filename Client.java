package edu.sdccd.cisc191.template;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 9878;

        try (Socket socket = new Socket(host, port);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to server on port " + port);

            while (true) {
                System.out.print("Enter row (0-2): ");
                int row = scanner.nextInt();
                System.out.print("Enter column (0-2): ");
                int col = scanner.nextInt();

                TicTacToeRequest request = new TicTacToeRequest(row, col);
                oos.writeObject(request);
                oos.flush(); // Ensure the object is fully written
                System.out.println("Sent request: " + request.getRow() + ", " + request.getCol());

                TicTacToeResponse response = (TicTacToeResponse) ois.readObject();
                System.out.println("Message from server: " + response.getMessage());

                if (response.isMoveAccepted()) {
                    printBoard(response.getBoard());
                    if (response.getWinner() != '_') {
                        System.out.println("Winner: " + response.getWinner());
                        break;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}