package com.example.sae_robots_mineur_JFX;

import com.example.sae_robots_mineur.Robot;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.ArrayList;

public class GestionEventParcelle implements EventHandler {
    private ParcelleGUI parcelleGUI;

    public GestionEventParcelle(ParcelleGUI parcelleGUI) {
        this.parcelleGUI = parcelleGUI;
    }

    @Override
    public void handle(Event event) {
        if (this.parcelleGUI.getParcelle().isPresence_robot()) {
            if (this.parcelleGUI.getGame().getCurseur_robot()==null) {
                // On fixe le curseur_robot
                this.parcelleGUI.getGame().setCurseur_robot(this.parcelleGUI.getRobotGUI());
                this.parcelleGUI.getRobotGUI().setCurseur(true);
            }
            else {
                this.parcelleGUI.getGame().getCurseur_robot().setCurseur(false);
                this.parcelleGUI.getGame().setCurseur_robot(this.parcelleGUI.getRobotGUI());
                this.parcelleGUI.getRobotGUI().setCurseur(true);
            }

            // Liste de tous les robots de la partie
            ArrayList<Robot> liste_robots = this.parcelleGUI.getGame().getPartie().getRobots();
            // Balayer tous les robots et appliquer le skin du robot_curseur
            for (Robot robot : liste_robots){
                if (robot.getRobotGUI().isCurseur()) {
                    if (robot.getSpecialite() == Robot.Specialite.Or) {
                        File file_skin = new File("src/image/robot_or_avec_curseur.png");
                        Image img_robot = new Image(file_skin.toURI().toString());
                        ImageView skin_robot = new ImageView(img_robot);
                        robot.getParcelle().getParcelleGUI().getChildren().remove(robot.getRobotGUI().getSkin());
                        robot.getRobotGUI().setSkin(skin_robot);
                        robot.getRobotGUI().getSkin().setFitHeight(this.parcelleGUI.getRobotGUI().getSize());
                        robot.getRobotGUI().getSkin().setPreserveRatio(true);
                        robot.getRobotGUI().getSkin().setLayoutX(13);
                        robot.getRobotGUI().getSkin().setLayoutY(12);
                        robot.getParcelle().getParcelleGUI().getChildren().add(robot.getRobotGUI().getSkin());
                    } else {
                        File file_skin = new File("src/image/robot_ni_avec_curseur.png");
                        Image img_robot = new Image(file_skin.toURI().toString());
                        ImageView skin_robot = new ImageView(img_robot);
                        robot.getParcelle().getParcelleGUI().getChildren().remove(robot.getRobotGUI().getSkin());
                        robot.getRobotGUI().setSkin(skin_robot);
                        robot.getRobotGUI().getSkin().setFitHeight(this.parcelleGUI.getRobotGUI().getSize());
                        robot.getRobotGUI().getSkin().setPreserveRatio(true);
                        robot.getRobotGUI().getSkin().setLayoutX(13);
                        robot.getRobotGUI().getSkin().setLayoutY(12);
                        robot.getParcelle().getParcelleGUI().getChildren().add(robot.getRobotGUI().getSkin());
                    }
                }
                else{
                    if (robot.getSpecialite() == Robot.Specialite.Or) {
                        File file_skin = new File("src/image/robot_or.png");
                        Image img_robot = new Image(file_skin.toURI().toString());
                        ImageView skin_robot = new ImageView(img_robot);
                        robot.getParcelle().getParcelleGUI().getChildren().remove(robot.getRobotGUI().getSkin());
                        robot.getRobotGUI().setSkin(skin_robot);
                        robot.getRobotGUI().getSkin().setFitHeight(this.parcelleGUI.getRobotGUI().getSize());
                        robot.getRobotGUI().getSkin().setPreserveRatio(true);
                        robot.getRobotGUI().getSkin().setLayoutX(13);
                        robot.getRobotGUI().getSkin().setLayoutY(15);
                        robot.getParcelle().getParcelleGUI().getChildren().add(robot.getRobotGUI().getSkin());
                    } else {
                        File file_skin = new File("src/image/robot_ni.png");
                        Image img_robot = new Image(file_skin.toURI().toString());
                        ImageView skin_robot = new ImageView(img_robot);
                        robot.getParcelle().getParcelleGUI().getChildren().remove(robot.getRobotGUI().getSkin());
                        robot.getRobotGUI().setSkin(skin_robot);
                        robot.getRobotGUI().getSkin().setFitHeight(this.parcelleGUI.getRobotGUI().getSize());
                        robot.getRobotGUI().getSkin().setPreserveRatio(true);
                        robot.getRobotGUI().getSkin().setLayoutX(13);
                        robot.getRobotGUI().getSkin().setLayoutY(15);
                        robot.getParcelle().getParcelleGUI().getChildren().add(robot.getRobotGUI().getSkin());
                    }
                }
            }
        }
    }
}