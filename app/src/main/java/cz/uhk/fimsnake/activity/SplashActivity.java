package cz.uhk.fimsnake.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.uhk.fimsnake.R;
import cz.uhk.fimsnake.dbs.FireBase;
import cz.uhk.fimsnake.dbs.IDAO;
import cz.uhk.fimsnake.dbs.MemoryCache;
import cz.uhk.fimsnake.model.user.NetworkService;
import cz.uhk.fimsnake.model.user.Score;
import cz.uhk.fimsnake.model.user.User;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        IDAO idao = new FireBase(getApplicationContext());
        idao.setUser(NetworkService.getInstance().getMacAddress(getApplicationContext()));
        idao.setScoreToCache(MemoryCache.getInstance());
/*
        User u = new User();
        u.setAlias("Muj test user");
        u.setMacAddress(NetworkService.getInstance().getMacAddress(getApplicationContext()));
        List<Score> s = new ArrayList<>();
        Score score = new Score();
        score.setDate(new Date());
        score.setScore(20);
        s.add(score);
        u.setScores(s);
        idao.addUser(u);*/
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }
}
