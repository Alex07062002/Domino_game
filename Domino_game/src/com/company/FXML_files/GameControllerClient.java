package com.company.FXML_files;

import com.company.model.*;
import com.company.OutputToPlayer.Fish;
import com.company.OutputToPlayer.OutBoardToPlayer;
import com.company.OutputToPlayer.PlayerDominoHave;
import com.company.OutputToPlayer.Winner;
import com.company.UI.GameOverUI;
import com.company.UI.GameUI;
import com.company.UI.PlayerUI;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class GameControllerClient implements GameUI, GameOverUI, PlayerUI{
    @FXML
    public HBox root;
    @FXML
    public Label size;
    @FXML
    public Label Name;
    @FXML
    public Button takeDomino;
    @FXML
    Button IsReverse;
    @FXML
    Button NextPlayer;
    @FXML
    GridPane Board;
    @FXML
    Button startGame;
    @FXML
    ScrollPane Comments;
    @FXML
    Text text;

    Alert alert;

    private Socket socket;

    public void doNextStep() {
        startGame.setDisable(true);
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String command;
            while (!socket.isClosed() && (command = in.readLine()) != null) {
                String[] parsed = command.split(Command.SEPARATOR);
                if (Command.StartPlayer.getCommandString().equals(parsed[0])) {
                    int start = Integer.parseInt(parsed[1]);
                    StartPlayer(start);
                }
                if (Command.Message.getCommandString().equals(parsed[0])) {
                    printMessagesFromGame(parsed[1]);
                }
                if (Command.ErrorMessage.getCommandString().equals(parsed[0])) {
                    printError(parsed[1]);
                }
                if (Command.PrintBoard.getCommandString().equals(parsed[0])) {
                    printBoard(new OutBoardToPlayer(dominoBoard(parsed[1])));
                }
                if (Command.PrintDomino.getCommandString().equals(parsed[0])) {
                    printPlayerDomino(new PlayerDominoHave("Player", dominoPlayer(parsed[1])));
                }
                if (Command.RespInt.getCommandString().equals(parsed[0])) {
                    String resp = Command.RespInt.getCommandString() + Command.SEPARATOR + firstAnswer();
                    out.println(resp);
                }
                if (Command.RespStr.getCommandString().equals(parsed[0])) {
                    String resp = Command.RespStr.getCommandString() + Command.SEPARATOR + AnswerFromPlayer();
                    out.println(resp);
                }
                if (Command.RespStrROrL.getCommandString().equals(parsed[0])) {
                    String resp = Command.RespStrROrL.getCommandString() + Command.SEPARATOR + WhereStay();
                    out.println(resp);
                }
                if (Command.RespIsReverse.getCommandString().equals(parsed[0])) {
                    String resp = Command.RespIsReverse.getCommandString() + Command.SEPARATOR + AnswerFromPlayerReverse();
                    out.println(resp);
                }
                if (Command.TotalHOD.getCommandString().equals(parsed[0])) {
                    int HOD = Integer.parseInt(parsed[1]);
                    totalNumberHOD(HOD);
                }
                if (Command.Fish.getCommandString().equals(parsed[0])) {
                    alert.setContentText(alert.getContentText()+"\n"+parsed[1]);
                }
                if (Command.Winner.getCommandString().equals(parsed[0])) {
                    alert.setContentText(alert.getContentText()+"\n"+parsed[1]);
                }
                if (Command.MessageGameOver.getCommandString().equals(parsed[0])){
                    printMessagesGameOver(parsed[1]);
                }
                if (Command.END.getCommandString().equals(parsed[0])) {
                    printGameOver();
                    out.close();
                    socket.close();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void start(){
        try{
            socket = new Socket("localhost",9999);
        System.out.println("Connect to server");

    } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Oops! I can't to connect to server!");
            alert.showAndWait();
    }
    }

    public Board dominoBoard(String str){
        Board domino = new Board();
        String line = str.substring(1, str.length() - 1);
        String[] acc = line.split(", |/");
        for (int i = 0; i< acc.length/2;i++){
            domino.addLastBoard(new DominoClass(Integer.parseInt(acc[2*i]),Integer.parseInt(acc[2*i+1])));
        }
        return domino;
    }

    public List<DominoClass> dominoPlayer(String str){
        List<DominoClass> domino = new ArrayList<>();
        String line = str.substring(1, str.length() - 1);
        String[] acc = line.split(", |/");
        for (int i = 0; i< acc.length/2;i++){
            domino.add(new DominoClass(Integer.parseInt(acc[2*i]),Integer.parseInt(acc[2*i+1])));
        }
        return domino;
    }

    public Group drawDomino(DominoClass domino){
        Group root = new Group();
        Rectangle rect = new Rectangle(50, 100);
        rect.setArcHeight(20);
        rect.setArcWidth(20);
        Line ln = new Line(0,50,50,50);
        ln.setFill(Color.BLACK);
        rect.setFill(Color.RED);
        root.getChildren().addAll(rect,ln);
        int OffsetY = (int) rect.getWidth();
        int dieSize = (int) rect.getWidth();
        int dotSize = dieSize / 12;
        int step = dieSize/4;
        int x2 = 2 * step;
        int x3 = 3 * step;
        int y2 = 2 * step;
        int y3 = 3 * step;
        switch (domino.getNumber1()) {
            case 0:
                break;
            case 1:
                Circle cr = new Circle(x2, y2, dotSize);
                root.getChildren().addAll(cr);
                break;
            case 2:
                cr = new Circle(step, y3, dotSize);
                Circle cr1 = new Circle(x3, step, dotSize);
                root.getChildren().addAll(cr, cr1);
                break;
            case 3:
                cr = new Circle(step, y3, dotSize);
                cr1 = new Circle(x3, step, dotSize);
                Circle cr2 = new Circle(x2, y2, dotSize);
                root.getChildren().addAll(cr, cr1, cr2);
                break;
            case 4:
                cr = new Circle(step, step, dotSize);
                cr1 = new Circle(step, y3, dotSize);
                cr2 = new Circle(x3, step, dotSize);
                Circle cr3 = new Circle(x3, y3, dotSize);
                root.getChildren().addAll(cr, cr1, cr2, cr3);
                break;
            case 5:
                cr = new Circle(step, step, dotSize);
                cr1 = new Circle(step, y3, dotSize);
                cr2 = new Circle(x3, step, dotSize);
                cr3 = new Circle(x3, y3, dotSize);
                Circle cr4 = new Circle(x2, y2, dotSize);
                root.getChildren().addAll(cr, cr1, cr2, cr3, cr4);
                break;
            case 6:
                cr = new Circle(step, step, dotSize);
                cr1 = new Circle(step, y3, dotSize);
                cr2 = new Circle(x3, step, dotSize);
                cr3 = new Circle(x3, y3, dotSize);
                cr4 = new Circle(step, y2, dotSize);
                Circle cr5 = new Circle(x3, y2, dotSize);
                root.getChildren().addAll(cr, cr1, cr2, cr3, cr4, cr5);
                break;
        }
        switch (domino.getNumber2()) {
            case 0:
                break;
            case 1:
                Circle cr = new Circle(x2, y2+OffsetY, dotSize);
                root.getChildren().addAll(cr);
                break;
            case 2:
                cr = new Circle(step, y3+OffsetY, dotSize);
                Circle cr1 = new Circle(x3, step +OffsetY, dotSize);
                root.getChildren().addAll(cr, cr1);
                break;
            case 3:
                cr = new Circle(step, y3+OffsetY, dotSize);
                cr1 = new Circle(x3, step +OffsetY, dotSize);
                Circle cr2 = new Circle(x2, y2+OffsetY, dotSize);
                root.getChildren().addAll(cr, cr1, cr2);
                break;
            case 4:
                cr = new Circle(step, step +OffsetY, dotSize);
                cr1 = new Circle(step, y3+OffsetY, dotSize);
                cr2 = new Circle(x3, step +OffsetY, dotSize);
                Circle cr3 = new Circle(x3, y3+OffsetY, dotSize);
                root.getChildren().addAll(cr, cr1, cr2, cr3);
                break;
            case 5:
                cr = new Circle(step, step +OffsetY, dotSize);
                cr1 = new Circle(step, y3+OffsetY, dotSize);
                cr2 = new Circle(x3, step +OffsetY, dotSize);
                cr3 = new Circle(x3, y3+OffsetY, dotSize);
                Circle cr4 = new Circle(x2, y2+OffsetY, dotSize);
                root.getChildren().addAll(cr, cr1, cr2, cr3, cr4);
                break;
            case 6:
                cr = new Circle(step, step +OffsetY, dotSize);
                cr1 = new Circle(step, y3+OffsetY, dotSize);
                cr2 = new Circle(x3, step +OffsetY, dotSize);
                cr3 = new Circle(x3, y3+OffsetY, dotSize);
                cr4 = new Circle(step, y2+OffsetY, dotSize);
                Circle cr5 = new Circle(x3, y2+OffsetY, dotSize);
                root.getChildren().addAll(cr, cr1, cr2, cr3, cr4, cr5);
                break;
        }
        return root;
    }


    @Override
    public void printPlayerDomino(PlayerDominoHave playerDominoHave) {
        root.getChildren().clear();

        for (DominoClass domino : playerDominoHave.getPlayerDomino()) {
            Node object = drawDomino(domino);
            root.getChildren().add(object);
        }
    }

    @Override
    public void printBoard(OutBoardToPlayer board) {
        Node object;
    if (Board.getChildren().isEmpty()){
        DominoClass domino = board.getBoard().getBoard().getFirst();
        object = drawDomino(domino);
        object.setRotate(object.getRotate()-90);
        Board.add(object,5,2);
    }else{
        int indexRow = 2;
        int indexColumns = 5;
        object = drawDomino(board.getBoard().getBoard().get(board.getBoard().getBoard().size()/2));
        object.setRotate(object.getRotate()-90);
        Board.add(object,indexColumns,indexRow);
        for (int i =board.getBoard().getBoard().size()/2-1;i>=0;i--){
            object = drawDomino(board.getBoard().getBoard().get(i));
            if (indexRow == 4){
                object.setRotate(object.getRotate()+90);
                Board.add(object,indexColumns,indexRow);
                indexColumns++;
            }
            if (indexRow == 2 && indexColumns==0){
                indexRow++;
                object.setRotate(object.getRotate()-180);
                Board.add(object,indexColumns,indexRow);
                GridPane.setHalignment(object, HPos.LEFT);
                GridPane.setValignment(object, VPos.TOP);

                indexRow++;
            }
            if (indexRow == 2 && indexColumns>0){
                object.setRotate(object.getRotate()-90);
                indexColumns--;
                Board.add(object,indexColumns,indexRow);
            }
        }
        indexRow = 2;
        indexColumns = 5;
        for (int i =board.getBoard().getBoard().size()/2+1;i<board.getBoard().getBoard().size();i++) {
            object = drawDomino(board.getBoard().getBoard().get(i));
            if (indexRow == 0) {
                object.setRotate(object.getRotate() + 90);
                Board.add(object, indexColumns, indexRow);
                indexColumns--;
            }
            if (indexRow == 2 && indexColumns == 10) {
                object.setRotate(object.getRotate()+180);
                indexRow--;
                Board.add(object, indexColumns, indexRow);
                GridPane.setHalignment(object, HPos.RIGHT);
                GridPane.setValignment(object, VPos.BOTTOM);
                indexRow--;
            }
            if (indexRow == 2 && indexColumns < 10) {
                object.setRotate(object.getRotate() - 90);
                indexColumns++;
                Board.add(object, indexColumns, indexRow);
            }
        }
    }
    }

   @Override
    public int firstAnswer() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true);
        dialog.setTitle("Answer");
        dialog.setHeaderText("Первый ход");
        dialog.setContentText("Выбери какую костяшку ты положешь...");
        Optional<String> result = dialog.showAndWait();
        int out;
       try {
           out = result.map(Integer::parseInt).orElse(9);
       }
       catch (NumberFormatException ex){
           out = 9;
       }
        return out;
    }

    @Override
    public void StartPlayer(int start) {
        text.setText("Начинает игрок "+start);
    }

    @Override
    public void printMessagesFromGame(String Message) {
        text.setText(text.getText()+"\n"+Message);
    }

    @Override
    public String AnswerFromPlayer() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Answer");
        dialog.setHeaderText("Выбери действие");
        dialog.setContentText("Какую костяшку положить на стол??? (число) или Взять из базара (eng(B)) или Пропустить ход(eng(N))???");
        Optional<String> result = dialog.showAndWait();
        return result.orElse("N");
    }

    @Override
    public String AnswerFromPlayerReverse() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Answer");
        dialog.setHeaderText("Выбери действие");
        dialog.setContentText("нужно ли переворачивать выбранную костяшку??? Y or N");
        Optional<String> result = dialog.showAndWait();
        return result.orElse("N");
    }

    @Override
    public String WhereStay() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Answer");
        dialog.setHeaderText("Выбери действие");
        dialog.setContentText("С какой стороны положить костяшку: слева(L) или справа(R)???");
        Optional<String> result = dialog.showAndWait();
        return result.orElse("Q");
    }
    @Override
    public void printError(String ErrorMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(ErrorMessage);
        alert.showAndWait();
    }

    @Override
    public void totalNumberHOD(int HOD) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Конец игры.\nОбщее число ходов : "+HOD);
    }

    @Override
    public void printFish(Fish fish) {
        alert.setContentText(alert.getContentText()+"\n"+fish.toString());
    }

    @Override
    public void printWinner(Winner winner) {
        alert.setContentText(alert.getContentText()+"\n"+winner.toString());
    }

    @Override
    public void printMessagesGameOver(String Message) {
        alert.setContentText(alert.getContentText()+"\n"+Message);
    }

    @Override
    public void printGameOver() {alert.setContentText(alert.getContentText()+"\nGame over!");
    alert.showAndWait();}

}
