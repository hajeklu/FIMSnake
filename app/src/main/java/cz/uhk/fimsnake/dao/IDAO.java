package cz.uhk.fimsnake.dao;

import com.google.android.gms.tasks.OnCompleteListener;

import cz.uhk.fimsnake.model.user.User;

public interface IDAO {

    boolean addScoreToPlayer(int value);

    void setUserScore(Cache cache);

    void setUser(Cache cache);

    void addUser(User user);

    void setScoreToCache(Cache cache);
}
