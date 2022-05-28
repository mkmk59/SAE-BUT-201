package com.example.java301fx;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.File;


public class Game extends Stage {
    private GestionEventGame gestionEventPartie;
    private GestionEventLauncher gestionEventLauncher;
    private Partie partie;
    private ParcelleGUI[][] parcelleGUIS;
    private Group info;
    private RobotGUI curseur_robot;

    public Game(GestionEventGame gestionEventPartie, Partie partie) {
        this.gestionEventPartie = gestionEventPartie;
        this.partie = partie;
        this.curseur_robot=null;
        this.gestionEventLauncher=null;

        VBox root = new VBox();

        GridPane carte = new GridPane();
        Group grid_carte = new Group();
        carte.setVgap(2);
        carte.setHgap(2);
        this.parcelleGUIS = new ParcelleGUI[10][10];
        for (Integer c=0;c<10;c++){
            Button colonne = new Button(c.toString());
            colonne.setPrefWidth(60);
            carte.add(colonne,c+1,0);
        }
        for (Integer l=0;l<10;l++){
            Button ligne = new Button(l.toString());
            ligne.setPrefHeight(60);
            carte.add(ligne,0,l+1);
        }
        for (int x=0;x<this.partie.getCarte().getMonde().length;x++){
            for (int y=0;y<this.partie.getCarte().getMonde().length;y++){
               this.parcelleGUIS[x][y] = new ParcelleGUI(this.partie.getCarte().getParcelle(x,y));
               this.parcelleGUIS[x][y].setGame(this);
               this.parcelleGUIS[x][y].setId("parcelle("+x+":"+y+")");
               carte.add(this.parcelleGUIS[x][y],y+1,x+1);
            }
        }
        grid_carte.getChildren().add(carte);

        //Informations de jeu

        HBox mid = new HBox();
        this.info = new Group();
        this.info.getChildren().add(this.creerVboxInformationsJeu());
        mid.getChildren().addAll(grid_carte,this.info);
        mid.setAlignment(Pos.CENTER);
        mid.setSpacing(40);


        // Bouttons de déplacement avec souris

        String path_f_haut = "M 900 400 L 700 600 L 850 600 L 850 750 L 950 750 L 950 600 L 1100 600 Z";
        SVGPath f_haut = new SVGPath();
        f_haut.setContent(path_f_haut);
        Button b_haut = new Button();
        b_haut.setMaxSize(50,50);
        b_haut.setMinSize(50,50);
        b_haut.setShape(f_haut);
        b_haut.setId("b_haut");

        String path_f_bas = "M 350 300 L 150 300 L 450 550 L 750 300 L 550 300 L 550 100 L 350 100 L 350 300 z";
        SVGPath f_bas = new SVGPath();
        f_bas.setContent(path_f_bas);
        Button b_bas = new Button();
        b_bas.setMaxSize(50,50);
        b_bas.setMinSize(50,50);
        b_bas.setShape(f_bas);
        b_bas.setId("b_bas");

        String path_f_droite = "M 450 500 L 450 700 L 700 400 L 450 100 L 450 300 L 150 300 L 150 500 L 450 500 Z";
        SVGPath f_droite = new SVGPath();
        f_droite.setContent(path_f_droite);
        Button b_droite = new Button();
        b_droite.setMaxSize(50,50);
        b_droite.setMinSize(50,50);
        b_droite.setShape(f_droite);
        b_droite.setId("b_droite");

        String path_f_gauche = "M 750 500 L 750 700 L 450 400 L 750 100 L 750 300 L 1050 300 L 1050 500 L 750 500 Z";
        SVGPath f_gauche = new SVGPath();
        f_gauche.setContent(path_f_gauche);
        Button b_gauche = new Button();
        b_gauche.setMaxSize(50,50);
        b_gauche.setMinSize(50,50);
        b_gauche.setShape(f_gauche);
        b_gauche.setId("b_gauche");

        //Boutton piocher
        File file = new File("src/image/piocher.png");
        Image piocher_img = new Image(file.toURI().toString());
        ImageView image_piocher = new ImageView(piocher_img);
        image_piocher.setFitHeight(50);
        image_piocher.setPreserveRatio(true);
        Button b_piocher = new Button();
        b_piocher.setPrefSize(50, 50);
        b_piocher.setGraphic(image_piocher);
        b_piocher.setId("b_piocher");

        //Boutton deposer
        HBox bouttons = new HBox();

        //Bouton commandes
        Button info_commandes = new Button("Commandes");
        info_commandes.setPrefSize(100, 30);


        File file_deposer = new File("src/image/deposer.png");
        Image deposer_img = new Image(file_deposer.toURI().toString());
        ImageView image_deposer = new ImageView(deposer_img);
        image_deposer.setFitHeight(50);
        image_deposer.setPreserveRatio(true);
        Button b_deposer = new Button();
        b_deposer.setPrefSize(50, 50);
        b_deposer.setGraphic(image_deposer);
        b_deposer.setId("b_deposer");

        File file_passer_tour = new File("src/image/passer_tour_robot.png");
        Image passer_tour_img = new Image(file_passer_tour.toURI().toString());
        ImageView image_passer_tour = new ImageView(passer_tour_img);
        image_passer_tour.setFitHeight(50);
        image_passer_tour.setPreserveRatio(true);
        Button b_passer = new Button();
        b_passer.setPrefSize(50, 50);
        b_passer.setGraphic(image_passer_tour);
        b_passer.setId("b_passer");


        Group bouttons_jeu = new Group();
        HBox commands = new HBox();
        commands.getChildren().addAll(b_gauche,b_droite,b_haut,b_bas,b_piocher,b_deposer,b_passer);
        commands.setAlignment(Pos.CENTER);
        commands.setSpacing(10);
        bouttons_jeu.getChildren().addAll(commands);

        bouttons.getChildren().addAll(bouttons_jeu,info_commandes);
        bouttons.setAlignment(Pos.CENTER);
        bouttons.setSpacing(250);

        File file_mine_or = new File("src/image/image_launcher_robot_mineur_flou.png");
        Image img_background = new Image(file_mine_or.toURI().toString());
        BackgroundSize backgroundSize = new BackgroundSize(1000,
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
        root.getChildren().addAll(mid,bouttons);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        root.setSpacing(50);
        Scene scene = new Scene(root,1000,800);
        this.setScene(scene);
        this.setTitle("Robot Mineur");
        this.centerOnScreen();
        this.setResizable(false);

        //Evénements
        b_haut.addEventHandler(MouseEvent.MOUSE_CLICKED,new GestionEventGame(this));
        b_bas.addEventHandler(MouseEvent.MOUSE_CLICKED,new GestionEventGame(this));
        b_droite.addEventHandler(MouseEvent.MOUSE_CLICKED,new GestionEventGame(this));
        b_gauche.addEventHandler(MouseEvent.MOUSE_CLICKED,new GestionEventGame(this));
        b_deposer.addEventHandler(MouseEvent.MOUSE_CLICKED,new GestionEventGame(this));
        b_piocher.addEventHandler(MouseEvent.MOUSE_CLICKED,new GestionEventGame(this));
        b_passer.addEventHandler(MouseEvent.MOUSE_CLICKED,new GestionEventGame(this));
        info_commandes.addEventHandler(MouseEvent.MOUSE_CLICKED,new GestionEventGame(this));
        this.addEventHandler(KeyEvent.KEY_PRESSED,new GestionEventGame(this));
    }

    public GridPane informations_jeu() {
        GridPane informations = new GridPane();
        int lig = 0;
        for (Mine m : this.partie.getMines()) {
            informations.add(new Label(m.getNom()), 0, lig);
            informations.add(new Label("(" + m.getParcelle().getCoordonnees()[0] + " : " + m.getParcelle().getCoordonnees()[1] + ")"), 1, lig);
            informations.add(new Label(m.getSpecialite().toString()), 2, lig);
            informations.add(new Label(m.getQuantite_minerai_restant()
                    + "/"
                    + m.getQuantite_minerai_max()), 3, lig);
            lig++;
        }
        lig = lig + this.partie.getMines().size();
        for (Entrepot e : this.partie.getEntrepots()) {
            informations.add(new Label(e.getNom()), 0, lig);
            informations.add(new Label("(" + e.getParcelle().getCoordonnees()[0] + " : " + e.getParcelle().getCoordonnees()[1]+ ")"), 1, lig);
            informations.add(new Label(e.getSpecialite().toString()), 2, lig);
            informations.add(new Label(e.getQuantite_minerai_actuelle()
                    + "/"
                    + e.getCapacite_stockage()), 3, lig);
            lig++;
        }
        lig = lig + this.partie.getEntrepots().size();
        for (Robot r : this.partie.getRobots()) {
            informations.add(new Label(r.getNom()), 0, lig);
            informations.add(new Label("(" + r.getParcelle().getCoordonnees()[0] + " : " + r.getParcelle().getCoordonnees()[1]+ ")"), 1, lig);
            informations.add(new Label(r.getSpecialite().toString()), 2, lig);
            informations.add(new Label(r.getQuantite_minerai()
                    + "/"
                    + r.getCapacite_minerai_max()), 3, lig);
            informations.add(new Label(r.getNombre_action() + "/1"),4,lig);
            lig++;
        }
        informations.setHgap(7);
        informations.setVgap(2);
        informations.setPadding(new Insets(20));
        return informations;
    }

    private void changeFontLabelInsideGrid(GridPane grid, String fontname, Integer fontsize,String couleur){
        for (Node n : grid.getChildren()){
            if (n instanceof Label) {
                    ((Label) n).setFont(Font.font(fontname, FontWeight.BOLD, fontsize));
                    ((Label) n).setTextFill(Color.valueOf(couleur));
                }
            }
    }

    public GestionEventGame getGestionEventPartie() {
        return this.gestionEventPartie;
    }

    public Partie getPartie() {
        return this.partie;
    }

    public ParcelleGUI[][] getParcelleGUIS() {
        return this.parcelleGUIS;
    }

    public RobotGUI getCurseur_robot() {
        return curseur_robot;
    }

    public void setCurseur_robot(RobotGUI curseur_robot) {
        this.curseur_robot = curseur_robot;
    }

    public ParcelleGUI getParcelleGUI(int ligne,int colonne){
        return this.parcelleGUIS[ligne][colonne];
    }

    public ParcelleGUI[][] getAllParcelleGUI(){
        return this.parcelleGUIS;
    }

    public Group getInfo_jeu() {
        return this.info;
    }

    public void setInfo_jeu(VBox info_jeu) {
        if(this.info.getChildren().isEmpty())
       this.info.getChildren().add(info_jeu);
        else{
            Alert pb_informations = new Alert(Alert.AlertType.WARNING);
            pb_informations.setTitle("Erreur mise à jour des informations");
            pb_informations.setContentText("Le conteneur n'as pas été vidé avant la réactualisation des informations");
            pb_informations.show();
        }
    }

    public void setGestionEventLauncher(GestionEventLauncher gestionEventLauncher) {
        this.gestionEventLauncher = gestionEventLauncher;
    }

    public GestionEventLauncher getGestionEventLauncher() {
        return gestionEventLauncher;
    }


    public VBox creerVboxInformationsJeu(){
        Label nb_tour = new Label("Tour : " + this.partie.getTour());
        nb_tour.setFont(Font.font("Century Gothic",FontWeight.BOLD,20));
        nb_tour.setTextFill(Color.valueOf("#FFFFFF"));
        VBox vb_info = new VBox();
        Pane paneInfoJeu = new Pane();
        GridPane informations_jeu = this.informations_jeu();
        paneInfoJeu.setMinSize(250, 90);
        paneInfoJeu.setMaxSize(250, 480);
        changeFontLabelInsideGrid(informations_jeu,"Century Gothic",18,"#FFFFFF");
        paneInfoJeu.getChildren().add(informations_jeu);
        paneInfoJeu.setStyle("-fx-border-color: white; -fx-border-width: 10");
        vb_info.getChildren().addAll(nb_tour,paneInfoJeu);
        vb_info.setSpacing(10);
        vb_info.setAlignment(Pos.CENTER);
        return vb_info;
    }
}
