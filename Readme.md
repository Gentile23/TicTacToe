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

## Tecnologie Utilizzate
-  **Java** - Il linguaggio di programmazione utilizzato per lo sviluppo back-end.
-  **Spring Framework** - Il framework Java utilizzato per lo sviluppo (Spring MVC/Web).
-  **Maven** - Gestione delle dipendenze.
-  **HTML5** - Il linguaggio di programmazione utilizzato per lo sviluppo front-end.

## Come avviare TicTacToe

### Avvio dell'Applicazione con IntelliJ IDEA

Per eseguire correttamente l'applicazione Spring Boot tramite **IntelliJ IDEA**, segui questi passaggi:
- **Apri il progetto in IntelliJ IDEA**
    - Avvia IntelliJ IDEA e apri la cartella del progetto Spring Boot.
    - Assicurati che il progetto sia importato correttamente come progetto **Maven** o **Gradle** (a seconda del sistema di build utilizzato).
- **Configura una nuova configurazione di esecuzione**
    - Vai su **Esegui** → **Modifica configurazioni...** (Run → Edit Configurations...).
    - Clicca su **+** in alto a sinistra e seleziona **Spring Boot**.
    - Inserisci un nome descrittivo per la configurazione, ad esempio `Avvio Applicazione`.
    - Nel campo **Classe principale (Main class)**, seleziona la classe che contiene il metodo `main()`, ad esempio:
      ```
      com.example.TicTacToeApplication
      ```
- **Salva la configurazione**
    - Clicca su **Applica** e poi su **OK** per salvare la configurazione.
- **Avvia l'applicazione**
    - Seleziona la configurazione appena creata nella barra in alto.
    - Clicca sul pulsante **Esegui** (▶) oppure premi `Shift + F10`.
    - Attendi che nella console venga visualizzato il messaggio:
      ```
      Started Application in ... seconds
      ```
      che indica l’avvio corretto dell'applicazione.
- **Accedi all'applicazione tramite browser**
    - Una volta avviata, l'applicazione sarà accessibile all'indirizzo:
      ```
      http://localhost:8080
      ```
- Se hai configurato una porta diversa, assicurati di aggiornare l'indirizzo di conseguenza.

### Avvio con Maven  
Se preferisci avviare l’app tramite terminale, esegui:  
```sh
mvn spring-boot:run
