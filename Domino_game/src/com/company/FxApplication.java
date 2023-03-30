package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class FxApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_files/start.fxml"));
        primaryStage.setTitle("Domino game");
        primaryStage.setScene(new Scene(root, 720, 480));
        root.getStylesheets().addAll(this.getClass().getResource("FXML_files/style.css").toExternalForm());
        primaryStage.show();
    }
}

