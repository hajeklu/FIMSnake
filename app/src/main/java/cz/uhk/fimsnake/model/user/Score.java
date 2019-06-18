package cz.uhk.fimsnake.model.user;

import java.util.Date;

import cz.uhk.fimsnake.model.user.User;

public class Score {

    private int score;
    private Date date;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
