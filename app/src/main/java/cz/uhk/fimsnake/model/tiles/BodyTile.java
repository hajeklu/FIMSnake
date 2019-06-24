package cz.uhk.fimsnake.model.tiles;

import android.graphics.Canvas;
import android.graphics.Color;

import cz.uhk.fimsnake.R;
import cz.uhk.fimsnake.activity.services.PreferencesService;
import cz.uhk.fimsnake.model.GameCanvas;
import cz.uhk.fimsnake.model.Snake;
import cz.uhk.fimsnake.view.GameView;

/**
 * Created by Luboš Hájek in 2019
 */
public class BodyTile extends Tile {

    private Snake snake;
    private boolean textures;

    public BodyTile(int x, int y, Snake snake) {
        this.snake = snake;
        this.x = x;
        this.y = y;
        paint.setColor(Color.GREEN);
        drawable = GameView.gameContext.getResources().getDrawable(R.mipmap.body, null);
        drawable.setBounds(x, y, x + GameCanvas.TILESIZE, y + GameCanvas.TILESIZE);
        textures = PreferencesService.isTextures();
    }

    /**
     * Can draw texture or square
     */
    @Override
    public void draw(Canvas g) {
        if (textures) {
            drawable.draw(g);
        } else {
            paint.setColor(Color.GREEN);
            g.drawRect(x, y, x + GameCanvas.TILESIZE, y + GameCanvas.TILESIZE, paint);
        }

    }

}
