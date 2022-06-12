package com.example.sae_robots_mineur_JFX;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;

public class Fenetre_Parametres extends Stage {
    private Integer nb_mines_Or;
    private Integer nb_mines_Ni;
    private Integer nb_robots_Or;
    private Integer nb_robots_Ni;

    public Fenetre_Parametres() {
        //Paramètres de jeu par défaut
        this.nb_mines_Or = 2;
        this.nb_mines_Ni = 2;
        this.nb_robots_Or = 2;
        this.nb_robots_Ni = 2;

        //Labels
        Label label_nb_mines_Or = new Label(this.nb_mines_Or.toString());
        Label label_nb_mines_Ni = new Label(this.nb_mines_Ni.toString());
        Label label_nb_robots_Or = new Label(this.nb_robots_Or.toString());
        Label label_nb_robots_Ni= new Label(this.nb_robots_Ni.toString());

        //Forme des boutton pour agmenter et diminuer la valeur de chaque paramètre
        // Boutton pour augmenter
        String path_up = "M 250 200 L 250 400 L 450 300 L 250 200 z";
        SVGPath up = new SVGPath();
        up.setContent(path_up);

        // Boutton pour diminuer
        String path_down = "M 450 150 L 250 300 L 450 450 L 450 150 z";
        SVGPath down = new SVGPath();
        down.setContent(path_down);

        Button down_button_nb_mines_or = new Button();
        down_button_nb_mines_or.setShape(down);
        Button up_button_nb_mines_or = new Button();
        up_button_nb_mines_or.setShape(up);
        Label labelmineOr = new Label("Nombre de mines d'Or");

        Button down_button_nb_mines_ni = new Button();
        down_button_nb_mines_ni.setShape(down);
        Button up_button_nb_mines_ni = new Button();
        up_button_nb_mines_ni.setShape(up);
        Label labelmineNi = new Label("Nombre de mines de Ni");

        Button down_button_nb_robots_Or = new Button();
        down_button_nb_robots_Or.setShape(down);
        Button up_button_nb_robots_Or = new Button();
        up_button_nb_robots_Or.setShape(up);
        Label labelRobotOr = new Label("Nombre de robots extracteur d'Or");

        Button down_button_nb_robots_Ni = new Button();
        down_button_nb_robots_Ni.setShape(down);
        Button up_button_nb_robots_Ni = new Button();
        up_button_nb_robots_Ni.setShape(up);
        Label labelRobotNi = new Label("Nombre de robots extracteur de Nickel");

        GridPane middle_grid = new GridPane();
        middle_grid.setPadding(new Insets(20));
        middle_grid.setAlignment(Pos.CENTER);
        middle_grid.setHgap(10);
        middle_grid.setVgap(20);
        middle_grid.getChildren().addAll( labelRobotOr,down_button_nb_robots_Or,label_nb_robots_Or,up_button_nb_robots_Or,
                                   labelRobotNi,down_button_nb_robots_Ni,label_nb_robots_Ni,up_button_nb_robots_Ni,
                                   labelmineOr,down_button_nb_mines_or,label_nb_mines_Or,up_button_nb_mines_or,
                                   labelmineNi,down_button_nb_mines_ni,label_nb_mines_Ni,up_button_nb_mines_ni);

        GridPane.setConstraints(labelRobotOr,0,0);
        GridPane.setConstraints(down_button_nb_robots_Or,1,0);
        GridPane.setConstraints(label_nb_robots_Or,2,0);
        GridPane.setConstraints(up_button_nb_robots_Or,3,0);

        GridPane.setConstraints(labelRobotNi,0,1);
        GridPane.setConstraints(down_button_nb_robots_Ni,1,1);
        GridPane.setConstraints(label_nb_robots_Ni,2,1);
        GridPane.setConstraints(up_button_nb_robots_Ni,3,1);

        GridPane.setConstraints(labelmineOr,0,2);
        GridPane.setConstraints(down_button_nb_mines_or,1,2);
        GridPane.setConstraints(label_nb_mines_Or,2,2);
        GridPane.setConstraints(up_button_nb_mines_or,3,2);

        GridPane.setConstraints(labelmineNi,0,3);
        GridPane.setConstraints(down_button_nb_mines_ni,1,3);
        GridPane.setConstraints(label_nb_mines_Ni,2,3);
        GridPane.setConstraints(up_button_nb_mines_ni,3,3);

        Label choix_params = new Label("Choix des Paramètres de la partie");
        choix_params.setFont(Font.font("Century Gothic", FontWeight.BOLD, 20));

        Button appliquer = new Button("Appliquer");
        appliquer.setMaxWidth(200);
        appliquer.setFont(Font.font("Century Gothic", FontWeight.BOLD,15));
        appliquer.setAlignment(Pos.CENTER);

        VBox root = new VBox();
        root.getChildren().addAll(choix_params,middle_grid,appliquer);
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 400, 300);
        this.setScene(scene);
        this.setTitle("Paramètres");
        this.centerOnScreen();
        this.setResizable(false);

        /*
        // Background bouton
        File file_fond_bouton = new File("src/image/fond_bouton.jpg");
        Image img_fond_bouton = new Image(file_fond_bouton.toURI().toString());
        BackgroundImage image_jouer = new BackgroundImage(img_fond_bouton,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        //set du background a chaque boutons
        appliquer.setBackground(new Background(image_jouer));
        */



