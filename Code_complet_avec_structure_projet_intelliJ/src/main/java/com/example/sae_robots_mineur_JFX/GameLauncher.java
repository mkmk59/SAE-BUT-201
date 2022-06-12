package com.example.sae_robots_mineur_JFX;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameLauncher extends Application {
    private GameLauncherStage gameLauncherStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
       this.gameLauncherStage = new GameLauncherStage();
       this.gameLauncherStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}
