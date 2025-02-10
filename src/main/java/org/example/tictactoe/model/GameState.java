package org.example.tictactoe.model;

import java.util.Random;

public class GameState {
    private char[][] board;
    private char currentPlayer;
    private boolean isGameOver;
    private String winner;
    private boolean isAIFirst;

    public GameState() {
        this.board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        // Scelta casuale del primo giocatore
        Random random = new Random();
        this.isAIFirst = random.nextBoolean();
        this.currentPlayer = isAIFirst ? 'X' : 'O'; // Se l'AI inizia, è X; altrimenti è il turno dell'utente (O)
        this.isGameOver = false;
        this.winner = null;
    }

    public char[][] getBoard() {
        return board;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public boolean isAIFirst() {
        return isAIFirst;
    }

    public boolean isAITurn() {
        return currentPlayer == 'X'; // L'AI gioca sempre come 'X'
    }
}
