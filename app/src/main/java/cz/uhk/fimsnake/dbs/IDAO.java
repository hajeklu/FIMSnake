package cz.uhk.fimsnake.dbs;

import com.google.android.gms.tasks.OnCompleteListener;

import java.util.List;

import cz.uhk.fimsnake.model.user.Players;
import cz.uhk.fimsnake.model.user.Score;
import cz.uhk.fimsnake.model.user.User;

public interface IDAO {

    boolean addScoreToPlayer(int value);

    void getData(OnCompleteListener listener);

    void setUser();

    void addUser(User user);

    void setScoreToCache(Cache cache);
}
