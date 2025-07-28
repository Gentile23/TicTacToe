package org.example.tictactoe.service;

// Definisce la classe per il giocatore controllato dall'intelligenza artificiale (AI).
public class AIPlayer {

    // Classe interna statica per rappresentare una mossa.
    // Una mossa è composta dalle coordinate (riga, colonna) e da un punteggio.
    public static class Move {
        public int row;      // Riga della mossa
        public int col;      // Colonna della mossa
        public int score;    // Punteggio associato alla mossa (usato internamente dal minimax)

        // Costruttore per creare un oggetto Move specificando riga e colonna.
        public Move(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    /**
     * Metodo principale che trova la mossa migliore possibile per l'AI sulla scacchiera attuale.
     * @param board Lo stato attuale della scacchiera (matrice di caratteri).
     * @return L'oggetto Move che rappresenta la mossa migliore.
     */
    public static Move findBestMove(char[][] board) {
        // Inizializza la mossa migliore con valori non validi (-1, -1).
        Move bestMove = new Move(-1, -1);
        // Inizializza il punteggio migliore con il valore intero più basso possibile.
        // Questo assicura che qualsiasi punteggio calcolato sarà inizialmente migliore.
        int bestScore = Integer.MIN_VALUE;

        // Inizializza una variabile per verificare se questa è la prima mossa dell'AI.
        boolean isFirstAIMove = true;
        // Scansiona la scacchiera per vedere se l'AI ('X') ha già giocato.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Se trova una 'X', significa che l'AI ha già fatto una mossa.
                if (board[i][j] == 'X') {
                    isFirstAIMove = false; // Imposta il flag a false.
                    break; // Esce dal ciclo interno.
                }
            }
        }

        // OTTIMIZZAZIONE: Se è la prima mossa dell'AI e il centro è libero...
        if (isFirstAIMove && board[1][1] == ' ') {
            // ...scegli il centro, che è la mossa strategicamente migliore per iniziare.
            return new Move(1, 1);
        }

