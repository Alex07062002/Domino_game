package com.company.FXML_files;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;



public class Start {
    public AnchorPane pane;
    @FXML
    private Button Quit;
    @FXML
    private Button Help;
    @FXML
    private Button StartGame;


    public void QuitPushed(){
        Stage stage = (Stage) Quit.getScene().getWindow();
        stage.close();
    }

    public void HelpPushed() throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("../FXML_files/help.fxml"));
        Scene helpView = new Scene(root2);
        helpView.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        Stage help = (Stage) Help.getScene().getWindow();
        help.setScene(helpView);
        help.show();
    }

    public void StartGamePushed() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML_files/listPlayers.fxml"));
        Scene listPlayers = new Scene(root);
        listPlayers.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        Stage startList = (Stage) StartGame.getScene().getWindow();
        startList.setScene(listPlayers);
        startList.show();
    }
}



