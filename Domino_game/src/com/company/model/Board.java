package com.company.model;

import java.util.LinkedList;

public class Board {
    public void setBoard(LinkedList<DominoClass> board) {
        this.board = board;
    }

    private  LinkedList<DominoClass> board;

    public Board(){
        board = new LinkedList<>();
    }

    public LinkedList<DominoClass> getBoard() {
        return board;
    }

    public void addFirstBoard(DominoClass domino) { this.board.addFirst(domino); }

    public void addLastBoard(DominoClass domino) { this.board.addLast(domino); }

    @Override
    public String toString(){
        return (this.board.toString());
    }
    public int getFirstFromBoard(){
        return this.getBoard().getFirst().getNumber1();
    }

    public int getLastFromBoard(){
        return this.getBoard().getLast().getNumber2();
    }
}

