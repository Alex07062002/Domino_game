package com.company.UI;

import com.company.OutputToPlayer.PlayerDominoHave;

public interface PlayerUI {

    void printPlayerDomino(PlayerDominoHave playerDominoHave);

    int firstAnswer();

    String AnswerFromPlayer();

    String AnswerFromPlayerReverse();

    String WhereStay();
}
