package com.company.model;

import com.company.OutputToPlayer.OutBoardToPlayer;
import com.company.model.Players.RealPlayer;
import com.company.UI.GameUI;
import com.company.model.Players.Player;
import com.company.model.Players.SocketRealPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Game2 {
    public final int NUMBER_DOMINOES_TWO_PLAYERS = 7;
    public final int NUMBER_DOMINOES_MORE_THAN_TWO_PLAYERS = 5;
    public final List<DominoClass> start = new ArrayList<>(Arrays.asList(new DominoClass(1, 1), new DominoClass(2, 2),
            new DominoClass(3, 3), new DominoClass(4, 4), new DominoClass(5, 5), new DominoClass(6, 6),
            new DominoClass(1, 0), new DominoClass(2, 0), new DominoClass(2, 1), new DominoClass(3, 0),
            new DominoClass(3, 1), new DominoClass(4, 0), new DominoClass(3, 2), new DominoClass(4, 1), new DominoClass(5, 0)));

    public List<Player> getPlayers() {
        return players;
    }

    private final List<Player> players;

    public List<Player> getListPlayers() {
        return listPlayers;
    }

    private List<Player> listPlayers;

    public int getEmptyHOD() {
        return emptyHOD;
    }

    private int emptyHOD;

    public int getHOD() {
        return HOD;
    }

    private int  HOD;
    public Board boards;
    public int numberPlayerHOD;
    private final Bazaar bazaar;
    private final GameUI ui;

    public Game2(List<Player> names, GameUI ui) {
        this.ui = ui;
        emptyHOD = 0;
        HOD = 0;
        boards = new Board();
        bazaar = new Bazaar();
        players = names;
             if (names.size() == 2) {
            addDomino(players, NUMBER_DOMINOES_TWO_PLAYERS);
        } else {
            addDomino(players, NUMBER_DOMINOES_MORE_THAN_TWO_PLAYERS);
        }
        identifyFirstPlayer(players);
    }

    public void addDomino(List<Player> players, int numberPlayers) {
        while (players.get(players.size() - 1).size() < numberPlayers) {
            for (Player play : players) {
                play.takeDomino(bazaar);
                bazaar.removeFirstBazaar();
            }
        }
    }

    public int start(List<Player> listPlayers, List<DominoClass> startPlay) { // определение какой игрок будет делать 1 ход.
        int min = 0;
        int size = startPlay.size();
        for (byte i = 0; i < size; i++) {
            for (byte j = 0; j < listPlayers.size(); j++) {
                for (byte k = 0; k < listPlayers.get(0).size(); k++) {
                    if (listPlayers.get(j).takeDominoToBoard(k).equals(startPlay.get(0))) {
                        min = j;
                        return min;
                    }
                }
            }
            startPlay.remove(0);
        }
        return min;
    }

    public void identifyFirstPlayer(List<Player> players) {
        int startedPlayer = start(players, start);
            ui.StartPlayer(startedPlayer);
        listPlayers = new LinkedList<>();
        while (listPlayers.size() != players.size()) {
            if (startedPlayer + 1 == players.size()) {
                listPlayers.add(players.get(startedPlayer));
                startedPlayer = 0;
            } else {
                listPlayers.add(players.get(startedPlayer));
                startedPlayer++;
            }
        }
    }


    public void makeHOD() {
        int domino_allow = 0;
        String condition;
        numberPlayerHOD = HOD % listPlayers.size();
        int domino;
        listPlayers.get(numberPlayerHOD).printDomino();
        if (HOD == 0) {
            while (domino_allow == 0) {
                if ((listPlayers.get(numberPlayerHOD) instanceof RealPlayer) ||(listPlayers.get(numberPlayerHOD) instanceof SocketRealPlayer)){
                    ui.printMessagesFromGame("Какую костяшку положить на стол???");
                }
                domino = listPlayers.get(numberPlayerHOD).firstAnswerOrNumberOfPlayers();
                if (domino < listPlayers.get(numberPlayerHOD).size()) {
                    firstHOD(numberPlayerHOD, domino);
                    domino_allow++;
                } else {
                    if ((listPlayers.get(numberPlayerHOD) instanceof RealPlayer) ||(listPlayers.get(numberPlayerHOD) instanceof SocketRealPlayer)){
                        ui.printError("Выбрана несуществующая костяшка.");
                    }
                }
            }
                nextHOD();
            HOD++;
        } else {
            int start = boards.getFirstFromBoard();
            int finish = boards.getLastFromBoard();
            while (domino_allow == 0) {
                if ((listPlayers.get(numberPlayerHOD) instanceof RealPlayer) ||(listPlayers.get(numberPlayerHOD) instanceof SocketRealPlayer)){
                    if (!(bazaar.isEmpty())) {
                        ui.printMessagesFromGame("Какую костяшку положить на стол??? (число) или Взять из базара (eng(B)) или Пропустить ход(eng(N))");
                    } else {
                        ui.printMessagesFromGame("Какую костяшку положить на стол??? (число) или Пропустить ход(eng(N))");
                    }
                }
                condition = listPlayers.get(numberPlayerHOD).AnswerFromPlayer(start, finish, bazaar.isEmpty());
                if (condition.equals("B") && (!bazaar.isEmpty())) {
                    if ((listPlayers.get(numberPlayerHOD) instanceof RealPlayer) ||(listPlayers.get(numberPlayerHOD) instanceof SocketRealPlayer)){
                        ui.printMessagesFromGame("Вы берёте костяшку из базара.");
                    }
                    listPlayers.get(numberPlayerHOD).takeDomino(bazaar);
                    bazaar.removeFirstBazaar();
                    if ((listPlayers.get(numberPlayerHOD) instanceof RealPlayer) ||(listPlayers.get(numberPlayerHOD) instanceof SocketRealPlayer)){
                        listPlayers.get(numberPlayerHOD).printDomino();
                    }
                } else if (condition.equals("B")) {
                    if ((listPlayers.get(numberPlayerHOD) instanceof RealPlayer) ||(listPlayers.get(numberPlayerHOD) instanceof SocketRealPlayer)){
                        ui.printMessagesFromGame("Базар пуст. Если нет подходящей костяшки пропускайте ход.");
                    }
                } else if (condition.equals("N") && bazaar.isEmpty()) {
                    if ((listPlayers.get(numberPlayerHOD) instanceof RealPlayer) ||(listPlayers.get(numberPlayerHOD) instanceof SocketRealPlayer)){
                        ui.printMessagesFromGame("Вы пропустили ход.");
                    }
                    HOD++;
                    emptyHOD++;
                    domino_allow--;
                        printBoard(boards);
                        nextHOD();
                } else if (condition.equals("N")) {
                    if ((listPlayers.get(numberPlayerHOD) instanceof RealPlayer) ||(listPlayers.get(numberPlayerHOD) instanceof SocketRealPlayer)) {
                        ui.printError("Вы не можете пропустить ход. Базар ещё не пуст.");
                    }
                        listPlayers.get(numberPlayerHOD).printDomino();

                } else {
                    try {
                        domino = Integer.parseInt(condition);
                    } catch (Exception e) {
                        if ((listPlayers.get(numberPlayerHOD) instanceof RealPlayer) ||(listPlayers.get(numberPlayerHOD) instanceof SocketRealPlayer)){
                            ui.printError("Выбрана неправильная функция.");
                        }
                        break;
                    }
                    if (domino > listPlayers.get(numberPlayerHOD).size()) {
                        if ((listPlayers.get(numberPlayerHOD) instanceof RealPlayer) ||(listPlayers.get(numberPlayerHOD) instanceof SocketRealPlayer)){
                            ui.printError("Выбрана несуществующая костяшка");
                        }
                                break;
                    }
                    if ((listPlayers.get(numberPlayerHOD) instanceof RealPlayer) ||(listPlayers.get(numberPlayerHOD) instanceof SocketRealPlayer)){
                        ui.printMessagesFromGame("нужно ли переворачивать выбранную костяшку??? Y or N");
                    }
                    condition = listPlayers.get(numberPlayerHOD).AnswerIsReverse(domino - 1, start, finish);
                    if (condition.equals("Y")) {
                        listPlayers.get(numberPlayerHOD).takeDominoToBoard(domino - 1).isReverse();
                        if ((listPlayers.get(numberPlayerHOD) instanceof RealPlayer) ||(listPlayers.get(numberPlayerHOD) instanceof SocketRealPlayer)){
                            listPlayers.get(numberPlayerHOD).printDomino();
                        }
                    } if (condition.equals("N") || condition.equals("Y")) {
                        if ((listPlayers.get(numberPlayerHOD) instanceof RealPlayer) ||(listPlayers.get(numberPlayerHOD) instanceof SocketRealPlayer)){
                            ui.printMessagesFromGame("С какой стороны положить костяшку: слева(L) или справа(R)???");
                        }
                        condition = listPlayers.get(numberPlayerHOD).AnswerFromPlayerRightOrLeft(domino - 1, start, finish);
                        switch (condition) {
                            case "L":
                                if (listPlayers.get(numberPlayerHOD).takeDominoToBoard(domino - 1).getNumber2() == start) {
                                    LeftNOTReverse(numberPlayerHOD, domino - 1);
                                    domino_allow++;
                                        printBoard(boards);
                                        nextHOD();
                                } else {
                                    if ((listPlayers.get(numberPlayerHOD) instanceof RealPlayer) ||(listPlayers.get(numberPlayerHOD) instanceof SocketRealPlayer)){
                                        ui.printError("Вы неправильно выбрали костяшку. Повторите попытку");
                                        listPlayers.get(numberPlayerHOD).printDomino();
                                    }
                                }
                                break;
                            case "R":
                                if (listPlayers.get(numberPlayerHOD).takeDominoToBoard(domino - 1).getNumber1() == finish) {
                                    RightNOTReverse(numberPlayerHOD, domino - 1);
                                    domino_allow++;
                                        printBoard(boards);
                                        nextHOD();
                                } else {
                                    if ((listPlayers.get(numberPlayerHOD) instanceof RealPlayer) ||(listPlayers.get(numberPlayerHOD) instanceof SocketRealPlayer)){
                                        ui.printError("Вы неправильно выбрали костяшку. Повторите попытку");
                                        listPlayers.get(numberPlayerHOD).printDomino();
                                    }
                                }
                                break;
                            default:
                                if ((listPlayers.get(numberPlayerHOD) instanceof RealPlayer) ||(listPlayers.get(numberPlayerHOD) instanceof SocketRealPlayer)){
                                    ui.printError("Справка: \nR - поставить домино справа,\nL - поставить домино слева.\n");
                                    listPlayers.get(numberPlayerHOD).printDomino();
                                }
                                break;
                        }
                    } else {
                        if ((listPlayers.get(numberPlayerHOD) instanceof RealPlayer) ||(listPlayers.get(numberPlayerHOD) instanceof SocketRealPlayer)){
                            ui.printError("Выбрана неправильная функция");
                        }
                        break;
                    }
                }
            }
        }
    }

    public void firstHOD(int numberPlayerHOD, int removeDominoFromPlayer) {
        boards.addFirstBoard(listPlayers.get(numberPlayerHOD).takeDominoToBoard(removeDominoFromPlayer));
        listPlayers.get(numberPlayerHOD).removeDomino(removeDominoFromPlayer);
            printBoard(boards);
    }

    public void nextHOD() {
            ui.printMessagesFromGame("Переход хода: ");
    }

    public void printBoard(Board boards) {
        ui.printBoard(new OutBoardToPlayer(boards));
    }

    public void LeftNOTReverse(int numberPlayerHOD, int domino) {
        boards.addFirstBoard(listPlayers.get(numberPlayerHOD).takeDominoToBoard(domino));
        listPlayers.get(numberPlayerHOD).removeDomino(domino);
        HOD++;
        emptyHOD = 0;
    }


    public void RightNOTReverse(int numberPlayerHOD, int domino) {
        boards.addLastBoard(listPlayers.get(numberPlayerHOD).takeDominoToBoard(domino));
        listPlayers.get(numberPlayerHOD).removeDomino(domino);
        HOD++;
        emptyHOD = 0;
    }

    public boolean booleanIsOver() { // условия, при которых заканчивается игра
        for (Player player : listPlayers) {
            if (player.isEmpty() && bazaar.isEmpty()) {
                return true;
            }
        }
        return emptyHOD == listPlayers.size();
    }
}

