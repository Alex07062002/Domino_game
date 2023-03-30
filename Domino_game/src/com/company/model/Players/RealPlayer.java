package com.company.model.Players;

import com.company.OutputToPlayer.PlayerDominoHave;
import com.company.UI.PlayerUI;
import com.company.model.Bazaar;
import com.company.model.DominoClass;

public class RealPlayer extends Player {
    private final PlayerUI ui;

    public RealPlayer(String name, int numberOfPlayers, PlayerUI ui) {
        super(name, numberOfPlayers);
        this.ui = ui;
    }
    @Override
    public int firstAnswerOrNumberOfPlayers() { return ui.firstAnswer()-1; }
    @Override
    public String AnswerFromPlayer(int start, int finish,boolean isEmpty) { return ui.AnswerFromPlayer(); }

    @Override
    public String AnswerFromPlayerRightOrLeft(int domino, int start, int finish)
        { return ui.WhereStay(); }

    @Override
    public String AnswerIsReverse(int domino, int start, int finish) {
        return ui.AnswerFromPlayerReverse();
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

   public void printDomino() {
        ui.printPlayerDomino(new PlayerDominoHave(this.name,this.getDominoHave()));
    }
}


