package cz.uhk.fimsnake.dbs;

import java.util.List;

import cz.uhk.fimsnake.model.user.Players;
import cz.uhk.fimsnake.model.user.User;

public interface IDAO {

    boolean addScorePlayer(int data, Players player);

    List<Integer> getData(Players player);

    void setUser(String mac);

    void addUser(User user);
}
