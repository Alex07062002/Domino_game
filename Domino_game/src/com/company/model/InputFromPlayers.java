package com.company.model;

import com.company.UI.InputUI;

import java.util.List;

public class InputFromPlayers {
    public InputUI ui;
    public List<String> names;

    public InputFromPlayers(InputUI ui){
        this.ui = ui;
    int condition = 0;
    while (condition == 0) {
        ui.printMessages("Введите количество игроков: ");
        int player = ui.NumberOfPlayers();
        if ((player < 2) || (player > 4)) { //TODO проверка на ввод String
            ui.printError("Игра не началась. Количество игроков не соответствует правилам игры.");
        } else {
            names = ui.NamesPlayers(player);
            condition++;
        }
    }

}
}
