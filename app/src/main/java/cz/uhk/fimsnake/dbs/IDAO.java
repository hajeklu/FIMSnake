package cz.uhk.fimsnake.dbs;

import com.google.android.gms.tasks.OnCompleteListener;

import java.util.List;

import cz.uhk.fimsnake.model.user.Players;
import cz.uhk.fimsnake.model.user.User;

public interface IDAO {

    boolean addScorePlayer(int data, Players player);

    void getData(OnCompleteListener listener);

    void setUser(String mac);

    void addUser(User user);
}
