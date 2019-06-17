package cz.uhk.fimsnake.dbs;

import java.util.List;

import cz.uhk.fimsnake.model.Score;

public interface Cache {

    List<Score> getAllScore();

    void resetAllScore(List<Score> score);
}
