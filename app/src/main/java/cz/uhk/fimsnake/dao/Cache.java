package cz.uhk.fimsnake.dao;

import android.content.Context;

import java.util.List;

import cz.uhk.fimsnake.model.user.Score;
import cz.uhk.fimsnake.model.user.User;

public interface Cache {

    List<Score> getAllScore();

    void resetAllScore(List<Score> score);

    void add(Score score);

    List<Score> getCurrentUserScore();

    void addToCurrentUserScore(Score score);

    void clear();

    double getAverageScoreCurrentUser();

    int getScoreCount();

    int getBigestScoreCurrentUser();

    User getUser();

    void setUser(User user, Context context);
}