/**
 * First vision constructor (неподошло для графического интерфейса)
 */
 /*public Game2(UserUI ui) {
    this.ui = ui;
    emptyHOD = 0;
    HOD = 0;
    boards = new board();
    bazaar = new Bazaar();
    String names;
    int condition = 0;
    while (condition == 0) {
        ui.printMessangesFromGame("Введите количество игроков: ");
        int player = ui.firstAnswerOrNumberOfPlayers();
        if ((player < 2) || (player > 4)) {
            ui.printMessangesFromGame("Игра не началась. Количество игроков не соответствует правилам игры.");
        } else {
            players = new ArrayList<>();
            for (byte i = 1; i <= player; i++) {
                names = ui.NamesPlayers(i);
                if (names.equals("computer")){
                    players.add(new Computer(names, i));
                } else {
                    players.add(new RealPlayer(names, i, ui));
                }
            }
            if (player == 2) {
                addDomino(players, NUMBER_DOMINOES_TWO_PLAYERS);
            } else {
                addDomino(players, NUMBER_DOMINOES_MORE_THAN_TWO_PLAYERS);
            }
            condition++;
        }
    }
    identifyFirstPlayer(players);
}*/

/**
 * Second vision constructor (неподошло для клиент-серверного интерфейса)
 */
/*public Game2(List<String> names, GameUI ui) {
    this.ui = ui;
    emptyHOD = 0;
    HOD = 0;
    boards = new board();
    bazaar = new Bazaar();
    players = new ArrayList<>();
       for (byte i = 0; i < names.size(); i++) {
            if (names.get(i).equals("computer")) {
                players.add(new Computer(names.get(i), i + 1));
            } else {
                players.add(new RealPlayer(names.get(i), i + 1, ui));
            }
        }
    if (names.size() == 2) {
        addDomino(players, NUMBER_DOMINOES_TWO_PLAYERS);
    } else {
        addDomino(players, NUMBER_DOMINOES_MORE_THAN_TWO_PLAYERS);
    }
    identifyFirstPlayer(players);
}*/