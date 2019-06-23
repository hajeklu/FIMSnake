package cz.uhk.fimsnake.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cz.uhk.fimsnake.model.user.Score;
import cz.uhk.fimsnake.model.user.User;

public class MemoryCache implements Cache {

    private List<Score> scores = new ArrayList<>();
    private List<Score> curretnUserScores = new ArrayList<>();
    private User user;


    protected MemoryCache() {
    }

    @Override
    public List<Score> getAllScore() {
        Collections.sort(scores);
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
    public List<Score> getCurrentUserScore() {
        Score.sortHelper = false;
        Collections.sort(curretnUserScores);
        Score.sortHelper = true;
        return curretnUserScores;
    }

    @Override
    public void addToCurrentUserScore(Score score) {
        Log.d("CACHE", "Current user cache added");
        curretnUserScores.add(score);
    }

    @Override
    public void clear() {
        Log.d("CACHE", "Cache clear");
        scores.clear();
        curretnUserScores.clear();
    }

    @Override
    public double getAverageScoreCurrentUser() {
        int temp = 0;
        for (Score score : curretnUserScores) {
            temp += score.getScore();
        }
        return temp / curretnUserScores.size();
    }

    @Override
    public int getScoreCount() {
        return curretnUserScores.size();
    }

    @Override
    public int getBiggestScoreCurrentUser() {
        List<Score> s1 = new ArrayList<>(curretnUserScores);
        Collections.sort(s1);
        return s1.get(0).getScore();
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user, Context context) {
        this.user = user;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("pref_alias", user.getAlias());
        editor.putString("pref_mac", user.getMacAddress());
        editor.commit();
        Log.d("CACHE", "user set: " + user);
    }

    @Override
    public List<Score> getScoreOrderbyDate() {
        Score.sortHelper = false;
        List<Score> s1 = new ArrayList<>(scores);
        Collections.sort(s1);
        Score.sortHelper = true;
        return s1;
    }
}
