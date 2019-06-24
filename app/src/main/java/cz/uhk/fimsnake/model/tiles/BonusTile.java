package cz.uhk.fimsnake.model.tiles;

import android.graphics.Canvas;
import android.graphics.Color;

import cz.uhk.fimsnake.R;
import cz.uhk.fimsnake.activity.services.PreferencesService;
import cz.uhk.fimsnake.model.GameCanvas;
import cz.uhk.fimsnake.view.GameView;

/**
 * Created by Luboš Hájek in 2019
 */
public class BonusTile extends Tile {

    private boolean textures;

    public BonusTile(int x, int y) {
        this.x = x;
        this.y = y;
        paint.setColor(Color.rgb(255, 140, 0));
        drawable = GameView.gameContext.getResources().getDrawable(R.mipmap.bonus, null);
        drawable.setBounds(x, y, x + GameCanvas.TILESIZE, y + GameCanvas.TILESIZE);
        textures = PreferencesService.isTextures();
    }

    @Override
    public void draw(Canvas g) {
        if (textures) {
            drawable.draw(g);
        } else {
            paint.setColor(Color.MAGENTA);
            g.drawRect(x, y, x + GameCanvas.TILESIZE, y + GameCanvas.TILESIZE, paint);
        }
    }

}
