<p align="center"><img src="src/main/resources/img/TicTacToe.jpg" style="width:500px;height:500px">

# TicTacToe AI

TicTacToe AI è un progetto che mira a sviluppare un'intelligenza artificiale capace di giocare a Tris in modo ottimale. Il sistema è stato progettato per offrire un'esperienza di gioco intuitiva, sia per chi desidera sfidare l'IA, sia per chi vuole analizzare strategie di gioco.

## Autore
Giulio Gentile

## Documentazione
Per accedere alla documentazione ufficiale, clicca sul seguente link:

[Documentazione ufficiale](src/main/resources/TicTacToe.pdf)

Per accedere alla presentazione ufficiale, clicca sul seguente link:

[Presentazione ufficiale](src/main/resources/TicTacToe_presentation.pdf)

## Utilizzo
L'IA utilizza algoritmi di ricerca come Minimax con ottimizzazioni tramite Alpha-Beta Pruning per garantire mosse ottimali.
L'utente può scegliere se giocare contro l'IA o osservare una partita tra due AI.

## Tecnologie Utilizzate
-  **Java** - Il linguaggio di programmazione utilizzato per lo sviluppo back-end.
-  **Spring Framework** - Il framework Java utilizzato per lo sviluppo (Spring MVC/Web).
-  **Maven** - Gestione delle dipendenze.
-  **HTML5** - Il linguaggio di programmazione utilizzato per lo sviluppo front-end.

## Come avviare TicTacToe

### Avvio con IntelliJ IDEA  
1. Apri il progetto in **IntelliJ IDEA**  
2. Vai su **Esegui** → **Modifica configurazioni...**  
3. Clicca su **+** → **Spring Boot** e seleziona la classe principale del progetto (es. `com.example.TicTacToeApplication`)  
4. Salva la configurazione e premi il pulsante **Esegui** (▶) per avviare l’applicazione  

### Avvio con Maven  
Se preferisci avviare l’app tramite terminale, esegui:  
```sh
mvn spring-boot:run
