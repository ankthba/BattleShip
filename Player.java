import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player {
    private GameBoard board;
    private Scanner scanner;

    public Player() {
        board = new GameBoard();
        scanner = new Scanner(System.in);
    }

    public void placeShips() {
        int[] shipLengths = {5, 4, 3, 3, 2}; // lengths of the ships in Battleship
        System.out.println("Place your ships (format: 'place ship of length [length] at row [row] column [col] [horizontally/vertically]'): ");
        Pattern pattern = Pattern.compile("place ship of length (\\d+) at row (\\d+) column (\\d+) (horizontally|vertically)");
        for (int length : shipLengths) {
            Ship ship = new Ship(length);
            boolean shipPlaced = false;
            while (!shipPlaced) {
                String input = scanner.nextLine();
                Matcher matcher = pattern.matcher(input);
                if (matcher.matches()) {
                    int row = Integer.parseInt(matcher.group(2));
                    int col = Integer.parseInt(matcher.group(3));
                    boolean horizontal = matcher.group(4).equals("horizontally");
                    if (board.canPlaceShip(ship, row, col, horizontal)) {
                        board.placeShip(ship, row, col, horizontal);
                        shipPlaced = true;
                    } else {
                        System.out.println("Cannot place ship there. Try again: ");
                    }
                } else {
                    System.out.println("Invalid input. Try again: ");
                }
            }
        }
    }

    public void takeTurn(GameBoard opponentBoard) {
        System.out.println("Enter target (format: 'target row [row] column [col]'): ");
        Pattern pattern = Pattern.compile("target row (\\d+) column (\\d+)");
        while (true) {
            String input = scanner.nextLine();
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                int row = Integer.parseInt(matcher.group(1));
                int col = Integer.parseInt(matcher.group(2));
                if (opponentBoard.getCell(row, col) == 'S') {
                    opponentBoard.setCell(row, col, 'H'); // H for Hit
                    System.out.println("Hit!");
                } else {
                    opponentBoard.setCell(row, col, 'M'); // M for Miss
                    System.out.println("Miss.");
                }
                break;
            } else {
                System.out.println("Invalid input. Try again: ");
            }
        }
    }

    public GameBoard getBoard() {
        return board;
    }
}