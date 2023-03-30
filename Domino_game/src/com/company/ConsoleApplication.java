package com.company;

import com.company.OutputToPlayer.OutBoardToPlayer;
import com.company.OutputToPlayer.Fish;
import com.company.OutputToPlayer.PlayerDominoHave;
import com.company.OutputToPlayer.Winner;
import com.company.UI.GameOverUI;
import com.company.UI.GameUI;
import com.company.UI.InputUI;
import com.company.UI.PlayerUI;
import com.company.model.ConvertToListPlayer;
import com.company.model.Game2;
import com.company.model.InputFromPlayers;
import com.company.model.Statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ConsoleApplication implements GameUI, InputUI, GameOverUI, PlayerUI {
    public static void main(String[] args) {
        InputFromPlayers inputFromPlayers = new InputFromPlayers(new ConsoleApplication());
        ConvertToListPlayer convert = new ConvertToListPlayer(inputFromPlayers.names,new ConsoleApplication());
        Game2 game = new Game2(convert.startList, new ConsoleApplication());
        while (!game.booleanIsOver()) {
            game.makeHOD();
        }
        Statistics stats = new Statistics(game, new ConsoleApplication());
        stats.statistics();
    }

    @Override
    public void printPlayerDomino(PlayerDominoHave playerDominoHave) {
        System.out.println(playerDominoHave.toString());
    }

    @Override
    public void printBoard(OutBoardToPlayer board) {
        System.out.println("------**Поле**-----\n"+board.toString()+"\n-----**----**-----");
    }

    @Override
    public int firstAnswer() {
        Scanner scan = new Scanner(System.in);
        try {
            return Integer.parseInt(scan.nextLine());
        }catch (NumberFormatException ex){
            return 9; // несуществующая доминошка при начале игры.
        }
    }

    @Override
    public void StartPlayer(int start) {
        System.out.println("Игру начинает игрок:" + (start + 1));
    }

    @Override
    public void printMessagesFromGame(String Message) {
        System.out.println(Message);
    }

    @Override
    public String AnswerFromPlayer() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    @Override
    public String AnswerFromPlayerReverse() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    @Override
    public String WhereStay() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }


    @Override
    public List<String> NamesPlayers(int player) {
        String name;
        List<String> listNames = new ArrayList<>();
        for (byte i = 1; i <= player; i++) {
            System.out.println("Введите имя " + i + " игрока");
            Scanner scan = new Scanner(System.in);
            name = scan.nextLine();
            listNames.add(name);
        }
        return listNames;
    }
    @Override
    public int NumberOfPlayers() {
        Scanner scan = new Scanner(System.in);
        try {
            return Integer.parseInt(scan.nextLine());
        }catch (NumberFormatException ex){
            return 5; // недопустимое число игороков, вернется назад
        }
    }
    @Override
    public void printMessages(String Message) {
        System.out.println(Message);
    }

    @Override
    public void printError(String ErrorMessage) {
        System.out.println(ErrorMessage+"!!!");
    }

    @Override
    public void totalNumberHOD(int HOD){
        System.out.println("Конец игры.\nОбщее число ходов : "+HOD);
    }
    @Override
    public void printFish(Fish fish){ System.out.println(fish.toString());
    }
    @Override
    public void printWinner(Winner winner){
        System.out.println(winner.toString());
    }

    @Override
    public void printMessagesGameOver(String Message) {
        System.out.println(Message);
    }

    @Override
    public void printGameOver() { System.out.println("Game over!");}
}

/**
 * Version 1.0 Without abstract class
 */
       /* Game game = new Game(new ConsoleApplication());
        while (!game.booleanIsOver()){
            game.makeHOD();
        }
        game.Statistics();*/