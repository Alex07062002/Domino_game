package com.company.model.old;

import com.company.UI.GameUI;
import com.company.model.Bazaar;
import com.company.model.DominoClass;

import java.util.ArrayList;
import java.util.List;

public class PlayerOldVersion {
    public final String name;
    private final int numberOfPlayers;
    private final List<DominoClass> dominoHave;
    private Type type;
    private final GameUI ui;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {computer, player}

    public String getName() {
        return name;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public List<DominoClass> getDominoHave() {
        return dominoHave;
    }


    public PlayerOldVersion(String name, int numberOfPlayers, GameUI ui) {
        this.name = name;
        this.numberOfPlayers = numberOfPlayers;
        this.dominoHave = new ArrayList<>();
        this.ui = ui;
        if (name.equals("computer")) {
            this.setType(Type.computer);
        } else {
            this.setType(Type.player);
        }
    }

   /* public int firstAnswerOrNumberOfPlayers() {
        return ui.firstAnswer()-1;
    }*/


    //public String AnswerFromPlayer() { return ui.AnswerFromPlayer(); }

    public void takeDomino(Bazaar bazaar) {
        getDominoHave().add(bazaar.firstDominoBazaar());
    }
    public void removeDomino (int domino) {
        getDominoHave().remove(domino);
    }
    public DominoClass takeDominoToBoard(int domino){
        return getDominoHave().get(domino);
    }
    public boolean isEmpty(){
        return getDominoHave().isEmpty();
    }
    public  int size(){ return getDominoHave().size(); }


    public String toString(){
        for (DominoClass domino : dominoHave){
            return domino.toString();
        }
        return null;
    }
}
