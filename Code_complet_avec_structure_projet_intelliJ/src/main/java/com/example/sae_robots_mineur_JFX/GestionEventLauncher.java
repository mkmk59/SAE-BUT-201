package com.example.sae_robots_mineur_JFX;

import com.example.sae_robots_mineur.Partie;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class GestionEventLauncher implements EventHandler {
    private Game fenetre_jeu;
    private Fenetre_Parametres fenetre_parametres;
    private Fenetre_commandes fenetre_commandes;
    private GameLauncherStage gameLauncherStage;

    public GestionEventLauncher(GameLauncherStage launcher_stage) {
        this.fenetre_jeu = null;
        this.fenetre_parametres = new Fenetre_Parametres();
        this.fenetre_commandes = new Fenetre_commandes();
        this.gameLauncherStage = launcher_stage;
    }

    @Override
    public void handle(Event event) {
        // Click sur le boutton LANCER:
        if (event.getSource().toString().contains("LANCER") && event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            Partie partie = new Partie();
            partie.initialiserPartie(this.fenetre_parametres.getNb_robots_Or(),this.fenetre_parametres.getNb_robots_Ni()
                        ,this.fenetre_parametres.getNb_mines_Or(),this.fenetre_parametres.getNb_mines_Ni());
            GestionEventGame gestionnaire_de_la_partie = new GestionEventGame();
            Game game = new Game(gestionnaire_de_la_partie,partie);
            gestionnaire_de_la_partie.setJeu(game);
            this.setFenetre_jeu(game);
            this.fenetre_jeu.setGestionEventLauncher(this);
            this.fenetre_jeu.show();
            this.gameLauncherStage.close();
        }

        // Choix des parmètres de la partie:
        if (event.getSource().toString().contains("Paramètres") && event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            if (this.fenetre_parametres==null) {
                this.fenetre_parametres = new Fenetre_Parametres();
            }
            this.fenetre_parametres.show();
        }

        // Affichage des commandes:
        if (event.getSource().toString().contains("Commandes") && event.getEventType() == MouseEvent.MOUSE_CLICKED) {
          this.fenetre_commandes = new Fenetre_commandes();
          this.fenetre_commandes.show();
        }
    }

    public Game getFenetre_jeu() {
        return fenetre_jeu;
    }

    public void setFenetre_jeu(Game fenetre_jeu) {
        this.fenetre_jeu = fenetre_jeu;
    }

    public Fenetre_Parametres getFenetre_parametres() {
        return fenetre_parametres;
    }

    public void setFenetre_parametres(Fenetre_Parametres fenetre_parametres) {
        this.fenetre_parametres = fenetre_parametres;
    }

    public Fenetre_commandes getFenetre_commandes() {
        return fenetre_commandes;
    }

    public void setFenetre_commandes(Fenetre_commandes fenetre_commandes) {
        this.fenetre_commandes = fenetre_commandes;
    }

    public void setGame(Game game) {
        this.fenetre_jeu = game;
    }
}
