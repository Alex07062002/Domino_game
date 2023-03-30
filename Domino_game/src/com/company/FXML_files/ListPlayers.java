package com.company.FXML_files;

import com.company.UI.InputUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListPlayers implements InputUI {
    @FXML
    public AnchorPane listPlayers;
    @FXML
    Label first;
    @FXML
    CheckBox Player1Real;
    @FXML
    CheckBox Player1Computer;
    @FXML
    TextField namePlayer1;
    @FXML
    Label name1;
    @FXML
    Label second;
    @FXML
    CheckBox Player2Real;
    @FXML
    CheckBox Player2Computer;
    @FXML
    TextField namePlayer2;
    @FXML
    Label name2;
    @FXML
    Label third;
    @FXML
    CheckBox Player3Real;
    @FXML
    CheckBox Player3Computer;
    @FXML
    TextField namePlayer3;
    @FXML
    Label name3;
    @FXML
    Label fourth;
    @FXML
    CheckBox Player4Real;
    @FXML
    CheckBox Player4Computer;
    @FXML
    TextField namePlayer4;
    @FXML
    Label name4;
    @FXML
    Button returnButton;
    @FXML
    Button startGame;
    @FXML
    TextField numberOfPlayers;


    public void setReturnToMenu() throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("start.fxml"));
        root2.setId("pane");
        root2.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        Scene helpView = new Scene(root2,720,480);
        Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.setScene(helpView);
        stage.show();
    }
    public void Players(){
NumberOfPlayers();
    }

    @FXML
    public void setStartGameAction() throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML_files/game.fxml"));
        Parent gameUi = loader.load();
        GameController gameController = loader.getController();
        gameController.startsGame(NamesPlayers(Integer.parseInt(numberOfPlayers.getText())));
        Scene gameScene = new Scene(gameUi,1200,640);
        gameScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        Stage stage = (Stage) startGame.getScene().getWindow();
        stage.setScene(gameScene);
        stage.show();
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
    }

    public void update(CheckBox checkBox,Label label, TextField textField, boolean textFielsIsView, boolean setSelected){
        checkBox.setSelected(setSelected);
        label.setVisible(textFielsIsView);
        textField.setVisible(textFielsIsView);
    }

    public void updateType() {
        if (Player1Real.isSelected()) {
            update(Player1Computer,name1,namePlayer1,true,false);
        }
        if (Player1Computer.isSelected()) {
            update(Player1Real,name1,namePlayer1,false,false);
        }
    }
        public void updateType2(){
            if (Player1Computer.isSelected()) {
                update(Player1Real,name1,namePlayer1,false,false);
            }
            if (Player1Real.isSelected()){
                update(Player1Computer,name1,namePlayer1,true,false);
            }
    }
    public void updateType3() {
        if (Player2Real.isSelected()) {
            update(Player2Computer,name2,namePlayer2,true,false);
        }
        if (Player2Computer.isSelected()) {
            update(Player2Real,name2,namePlayer2,false,false);
        }
    }
    public void updateType4(){
        if (Player2Computer.isSelected()) {
            update(Player2Real,name2,namePlayer2,false,false);
        }
        if (Player2Real.isSelected()){
            update(Player2Computer,name2,namePlayer2,true,false);
        }
    }
    public void updateType5() {
        if (Player3Real.isSelected() && (Player2Real.isSelected() || Player2Computer.isSelected())) {
            update(Player3Computer,name3,namePlayer3,true,false);
        }
        if (Player3Computer.isSelected()) {
            update(Player3Real,name3,namePlayer3,false,false);
        }
    }
    public void updateType6(){
        if (Player3Computer.isSelected() && (Player2Real.isSelected() || Player2Computer.isSelected())) {
            update(Player3Real,name3,namePlayer3,false,false);
        }
        if (Player3Real.isSelected()){
            update(Player3Computer,name3,namePlayer3,true,false);
        }
    }
    public void updateType7() {
        if (Player4Real.isSelected() && (Player3Real.isSelected() || Player3Computer.isSelected())) {
            update(Player4Computer,name4,namePlayer4,true,false);
        }
        if (Player4Computer.isSelected() && (Player3Real.isSelected() || Player3Computer.isSelected())) {
            update(Player4Real,name4,namePlayer4,false,false);
        }
    }
    public void updateType8(){
        if (Player4Computer.isSelected()) {
            update(Player4Real,name4,namePlayer4,false,false);
        }
        if (Player4Real.isSelected()){
            update(Player4Computer,name4,namePlayer4,true,false);
        }
    }

    @Override
    public List<String> NamesPlayers(int NumberOfPlayers) {
        List<String>names = new ArrayList<>();
        int condition = 0;
        while (condition == 0) {
            if (Player1Real.isSelected() && namePlayer1.getText() != null) {
                names.add(namePlayer1.getText());
            } else if (Player1Computer.isSelected()) {
                names.add("computer");
            }
            if (Player2Real.isSelected() && namePlayer2.getText() != null) {
                names.add(namePlayer2.getText());
            } else if (Player2Computer.isSelected()) {
                names.add("computer");
            }
            if (Player3Real.isSelected() && namePlayer3.getText() != null) {
                names.add(namePlayer3.getText());
            } else if (Player3Computer.isSelected()) {
                names.add("computer");
            }
            if (Player4Real.isSelected() && namePlayer4.getText() != null) {
                names.add(namePlayer4.getText());
            } else if (Player4Computer.isSelected()) {
                names.add("computer");
            }
            if (names.size()<2){
                printError("Недопустимое количество игроков");
            }else {
                condition++;
            }
        }
        return names;
    }

    @Override
    public int NumberOfPlayers() {
        try {
            int number = Integer.parseInt(numberOfPlayers.getText());
            if (number < 2 || number > 4) {
                printError("Недопустимое количество игроков");
            } else {
                first.setVisible(true);
                Player1Real.setVisible(true);
                Player1Computer.setVisible(true);
                second.setVisible(true);
                Player2Real.setVisible(true);
                Player2Computer.setVisible(true);
                if (number == 3) {
                    third.setVisible(true);
                    Player3Real.setVisible(true);
                    Player3Computer.setVisible(true);
                }
                if (number == 4) {
                    third.setVisible(true);
                    Player3Real.setVisible(true);
                    Player3Computer.setVisible(true);
                    fourth.setVisible(true);
                    Player4Real.setVisible(true);
                    Player4Computer.setVisible(true);
                }
            }
            return number;
        }catch (NumberFormatException ex){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Неверный ввод!!!");
            alert.showAndWait();
            return  -1;
        }
    }

    @Override
    public void printMessages(String Message) {
    }

    @Override
    public void printError(String ErrorMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(ErrorMessage);
        alert.showAndWait();
    }

}
