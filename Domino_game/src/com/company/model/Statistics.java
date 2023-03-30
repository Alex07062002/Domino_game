package com.company.model;
import com.company.OutputToPlayer.PlayerDominoHave;
import com.company.UI.GameOverUI;
import com.company.OutputToPlayer.Fish;
import com.company.OutputToPlayer.Winner;
import com.company.model.Players.Player;

public class Statistics{
    public GameOverUI ui;
    private final Game2 game;

    public Statistics(Game2 game,GameOverUI ui){
        this.ui = ui;
        this.game = game;
    }
    public void statistics() {
        ui.totalNumberHOD(game.getHOD());
        for (Player player : game.getPlayers()) {
            ui.printPlayerDomino(new PlayerDominoHave(player.getName(), player.getDominoHave()));
        }
        if (game.getEmptyHOD() == game.getPlayers().size()) {
            ui.printMessagesGameOver("Никто не смог подложить костяшку - 'Рыба'.");
            int[] Points = sumPointsEveryOne();
            for (int i = 0; i < game.getPlayers().size(); i++) {
                ui.printFish(new Fish(game.getPlayers().get(i).name, Points[i], game.getPlayers().get(i).getNumberOfPlayers()));
            }
        } else {
            byte count = (byte) game.getPlayers().stream()
                    .filter(Player::isEmpty)
                    .count();
            if (count > 1) {
                ui.printMessagesGameOver("Ничья.");
            } else {
                int winner = 0;
                while (!(game.getPlayers().get(winner).isEmpty())) {
                    winner++;
                }
                ui.printMessagesGameOver("У одного игрока закончились кости.");
                ui.printWinner(new Winner(game.getPlayers().get(winner).name, sumPoints(), game.getPlayers().get(winner).getNumberOfPlayers()));
            }
        }
        ui.printGameOver();
    }
    public int[] sumPointsEveryOne(){ // считает очки при ситуации "рыба"
        int point = 0;
        int[] Points = new int [game.getPlayers().size()];
        for (byte i = 0;i<game.getPlayers().size();i++){
            for (byte j = 0;j<game.getPlayers().size();j++){
                if (i != j){
                    for (byte k = 0; k < game.getPlayers().get(j).size(); k++) {
                        point += game.getPlayers().get(j).takeDominoToBoard(k).getPoint();
                    }
                }
            }
            Points[i] = point;
            point = 0;
        }
        return Points;
    }

    public int sumPoints(){ // считает очки для победителя - игрока, у которого не осталось костей
        int point = 0;
        for (Player listPlayer : game.getPlayers()) {
            for (byte k = 0; k < listPlayer.size(); k++) {
                if (listPlayer.takeDominoToBoard(k).getPoint() == 0 && listPlayer.size() == 1){
                    point += 25;
                }
                point += listPlayer.takeDominoToBoard(k).getPoint();
            }
        }
        return point;
    }

}
