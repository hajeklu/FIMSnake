package cz.uhk.fimsnake.model;

import android.graphics.Canvas;

import cz.uhk.fimsnake.model.tiles.Tile;

/**
 * Class onlo for collision
 */
public class CollisionTile extends Tile {

	public CollisionTile(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(Canvas g) {
		// tile whitout draw... only abstract on Collision
	}

}
