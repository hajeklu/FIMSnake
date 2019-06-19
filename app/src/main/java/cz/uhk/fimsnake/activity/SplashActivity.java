package cz.uhk.fimsnake.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuWrapperFactory;

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
        idao.setUser();
        idao.setScoreToCache(MemoryCache.getInstance());
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
