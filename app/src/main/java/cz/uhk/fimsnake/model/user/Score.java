package cz.uhk.fimsnake.model.user;

import com.google.firebase.firestore.Exclude;

import java.util.Date;

import cz.uhk.fimsnake.model.user.User;

public class Score implements Comparable<Score> {

    private int score;
    private Date date;
    public static boolean sortHelper = true; //true - sort by score,  false - sort by date

    @Exclude
    private String userAlias;

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
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

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                ", date=" + date +
                '}';
    }

    @Override
    public int compareTo(Score o) {
        return sortHelper ? o.getScore() - this.getScore() : o.getDate().compareTo(date);
    }
}
