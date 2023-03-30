package com.company;

import com.company.FXML_files.GameControllerClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class FxClient extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_files/gameClient.fxml"));
        Parent gameUi = loader.load();
        GameControllerClient gameController = loader.getController();
        gameController.start();
        primaryStage.setTitle("Domino game");
        primaryStage.setScene(new Scene(gameUi, 1200, 640));
        primaryStage.show();
    }
}

