package com.company.model.old;

public class GameOld {

  /*  public final int NUMBER_DOMINOES_TWO_PLAYERS = 7;
    public final int NUMBER_DOMINOES_MORE_THAN_TWO_PLAYERS = 5;
    public final List<DominoClass> start = new ArrayList<>(Arrays.asList(new DominoClass(1, 1), new DominoClass(2, 2),
            new DominoClass(3, 3), new DominoClass(4, 4), new DominoClass(5, 5), new DominoClass(6, 6),
            new DominoClass(1, 0), new DominoClass(2, 0), new DominoClass(2, 1), new DominoClass(3, 0),
            new DominoClass(3, 1), new DominoClass(4, 0), new DominoClass(3, 2), new DominoClass(4, 1), new DominoClass(5, 0)));
    private List<Player> players;
    private List<Player> listPlayers;
    private int emptyHOD;
    private int HOD;
    private final board boards;
    private final Bazaar bazaar;
    private final GameUI ui;

    public Game(List<String> names, GameUI ui) {
        this.ui = ui;
        emptyHOD = 0;
        HOD = 0;
        boards = new board();
        bazaar = new Bazaar();
        String name;
        int condition = 0;
        while (condition == 0) {
            ui.printMessagesFromGame("Введите количество игроков: ");
            int player = ui.firstAnswer();
            if ((player < 2) || (player > 4)) {
                ui.printMessagesFromGame("Игра не началась. Количество игроков не соответствует правилам игры.");
            } else {
                players = new ArrayList<>();
                for (int i = 1; i <= player; i++) {
                    name = names.get(i);
                    players.add(new Player(name, i, ui));
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
    }

    public void addDomino(List<Player> players, int numberPlayers){
        while (players.get(players.size()-1).size() < numberPlayers) {
            for (Player play : players) {
                play.takeDomino(bazaar);
                bazaar.removeFirstBazaar();
            }
        }
    }

    public int start(List<Player> listPlayers, List<DominoClass> startPlay) { // определение какой игрок будет делать 1 ход.
        int min = 0;
        int size = startPlay.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < listPlayers.size(); j++) {
                for (int k = 0; k < listPlayers.get(0).size(); k++) {
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
        int numberPlayerHOD = HOD % listPlayers.size();
        int domino;
        if (listPlayers.get(numberPlayerHOD).getType().equals(Player.Type.player)) {
            printPlayerDomino(numberPlayerHOD);
            if (HOD == 0) {
                while (domino_allow == 0) {
                    ui.printMessagesFromGame("Какую костяшку положить на стол???");
                   // domino = listPlayers.get(numberPlayerHOD).firstAnswerOrNumberOfPlayers();
                        if (domino < listPlayers.get(numberPlayerHOD).size()) {
                            firstHOD(numberPlayerHOD, domino);
                            domino_allow++;
                        }else{
                        ui.printMessagesFromGame("Выбрана несуществующая костяшка.");
                    }
                }
                    nextHOD();
                    HOD++;
            } else {
                int start = boards.getFirstFromBoard();
                int finish = boards.getLastFromBoard();
                while (domino_allow == 0) {
                    if (!(bazaar.isEmpty())) {
                        ui.printMessagesFromGame("Какую костяшку положить на стол??? (число) или Взять из базара (eng(B)) или Пропустить ход(eng(N))");
                    } else {
                        ui.printMessagesFromGame("Какую костяшку положить на стол??? (число) или Пропустить ход(eng(N))");
                    }
                  //  condition = listPlayers.get(numberPlayerHOD).AnswerFromPlayer();
                    if (condition.equals("B") && (!bazaar.isEmpty())) {
                        ui.printMessagesFromGame("Вы берёте костяшку из базара.");
                        listPlayers.get(numberPlayerHOD).takeDomino(bazaar);
                        bazaar.removeFirstBazaar();
                        printPlayerDomino(numberPlayerHOD);
                    } else if (condition.equals("B")) {
                        ui.printMessagesFromGame("Базар пуст. Если нет подходящей костяшки пропускайте ход.");
                    } else if (condition.equals("N") && bazaar.isEmpty()) {
                        ui.printMessagesFromGame("Вы пропустили ход.");
                        HOD++;
                        emptyHOD++;
                        domino_allow--;
                    } else if (condition.equals("N")) {
                        ui.printMessagesFromGame("Вы не можете пропустить ход. Базар ещё не пуст.");
                        printPlayerDomino(numberPlayerHOD);
                    } else {
                        try {
                            domino = Integer.parseInt(condition);
                        } catch (Exception e) {
                            ui.printMessagesFromGame("Выбрана неправильная функция.");
                            break;
                        }
                        if (domino>=listPlayers.get(numberPlayerHOD).size()){
                            ui.printMessagesFromGame("Выбрана несуществующая костяшка");
                            break;
                        }
                        ui.printMessagesFromGame("С какой стороны положить костяшку: слева(L) или справа(R)??? нужно ли переворачивать(r)???");
                        condition = listPlayers.get(numberPlayerHOD).AnswerFromPlayer();
                        switch (condition) {
                            case "L":
                                if (listPlayers.get(numberPlayerHOD).takeDominoToBoard(domino - 1).getNumber2() == start) {
                                    LeftNOTReverse(numberPlayerHOD, domino - 1);
                                    domino_allow++;
                                } else {
                                    ui.printMessagesFromGame("Вы неправильно выбрали костяшку. Повторите попытку");
                                    printPlayerDomino(numberPlayerHOD);
                                }
                                break;
                            case "Lr":
                                if (listPlayers.get(numberPlayerHOD).takeDominoToBoard(domino - 1).getNumber1() == start) {
                                    LeftReverse(numberPlayerHOD, domino - 1);
                                    domino_allow++;
                                } else {
                                    ui.printMessagesFromGame("Вы неправильно выбрали костяшку. Повторите попытку");
                                    printPlayerDomino(numberPlayerHOD);
                                }
                                break;
                            case "R":
                                if (listPlayers.get(numberPlayerHOD).takeDominoToBoard(domino - 1).getNumber1() == finish) {
                                    RightNOTReverse(numberPlayerHOD, domino - 1);
                                    domino_allow++;
                                } else {
                                    ui.printMessagesFromGame("Вы неправильно выбрали костяшку. Повторите попытку");
                                    printPlayerDomino(numberPlayerHOD);
                                }
                                break;
                            case "Rr":
                                if (listPlayers.get(numberPlayerHOD).takeDominoToBoard(domino - 1).getNumber2() == finish) {
                                    RightReverse(numberPlayerHOD, domino - 1);
                                    domino_allow++;
                                } else {
                                    ui.printMessagesFromGame("Вы неправильно выбрали костяшку. Повторите попытку");
                                    printPlayerDomino(numberPlayerHOD);
                                }
                                break;
                            default:
                                ui.printMessagesFromGame("Справка: \nR - поставить домино справа,\nRr - поставить перевернутое домино справа,\n" +
                                        "L - поставить домино слева,\nLr - поставить перевернутое домино слева\n");
                                printPlayerDomino(numberPlayerHOD);
                                break;
                        }
                    }
                    printBoard(boards);
                    nextHOD();
                }
            }
        }else{
            if (HOD == 0){
                firstHOD(numberPlayerHOD,0);
                HOD++;
            }else {
                int start = boards.getFirstFromBoard();
                int finish = boards.getLastFromBoard();
                if (listPlayers.get(numberPlayerHOD).isEmpty() && !bazaar.getBazaar().isEmpty()){
                    listPlayers.get(numberPlayerHOD).takeDomino(bazaar);
                    bazaar.removeFirstBazaar();
                }
                boolean have = IS(listPlayers, start, finish);
                while ((!have) && !(bazaar.isEmpty())) {
                    listPlayers.get(numberPlayerHOD).takeDomino(bazaar);
                    bazaar.removeFirstBazaar();
                        have = IS(listPlayers, start, finish);
                    }
                    if (!have) {
                        emptyHOD++;
                        HOD++;
                    } else {
                        int domino_from_computer = 0;
                        while (domino_allow == 0) {
                            if (listPlayers.get(numberPlayerHOD).takeDominoToBoard(domino_from_computer).getNumber2() == start) {
                               LeftNOTReverse(numberPlayerHOD,domino_from_computer);
                               domino_allow++;
                            } else if (listPlayers.get(numberPlayerHOD).takeDominoToBoard(domino_from_computer).getNumber1() == start) {
                                LeftReverse(numberPlayerHOD,domino_from_computer);
                                domino_allow++;
                            } else if (listPlayers.get(numberPlayerHOD).takeDominoToBoard(domino_from_computer).getNumber1() == finish) {
                                RightNOTReverse(numberPlayerHOD,domino_from_computer);
                                domino_allow++;
                            } else if (listPlayers.get(numberPlayerHOD).takeDominoToBoard(domino_from_computer).getNumber2() == finish) {
                                RightReverse(numberPlayerHOD,domino_from_computer);
                                domino_allow++;
                            } else {
                                domino_from_computer++;
                            }
                        }
                    }
                }
            nextHOD();
            printBoard(boards);
            }
        }

        public void firstHOD(int numberPlayerHOD, int removeDominoFromPlayer){
            boards.addFirstBoard(listPlayers.get(numberPlayerHOD).takeDominoToBoard(removeDominoFromPlayer));
            listPlayers.get(numberPlayerHOD).removeDomino(removeDominoFromPlayer);
            printBoard(boards);
        }


    public boolean IS(List<Player> playlist,int start,int finish) { // проверка, существует ли костяшка у игрока, чтобы подложить (для бота)
        for (int i = 0;i<playlist.get(HOD % listPlayers.size()).size();i++){
            if ((playlist.get(HOD % listPlayers.size()).takeDominoToBoard(i).isHave(start)) ||(playlist.get(HOD % listPlayers.size()).takeDominoToBoard(i).isHave(finish))){
                return true;
            }
        }
        return false;
    }

        public void printPlayerDomino(int numberPlayerHOD){
        PlayerDominoHave playerDominoHave = new PlayerDominoHave(listPlayers.get(numberPlayerHOD).getName(),listPlayers.get(numberPlayerHOD).getDominoHave());
        ui.printPlayerDomino(playerDominoHave);
        }

        public void nextHOD(){
        ui.printMessagesFromGame("Переход хода: ");
        }
        public void printBoard(board boards){
        ui.printBoard(new Board(boards));
        }

        public void LeftNOTReverse(int numberPlayerHOD, int domino){
            boards.addFirstBoard(listPlayers.get(numberPlayerHOD).takeDominoToBoard(domino));
            listPlayers.get(numberPlayerHOD).removeDomino(domino);
            HOD++;
            emptyHOD = 0;
        }

        public void LeftReverse(int numberPlayerHOD, int domino){
            boards.addFirstBoard(listPlayers.get(numberPlayerHOD).takeDominoToBoard(domino).isReverse());
            listPlayers.get(numberPlayerHOD).removeDomino(domino);
            HOD++;
            emptyHOD = 0;
        }

        public void RightNOTReverse(int numberPlayerHOD, int domino){
            boards.addLastBoard(listPlayers.get(numberPlayerHOD).takeDominoToBoard(domino));
            listPlayers.get(numberPlayerHOD).removeDomino(domino);
            HOD++;
            emptyHOD = 0;
        }

        public void RightReverse(int numberPlayerHOD, int domino){
            boards.addLastBoard(listPlayers.get(numberPlayerHOD).takeDominoToBoard(domino).isReverse());
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
    }*/
}


