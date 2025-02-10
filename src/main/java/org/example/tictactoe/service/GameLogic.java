package org.example.tictactoe.service;

import org.example.tictactoe.model.GameState;

public class GameLogic {
    public static boolean makeMove(GameState gameState, int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 ||
                gameState.getBoard()[row][col] != ' ' ||
                gameState.isGameOver()) {
            return false;
        }

        gameState.getBoard()[row][col] = gameState.getCurrentPlayer();

        if (checkWin(gameState.getBoard(), gameState.getCurrentPlayer())) {
            gameState.setGameOver(true);
            gameState.setWinner(String.valueOf(gameState.getCurrentPlayer()));
        } else if (isBoardFull(gameState.getBoard())) {
            gameState.setGameOver(true);
            gameState.setWinner("Draw");
        } else {
            gameState.switchPlayer();
        }

        return true;
    }

    private static boolean checkWin(char[][] board, char player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    private static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}