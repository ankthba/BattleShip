import java.util.HashMap;

public class GameBoard {
    private HashMap<String, Character> board;
    private final int size = 10;

    public GameBoard() {
        board = new HashMap<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board.put(i + "," + j, '-');
            }
        }
    }

    public void placeShip(int row, int col, int length, boolean horizontal) {
        for (int i = 0; i < length; i++) {
            if (horizontal) {
                if (col + i < size) {
                    board.put(row + "," + (col + i), 'S');
                }
            } else {
                if (row + i < size) {
                    board.put((row + i) + "," + col, 'S');
                }
            }
        }
    }

    public char getCell(int row, int col) {
        return board.get(row + "," + col);
    }

    public void setCell(int row, int col, char value) {
        board.put(row + "," + col, value);
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board.get(i + "," + j) + " ");
            }
            System.out.println();
        }
    }

    public boolean canPlaceShip(int row, int col, int length, boolean horizontal) {
        for (int i = 0; i < length; i++) {
            if (horizontal) {
                if (col + i >= size || board.get(row + "," + (col + i)) == 'S') {
                    return false;
                }
            } else {
                if (row + i >= size || board.get((row + i) + "," + col) == 'S') {
                    return false;
                }
            }
        }
        return true;
    }
}