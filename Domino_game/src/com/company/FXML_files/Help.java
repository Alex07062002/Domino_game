package com.company.FXML_files;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;


public class Help {
    @FXML
    public AnchorPane pane;
    @FXML
    public Text text;
    @FXML
    private Button returnToMenu;

    public void setReturnToMenu() throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("start.fxml"));
        root2.setId("pane");
        root2.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        Scene helpView = new Scene(root2,720,480);
        Stage stage = (Stage) returnToMenu.getScene().getWindow();
        stage.setScene(helpView);
        stage.show();
    }
    }
