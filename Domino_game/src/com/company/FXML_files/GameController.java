package com.company.FXML_files;

import com.company.UI.GameOverUI;
import com.company.OutputToPlayer.OutBoardToPlayer;
import com.company.OutputToPlayer.Fish;
import com.company.OutputToPlayer.PlayerDominoHave;
import com.company.OutputToPlayer.Winner;
import com.company.UI.GameUI;
import com.company.UI.PlayerUI;
import com.company.model.*;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameController implements GameUI, GameOverUI, PlayerUI {
    @FXML
    AnchorPane Anchor;
    @FXML
    HBox dominoPlayer;
    @FXML
    Label Name;
    @FXML
    Button takeDomino;
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

    private Game2 game;

    private Socket socket;

    public void startsGame(List<String> names){
        ConvertToListPlayer convert = new ConvertToListPlayer(names,this);
        game = new Game2(convert.startList,this);
    }

    public void doNextStep() {
        startGame.setDisable(true);
        while (!game.booleanIsOver()) {
            game.makeHOD();
        }
        if (game.booleanIsOver()) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            Statistics stats = new Statistics(game, this);
            stats.statistics();
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
            alert.setTitle("Game Over");
            alert.setHeaderText("Game Over");
            alert.showAndWait();
        }

    }

    @Override
    public void printPlayerDomino(PlayerDominoHave playerDominoHave) {
        dominoPlayer.getChildren().clear();
        Name.setText(game.getListPlayers().get(game.numberPlayerHOD).name);
        for (DominoClass domino : game.getListPlayers().get(game.numberPlayerHOD).getDominoHave()) {
            Node object = drawDomino(domino);
            dominoPlayer.getChildren().add(object);
        }
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
        text.setText("Начинает игрок "+(start+1));
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
    public void printGameOver() {alert.setContentText(alert.getContentText()+"\nGame over!"); }
}
/**
 * Попытка сделать ручное управление через MouseEvent
 */
   /*    double oldX;
         double oldY;
   private void updateGame() {
        for (Node domino : root.getChildren()) {
            domino.setOnDragDetected((MouseEvent event) -> {
                Dragboard db = domino.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(domino.getId());
                System.out.println(domino.getId());
                db.setContent(content);
                System.out.println(content.toString());
                event.consume();
            });
            Board.addEventHandler(DragEvent.DRAG_OVER, (DragEvent event) -> {
                if (event.getGestureSource() != Board
                        && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            });
            Board.addEventHandler(DragEvent.DRAG_DROPPED, (DragEvent event) -> {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.getString().equals(domino.getId())) {
                    Board.getChildren().add(domino);

                    success = true;
               }
                event.setDropCompleted(success);
                event.consume();
            });
        }
    }*/