package com.company.model;

import com.company.UI.PlayerUI;
import com.company.model.Players.Computer;
import com.company.model.Players.Player;
import com.company.model.Players.RealPlayer;

import java.util.ArrayList;
import java.util.List;

public class ConvertToListPlayer {

    public List<Player> startList;

    public ConvertToListPlayer(List<String> names, PlayerUI ui){
        startList = new ArrayList<>();
          for (byte i = 0; i < names.size(); i++) {
            if (names.get(i).equals("computer")) {
                startList.add(new Computer(names.get(i), i + 1));
            } else {
                startList.add(new RealPlayer(names.get(i), i + 1, ui));
            }
        }
    }
}
