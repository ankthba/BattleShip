import java.util.Scanner;
import java.util.*;

public class Player {
    private GameBoard board;
    private Scanner scanner;

    public Player(Scanner scanner, GameBoard board) {
        this.board = board;
        this.scanner = scanner;
    }

    public void placeShips() {
        int[] shipLengths = {5, 4, 3, 3, 2};
        for (int length : shipLengths) {
            Ship ship = new Ship(length);
            boolean shipPlaced = false;
            while (!shipPlaced) {
                if (scanner != null) {
                    System.out.println("Enter the row and column to place a ship of length " + length + " (e.g., 3 4):");
                    int row = scanner.nextInt();
                    int col = scanner.nextInt();
                    System.out.println("Place horizontally? (true/false):");
                    boolean horizontal = scanner.nextBoolean();
                    if (board.canPlaceShip(ship, row, col, horizontal)) {
                        board.placeShip(ship, row, col, horizontal);
                        shipPlaced = true;
                    } else {
                        System.out.println("Cannot place ship here. Try again.");
                    }
                } else {
                    shipPlaced = true;
                }
            }
        }
    }

    public void takeTurn(GameBoard opponentBoard) {
        boolean validMove = false;
        while (!validMove) {
            try {
                System.out.println("Enter the row and column to attack (e.g., 3 4):");
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                
                if (row < 0 || row >= 10 || col < 0 || col >= 10) {
                    System.out.println("Invalid input. Row and column must be between 0 and 9.");
                    continue;
                }

                char cell = opponentBoard.getCell(row, col);
                if (cell == '-') {
                    opponentBoard.setCell(row, col, 'M'); // Mark as Miss
                    System.out.println("Miss!");
                    validMove = true;
                } else if (cell == 'S') {
                    opponentBoard.setCell(row, col, 'H'); // Mark as Hit
                    System.out.println("Hit!");
                    validMove = true;
                } else if (cell == 'H' || cell == 'M') {
                    System.out.println("You have already attacked this cell. Try again.");
                } else {
                    System.out.println("Invalid input. You can't attack your own ship.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter integers for the row and column.");
                scanner.nextLine();
            }
        }
    }

    public GameBoard getBoard() {
        return board;
    }
}
