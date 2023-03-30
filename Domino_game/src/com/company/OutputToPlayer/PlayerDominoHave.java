package com.company.OutputToPlayer;

import com.company.model.DominoClass;

import java.util.List;

public final class PlayerDominoHave {
    private final String PlayerName;

    public List<DominoClass> getPlayerDomino() {
        return PlayerDomino;
    }

    private final List<DominoClass> PlayerDomino;

   public PlayerDominoHave(String PlayerName, List<DominoClass> PlayerDomino) {
    this.PlayerDomino = PlayerDomino;
    this.PlayerName = PlayerName;
    }

    @Override
    public String toString(){
       return "Костяшки игрока "+this.PlayerName+ " "+this.PlayerDomino.toString();
           }
}



