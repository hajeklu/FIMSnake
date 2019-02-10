package cz.uhk.fimsnake.model.tiles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import cz.uhk.fimsnake.model.GameCanvas;
import cz.uhk.fimsnake.model.Snake;

/**
 * @author Hájek Class body tile
 */
public class BodyTile extends Tile {

    //private ImageMaker imageMaker;
    private Snake snake;

    public BodyTile(int x, int y, Snake snake) {
        this.snake = snake;
        this.x = x;
        this.y = y;
        paint.setColor(Color.GREEN);
        //	imageMaker = ImageMaker.getInstance();
    }

    /**
     * Can draw texture or square
     */
    @Override
    public void draw(Canvas g) {
        // texture or if can not read picture
        //	if (textures || !imageMaker.isReadyPicture()) {
        g.drawRect(x, y, x + GameCanvas.TILESIZE, y + GameCanvas.TILESIZE, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        g.drawRect(x, y, x + GameCanvas.TILESIZE, y + GameCanvas.TILESIZE, paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);

	/*	} else {
			// change color of snake
			if (snake.getSnakeTextures() == SnakeTextures.REDCIRCLE) {
				g.drawImage(imageMaker.getRedbody(), x, y, null);
			} else {
				g.drawImage(imageMaker.getBody(), x, y, null);
			}
		}*/
    }

}
