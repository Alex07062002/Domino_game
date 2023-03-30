package com.company.model.Players;

import com.company.model.Bazaar;
import com.company.model.DominoClass;


public class Computer extends Player {
    public Computer(String name, int numberOfPlayers) {
        super(name, numberOfPlayers);
    }
    public boolean IS(int start, int finish) { // проверка, существует ли костяшка у игрока, чтобы подложить (для бота)
        for (int i = 0;i<dominoHave.size();i++){
            if ((takeDominoToBoard(i).isHave(start)) ||(takeDominoToBoard(i).isHave(finish))){
                return true;
            }
        }
        return false;
    }

    @Override
    public int firstAnswerOrNumberOfPlayers() {
        return 0;
    }

    @Override
    public String AnswerFromPlayer(int start,int finish,boolean isEmpty) {
        if (dominoHave.isEmpty()) {
            return "B";
        }
        boolean have = IS(start, finish);
        if ((!have) && !(isEmpty)){
            return "B";
        }if(!have) {
            return "N";
        }else {
            int domino_allow = 0;
            int domino_from_computer = 0;
            while (domino_allow == 0) {
                if (takeDominoToBoard(domino_from_computer).getNumber2() == start) {
                    domino_allow++;
                } else if (takeDominoToBoard(domino_from_computer).getNumber1() == start) {
                    domino_allow++;
                } else if (takeDominoToBoard(domino_from_computer).getNumber1() == finish) {
                    domino_allow++;
                } else if (takeDominoToBoard(domino_from_computer).getNumber2() == finish) {
                    domino_allow++;
                } else {
                    domino_from_computer++;
                }
            }
            return Integer.toString(domino_from_computer+1);
        }
    }

    @Override
    public String AnswerFromPlayerRightOrLeft(int domino,int start, int finish) {
        String condition;
        if (takeDominoToBoard(domino).getNumber2() == start) {
            condition = "L";
        } else {
            condition = "R";
        }
        return condition;
    }

    @Override
    public String AnswerIsReverse(int domino, int start, int finish) {
        String condition;
        if ((takeDominoToBoard(domino).getNumber1() == finish) || (takeDominoToBoard(domino).getNumber2() == start)) {
            condition ="N";
        }else{
            condition ="Y";
        }
        return condition;
    }

    @Override
    public void takeDomino(Bazaar bazaar) {
        getDominoHave().add(bazaar.firstDominoBazaar());
    }

    @Override
    public void removeDomino(int domino) {
        getDominoHave().remove(domino);
    }

    @Override
    public DominoClass takeDominoToBoard(int domino) {
        return getDominoHave().get(domino);
    }

    @Override
    public boolean isEmpty() {
        return getDominoHave().isEmpty();
    }

    @Override
    public int size() {
        return getDominoHave().size();
    }

    @Override
    public void printDomino() {
    }
}

