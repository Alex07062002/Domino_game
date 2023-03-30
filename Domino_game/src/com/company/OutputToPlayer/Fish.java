package com.company.OutputToPlayer;

public class Fish {
    private final String PlayerName;
    private final int points;
    private final int numberOfPlayers;

    public Fish(String PlayerName, int points,int numberOfPlayers) {
        this.PlayerName = PlayerName;
        this.points = points;
        this.numberOfPlayers = numberOfPlayers;
    }
    @Override
    public String toString(){
        return "Имя игрока "+PlayerName+ " игорок "+ numberOfPlayers+" количество очков  "+points;
    }
}

