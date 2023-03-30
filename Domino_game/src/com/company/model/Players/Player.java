package com.company.model.Players;

import com.company.model.Bazaar;
import com.company.model.DominoClass;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    public final String name;
    protected final int numberOfPlayers;
    protected final List<DominoClass> dominoHave;


    public String getName() {
        return name;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public List<DominoClass> getDominoHave() {
        return dominoHave;
    }


    public Player(String name, int numberOfPlayers) {
        this.name = name;
        this.numberOfPlayers = numberOfPlayers;
        this.dominoHave = new ArrayList<>();

    }
    public abstract int firstAnswerOrNumberOfPlayers();

    public abstract String AnswerFromPlayer(int start,int finish,boolean isEmpty);

    public abstract String AnswerFromPlayerRightOrLeft(int domino, int start, int finish);

    public abstract String AnswerIsReverse(int domino, int start, int finish);

    public abstract void takeDomino(Bazaar bazaar);

    public abstract void removeDomino (int domino);

    public abstract DominoClass takeDominoToBoard(int domino);

    public abstract boolean isEmpty();

    public abstract int size();

    public abstract void printDomino();
}

