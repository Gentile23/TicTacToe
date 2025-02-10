package org.example.tictactoe.service;

public class AIPlayer {
    public static class Move {
        public int row;
        public int col;
        public int score;

        public Move(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static Move findBestMove(char[][] board) {
        Move bestMove = new Move(-1, -1);
        int bestScore = Integer.MIN_VALUE;

        // Verifica se è la prima mossa dell'AI contando solo le X
        boolean isFirstAIMove = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'X') {
                    isFirstAIMove = false;
                    break;
                }
            }
        }

        // Se è la prima mossa dell'AI e il centro è disponibile, prendilo
        if (isFirstAIMove && board[1][1] == ' ') {
            return new Move(1, 1);
        }

        // Cerca la mossa migliore
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'X'; // L'AI gioca come 'X'
                    int score = minimax(board, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    board[i][j] = ' '; // Annulla la mossa

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove.row = i;
                        bestMove.col = j;
                    }
                }
            }
        }

        return bestMove;
    }

    private static int minimax(char[][] board, int depth, boolean isMaximizing, int alpha, int beta) {
        // Valutazione dello stato terminale
        int score = evaluateBoard(board);

        if (score == 10) return score - depth;  // AI vince
        if (score == -10) return score + depth; // Giocatore vince
        if (isBoardFull(board)) return 0;      // Pareggio

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'X'; // L'AI gioca come 'X'
                        bestScore = Math.max(bestScore, minimax(board, depth + 1, false, alpha, beta));
                        board[i][j] = ' '; // Annulla la mossa
                        alpha = Math.max(alpha, bestScore);
                        if (beta <= alpha) break;
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'O'; // Il giocatore gioca come 'O'
                        bestScore = Math.min(bestScore, minimax(board, depth + 1, true, alpha, beta));
                        board[i][j] = ' '; // Annulla la mossa
                        beta = Math.min(beta, bestScore);
                        if (beta <= alpha) break;
                    }
                }
            }
            return bestScore;
        }
    }

    private static int evaluateBoard(char[][] board) {
        // Controlla righe
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == 'X') return 10;
                if (board[row][0] == 'O') return -10;
            }
        }

        // Controlla colonne
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == 'X') return 10;
                if (board[0][col] == 'O') return -10;
            }
        }

        // Controlla diagonali
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 'X') return 10;
            if (board[0][0] == 'O') return -10;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == 'X') return 10;
            if (board[0][2] == 'O') return -10;
        }

        return 0;
    }

    private static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ') return false;
        return true;
    }
}
