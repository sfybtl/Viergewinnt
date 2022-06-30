public class Game {
    public static final int VIER_GEWINNT = 4;
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
    public static final int NUMBER_OF_PLAYERS = 2;
    private static final int[][] EMPTY_BOARD = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
    };
    
    public static int[][] board = EMPTY_BOARD;

    public static int activePlayer = 1;
    public static int winner = 0;

    public static String statusMessage = "";
    public static Object title;

    public static void startNewGame() {
        activePlayer = 1;
        winner = 0;
        statusMessage = "";
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                board[row][column] = 0;
            }
        }

    }

    public static void playChip(int column) {
        if (winner > 0) {
            return;
        }

        int numberOfChipsInColumn = getNumberOfChipsInColumn(column);
        if (numberOfChipsInColumn < 6) {


            int rowForChip = 5 - numberOfChipsInColumn;
            board[rowForChip][column] = activePlayer;

            if (activePlayer == 1) {
                activePlayer = 2;
            } else {
                activePlayer = 1;
            }

            checkBoard();
        }
    }

    private static int getNumberOfChipsInColumn(int column) {
        int numberOfChipsInColumn = 0;
        for (int row = 5; row >= 0; row--) {
            if (board[row][column] > 0) {
                numberOfChipsInColumn = numberOfChipsInColumn + 1;
            }

        }
        return numberOfChipsInColumn;
    }


    private static int checkRowsForWinner() {
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 4; column++) {
                if (board[row][column] == 1 && board[row][column + 1] == 1 && board[row][column + 2] == 1 && board[row][column + 3] == 1) {
                    return 1;
                }
                else if (board[row][column] == 2 && board[row][column + 1] == 2 && board[row][column + 2] == 2 && board[row][column + 3] == 2) {
                    return 2;
                }
            }

        } return 0;
    }


    private static int checkColumnsForWinner() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 7; column++) {
                if (board[row][column] == 1 && board[row + 1][column] == 1 && board[row + 2][column] == 1 && board[row + 3][column] == 1) {
                    return 1;
                } else if (board[row][column] == 2 && board[row + 1][column] == 2 && board[row + 2][column] == 2 && board[row + 3][column] == 2) {
                    return 2;
                }
            }
        }

        return 0;
    }


    private static int checkUpperLeftToLowerRightForWinner() {
        for (int row = 0; row <3; row++) {
            for (int column = 0; column <3; column++) {
                if (board[row][column] == 1 && board[row + 1][column + 1] == 1 && board[row + 2][column + 2] == 1 && board[row + 3][column + 3] == 1) {
                    return 1;
                }
                else if (board[row][column] == 2 && board[row + 1][column + 1] == 2 && board[row + 2][column + 2] == 2 && board[row + 3][column + 3] == 2) {
                    return 2;
                }
            }
            // TODO check diagonals upper left to lower right for a winner
       }
        return 0;
    }

    public static int checkLowerLeftToUpperRight() {
        for (int row = 3; row <6; row++) {
            for (int column = 0; column <4; column++) {
                if (board[row][column] == 1 && board[row - 1][column + 1] == 1 && board[row - 2][column + 2] == 1 && board[row - 3][column + 3] == 1) {
                    return 1;
                }
                else if (board[row][column] == 2 && board[row - 1][column + 1] == 2 && board[row - 2][column + 2] == 2 && board[row - 3][column + 3] == 2) {
                    return 2;
                }
            }
            // TODO check diagonals upper left to lower right for a winner
        }
        return 0;
    }


    private static void checkBoard() {
        // check rows for a winner
        int winnerInRow = checkRowsForWinner();
        if (winnerInRow > 0) {
            statusMessage = "Spieler " + winnerInRow + " hat GEWONNEN.";
            winner = winnerInRow;
            activePlayer = 0;
            UserInterface.setWinner(winnerInRow);
            return;
        }


        // check columns for a winner
        int winnerInColumns = checkColumnsForWinner();
        if (winnerInColumns > 0) {
            statusMessage = "Spieler " + winnerInColumns + " hat GEWONNEN.";
            winner = winnerInColumns;
            activePlayer = 0;
            UserInterface.setWinner(winnerInColumns);
            return;
        }

        // check diagonals for a winner
        int winnerInDiagonals = checkUpperLeftToLowerRightForWinner();
        if (winnerInDiagonals > 0) {
            statusMessage = "Spieler " + winnerInDiagonals + " hat GEWONNEN.";
            winner = winnerInDiagonals;
            activePlayer = 0;
            UserInterface.setWinner(winnerInDiagonals);
            return;
        }

        winnerInDiagonals = checkLowerLeftToUpperRight();
        if (winnerInDiagonals > 0) {
            statusMessage = "Spieler " + winnerInDiagonals + " hat GEWONNEN.";
            winner = winnerInDiagonals;
            activePlayer = 0;
            UserInterface.setWinner(winnerInDiagonals);
        }
    }
}
