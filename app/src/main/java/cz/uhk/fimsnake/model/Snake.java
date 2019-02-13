package cz.uhk.fimsnake.model;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import cz.uhk.fimsnake.model.tiles.BodyTile;
import cz.uhk.fimsnake.model.tiles.FullBodyTile;
import cz.uhk.fimsnake.model.tiles.HeadTile;
import cz.uhk.fimsnake.model.tiles.Tile;

/**
 * @author Hájek
 * Class of player snake
 */
public class Snake {
    protected List snakeTiles = Collections.synchronizedList(new ArrayList<>());
    protected HeadTile head;
    protected Direction direction = Direction.RIGHT;
    protected int lenght = 10;
    protected Tile lastTile;
    protected boolean eat = false;
    //protected SnakeTextures snakeTextures;

    // set start pozicion
    public Snake(int startW, int startH) {
        //snakeTextures = SnakeTextures.BLUECIRCLE;
        head = new HeadTile(startW, startH, direction, this);
        lastTile = new BodyTile(-1, -1, this);
    }

    // check collision whit other snake
    public boolean isCollison(Snake snake) {
        for (Tile tile : snake.getSnakeTiles()) {
            if (head.equals(tile)) {
                return true;
            }
        }
        if (head.equals(snake.getHead())) {
            return true;
        }
        return false;
    }

    // check collision whit himSelf
    public boolean isCollisonHimSelf() {
        List<Tile> snakeTiless = snakeTiles;
        for (Tile tile : snakeTiless) {
            if (head.equals(tile)) {
                return true;
            }
        }
        if (head.equals(lastTile)) {
            return true;
        }
        return false;
    }

    // add lenght
    public void eatingBonus() {
        eat = true;
    }

    /**
     * shift snake by direction, one tile add and one tile delete
     */
    public void tick() {
        // add one tile
        if (eat) {
            snakeTiles.add(head.toFullBody());
        } else {
            snakeTiles.add(head.toBody());
        }
        // add new head
        switch (direction) {
            case DOWN:
                head = new HeadTile(Math.round(head.getX()), (head.getY()) + GameCanvas.TILESIZE, direction, this);
                break;
            case UP:
                head = new HeadTile(head.getX(), head.getY() - GameCanvas.TILESIZE, direction, this);
                break;
            case LEFT:
                head = new HeadTile(head.getX() - GameCanvas.TILESIZE, head.getY(), direction, this);
                break;
            case RIGHT:
                head = new HeadTile(head.getX() + GameCanvas.TILESIZE, head.getY(), direction, this);
                break;
        }
        // delete one tile
        if (lenght < snakeTiles.size()) {
            // if remove fullBody add lenght
            if (snakeTiles.get(0) instanceof FullBodyTile) {
                lenght++;
            }
            lastTile = (Tile) snakeTiles.get(0);
            snakeTiles.remove(0);
        }
        eat = false;
    }

    public void drawBody(Canvas g) {
        synchronized (snakeTiles) {
            Iterator i = snakeTiles.iterator(); // Must be in synchronized block
            while (i.hasNext())
                ((Tile) i.next()).draw(g);
        }
    }

    public void draw(Canvas g) {
        drawBody(g);
        head.draw(g);
    }

    public void turnLeft() {
        switch (direction) {
            case UP:
                direction = Direction.LEFT;
                break;
            case DOWN:
                direction = Direction.RIGHT;
                break;
            case LEFT:
                direction = Direction.DOWN;
                break;
            case RIGHT:
                direction = Direction.UP;
                break;
        }
        System.out.println(direction);
    }

    public void turnRight() {
        switch (direction) {
            case UP:
                direction = Direction.RIGHT;
                break;
            case DOWN:
                direction = Direction.LEFT;
                break;
            case LEFT:
                direction = Direction.UP;
                break;
            case RIGHT:
                direction = Direction.DOWN;
                break;
        }
        System.out.println(direction);
    }

    public List<Tile> getSnakeTiles() {
        return snakeTiles;
    }

    public Tile getHead() {
        return head;
    }

    public void setHead(HeadTile head) {
        this.head = head;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public Tile getLastTile() {
        return lastTile;
    }

    public void setLastTile(Tile lastTile) {
        this.lastTile = lastTile;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
	/*
	public SnakeTextures getSnakeTextures() {
		return snakeTextures;
	}
	
	public void setTypOfSnake(SnakeTextures snakeTextures) {
		this.snakeTextures = snakeTextures;
	}*/
}
