package org.example.tictactoe.controller;

import org.example.tictactoe.model.GameState;
import org.example.tictactoe.service.AIPlayer;
import org.example.tictactoe.service.GameLogic;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {
    private GameState gameState = new GameState();

    @GetMapping("/state")
    public GameState getGameState() {
        // Se l'IA inizia per prima, fai la sua mossa
        if (gameState.isAIFirst() && gameState.getBoard()[0][0] == ' ') {
            AIPlayer.Move aiMove = AIPlayer.findBestMove(gameState.getBoard());
            GameLogic.makeMove(gameState, aiMove.row, aiMove.col);
        }
        return gameState;
    }

    @PostMapping("/move")
    public GameState makeMove(@RequestParam int row, @RequestParam int col) {
        if (GameLogic.makeMove(gameState, row, col)) {
            // Se il gioco non è finito e è il turno dell'IA, fai la mossa
            if (!gameState.isGameOver() && gameState.isAITurn()) {
                AIPlayer.Move aiMove = AIPlayer.findBestMove(gameState.getBoard());
                GameLogic.makeMove(gameState, aiMove.row, aiMove.col);
            }
        }
        return gameState;
    }

    @PostMapping("/reset")
    public GameState resetGame() {
        gameState = new GameState();
        // Se l'IA inizia, fai la sua prima mossa
        if (gameState.isAIFirst()) {
            AIPlayer.Move aiMove = AIPlayer.findBestMove(gameState.getBoard());
            GameLogic.makeMove(gameState, aiMove.row, aiMove.col);
        }
        return gameState;
    }
}