package cz.uhk.fimsnake.dbs;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cz.uhk.fimsnake.model.user.Score;

public class MemoryCache implements Cache {

    private List<Score> scores = new ArrayList<>();
    private static MemoryCache memoryCache;

    private MemoryCache() {
    }

    public static MemoryCache getInstance() {
        if (memoryCache == null) {
            memoryCache = new MemoryCache();
        }
        return memoryCache;
    }

    @Override
    public List<Score> getAllScore() {
        return scores;
    }

    @Override
    public void resetAllScore(List<Score> score) {
        this.scores = score;
    }

    @Override
    public void add(Score score) {
        scores.add(score);
        Log.d("CACHE", "Added to cache size: " + scores.size());
    }

    @Override
    public void clear() {
        Log.d("CACHE", "Cache clear");
        scores.clear();
    }
}
