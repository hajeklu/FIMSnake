package cz.uhk.fimsnake.model.tiles;

import android.graphics.Canvas;
import android.graphics.Color;

import cz.uhk.fimsnake.R;
import cz.uhk.fimsnake.activity.services.PreferencesService;
import cz.uhk.fimsnake.model.GameCanvas;
import cz.uhk.fimsnake.model.Snake;
import cz.uhk.fimsnake.view.GameView;

/**
 * @author Hajek if snake eat bonus ... draw this tile
 */
public class FullBodyTile extends Tile {

    private boolean textures;

    public FullBodyTile(int x, int y, Snake snake) {
        this.x = x;
        this.y = y;
        paint.setColor(Color.YELLOW);
        drawable = GameView.gameContext.getResources().getDrawable(R.mipmap.fullbody, null);
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
            paint.setColor(Color.CYAN);
            g.drawRect(x, y, x + GameCanvas.TILESIZE, y + GameCanvas.TILESIZE, paint);
        }

    }

}
