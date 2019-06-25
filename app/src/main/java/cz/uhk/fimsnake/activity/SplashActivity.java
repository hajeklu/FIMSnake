package cz.uhk.fimsnake.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import cz.uhk.fimsnake.R;
import cz.uhk.fimsnake.activity.services.NetworkService;
import cz.uhk.fimsnake.activity.services.PreferencesService;
import cz.uhk.fimsnake.dao.CacheFactory;
import cz.uhk.fimsnake.dao.DAOFactory;
import cz.uhk.fimsnake.dao.IDAO;

/**
 * Created by Luboš Hájek in 2019
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        IDAO idao = DAOFactory.getDAO(getApplicationContext());
        CacheFactory.getInstance().clear();
        idao.setUser(CacheFactory.getInstance());
        idao.setScoreToCache(CacheFactory.getInstance());
        idao.setUserScore(CacheFactory.getInstance());
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        PreferencesService.setTextures(sharedPreferences.getBoolean(PreferencesActivity.PRE_TEXTURES, PreferencesActivity.TEXTURES_DEFAULT));

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!NetworkService.getInstance().isInternetAvailable(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(), "Score can not by load, because internet connection is not available.", Toast.LENGTH_LONG);
                } else if (CacheFactory.getInstance().getUser() == null) {

                }
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }
}
