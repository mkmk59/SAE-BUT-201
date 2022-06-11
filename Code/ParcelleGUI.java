package com.example.sae_robots_mineur;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.net.Inet4Address;

public class ParcelleGUI extends Pane {
    private Parcelle parcelle;
    private Label labelLieu;
    private Game game;
    private Integer size;
    private RobotGUI robotGUI;

    private GestionEventParcelle gestionEventParcelle;

    public ParcelleGUI(Parcelle parcelle) {
        this.parcelle = parcelle;
        this.parcelle.setParcelleGUI(this);
        this.game=null;
        this.size=60;

        //Fixe la taille du background de chaque parcelle
        this.gestionEventParcelle= new GestionEventParcelle(this);
        BackgroundSize backgroundSize = new BackgroundSize(700,
                500,
                true,
                true,
                true,
                false);
        // Mine
        if (this.parcelle.getLieu().getType_Lieu()==Lieu.Type_Lieu.MINE){
            Mine mine = (Mine) this.parcelle.getLieu();
            this.labelLieu=new Label(mine.getNom());
            this.getChildren().add(this.labelLieu);
            this.labelLieu.setFont(Font.font("Century Gothic", FontWeight.BOLD,13));
            this.labelLieu.setLayoutX(this.size/3 + 1);
            this.labelLieu.setLayoutY(this.size/6 + 2);
            if (mine.getSpecialite()== Robot.Specialite.Or) {
                File file_mine_or = new File("src/image/mine_or.png");
                Image mine_or_img = new Image(file_mine_or.toURI().toString());
                BackgroundImage image = new BackgroundImage(mine_or_img,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        backgroundSize);
                this.setBackground(new Background(image));
            }
            else if (mine.getSpecialite()== Robot.Specialite.Ni){
                File file_mine_ni = new File("src/image/mine_ni.png");
                Image mine_ni_img = new Image(file_mine_ni.toURI().toString());
                BackgroundImage image = new BackgroundImage(mine_ni_img,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        backgroundSize);
                this.setBackground(new Background(image));
            }
        }

        // Entrepot
        else if (this.parcelle.getLieu().getType_Lieu()==Lieu.Type_Lieu.ENTREPOT){
            Entrepot entrepot = (Entrepot) this.parcelle.getLieu();
            this.labelLieu=new Label(entrepot.getNom());
            this.getChildren().add(this.labelLieu);
            this.labelLieu.setFont(Font.font("Century Gothic", FontWeight.BOLD,13));
            this.labelLieu.setLayoutX(this.size/3 + 1);
            this.labelLieu.setLayoutY(this.size/6);
            if (entrepot.getSpecialite()== Robot.Specialite.Or) {
                File file_entrepot = new File("src/image/entrepot_or.png");
                Image entrepot_or_img = new Image(file_entrepot.toURI().toString());
                BackgroundImage image = new BackgroundImage(entrepot_or_img,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        backgroundSize);
                this.setBackground(new Background(image));
            }
            else if (entrepot.getSpecialite()== Robot.Specialite.Ni){
                File file_entrepot = new File("src/image/entrepot_ni.png");
                Image entrepot_ni_img = new Image(file_entrepot.toURI().toString());
                BackgroundImage image = new BackgroundImage(entrepot_ni_img,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        backgroundSize);
                this.setBackground(new Background(image));
            }
        }

        // Plan d'eau
        else if (this.parcelle.getLieu().getType_Lieu()==Lieu.Type_Lieu.PLAN_D_EAU){
            File file_plan_d_eau = new File("src/image/plan_d_eau_1.png");
            Image plan_d_eau_img = new Image(file_plan_d_eau.toURI().toString());
            BackgroundImage image = new BackgroundImage(plan_d_eau_img,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    backgroundSize);
            this.setBackground(new Background(image));
        }

        //Terrain vide
        else if(this.parcelle.getLieu().getType_Lieu()==Lieu.Type_Lieu.TERRAIN_VIDE){
            File file_terrain_vide = new File("src/image/terrain_vide.png");
            Image plan_terrain_vide_img = new Image(file_terrain_vide.toURI().toString());
            BackgroundImage image = new BackgroundImage(plan_terrain_vide_img,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    backgroundSize);
            this.setBackground(new Background(image));
        }

        // Robots
        if (this.parcelle.isPresence_robot()){
            this.robotGUI= this.parcelle.getRobot().getRobotGUI();
            if (this.parcelle.getRobot().getSpecialite()== Robot.Specialite.Or){
                ImageView skin_robot_or =this.robotGUI.getSkin();
                this.getChildren().add(skin_robot_or);
                skin_robot_or.setLayoutX(13);
                skin_robot_or.setLayoutY(15);
            }
            else if (this.parcelle.getRobot().getSpecialite()== Robot.Specialite.Ni){
                ImageView skin_robot_or =this.robotGUI.getSkin();
                this.getChildren().add(skin_robot_or);
                skin_robot_or.setLayoutX(13);
                skin_robot_or.setLayoutY(15);
            }
        }
        else{
            this.robotGUI=null;
        }
        this.setPrefSize(this.size,this.size);

        this.setOnMouseClicked(this.gestionEventParcelle);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Parcelle getParcelle() {
        return this.parcelle;
    }

    public Label getLabelLieu() {
        return this.labelLieu;
    }

    public Game getGame() {
        return this.game;
    }

    public GestionEventParcelle getGestionEventParcelle() {
        return this.gestionEventParcelle;
    }

    public RobotGUI getRobotGUI() {
        return this.robotGUI;
    }

    public void setRobotGUI(RobotGUI robotGUI) {
        this.robotGUI = robotGUI;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
