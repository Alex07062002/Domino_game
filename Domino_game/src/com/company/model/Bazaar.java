package com.company.model;

import java.util.*;

public class Bazaar {
    private final List<DominoClass> bazaar;


    public List<DominoClass> getBazaar() {
        return bazaar;
    }

    public void removeFirstBazaar(){
        bazaar.remove(0);
    }

    public DominoClass firstDominoBazaar(){
        return bazaar.get(0);
    }

    public Bazaar() {
       List<DominoClass> dominoBazaar = new LinkedList<>();
            for (byte i = 0; i < 7; i++) {
                for (byte j = 0; j <= i; j++) {
                    dominoBazaar.add(new DominoClass(i, j));
                }
            }
        bazaar = dominoBazaar;
        Collections.shuffle(getBazaar());
    }

    @Override
    public String toString() {
        return (getBazaar().toString());
    }

    public boolean isEmpty(){ return bazaar.isEmpty();}
}