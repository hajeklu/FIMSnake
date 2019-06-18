package cz.uhk.fimsnake.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;

import cz.uhk.fimsnake.R;
import cz.uhk.fimsnake.dbs.FireBase;
import cz.uhk.fimsnake.dbs.IDAO;
import cz.uhk.fimsnake.model.GameCanvas;

public class GameView extends View {

    private int FPS = 30;
    private double avgFPS;
    private Timer timer;
    private int speed = 130;
    private GameCanvas gameCanvas;
    private IDAO databaseHelper;

    private static boolean gameRun = true;
    public static View gameContext;

    public GameView(Context context) {
        super(context);
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(speed);
                        if (gameCanvas != null && gameRun)
                            gameCanvas.tickScene();
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();

        databaseHelper = new FireBase(context);
        gameContext = this;
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.DKGRAY);
        gameCanvas = GameCanvas.getGameCanvas(canvas, this);
        gameCanvas.drawScene();
        if (!gameRun) {
            Drawable d = getResources().getDrawable(R.mipmap.gameover1, null);
            int border = (canvas.getHeight() / 2) / 2;
            d.setBounds(0, border - 50, canvas.getWidth(), canvas.getHeight() - border - 50);
            d.draw(canvas);
        }
        invalidate();
    }

    public void gameOver() {

        gameCanvas.saveScore(databaseHelper);
        gameRun = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (!gameRun) {
            gameRun = true;
            gameCanvas.restart();
        }

        int mid = gameCanvas.getWidth() / 2;

        if (event.getX() < mid)
            gameCanvas.getSnake().turnLeft();
        else if (event.getX() > mid)
            gameCanvas.getSnake().turnRight();

        return super.onTouchEvent(event);
    }
}
