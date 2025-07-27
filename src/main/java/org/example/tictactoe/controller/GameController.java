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
        return gameState;
    }

    /**
     * Gestisce solo la mossa del giocatore umano.
     */
    @PostMapping("/move")
    public GameState makeMove(@RequestParam int row, @RequestParam int col) {
        // Esegui la mossa solo se il gioco non è finito e se è il turno del giocatore
        if (!gameState.isGameOver() && !gameState.isAITurn()) {
            GameLogic.makeMove(gameState, row, col);
        }
        return gameState;
    }

    /**
     * Nuovo endpoint per far giocare l'IA.
     * Il frontend lo chiamerà dopo un ritardo.
     */
    @PostMapping("/ai-move")
    public GameState triggerAIMove() {
        // Esegui la mossa dell'IA solo se il gioco non è finito e se è il suo turno
        if (!gameState.isGameOver() && gameState.isAITurn()) {
            AIPlayer.Move aiMove = AIPlayer.findBestMove(gameState.getBoard());
            GameLogic.makeMove(gameState, aiMove.row, aiMove.col);
        }
        return gameState;
    }

    @PostMapping("/reset")
    public GameState resetGame() {
        gameState = new GameState();
        // Non facciamo più muovere l'IA qui.
        // Il frontend gestirà la prima mossa dell'IA se necessario.
        return gameState;
    }
}