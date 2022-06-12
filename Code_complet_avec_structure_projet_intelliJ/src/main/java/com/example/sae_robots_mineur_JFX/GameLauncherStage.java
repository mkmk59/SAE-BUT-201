package com.example.sae_robots_mineur_JFX;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;

public class GameLauncherStage extends Stage {

    public GameLauncherStage() {
        HBox bouttons = new HBox();
        Button jouer = new Button("LANCER");
        Button parametres = new Button("Paramètres");
        Button commandes = new Button("Commandes");
        jouer.setPrefWidth(200);
        jouer.setFont(Font.font("Century Gothic", FontWeight.BOLD,15));
        parametres.setPrefWidth(200);
        parametres.setFont(Font.font("Century Gothic", FontWeight.BOLD,15));
        commandes.setPrefWidth(200);
        commandes.setFont(Font.font("Century Gothic", FontWeight.BOLD,15));
        bouttons.getChildren().addAll(parametres,jouer,commandes);
        bouttons.setSpacing(40);
        bouttons.setAlignment(Pos.CENTER);
        Pane root = new Pane();
        root.getChildren().add(bouttons);
        bouttons.setLayoutX(200);
        bouttons.setLayoutY(500);
        Scene scene = new Scene(root,1080,600);
        File file_mine_or = new File("src/image/image_launcher_robot_mineur.jpg");
        Image img_background = new Image(file_mine_or.toURI().toString());
        BackgroundSize backgroundSize = new BackgroundSize(800,
                600,
                false,
                false,
                true,
                true);
        BackgroundImage image = new BackgroundImage(img_background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
        root.setBackground(new Background(image));
        this.setScene(scene);
        this.setTitle("Launcher_Robot_Mineur");
        this.setResizable(false);
        this.centerOnScreen();

        /*
        // Background des boutons
        File file_fond_bouton = new File("src/image/fond_bouton.jpg");
        Image img_fond_bouton = new Image(file_fond_bouton.toURI().toString());
        BackgroundImage image_jouer = new BackgroundImage(img_fond_bouton,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        //set du background a chaque boutons
        jouer.setBackground(new Background(image_jouer));
        parametres.setBackground(new Background(image_jouer));
        commandes.setBackground(new Background(image_jouer));
         */

        // Evénements
        GestionEventLauncher gestionEventLauncher = new GestionEventLauncher(this);
        jouer.addEventHandler(MouseEvent.MOUSE_CLICKED, gestionEventLauncher);
        parametres.addEventHandler(MouseEvent.MOUSE_CLICKED, gestionEventLauncher);
        commandes.addEventHandler(MouseEvent.MOUSE_CLICKED, gestionEventLauncher);
    }
}
