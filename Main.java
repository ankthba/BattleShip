import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Create separate game boards for the player and AI
        GameBoard playerBoard = new GameBoard();
        GameBoard aiBoard = new GameBoard();
        
        // Create player and AI instances with their respective boards
        Player player = new Player(scanner, playerBoard);
        AIPlayer aiPlayer = new AIPlayer(aiBoard);

        // Initialize the game
        System.out.println("Player, place your ships on the game board:");
        player.placeShips();
        System.out.println("Player's board:");
        playerBoard.printBoard();
        
        System.out.println("AI is placing ships...");
        aiPlayer.placeShips();
        System.out.println("AI's board (hidden):");
        aiBoard.printBoard();

        // Game loop
        boolean gameRunning = true;
        while (gameRunning) {
            // Player's turn: pass AI board to player
            System.out.println("Player's turn:");
            player.takeTurn(aiBoard);
            System.out.println("Current status of Player's board:");
            playerBoard.printBoard();
            System.out.println("Current status of AI's board:");
            aiBoard.printBoard();
            
            if (gameOver(aiBoard)) {
                System.out.println("Player wins!");
                break;
            }

            // AI's turn: pass player board to AI
            System.out.println("AI's turn:");
            aiPlayer.takeTurn(playerBoard);
            System.out.println("Current status of Player's board:");
            playerBoard.printBoard();
            System.out.println("Current status of AI's board:");
            aiBoard.printBoard();

            if (gameOver(playerBoard)) {
                System.out.println("AI wins!");
                break;
            }
        }
        scanner.close();
    }

    public static boolean gameOver(GameBoard board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board.getCell(i, j) == 'S') {
                    return false;
                }
            }
        }
        return true;
    }
}
