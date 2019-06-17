package cz.uhk.fimsnake.model;

import java.util.Date;

import cz.uhk.fimsnake.model.user.User;

public class Score {

    private User user;
    private int score;
    private Date date;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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
