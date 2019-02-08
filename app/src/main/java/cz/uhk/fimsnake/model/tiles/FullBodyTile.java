package cz.uhk.fimsnake.model.tiles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import cz.uhk.fimsnake.model.GameCanvas;
import cz.uhk.fimsnake.model.Snake;

/**
 * @author Hajek if snake eat bonus ... draw this tile
 */
public class FullBodyTile extends Tile {

    //	private ImageMaker imageMaker;
    private Snake snake;

    public FullBodyTile(int x, int y, Snake snake) {
        this.snake = snake;
        this.x = x;
        this.y = y;
        paint.setColor(Color.YELLOW);
        //imageMaker = ImageMaker.getInstance();
    }

    /**
     * Can draw texture or square
     */
    @Override
    public void draw(Canvas g) {
        // if can not read picture
        //	if (textures || !imageMaker.isReadyPicture()) {
        g.drawRect(x, y, x + GameCanvas.TILESIZE, y + GameCanvas.TILESIZE, paint);
/*
		} else {
			// chance color of snake
			if (snake.getSnakeTextures() == SnakeTextures.REDCIRCLE) {
				g.drawImage(imageMaker.getRedfullbody(), x, y, null);
			} else {
				g.drawImage(imageMaker.getFullbody(), x, y, null);
			}
		}*/
    }

}
