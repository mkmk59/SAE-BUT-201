package com.example.java301fx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameLauncher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameLauncherStage gameLauncherStage = new GameLauncherStage(this);
        gameLauncherStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }

    public void closeLauncher(){

    }
}
