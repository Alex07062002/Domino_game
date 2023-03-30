package com.company.OutputToPlayer;

import com.company.model.Board;

public class OutBoardToPlayer {

    public Board getBoard() {
        return board;
    }

    private final Board board;
    public OutBoardToPlayer(Board board){
        this.board = board;
    }
    @Override
    public String toString() {
        return board.toString();
    }
}
