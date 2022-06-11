package com.example.sae_robots_mineur;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;

public class Fenetre_commandes extends Stage {
    private KeyCode deposer;
    private KeyCode piocher;
    private KeyCode aller_a_gauche;
    private KeyCode aller_en_haut;
    private KeyCode aller_a_droite;
    private KeyCode aller_en_bas;
    private KeyCode passer_tour_robot;

    public Fenetre_commandes() {
        // Commandes par défaut
        this.deposer = KeyCode.E;
        this.piocher = KeyCode.A;
        this.aller_a_droite = KeyCode.D;
        this.aller_a_gauche = KeyCode.Q;
        this.aller_en_haut = KeyCode.Z;
        this.aller_en_bas = KeyCode.S;
        this.passer_tour_robot = KeyCode.F;

        Group grid_touches = new Group();
        GridPane touches = new GridPane();
        VBox root_commandes = new VBox();

        Label titre = new Label("COMMANDES DE JEU");
        titre.setFont(Font.font("Century Gothic", FontWeight.BOLD,20));

        Label lb_deposer = new Label("Déposer");
        Button b_deposer = new Button("E");
        b_deposer.setPrefSize(40,40);

        Label lb_piocher = new Label("Piocher");
        Button b_piocher = new Button("A");
        b_piocher.setPrefSize(40,40);

        Label lb_haut = new Label("Déplacement vers le haut");
        Button b_haut = new Button("Z");
        b_haut.setPrefSize(40,40);

        Label lb_bas = new Label("Déplacement vers le bas");
        Button b_bas = new Button("S");
        b_bas.setPrefSize(40,40);

        Label lb_droite = new Label("Déplacement vers la droite");
        Button b_droite = new Button("D");
        b_droite.setPrefSize(40,40);

        Label lb_gauche = new Label("Déplacement vers la gauche");
        Button b_gauche = new Button("Q");
        b_gauche.setPrefSize(40,40);

        Label lb_passer_tour_robot = new Label("Passer le tour du robot");
        Button b_passer_tour_robot = new Button("F");
        b_passer_tour_robot.setPrefSize(40,40);

        touches.add(lb_haut, 0, 0);
        touches.add(b_haut, 1, 0);
        touches.add(lb_deposer, 2, 0);
        touches.add(b_deposer, 3, 0);
        touches.add(lb_gauche, 0, 1);
        touches.add(b_gauche, 1, 1);
        touches.add(lb_piocher, 2, 1);
        touches.add(b_piocher, 3, 1);
        touches.add(lb_bas, 0, 2);
        touches.add(b_bas, 1, 2);
        touches.add(lb_passer_tour_robot, 2, 2);
        touches.add(b_passer_tour_robot, 3, 2);
        touches.add(lb_droite, 0, 3);
        touches.add(b_droite, 1, 3);

        touches.setVgap(10);
        touches.setHgap(10);
        grid_touches.getChildren().add(touches);

        Button fermer = new Button("Fermer");
        fermer.setMaxWidth(200);
        fermer.setFont(Font.font("Century Gothic", FontWeight.BOLD,15));
        fermer.setAlignment(Pos.CENTER);

        root_commandes.getChildren().addAll(titre, grid_touches, fermer);
        root_commandes.setSpacing(30);
        root_commandes.setAlignment(Pos.CENTER);

        Scene scene_commandes = new Scene(root_commandes, 450, 450);
        this.setScene(scene_commandes);
        this.setTitle("Commandes de jeu");
        this.centerOnScreen();

        /*
        // Background bouton
        File file_fond_bouton = new File("src/image/fond_bouton.jpg");
        Image img_fond_bouton = new Image(file_fond_bouton.toURI().toString());
        BackgroundImage image_jouer = new BackgroundImage(img_fond_bouton,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        //set du background a chaque boutons
        fermer.setBackground(new Background(image_jouer));
        */

        //Evénements
        fermer.setOnMouseClicked(event->{
            this.close();
        });
    }

    public KeyCode getDeposer() {
        return deposer;
    }

    public void setDeposer(KeyCode deposer) {
        this.deposer = deposer;
    }

    public KeyCode getPiocher() {
        return piocher;
    }

    public void setPiocher(KeyCode piocher) {
        this.piocher = piocher;
    }

    public KeyCode getAller_a_gauche() {
        return aller_a_gauche;
    }

    public void setAller_a_gauche(KeyCode aller_a_gauche) {
        this.aller_a_gauche = aller_a_gauche;
    }

    public KeyCode getAller_en_haut() {
        return aller_en_haut;
    }

    public void setAller_en_haut(KeyCode aller_en_haut) {
        this.aller_en_haut = aller_en_haut;
    }

    public KeyCode getAller_a_droite() {
        return aller_a_droite;
    }

    public void setAller_a_droite(KeyCode aller_a_droite) {
        this.aller_a_droite = aller_a_droite;
    }

    public KeyCode getAller_en_bas() {
        return aller_en_bas;
    }

    public void setAller_en_bas(KeyCode aller_en_bas) {
        this.aller_en_bas = aller_en_bas;
    }

    public KeyCode getPasser_tour_robot() {
        return passer_tour_robot;
    }

    public void setPasser_tour_robot(KeyCode passer_tour_robot) {
        this.passer_tour_robot = passer_tour_robot;
    }
}

