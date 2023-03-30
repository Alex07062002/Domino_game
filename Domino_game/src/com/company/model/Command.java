package com.company.model;

public enum Command {
    StartPlayer("Начинает игрок"),
    Message("Сообщение"),
    ErrorMessage("Предупреждение"),
    PrintBoard("Поле"),
    RespInt("Введите число "),
    RespStr("Введите нужную операцию "),
    RespStrROrL("С какой стороны поставить "),
    RespIsReverse("Нужно ли перевернуть "),
    PrintDomino("Домино игрока"),
    TotalHOD("Общее число ходов"),
    Fish("Рыба"),
    Winner("Победитель"),
    FinalPrintDomino("Домино"),
    END("Конец"),
    MessageGameOver("Итог");





    public static final String SEPARATOR = ":";

    private final String commandString;

    Command(String commandString) {
        this.commandString = commandString;
    }

    public String getCommandString() {
        return commandString;
    }
}
