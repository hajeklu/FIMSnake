package cz.uhk.fimsnake.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import cz.uhk.fimsnake.model.tiles.Tile;

public class GameCanvas {

    public static int TILESIZE = 40;

    private static GameCanvas gameCanvas;

    private Canvas canvas;
    private Snake snake;
    private int width;
    private int height;
    private int wbezels;
    private int hbezels;

    public static GameCanvas getGameCanvas(Canvas canvas) {
        if (gameCanvas == null)
            gameCanvas = new GameCanvas(canvas);
        gameCanvas.setCanvas(canvas);
        return gameCanvas;
    }

    private GameCanvas(Canvas canvas) {
        this.canvas = canvas;

        TILESIZE = canvas.getWidth() / 40;


        for (int i = canvas.getHeight(); i % TILESIZE != 0; i--) {
            height = i - 1;
        }

        for (int i = canvas.getWidth(); i % TILESIZE != 0; i--) {
            width = i - 1;
        }

        wbezels = (canvas.getWidth() - width) / 2;
        hbezels = (canvas.getHeight() - height) / 2;

        System.out.println("Game board width: " + width);
        System.out.println("Game board height: " + height);
        System.out.println("Game board width bezel: " + wbezels);
        System.out.println("Game board height bezel: " + hbezels);
        System.out.println("TileSize:" + TILESIZE);

        this.snake = new Snake(wbezels, hbezels);

    }

    public void tickScene() {
        snake.tick();
        wallWalk(snake.head);
        System.out.println("X: " + snake.getHead().getX());
        System.out.println("Y: " + snake.getHead().getY());
    }

    public void drawScene() {
        Paint paint = new Paint(Color.BLACK);
        canvas.drawRect(wbezels, hbezels, width + wbezels, height + hbezels, paint);


        snake.draw(canvas);
    }

    private void wallWalk(Tile head) {
        if (head.getX() < wbezels) {
            head.setLocation(width + wbezels - TILESIZE, head.getY());
        } else if (head.getX() > width - wbezels) {
            head.setLocation(wbezels, head.getY());
        } else if (head.getY() < hbezels) {
            head.setLocation(head.getX(), height + hbezels - TILESIZE);
        } else if (head.getY() > height - hbezels) {
            head.setLocation(head.getX(), hbezels);
        }
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
