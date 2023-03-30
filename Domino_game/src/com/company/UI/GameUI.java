package com.company.UI;

import com.company.OutputToPlayer.OutBoardToPlayer;

public interface GameUI {

    void printBoard(OutBoardToPlayer board);

    void StartPlayer(int start);

    void printMessagesFromGame(String Message);

    void printError(String ErrorMessage);
}

