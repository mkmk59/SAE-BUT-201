package com.example.java301fx;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;

public class GestionEventGame implements EventHandler {
    private Game jeu;

    public GestionEventGame() {
        this.jeu = null;
    }
    public GestionEventGame(Game jeu) {
        this.jeu = jeu;
    }

    @Override
    public void handle(Event event) {
        // Gestion des bouttons souris
        if (this.jeu.getCurseur_robot() == null) {
            if (!(event.getSource().toString().contains("Paramètres")) && !(event.getSource().toString().contains("Commandes"))){
                Alert robot_non_selectionne = new Alert(Alert.AlertType.WARNING);
                robot_non_selectionne.setContentText("Aucun robot sélectionné !\nClique sur l'un des robots présents sur la carte\npour le sélectionner.");
                robot_non_selectionne.setTitle("Robot non sélectionné");
                robot_non_selectionne.show();
            }
        } else if (event.getSource().toString().contains("b_gauche") && event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            //Déplacement du robot
            this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().remove(this.jeu.getCurseur_robot().getSkin());
            this.jeu.getCurseur_robot().getRobot().Aller_a_gauche();
            this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().add(this.jeu.getCurseur_robot().getSkin());

            // Changement des informations
            this.jeu.getInfo_jeu().getChildren().clear();
            this.jeu.setInfo_jeu(this.jeu.creerVboxInformationsJeu());

        } else if (event.getSource().toString().contains("b_droite") && event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            //Déplacement du robot
            this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().remove(this.jeu.getCurseur_robot().getSkin());
            this.jeu.getCurseur_robot().getRobot().Aller_a_droite();
            this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().add(this.jeu.getCurseur_robot().getSkin());

            // Changement des informations
            this.jeu.getInfo_jeu().getChildren().clear();
            this.jeu.setInfo_jeu(this.jeu.creerVboxInformationsJeu());

        } else if (event.getSource().toString().contains("b_haut") && event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            //Déplacement du robot
            this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().remove(this.jeu.getCurseur_robot().getSkin());
            this.jeu.getCurseur_robot().getRobot().Aller_en_haut();
            this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().add(this.jeu.getCurseur_robot().getSkin());

            // Changement des informations
            this.jeu.getInfo_jeu().getChildren().clear();
            this.jeu.setInfo_jeu(this.jeu.creerVboxInformationsJeu());

        } else if (event.getSource().toString().contains("b_bas") && event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            //Déplacement du robot
            this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().remove(this.jeu.getCurseur_robot().getSkin());
            this.jeu.getCurseur_robot().getRobot().Aller_en_bas();
            this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().add(this.jeu.getCurseur_robot().getSkin());

            // Changement des informations
            this.jeu.getInfo_jeu().getChildren().clear();
            this.jeu.setInfo_jeu(this.jeu.creerVboxInformationsJeu());

        } else if (event.getSource().toString().contains("b_piocher") && event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            //Déplacement du robot
            this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().remove(this.jeu.getCurseur_robot().getSkin());
            this.jeu.getCurseur_robot().getRobot().collecter();
            this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().add(this.jeu.getCurseur_robot().getSkin());

            // Changement des informations
            this.jeu.getInfo_jeu().getChildren().clear();
            this.jeu.setInfo_jeu(this.jeu.creerVboxInformationsJeu());

        } else if (event.getSource().toString().contains("b_deposer") && event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            //Déplacement du robot
            this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().remove(this.jeu.getCurseur_robot().getSkin());
            this.jeu.getCurseur_robot().getRobot().deposer();
            this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().add(this.jeu.getCurseur_robot().getSkin());

            // Changement des informations
            this.jeu.getInfo_jeu().getChildren().clear();
            this.jeu.setInfo_jeu(this.jeu.creerVboxInformationsJeu());
        }
        else if (event.getSource().toString().contains("b_passer") && event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            //Passage du tour du robot
            this.jeu.getCurseur_robot().getRobot().setNombre_action(1);

            // Changement des informations
            this.jeu.getInfo_jeu().getChildren().clear();
            this.jeu.setInfo_jeu(this.jeu.creerVboxInformationsJeu());
        }

        // GESTIONS DES TOUCHES

        else if (event.getEventType().equals(KeyEvent.KEY_PRESSED)){
            // piocher
            if (((KeyEvent) event).getCode().equals(this.jeu.getGestionEventLauncher().getFenetre_commandes().getPiocher())){
                this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().remove(this.jeu.getCurseur_robot().getSkin());
                this.jeu.getCurseur_robot().getRobot().collecter();
                this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().add(this.jeu.getCurseur_robot().getSkin());

                // Changement des informations
                this.jeu.getInfo_jeu().getChildren().clear();
                this.jeu.setInfo_jeu(this.jeu.creerVboxInformationsJeu());
            }
            // déposer
            else if (((KeyEvent) event).getCode().equals(this.jeu.getGestionEventLauncher().getFenetre_commandes().getDeposer())){
                this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().remove(this.jeu.getCurseur_robot().getSkin());
                this.jeu.getCurseur_robot().getRobot().deposer();
                this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().add(this.jeu.getCurseur_robot().getSkin());

                // Changement des informations
                this.jeu.getInfo_jeu().getChildren().clear();
                this.jeu.setInfo_jeu(this.jeu.creerVboxInformationsJeu());
            }
            // aller à gauche
            else if (((KeyEvent) event).getCode().equals(this.jeu.getGestionEventLauncher().getFenetre_commandes().getAller_a_gauche())){
                this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().remove(this.jeu.getCurseur_robot().getSkin());
                this.jeu.getCurseur_robot().getRobot().Aller_a_gauche();
                this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().add(this.jeu.getCurseur_robot().getSkin());

                // Changement des informations
                this.jeu.getInfo_jeu().getChildren().clear();
                this.jeu.setInfo_jeu(this.jeu.creerVboxInformationsJeu());
            }
            // aller à droite
            else if (((KeyEvent) event).getCode().equals(this.jeu.getGestionEventLauncher().getFenetre_commandes().getAller_a_droite())){
                this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().remove(this.jeu.getCurseur_robot().getSkin());
                this.jeu.getCurseur_robot().getRobot().Aller_a_droite();
                this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().add(this.jeu.getCurseur_robot().getSkin());

                // Changement des informations
                this.jeu.getInfo_jeu().getChildren().clear();
                this.jeu.setInfo_jeu(this.jeu.creerVboxInformationsJeu());
            }
            // aller en haut
            else if (((KeyEvent) event).getCode().equals(this.jeu.getGestionEventLauncher().getFenetre_commandes().getAller_en_haut())){
                this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().remove(this.jeu.getCurseur_robot().getSkin());
                this.jeu.getCurseur_robot().getRobot().Aller_en_haut();
                this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().add(this.jeu.getCurseur_robot().getSkin());

                // Changement des informations
                this.jeu.getInfo_jeu().getChildren().clear();
                this.jeu.setInfo_jeu(this.jeu.creerVboxInformationsJeu());
            }
            // aller en bas
            else if (((KeyEvent) event).getCode().equals(this.jeu.getGestionEventLauncher().getFenetre_commandes().getAller_en_bas())){
                this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().remove(this.jeu.getCurseur_robot().getSkin());
                this.jeu.getCurseur_robot().getRobot().Aller_en_bas();
                this.jeu.getCurseur_robot().getRobot().getParcelle().getParcelleGUI().getChildren().add(this.jeu.getCurseur_robot().getSkin());

                // Changement des informations
                this.jeu.getInfo_jeu().getChildren().clear();
                this.jeu.setInfo_jeu(this.jeu.creerVboxInformationsJeu());
            }
            // Passer son tour
            else if (((KeyEvent) event).getCode().equals(this.jeu.getGestionEventLauncher().getFenetre_commandes().getPasser_tour_robot())){
                this.jeu.getCurseur_robot().getRobot().setNombre_action(1);
                // Changement des informations
                this.jeu.getInfo_jeu().getChildren().clear();
                this.jeu.setInfo_jeu(this.jeu.creerVboxInformationsJeu());
            }
        }

        // Fin d'un tour de jeu
        if (this.jeu.getPartie().passerAuTourSuivant() && this.jeu.getPartie().getRobots().size()!=0){
            // Changement des informations
            this.jeu.getInfo_jeu().getChildren().clear();
            this.jeu.setInfo_jeu(this.jeu.creerVboxInformationsJeu());

            // Affichage d'une fenêtre qui indique le changement du tour
            Alert fin_tour = new Alert(Alert.AlertType.INFORMATION);
            fin_tour.setHeight(250);
            fin_tour.setWidth(400);
            fin_tour.setContentText("Tous les robots ont joué !");
            fin_tour.setHeaderText("FIN DU TOUR");
            fin_tour.setTitle("Fin de Tour");
            fin_tour.show();
        }
        // Afficher les commandes de jeu
        if (event.getSource().toString().contains("Commandes")){
            this.jeu.getGestionEventLauncher().getFenetre_commandes().show();
        }

        // Fin du jeu si toutes les mines sont vides et tous les robots également == entrepôts tous plein
        if (this.jeu.getPartie().fin_de_partie() && this.jeu.getPartie().getTour()!=0){
            Alert fin_de_partie = new Alert(Alert.AlertType.CONFIRMATION);
            fin_de_partie.setTitle("Fin de la partie.");
            fin_de_partie.setHeaderText("GAGNE ! Nombre de tours = "+this.jeu.getPartie().getTour());
            fin_de_partie.setContentText("FIN DE LA PARTIE");
            fin_de_partie.show();
            if (!fin_de_partie.isShowing() && this.jeu.isShowing()) {
                this.jeu.close();
                System.exit(0);
            }
        }

    }

    public void setJeu(Game jeu) {
        this.jeu = jeu;
    }
}
