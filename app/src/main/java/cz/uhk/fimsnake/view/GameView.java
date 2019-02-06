package cz.uhk.fimsnake.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import cz.uhk.fimsnake.R;

public class GameView extends View {

    private Bitmap bmp;

    public GameView(Context context) {
        super(context);
        bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
//        canvas.drawBitmap(bmp,10,10,null);
    }
}
