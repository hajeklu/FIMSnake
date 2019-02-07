package cz.uhk.fimsnake;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import cz.uhk.fimsnake.view.GameView;

public class GameThread extends Thread {

    private GameView view;
    private boolean running = true;
    private int FPS = 30;
    private double avgFPS;
    private SurfaceHolder holder;
    public static Canvas canvas;

    public GameThread(GameView view, SurfaceHolder holder) {
        super();
        this.view = view;
        this.holder = holder;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long tagetTime = 1000 / FPS;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.holder.lockCanvas();
                synchronized (holder) {
                    this.view.update();
                    this.view.draw(canvas);

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        holder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = tagetTime - timeMillis;


            try {
                if (waitTime < 0)
                    waitTime = 0;
                this.sleep(waitTime);
            } catch (Exception e) {
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == FPS) {
                avgFPS = 100 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println("Average FPS: " + avgFPS);
            }
        }
    }

    public GameView getView() {
        return view;
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
