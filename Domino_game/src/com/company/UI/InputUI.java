package com.company.UI;

import java.util.List;

public interface InputUI {
    List<String> NamesPlayers(int NumberOfPlayers);

    int NumberOfPlayers();

    void printMessages(String Message);

    void printError(String ErrorMessage);

}
