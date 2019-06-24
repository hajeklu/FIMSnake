package cz.uhk.fimsnake.model.tiles;

import android.graphics.Canvas;
import android.graphics.Color;

import cz.uhk.fimsnake.R;
import cz.uhk.fimsnake.activity.services.PreferencesService;
import cz.uhk.fimsnake.model.Direction;
import cz.uhk.fimsnake.model.GameCanvas;
import cz.uhk.fimsnake.model.Snake;
import cz.uhk.fimsnake.view.GameView;

/**
 * @author Hajek
 * Class to draw head by direction
 */
public class HeadTile extends Tile {

    private Direction direction;
    private Snake snake;
    private boolean textures;

    public HeadTile(int x, int y, Direction direction, Snake snake) {
        super(x, y);
        this.snake = snake;
        this.direction = direction;
        paint.setColor(Color.RED);
        switch (direction) {
            case UP:
                drawable = GameView.gameContext.getResources().getDrawable(R.mipmap.uphead, null);
                break;
            case DOWN:
                drawable = GameView.gameContext.getResources().getDrawable(R.mipmap.downhead, null);
                break;
            case LEFT:
                drawable = GameView.gameContext.getResources().getDrawable(R.mipmap.lefthead, null);
                break;
            case RIGHT:
                drawable = GameView.gameContext.getResources().getDrawable(R.mipmap.righthead, null);
                break;
        }
        drawable.setBounds(x, y, x + GameCanvas.TILESIZE, y + GameCanvas.TILESIZE);

        textures = PreferencesService.isTextures();

    }

    @Override
    public void draw(Canvas g) {
        if (textures) {
            drawable.draw(g);
        } else {
            paint.setColor(Color.RED);
            g.drawRect(x, y, x + GameCanvas.TILESIZE, y + GameCanvas.TILESIZE, paint);
        }
    }


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
