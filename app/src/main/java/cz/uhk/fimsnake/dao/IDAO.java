package cz.uhk.fimsnake.dao;

import android.content.Context;

import cz.uhk.fimsnake.model.user.User;

/**
 * Created by Luboš Hájek in 2019
 */
public interface IDAO {

    boolean addScoreToPlayer(int value);

    void setUserScore(Cache cache);

    void setUser(Cache cache);

    void addUser(User user);

    void setScoreToCache(Cache cache);

    void invalidAndRestartCache(Context context);

    void setNewAliasToCurrentUser(String alias);

}
