package com.company.UI;

import com.company.OutputToPlayer.Fish;
import com.company.OutputToPlayer.PlayerDominoHave;
import com.company.OutputToPlayer.Winner;

public interface GameOverUI {
    void printPlayerDomino(PlayerDominoHave playerDominoHave);

    void totalNumberHOD(int HOD);

    void printFish(Fish fish);

    void printWinner(Winner winner);

    void printMessagesGameOver(String Message);

    void printGameOver();

}
