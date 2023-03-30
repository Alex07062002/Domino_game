package com.company.server;

import com.company.model.Game2;
import com.company.model.Players.Computer;
import com.company.model.Players.Player;
import com.company.model.Players.SocketRealPlayer;
import com.company.model.Statistics;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientServerApplication implements Runnable {

    private final Game2 game;
    private final ConnectionHandler handler;

    public ClientServerApplication(Socket socket, ConnectionHandler handler) {
        this.handler = handler;
        List<Player> start = new ArrayList<>();
        start.add(new SocketRealPlayer("Player", 1, socket));
       // start.add(new Computer("Computer", 1));
        start.add(new Computer("Computer", 2));
        game = new Game2(start, handler);
    }

    @Override
    public void run() {
      while (!game.booleanIsOver()) {
            game.makeHOD();
       }
         Statistics stats = new Statistics(game,handler);
         stats.statistics();
    }

}
