package cz.uhk.fimsnake.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.wifi.p2p.WifiP2pManager;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import cz.uhk.fimsnake.model.Snake;

public class GameView extends View {

    private Snake snake;
    private int FPS = 30;
    private double avgFPS;
    private Timer timer;
    private int speed = 100;

    public GameView(Context context) {
        super(context);
        snake = new Snake();


        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(speed);
                        snake.tick();
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        //  canvas.drawRect(10,10, 50, 50, paint);
        snake.draw(canvas);
        invalidate();
    }

    public void update() {

    }
}
