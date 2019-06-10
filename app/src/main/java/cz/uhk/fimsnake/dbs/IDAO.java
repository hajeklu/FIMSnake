package cz.uhk.fimsnake.dbs;

import java.util.List;

import cz.uhk.fimsnake.model.Players;

public interface IDAO {

    boolean addScorePlayer(int data, Players player);

    List<Integer> getData(Players player);
}
