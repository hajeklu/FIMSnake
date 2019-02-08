package cz.uhk.fimsnake.model.tiles;

import android.graphics.Canvas;

/**
 * 
 * @author Hajek 
 * Interface of all tiles
 *
 */
public interface InterfaceTile {

	void draw(Canvas g);

	int getX();

	void setX(int x);

	int getY();

	void setY(int y);

	void setLocation(int x, int y);
}
