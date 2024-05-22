import java.util.Scanner;

public class Player {
    private GameBoard board;
    private Scanner scanner;

    public Player() {
        board = new GameBoard();
        scanner = new Scanner(System.in);
    }

    public void placeShips() {
        int[] shipLengths = {5, 4, 3, 3, 2}; // lengths of the ships in Battleship
        System.out.println("Place your ships (format: row col horizontal(1 or 0)): ");
        for (int length : shipLengths) {
            Ship ship = new Ship(length);
            boolean shipPlaced = false;
            while (!shipPlaced) {
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                boolean horizontal = scanner.nextInt() == 1;
                if (board.canPlaceShip(ship, row, col, horizontal)) {
                    board.placeShip(ship, row, col, horizontal);
                    shipPlaced = true;
                } else {
                    System.out.println("Cannot place ship there. Try again: ");
                }
            }
        }
    }

    public void takeTurn(GameBoard opponentBoard) {
        System.out.println("Enter target (format: row col): ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        if (opponentBoard.getCell(row, col) == 'S') {
            opponentBoard.setCell(row, col, 'H'); // H for Hit
            System.out.println("Hit!");
        } else {
            opponentBoard.setCell(row, col, 'M'); // M for Miss
            System.out.println("Miss.");
        }
    }

    public GameBoard getBoard() {
        return board;
    }
}