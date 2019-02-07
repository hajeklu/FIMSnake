package cz.uhk.fimsnake.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import cz.uhk.fimsnake.GameThread;

public class GameView extends View {

    int i = 10;
    private GameThread gameThread;
    private int FPS = 30;
    private double avgFPS;

    public GameView(Context context) {
        super(context);

    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(10);
        i++;
        canvas.drawCircle(i, 100, 100, paint);
        invalidate();
    }

    public void update() {

    }
}
