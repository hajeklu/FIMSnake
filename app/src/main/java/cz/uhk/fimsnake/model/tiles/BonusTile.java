package cz.uhk.fimsnake.model.tiles;

import android.graphics.Canvas;
import android.graphics.Color;

import cz.uhk.fimsnake.R;
import cz.uhk.fimsnake.model.GameCanvas;
import cz.uhk.fimsnake.view.GameView;

/**
 * 
 * @author Hajek
 * Tile of bonus
 *
 */
public class BonusTile extends Tile {


	public BonusTile(int x, int y) {
		this.x = x;
		this.y = y;
		//imageMaker = ImageMaker.getInstance();
		paint.setColor(Color.rgb(255,140,0));
		drawable = GameView.gameContext.getResources().getDrawable(R.mipmap.bonus,null);
		drawable.setBounds(x, y, x + GameCanvas.TILESIZE, y + GameCanvas.TILESIZE);
	}

	@Override
	public void draw(Canvas g) {
		//g.drawRect(x, y, x+ GameCanvas.TILESIZE, y+GameCanvas.TILESIZE, paint);
		drawable.draw(g);
	}

}
