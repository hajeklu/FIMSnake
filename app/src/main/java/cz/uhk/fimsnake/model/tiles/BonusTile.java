package cz.uhk.fimsnake.model.tiles;

import android.graphics.Canvas;
import android.graphics.Color;

import cz.uhk.fimsnake.model.GameCanvas;

/**
 * 
 * @author Hajek
 * Tile of bonus
 *
 */
public class BonusTile extends Tile {

	//private ImageMaker imageMaker;

	public BonusTile(int x, int y) {
		this.x = x;
		this.y = y;
		//imageMaker = ImageMaker.getInstance();
		paint.setColor(Color.rgb(255,140,0));
	}

	@Override
	public void draw(Canvas g) {
		// texture or if can not read picture
//		if (textures || !imageMaker.isReadyPicture()) {
			g.drawRect(x, y, x+ GameCanvas.TILESIZE, y+GameCanvas.TILESIZE, paint);

	/*	} else {
			g.drawImage(imageMaker.getBonus(), x, y, null);
	//	}*/
	}

}