        // Gestion des Evénement
        // Nb de robot extracteur d'or
        up_button_nb_robots_Or.setOnMouseClicked(e-> {
                if (this.nb_robots_Or < 2) {
                    this.nb_robots_Or++;
                    label_nb_robots_Or.setText(this.nb_robots_Or.toString());
                } else {
                    Alert alert_nb_robots_or_trop_grand = new Alert(Alert.AlertType.WARNING);
                    alert_nb_robots_or_trop_grand.setTitle("Limite max de robot atteinte");
                    alert_nb_robots_or_trop_grand.setContentText("Vous ne pouvez pas jouer avec plus de 2 robots d'un même type !");
                    alert_nb_robots_or_trop_grand.show();
                }
        });
        down_button_nb_robots_Or.setOnMouseClicked(e -> {
                if (this.nb_robots_Or > 0) {
                    this.nb_robots_Or--;
                    label_nb_robots_Or.setText(this.nb_robots_Or.toString());
                } else {
                    Alert alert_nb_robots_or_negatif = new Alert(Alert.AlertType.WARNING);
                    alert_nb_robots_or_negatif.setContentText("Le nombre de robots ne peut pas être négatif !");
                    alert_nb_robots_or_negatif.setTitle("Nombre de robots négatif");
                    alert_nb_robots_or_negatif.show();
                }
        });

        // Nb de robot extracteur de Ni
        up_button_nb_robots_Ni.setOnMouseClicked(e-> {
                if (this.nb_robots_Ni < 2) {
                    this.nb_robots_Ni++;
                    label_nb_robots_Ni.setText(this.nb_robots_Ni.toString());
                } else {
                    Alert alert_nb_robots_ni_trop_grand = new Alert(Alert.AlertType.WARNING);
                    alert_nb_robots_ni_trop_grand.setTitle("Limite max de robot atteinte");
                    alert_nb_robots_ni_trop_grand.setContentText("Vous ne pouvez pas jouer avec plus de 2 robots d'un même type !");
                    alert_nb_robots_ni_trop_grand.show();
                }
        });
        down_button_nb_robots_Ni.setOnMouseClicked(e -> {
                if (this.nb_robots_Ni > 0) {
                    this.nb_robots_Ni--;
                    label_nb_robots_Ni.setText(this.nb_robots_Ni.toString());
                } else {
                    Alert alert_nb_robots_ni_negatif = new Alert(Alert.AlertType.WARNING);
                    alert_nb_robots_ni_negatif.setContentText("Le nombre de robots ne peut pas être négatif !");
                    alert_nb_robots_ni_negatif.setTitle("Nombre de robots négatif");
                    alert_nb_robots_ni_negatif.show();
                }
        });

        // Nb de mine d'Or
        up_button_nb_mines_or.setOnMouseClicked(e-> {
            if (this.nb_mines_Or < 5) {
                this.nb_mines_Or++;
                label_nb_mines_Or.setText(this.nb_mines_Or.toString());
            }
            else{
                Alert alert_nb_mines_or_trop_grand = new Alert(Alert.AlertType.WARNING);
                alert_nb_mines_or_trop_grand.setTitle("Limite max de mines d'or atteinte");
                alert_nb_mines_or_trop_grand.setContentText("Vous ne pouvez pas placer plus de 5 mines d'un même type !");
                alert_nb_mines_or_trop_grand.show();
            }
        });
        down_button_nb_mines_or.setOnMouseClicked(e -> {
            if (this.nb_mines_Or > 0){
                this.nb_mines_Or--;
                label_nb_mines_Or.setText(this.nb_mines_Or.toString());
            }
            else{
                Alert alert_nb_mines_or_negatif = new Alert(Alert.AlertType.WARNING);
                alert_nb_mines_or_negatif.setContentText("Le nombre de mines ne peut pas être négatif !");
                alert_nb_mines_or_negatif.setTitle("Nombre de mines négatif");
                alert_nb_mines_or_negatif.show();
            }
        });

        // Nb de mine de Nickel
        up_button_nb_mines_ni.setOnMouseClicked(e-> {
            if (this.nb_mines_Ni < 5) {
                this.nb_mines_Ni++;
                label_nb_mines_Ni.setText(this.nb_mines_Ni.toString());
            }
            else{
                Alert alert_nb_mines_ni_trop_grand = new Alert(Alert.AlertType.WARNING);
                alert_nb_mines_ni_trop_grand.setTitle("Limite max de mines d'or atteinte");
                alert_nb_mines_ni_trop_grand.setContentText("Vous ne pouvez pas placer plus de 5 mines d'un même type !");
                alert_nb_mines_ni_trop_grand.show();
            }
        });
        down_button_nb_mines_ni.setOnMouseClicked(e -> {
            if (this.nb_mines_Ni > 0){
                this.nb_mines_Ni--;
                label_nb_mines_Ni.setText(this.nb_mines_Ni.toString());
            }
            else{
                Alert alert_nb_mines_ni_negatif = new Alert(Alert.AlertType.WARNING);
                alert_nb_mines_ni_negatif.setContentText("Le nombre de mines ne peut pas être négatif !");
                alert_nb_mines_ni_negatif.setTitle("Nombre de mines négatif");
                alert_nb_mines_ni_negatif.show();
            }
        });

        appliquer.setOnAction(e->{
            this.close();
        });
    }

    public Integer getNb_mines_Or() {
        return nb_mines_Or;
    }

    public Integer getNb_mines_Ni() {
        return nb_mines_Ni;
    }

    public Integer getNb_robots_Or() {
        return nb_robots_Or;
    }

    public Integer getNb_robots_Ni() {
        return nb_robots_Ni;
    }
}
