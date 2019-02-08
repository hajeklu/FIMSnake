package cz.uhk.fimsnake.model.tiles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import cz.uhk.fimsnake.model.Direction;
import cz.uhk.fimsnake.model.GameCanvas;
import cz.uhk.fimsnake.model.Snake;

/**
 * @author Hajek
 * Class to draw head by direction
 */
public class HeadTile extends Tile {

    //	private ImageMaker imageMaker;
    private Direction direction;
    private Snake snake;

    public HeadTile(int x, int y, Direction direction, Snake snake) {
        super(x, y);
        this.snake = snake;
        this.direction = direction;
        paint.setColor(Color.RED);
        //imageMaker = ImageMaker.getInstance();
    }

    @Override
    public void draw(Canvas g) {
        // texture or fillRect
        //if (textures || !imageMaker.isReadyPicture()) {
        g.drawRect(x, y, x + GameCanvas.TILESIZE, y + GameCanvas.TILESIZE, paint);
    } /*else {

			// copy code for fast run and better read code
			if (snake.getSnakeTextures() == SnakeTextures.REDCIRCLE) {
				// texture by direction to rotate head
				switch (direction) {
				case UP:
					g.drawImage(imageMaker.getRedupmouth(), x, y, null);
					break;
				case DOWN:
					g.drawImage(imageMaker.getReddownmouth(), x, y, null);
					break;
				case LEFT:
					g.drawImage(imageMaker.getRedleftmouth(), x, y, null);
					break;
				case RIGHT:
					g.drawImage(imageMaker.getRedrightmouth(), x, y, null);
					break;
				}
			} else {
				// change color of snake
				// texture by direction to rotate head
				switch (direction) {
				case UP:
					g.drawImage(imageMaker.getUpmouth(), x, y, null);
					break;
				case DOWN:
					g.drawImage(imageMaker.getDownmouth(), x, y, null);
					break;
				case LEFT:
					g.drawImage(imageMaker.getLeftmouth(), x, y, null);
					break;
				case RIGHT:
					g.drawImage(imageMaker.getRightmouth(), x, y, null);
					break;
				}
			}
		}
	}
*/

    /**
     * @return BodyTile make body from head
     */
    public BodyTile toBody() {
        return new BodyTile(x, y, snake);
    }

    /**
     * @return Tile make fullBody from head
     */
    public Tile toFullBody() {
        return new FullBodyTile(x, y, snake);
    }
}
