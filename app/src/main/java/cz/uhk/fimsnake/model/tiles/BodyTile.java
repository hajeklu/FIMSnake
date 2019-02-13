package cz.uhk.fimsnake.model.tiles;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import cz.uhk.fimsnake.R;
import cz.uhk.fimsnake.model.GameCanvas;
import cz.uhk.fimsnake.model.Snake;
import cz.uhk.fimsnake.view.GameView;

/**
 * @author Hájek Class body tile
 */
public class BodyTile extends Tile {

    private Snake snake;

    public BodyTile(int x, int y, Snake snake) {
        this.snake = snake;
        this.x = x;
        this.y = y;
        paint.setColor(Color.GREEN);

        drawable = GameView.gameContext.getResources().getDrawable(R.mipmap.body,null);
        drawable.setBounds(x, y, x + GameCanvas.TILESIZE, y + GameCanvas.TILESIZE);
    }

    /**
     * Can draw texture or square
     */
    @Override
    public void draw(Canvas g) {
        // texture or if can not read picture
        //	if (textures || !imageMaker.isReadyPicture()) {
   /*     g.drawRect(x, y, x + GameCanvas.TILESIZE, y + GameCanvas.TILESIZE, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        g.drawRect(x, y, x + GameCanvas.TILESIZE, y + GameCanvas.TILESIZE, paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
*/
        drawable.draw(g);

    }

}
