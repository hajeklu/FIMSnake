package cz.uhk.fimsnake.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.wifi.p2p.WifiP2pManager;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import cz.uhk.fimsnake.model.GameCanvas;
import cz.uhk.fimsnake.model.Snake;

public class GameView extends View {

    private int FPS = 30;
    private double avgFPS;
    private Timer timer;
    private int speed = 120;
    private GameCanvas gameCanvas;

    public GameView(Context context) {
        super(context);
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(speed);
                        if (gameCanvas != null)
                            gameCanvas.tickScene();
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }

    @Override
    public void onDraw(Canvas canvas) {
        gameCanvas = GameCanvas.getGameCanvas(canvas);
        canvas.drawColor(Color.DKGRAY);
        gameCanvas.drawScene();;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int mid = gameCanvas.getWidth() / 2;

        if (event.getX() < mid)
            gameCanvas.getSnake().turnLeft();
        else if (event.getX() > mid)
            gameCanvas.getSnake().turnRight();

        return super.onTouchEvent(event);
    }
}
