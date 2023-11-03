import java.util.Scanner;
import java.util.Random;

public class MainGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Basic Tic Tac Toe");
            System.out.println("2. Number Guesser (5 chances)");
            System.out.println("3. Love Calculator");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    playTicTacToe(scanner);
                    break;
                case 2:
                    playNumberGuesser(scanner);
                    break;
                case 3:
                    playLoveCalculator(scanner);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

private static void playTicTacToe(Scanner scanner) {
        System.out.print("How many players will play (2 to 5): ");
        int numPlayers = scanner.nextInt();

        if (numPlayers < 2 || numPlayers > 5) {
            System.out.println("Invalid number of players. Please choose between 2 and 5.");
            return;
        }

        System.out.print("Enter the size of the grid (3 to 5): ");
        int gridSize = scanner.nextInt();

        if (gridSize < 3 || gridSize > 5) {
            System.out.println("Invalid grid size. Please choose between 3 and 5.");
            return;
        }

        char[] playerSymbols = {'A', 'B', 'C', 'D', 'E'};

        while (true) {
            char[][] board = new char[gridSize][gridSize];
            initializeBoard(board);

            int currentPlayerIndex = 0;
            int totalMoves = 0;

            while (true) {
                char currentPlayer = playerSymbols[currentPlayerIndex];
                printBoard(board, gridSize);

                int[] move = getPlayerMove(scanner, currentPlayer, gridSize);
                int row = move[0];
                int col = move[1];

                if (isValidMove(board, row, col)) {
                    board[row][col] = currentPlayer;
                    totalMoves++;

                    if (isGameWon(board, currentPlayer, row, col)) {
                        printBoard(board, gridSize);
                        System.out.println("Player " + currentPlayer + " wins!");
                        break;
                    } else if (totalMoves == gridSize * gridSize) {
                        printBoard(board, gridSize);
                        System.out.println("It's a draw!");
                        break;
                    } else {
                        currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
                    }
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            }

            System.out.print("Play another round? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();
            if (!playAgain.equals("yes")) {
                break;
            }
        }
    }

    private static void initializeBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void printBoard(char[][] board, int gridSize) {
        System.out.print("  ");
        for (int i = 0; i < gridSize; i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println();

        for (int i = 0; i < gridSize; i++) {
            System.out.printf("%2d ", i);
            for (int j = 0; j < gridSize; j++) {
                System.out.print(board[i][j]);
                if (j < gridSize - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < gridSize - 1) {
                System.out.print("   ");
                for (int j = 0; j < gridSize; j++) {
                    if (j < gridSize - 1) {
                        System.out.print("---+");
                    } else {
                        System.out.println("---");
                    }
                }
            }
        }
    }

    private static int[] getPlayerMove(Scanner scanner, char currentPlayer, int gridSize) {
        System.out.print("Player " + currentPlayer + ", enter your move (row and column): ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        if (row >= 0 && row < gridSize && col >= 0 && col < gridSize) {
            return new int[]{row, col};
        } else {
            return getPlayerMove(scanner, currentPlayer, gridSize);
        }
    }

    private static boolean isValidMove(char[][] board, int row, int col) {
        return board[row][col] == ' ';
    }

    private static boolean isGameWon(char[][] board, char player, int row, int col) {
        int gridSize = board.length;
        int i;

        for (i = 0; i < gridSize; i++) {
            if (board[i][col] != player) {
                break;
            }
        }
        if (i == gridSize) {
            return true;
        }

        for (i = 0; i < gridSize; i++) {
            if (board[row][i] != player) {
                break;
            }
        }
        if (i == gridSize) {
            return true;
        }

        if (row == col) {
            for (i = 0; i < gridSize; i++) {
                if (board[i][i] != player) {
                    break;
                }
            }
            if (i == gridSize) {
                return true;
            }
        }

        if (row + col == gridSize - 1) {
            for (i = 0; i < gridSize; i++) {
                if (board[i][gridSize - 1 - i] != player) {
                    break;
                }
            }
            if (i == gridSize) {
                return true;
            }
        }

        return false;
    }

    private static void playNumberGuesser(Scanner scanner) {
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1;
        int numberOfTries = 0;
        int maxTries = 5;

        System.out.println("Welcome to the Number Guesser Game!");
        System.out.println("I'm thinking of a number between 1 and 100.");

        while (numberOfTries < maxTries) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            numberOfTries++;

            if (userGuess < numberToGuess) {
                System.out.println("The number is higher. Try again.");
            } else if (userGuess > numberToGuess) {
                System.out.println("The number is lower. Try again.");
            } else {
                System.out.println("Congratulations! You guessed the number " + numberToGuess + " in " + numberOfTries + " tries.");
                break;
            }
        }

        if (numberOfTries == maxTries) {
            System.out.println("Sorry, you've reached the maximum number of tries. The number was " + numberToGuess + ".");
        }
    }

    private static void playLoveCalculator(Scanner scanner) {
        System.out.println("Welcome to the Love Calculator!");
        System.out.print("Enter your name: ");
        String name1 = scanner.next();
        System.out.print("Enter your partner's name: ");
        String name2 = scanner.next();

        String flamesResult = calculateFLAMES(name1, name2);
        String result = getResultFromFLAMES(flamesResult);

        System.out.println("Your love compatibility with " + name2 + " is: " + result);
    }

    private static String calculateFLAMES(String name1, String name2) {
        String name1LowerCase = name1.toLowerCase().replaceAll(" ", "");
        String name2LowerCase = name2.toLowerCase().replaceAll(" ", "");

        for (char character : name1LowerCase.toCharArray()) {
            name2LowerCase = name2LowerCase.replaceFirst(String.valueOf(character), "");
        }

        return name2LowerCase;
    }

  private static String getResultFromFLAMES(String flamesResult) {
    String flames = "FLAMES";
    while (flames.length() > 1) {
        int index = flamesResult.length() % flames.length();
        String firstPart = flames.substring(0, index);
        String secondPart = flames.substring(index + 1);
        flames = firstPart + secondPart;
    }

    switch (flames) {
        case "F":
            return "Friends";
        case "L":
            return "Lovers";
        case "A":
            return "Admirers";
        case "M":
            return "Married";
        case "E":
            return "Enemies";
        case "S":
            return "Siblings";
        default:
            return "Invalid result";
    }
}

}
