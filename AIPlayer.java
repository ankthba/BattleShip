import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class AIPlayer extends Player {
    private Random random;
    private Queue<String> targetQueue;
    private String lastHit;

    public AIPlayer(GameBoard board) {
        super(null, board);
        random = new Random();
        targetQueue = new LinkedList<>();
        lastHit = null;
    }

    @Override
    public void placeShips() {
        int[] shipLengths = {5, 4, 3, 3, 2};
        for (int length : shipLengths) {
            Ship ship = new Ship(length);
            boolean shipPlaced = false;
            while (!shipPlaced) {
                int row = random.nextInt(10);
                int col = random.nextInt(10);
                boolean horizontal = random.nextBoolean();
                if (getBoard().canPlaceShip(ship, row, col, horizontal)) {
                    getBoard().placeShip(ship, row, col, horizontal);
                    shipPlaced = true;
                }
            }
        }
    }

    @Override
    public void takeTurn(GameBoard opponentBoard) {
        int row, col;
        if (targetQueue.isEmpty()) {
            do {
                row = random.nextInt(10);
                col = random.nextInt(10);
            } while (opponentBoard.getCell(row, col) != '-');
        } else {
            String[] coords = targetQueue.poll().split(",");
            row = Integer.parseInt(coords[0]);
            col = Integer.parseInt(coords[1]);
        }

        if (opponentBoard.getCell(row, col) == 'S') {
            opponentBoard.setCell(row, col, 'H');
            lastHit = row + "," + col;
            addTargets(row, col);
        } else {
            opponentBoard.setCell(row, col, 'M');
        }
    }

    private void addTargets(int row, int col) {
        if (lastHit != null) {
            String[] coords = lastHit.split(",");
            int lastRow = Integer.parseInt(coords[0]);
            int lastCol = Integer.parseInt(coords[1]);
            if (lastRow == row) {
                if (col > 0) targetQueue.add(row + "," + (col - 1));
                if (col < 9) targetQueue.add(row + "," + (col + 1));
            } else if (lastCol == col) {
                if (row > 0) targetQueue.add((row - 1) + "," + col);
                if (row < 9) targetQueue.add((row + 1) + "," + col);
            }
        }
    }
}
