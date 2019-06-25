package cz.uhk.fimsnake.activity.services;

import android.content.Context;
import android.media.MediaPlayer;

import cz.uhk.fimsnake.R;

/**
 * Created by Luboš Hájek on 25.06.2019.
 */
public class SoundService {


    private static MediaPlayer mediaPlayer;

    public static void play(Context context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.bensound_summer);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public static void stop() {
        mediaPlayer.stop();
    }
}
