package com.company.server;

import com.company.OutputToPlayer.OutBoardToPlayer;
import com.company.OutputToPlayer.Fish;
import com.company.OutputToPlayer.PlayerDominoHave;
import com.company.OutputToPlayer.Winner;
import com.company.UI.GameOverUI;
import com.company.UI.GameUI;
import com.company.model.Command;

import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler implements GameUI, GameOverUI {

    private PrintWriter out;

    public ConnectionHandler(Socket socket) {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void printBoard(OutBoardToPlayer board) {
        String command = Command.PrintBoard.getCommandString() + Command.SEPARATOR + board;
        System.out.println("To client: " + command);
        out.println(command);
    }

    @Override
    public void StartPlayer(int start) {
        String command = Command.StartPlayer.getCommandString() + Command.SEPARATOR + (start + 1);
        System.out.println("To client: " + command);
        out.println(command);
    }


    @Override
    public void printMessagesFromGame(String Message) {
        String command = Command.Message.getCommandString() + Command.SEPARATOR + Message;
        System.out.println("To client: " + command);
        out.println(command);
    }

    @Override
    public void printError(String ErrorMessage) {
        String command = Command.ErrorMessage.getCommandString() + Command.SEPARATOR + ErrorMessage + "!!!";
        System.out.println("To client: " + command);
        out.println(command);
    }

    @Override
    public void printPlayerDomino(PlayerDominoHave playerDominoHave) {
        String command = Command.FinalPrintDomino.getCommandString()+Command.SEPARATOR+playerDominoHave.toString();
        System.out.println("To client: " + command);
        out.println(command);
    }

    @Override
    public void totalNumberHOD(int HOD) {
        String command = Command.TotalHOD.getCommandString() + Command.SEPARATOR+HOD;
        System.out.println("To client: " + command);
        out.println(command);
    }

    @Override
    public void printFish(Fish fish) {
        String command = Command.Fish.getCommandString()+Command.SEPARATOR+fish.toString();
        System.out.println("To client: " + command);
        out.println(command);
    }

    @Override
    public void printWinner(Winner winner) {
        String command = Command.Winner.getCommandString()+Command.SEPARATOR+winner.toString();
        System.out.println("To client: " + command);
        out.println(command);
    }

    @Override
    public void printMessagesGameOver(String Message) {
        String command = Command.MessageGameOver.getCommandString() + Command.SEPARATOR + Message;
        System.out.println("To client: " + command);
        out.println(command);
    }

    @Override
    public void printGameOver() {
        String command = Command.END.getCommandString() + Command.SEPARATOR + "Game over!";
        System.out.println("To client: " + command);
        out.println(command);
        out.close();
    }
}
