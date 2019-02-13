package cz.uhk.fimsnake.model.tiles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import cz.uhk.fimsnake.R;
import cz.uhk.fimsnake.model.GameCanvas;
import cz.uhk.fimsnake.model.Snake;
import cz.uhk.fimsnake.view.GameView;

/**
 * @author Hajek if snake eat bonus ... draw this tile
 */
public class FullBodyTile extends Tile {

    private Snake snake;

    public FullBodyTile(int x, int y, Snake snake) {
        this.snake = snake;
        this.x = x;
        this.y = y;
        paint.setColor(Color.YELLOW);
        drawable = GameView.gameContext.getResources().getDrawable(R.mipmap.fullbody,null);
        drawable.setBounds(x, y, x + GameCanvas.TILESIZE, y + GameCanvas.TILESIZE);
    }

    /**
     * Can draw texture or square
     */
    @Override
    public void draw(Canvas g) {

       // g.drawRect(x, y, x + GameCanvas.TILESIZE, y + GameCanvas.TILESIZE, paint);
        drawable.draw(g);
    }

}
