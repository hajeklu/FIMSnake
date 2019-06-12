package cz.uhk.fimsnake.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cz.uhk.fimsnake.R;
import cz.uhk.fimsnake.dbs.FireBase;
import cz.uhk.fimsnake.dbs.IDAO;
import cz.uhk.fimsnake.model.user.MacAddressProvider;
import cz.uhk.fimsnake.model.user.User;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        IDAO idao = new FireBase(getApplicationContext());
        idao.setUser(MacAddressProvider.getInstance().getMacAddress(getApplicationContext()));

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
