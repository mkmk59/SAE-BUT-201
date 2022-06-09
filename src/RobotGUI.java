package com.example.java301fx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.File;

public class RobotGUI extends StackPane {
    private Robot robot;
    private ImageView skin;
    private Integer size;
    private boolean curseur;

    public RobotGUI(Robot robot) {
        this.size=60;
        this.robot = robot;
        this.robot.setRobotGUI(this);
        this.curseur=false;
        if (this.robot.getSpecialite()== Robot.Specialite.Or) {
            File file_robot_or = new File("src/image/robot_or.png");
            Image img_robot_or = new Image(file_robot_or.toURI().toString());
            this.skin = new ImageView(img_robot_or);
            this.skin.setFitHeight(this.size);
            this.skin.setPreserveRatio(true);
            this.getChildren().add(this.skin);
            this.setId(this.getRobot().getNom());
        }
        else if (this.robot.getSpecialite()== Robot.Specialite.Ni){
            File file_robot_ni = new File("src/image/robot_ni.png");
            Image img_robot_ni = new Image(file_robot_ni.toURI().toString());
            this.skin = new ImageView(img_robot_ni);
            this.skin.setFitHeight(this.size);
            this.skin.setPreserveRatio(true);
            this.getChildren().add(this.skin);
            this.setId(this.getRobot().getNom());
        }
        else {
            this.skin=null;
        }
    }

    public Robot getRobot() {
        return this.robot;
    }

    public ImageView getSkin() {
        return this.skin;
    }

    public void setSkin(ImageView skin) {
        this.getChildren().remove(this.skin);
        this.skin=skin;
        this.getChildren().add(skin);
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return this.size;
    }

    public boolean isCurseur() {
        return curseur;
    }

    public void setCurseur(boolean curseur) {
        this.curseur = curseur;
    }
}
