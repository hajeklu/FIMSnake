package cz.uhk.fimsnake.activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import cz.uhk.fimsnake.R;
import cz.uhk.fimsnake.activity.services.NetworkService;
import cz.uhk.fimsnake.dao.CacheFactory;
import cz.uhk.fimsnake.model.user.User;

/**
 * Created by Luboš Hájek in 2019
 */
public class MainActivity extends AppCompatActivity {

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        ImageButton playButton = findViewById(R.id.playbtn);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);

            }
        });

        ImageButton preff = findViewById(R.id.preferences);

        preff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

                if (NetworkService.getInstance().isInternetAvailable(getApplicationContext())) {
                    Intent intent = new Intent(MainActivity.this, PreferencesActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Internet connection needed.", Toast.LENGTH_LONG).show();
                }
            }
        });

        ImageButton scoreBnt = findViewById(R.id.score);

        scoreBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("onClick: " + NetworkService.getInstance().isInternetAvailable(getApplicationContext()));
                if (NetworkService.getInstance().isInternetAvailable(getApplicationContext())) {
                    Intent intent = new Intent(MainActivity.this, TapScore.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Internet connection needed.", Toast.LENGTH_LONG).show();
                }
            }
        });

        User user = CacheFactory.getInstance().getUser();
        if (user == null || user.getMacAddress().equals(user.getAlias())) {
            Intent intent = new Intent(MainActivity.this, PopupActivity.class);
            startActivity(intent);
        }
    }

    public void createNotificationNewRecord() {
        Intent intent = new Intent(this, TapScore.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel")
                .setSmallIcon(R.drawable.snake_head)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(1, builder.build());

    }
}