        // Inizia la ricerca della mossa migliore scorrendo tutte le celle.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Se la cella è vuota (' '), è una mossa possibile.
                if (board[i][j] == ' ') {
                    // Esegui la mossa sulla scacchiera (in modo temporaneo).
                    board[i][j] = 'X'; // L'AI gioca come 'X'.

                    // Calcola il punteggio di questa mossa usando l'algoritmo minimax.
                    // 'depth' è 0 (inizio della ricerca), 'isMaximizing' è false (il prossimo turno è del minimizzatore 'O').
                    int score = minimax(board, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);

                    // Annulla la mossa per ripristinare lo stato originale della scacchiera.
                    board[i][j] = ' ';

                    // Se il punteggio della mossa appena valutata è migliore del miglior punteggio trovato finora...
                    if (score > bestScore) {
                        bestScore = score; // ...aggiorna il miglior punteggio.
                        bestMove.row = i;  // ...e salva le coordinate di questa mossa come la migliore.
                        bestMove.col = j;
                    }
                }
            }
        }
        // Restituisce la mossa che ha prodotto il punteggio più alto.
        return bestMove;
    }

    /**
     * Implementazione dell'algoritmo Minimax con potatura Alpha-Beta.
     * @param board La scacchiera.
     * @param depth La profondità attuale nell'albero delle decisioni.
     * @param isMaximizing true se è il turno del giocatore che massimizza ('X'), false altrimenti ('O').
     * @param alpha Il miglior punteggio finora per il massimizzatore.
     * @param beta Il miglior punteggio finora per il minimizzatore.
     * @return Il punteggio dello stato della scacchiera.
     */
    private static int minimax(char[][] board, int depth, boolean isMaximizing, int alpha, int beta) {
        // Chiama la funzione per valutare lo stato attuale della scacchiera.
        int score = evaluateBoard(board);

        // CASI BASE (terminazione della ricorsione):

        // Se 'score' è 10, il giocatore 'X' (AI, massimizzatore) ha vinto.
        // Si sottrae 'depth' per preferire le vittorie più rapide.
        if (score == 10) return score - depth;

        // Se 'score' è -10, il giocatore 'O' (umano, minimizzatore) ha vinto.
        // Si aggiunge 'depth' per preferire le sconfitte più lontane nel tempo.
        if (score == -10) return score + depth;

        // Se la scacchiera è piena e nessuno ha vinto, è un pareggio.
        if (isBoardFull(board)) return 0;

        // PARTE RICORSIVA:

        // Se è il turno del giocatore che MASSIMIZZA ('X').
        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE; // Inizializza il punteggio con il valore più basso.
            // Scansiona la scacchiera per trovare mosse possibili.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'X'; // Prova la mossa.
                        // Chiamata ricorsiva per il turno del minimizzatore.
                        bestScore = Math.max(bestScore, minimax(board, depth + 1, false, alpha, beta));
                        board[i][j] = ' '; // Annulla la mossa.
                        // Aggiorna 'alpha', il miglior punteggio per il massimizzatore.
                        alpha = Math.max(alpha, bestScore);
                        // POTATURA ALPHA-BETA: se 'beta' (peggior scelta del minimizzatore)
                        // è <= di 'alpha' (miglior scelta del massimizzatore), questo ramo non
                        // sarà mai scelto, quindi si può interrompere la ricerca qui.
                        if (beta <= alpha) break;
                    }
                }
                if (beta <= alpha) break; // Uscita anche dal ciclo esterno
            }
            return bestScore;
        }
        // Se è il turno del giocatore che MINIMIZZA ('O').
        else {
            int bestScore = Integer.MAX_VALUE; // Inizializza il punteggio con il valore più alto.
            // Scansiona la scacchiera per trovare mosse possibili.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'O'; // Prova la mossa.
                        // Chiamata ricorsiva per il turno del massimizzatore.
                        bestScore = Math.min(bestScore, minimax(board, depth + 1, true, alpha, beta));
                        board[i][j] = ' '; // Annulla la mossa.
                        // Aggiorna 'beta', il miglior punteggio (cioè il più basso) per il minimizzatore.
                        beta = Math.min(beta, bestScore);
                        // POTATURA ALPHA-BETA: stessa logica di prima.
                        if (beta <= alpha) break;
                    }
                }
                if (beta <= alpha) break; // Uscita anche dal ciclo esterno
            }
            return bestScore;
        }
    }

    /**
     * Valuta lo stato della scacchiera e restituisce un punteggio.
     * @param board La scacchiera.
     * @return 10 se 'X' vince, -10 se 'O' vince, 0 altrimenti.
     */
    private static int evaluateBoard(char[][] board) {
        // Controlla tutte le righe per una vittoria.
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == 'X') return 10;
                if (board[row][0] == 'O') return -10;
            }
        }

        // Controlla tutte le colonne per una vittoria.
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == 'X') return 10;
                if (board[0][col] == 'O') return -10;
            }
        }

        // Controlla le due diagonali per una vittoria.
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 'X') return 10;
            if (board[0][0] == 'O') return -10;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == 'X') return 10;
            if (board[0][2] == 'O') return -10;
        }

        // Se nessuno ha vinto, restituisce 0 (indica che il gioco continua o è un pareggio).
        return 0;
    }

    /**
     * Controlla se la scacchiera è piena (nessuna cella vuota).
     * @param board La scacchiera.
     * @return true se la scacchiera è piena, false altrimenti.
     */
    private static boolean isBoardFull(char[][] board) {
        // Scansiona tutte le celle.
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                // Se trova anche una sola cella vuota, la scacchiera non è piena.
                if (board[i][j] == ' ') return false;
        // Se il ciclo finisce senza trovare celle vuote, la scacchiera è piena.
        return true;
    }
}