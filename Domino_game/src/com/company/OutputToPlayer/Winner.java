package com.company.OutputToPlayer;

public class Winner {
    private final String PlayerName;
    private final int points;
    private final int numberOfPlayers;

    public Winner(String PlayerName, int points, int numberOfPlayers) {
        this.PlayerName = PlayerName;
        this.points = points;
        this.numberOfPlayers = numberOfPlayers;
    }
    @Override
    public String toString(){
        return ("Победитель "+PlayerName+" Игорок "+numberOfPlayers+" количество очков у победителя "+points);
    }
}

