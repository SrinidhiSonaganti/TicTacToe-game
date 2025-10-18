import java.util.Scanner;

public class tictactoe {

    private static final int SIZE = 3;   
    private static char[][] board = new char[SIZE][SIZE]; 
    private static char currentPlayer = 'X'; // Current player ('X' or 'O')

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe Game!");
        System.out.println("Two players: Player 1 -> 'X', Player 2 -> 'O'");
        System.out.println("To make a move, enter row and column (0, 1, or 2). Example: 1 2");
        System.out.println("Let's begin!\n");

        boolean playAgain;
        do {
            initializeBoard();
            playGame(sc);
            playAgain = askReplay(sc);
        } while (playAgain);

        System.out.println("Thanks for playing! Goodbye!");
        sc.close();
    }

    /** 
     * Initializes the board with empty cells ('-')
     */
    private static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /** 
     * Displays the current state of the board 
     */
/**
 * Displays the current state of the board with closed borders (grid style)
 */
private static void printBoard() {
    System.out.println("Current Board:");
    System.out.println("┌───┬───┬───┐");
    for (int i = 0; i < SIZE; i++) {
        System.out.print("│ ");
        for (int j = 0; j < SIZE; j++) {
            System.out.print(board[i][j] + " │ ");
        }
        System.out.println();
        if (i < SIZE - 1) {
            System.out.println("├───┼───┼───┤");
        }
    }
    System.out.println("└───┴───┴───┘");
}



    /** 
     * Handles the main gameplay loop 
     */
    private static void playGame(Scanner sc) {
        boolean gameEnded = false;
        currentPlayer = 'X'; // Start with Player X

        while (!gameEnded) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");

            int row = sc.nextInt();
            int col = sc.nextInt();

            // Validate move
            if (!isValidMove(row, col)) {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            // Place move
            board[row][col] = currentPlayer;

            // Check game status
            if (checkWin(currentPlayer)) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins! 🎉");
                gameEnded = true;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                gameEnded = true;
            } else {
                switchPlayer();
            }
        }
    }

    /** 
     * Checks if the input move is valid 
     */
    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == '-';
    }

    /** 
     * Switches turn between players 
     */
    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    /** 
     * Checks if the current player has won 
     */
    private static boolean checkWin(char player) {
        // Check rows
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player)
                return true;
        }

        // Check columns
        for (int j = 0; j < SIZE; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player)
                return true;
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player)
            return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player)
            return true;

        return false;
    }

    /** 
     * Checks if the board is completely filled 
     */
    private static boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '-')
                    return false;
            }
        }
        return true;
    }

    /** 
     * Asks players if they want to play again 
     */
    private static boolean askReplay(Scanner sc) {
        System.out.println("Do you want to play again? (yes/no)");
        String response = sc.next().toLowerCase();
        return response.startsWith("y");
    }
}
