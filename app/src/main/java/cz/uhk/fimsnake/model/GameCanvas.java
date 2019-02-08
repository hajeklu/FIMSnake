package cz.uhk.fimsnake.model;

import android.graphics.Canvas;

public class GameCanvas {

    public static int TILESIZE = 30;

    private Canvas canvas;
    private Snake snake;
    private int width;
    private int height;

    public GameCanvas(Canvas canvas, Snake snake) {
        this.canvas = canvas;
        this.snake = snake;

        width = canvas.getWidth();
        height = canvas.getHeight();

        TILESIZE = width / 20;
    }
}
