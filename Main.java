public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        AIPlayer aiPlayer = new AIPlayer();

        // Initialize the game
        System.out.println("Player, place your ships on the game board:");
        player.placeShips();
        System.out.println("AI is placing ships...");
        aiPlayer.placeShips();

        // Game loop
        boolean gameRunning = true;
        while (gameRunning) {
            System.out.println("Player's turn:");
            player.takeTurn(aiPlayer.getBoard());
            System.out.println("AI's turn:");
            aiPlayer.takeTurn(player.getBoard());

            // Check if the game is over
            if (gameOver(player.getBoard())) {
                System.out.println("AI wins!");
                gameRunning = false;
            } else if (gameOver(aiPlayer.getBoard())) {
                System.out.println("Player wins!");
                gameRunning = false;
            }
        }
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