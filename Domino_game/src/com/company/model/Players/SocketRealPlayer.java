package com.company.model.Players;

import com.company.model.Bazaar;
import com.company.model.Command;
import com.company.model.DominoClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketRealPlayer extends Player{
    private final PrintWriter out;
    private final BufferedReader in;

    public SocketRealPlayer(String name, int numberOfPlayers, Socket socket) {
        super(name, numberOfPlayers);
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot connect to client", ex);
        }
    }

    @Override
    public int firstAnswerOrNumberOfPlayers() {
        String answer;
        try {
            String command = Command.RespInt.getCommandString();
            System.out.println("To client: "+command);
            out.println(command);
            while ((answer = in.readLine()) == null) {
            }
            System.out.println("From client: "+answer);
            String[] answerParsed = answer.split(Command.SEPARATOR);
            if (Command.RespInt.getCommandString().equals(answerParsed[0])) {
                System.out.println("Первая костяшка: " + answerParsed[1]);
            } else {
                throw new IllegalArgumentException("Client response is not recognized: "+answer);
            }
            return Integer.parseInt(answerParsed[1])-1;
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot communicate with a client", ex);
        }
    }

    @Override
    public String AnswerFromPlayer(int start, int finish, boolean isEmpty) {
        String answer;
        try {
            String command = Command.RespStr.getCommandString();
            System.out.println("To client: "+command);
            out.println(command);
            while ((answer = in.readLine()) == null) {
            }
            System.out.println("From client: "+answer);
            String[] answerParsed = answer.split(Command.SEPARATOR);
            if (Command.RespStr.getCommandString().equals(answerParsed[0])) {
                System.out.println("Функция" + answerParsed[1]);
            } else {
                throw new IllegalArgumentException("Client response is not recognized: "+answer);
            }
            return answerParsed[1];
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot communicate with a client", ex);
        }
    }

    @Override
    public String AnswerFromPlayerRightOrLeft(int domino, int start, int finish) {
        String answer;
        try {
            String command = Command.RespStrROrL.getCommandString();
            System.out.println("To client: "+command);
            out.println(command);
            while ((answer = in.readLine()) == null) {
            }
            System.out.println("From client: "+answer);
            String[] answerParsed = answer.split(Command.SEPARATOR);
            if (Command.RespStrROrL.getCommandString().equals(answerParsed[0])) {
                System.out.println("С какой стороны положить" + answerParsed[1]);
            } else {
                throw new IllegalArgumentException("Client response is not recognized: "+answer);
            }
            return answerParsed[1];
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot communicate with a client", ex);
        }
    }

    @Override
    public String AnswerIsReverse(int domino, int start, int finish) {
        String answer;
        try {
            String command = Command.RespIsReverse.getCommandString();
            System.out.println("To client: "+command);
            out.println(command);
            while ((answer = in.readLine()) == null) {
            }
            System.out.println("From client: "+answer);
            String[] answerParsed = answer.split(Command.SEPARATOR);
            if (Command.RespIsReverse.getCommandString().equals(answerParsed[0])) {
                System.out.println("Нужно ли переворачивать" + answerParsed[1]);
            } else {
                throw new IllegalArgumentException("Client response is not recognized: "+answer);
            }
            return answerParsed[1];
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot communicate with a client", ex);
        }
    }


    @Override
    public void takeDomino(Bazaar bazaar) {
        getDominoHave().add(bazaar.firstDominoBazaar());
    }

    @Override
    public void removeDomino (int domino) {
        getDominoHave().remove(domino);
    }

    @Override
    public DominoClass takeDominoToBoard(int domino){
        return getDominoHave().get(domino);
    }

    @Override
    public boolean isEmpty(){
        return getDominoHave().isEmpty();
    }

    @Override
    public  int size(){ return getDominoHave().size(); }

    @Override
    public void printDomino() {
        String command = Command.PrintDomino.getCommandString()+Command.SEPARATOR+getDominoHave().toString();
        System.out.println("To client: "+getDominoHave().toString());
        out.println(command);
    }
}
