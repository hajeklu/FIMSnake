package cz.uhk.fimsnake.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.util.Random;

import cz.uhk.fimsnake.activity.services.NetworkService;
import cz.uhk.fimsnake.activity.services.NotifiService;
import cz.uhk.fimsnake.dao.CacheFactory;
import cz.uhk.fimsnake.dao.IDAO;
import cz.uhk.fimsnake.model.tiles.BonusTile;
import cz.uhk.fimsnake.model.tiles.Tile;
import cz.uhk.fimsnake.view.GameView;

/**
 * Created by Luboš Hájek in 2019
 */
public class GameCanvas {

    public static int TILESIZE = 40;
    private static GameCanvas gameCanvas;
    private Canvas canvas;
    private Snake snake;
    private UISnake uiSnake;
    private BonusTile bonus;
    private int width;
    private int height;
    private int wbezels;
    private int hbezels;
    private Paint paint = new Paint(Color.rgb(255, 255, 255));
    private static int TOTALSIZE = 50;
    private GameView gameView;

    public static GameCanvas getGameCanvas(Canvas canvas, GameView gameView) {
        if (gameCanvas == null)
            gameCanvas = new GameCanvas(canvas, gameView);
        gameCanvas.setCanvas(canvas);
        return gameCanvas;
    }

    private GameCanvas(Canvas canvas, GameView gameView) {
        this.canvas = canvas;
        this.gameView = gameView;
        paint.setAntiAlias(true);
        paint.setTextSize(40);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setLinearText(true);
        TILESIZE = canvas.getWidth() / TOTALSIZE;

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
        bonus = new BonusTile(wbezels + 20 * TILESIZE, hbezels + 20 * TILESIZE);
        this.snake = new Snake(wbezels, hbezels);
        this.uiSnake = new UISnake(width - TILESIZE + wbezels, height - TILESIZE + hbezels);
        uiSnake.setDirection(Direction.LEFT);

    }

    public void restart() {
        System.out.println("******************************Restart****************************");
        gameCanvas = new GameCanvas(canvas, gameView);
    }

    public void tickScene() {
        snake.tick();
        uiSnake.setNextDirectionAndTick(snake, bonus);
        wallWalk(snake.getHead());
        wallWalk(uiSnake.getHead());


        if (snake.isCollison(uiSnake)) {
            gameView.gameOver();

            System.out.println("HEAD: " + snake.getHead().getX() + ",  " + snake.getHead().getY());
            for (Tile tile : snake.getSnakeTiles()) {
                System.out.println("Snake tile: x " + tile.getX() + " y " + tile.getY());
            }

            for (Tile tile : uiSnake.getSnakeTiles()) {
                System.out.println("uiSnake tile: x " + tile.getX() + " y " + tile.getY());
            }

        }
        if (uiSnake.isCollison(snake)) {
            gameView.gameOver();
        }

        if (isEat(bonus, snake)) {
            snake.eatingBonus();
            generateNewPosition(bonus);
        }

        if (isEat(bonus, uiSnake)) {
            uiSnake.eatingBonus();
            generateNewPosition(bonus);
        }
    }

    public void drawScene() {
        paint.setColor(Color.BLACK);
        canvas.drawRect(wbezels, hbezels, width + wbezels, height + hbezels, paint);

        bonus.draw(canvas);
        snake.draw(canvas);

        uiSnake.draw(canvas);
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


    private boolean isEat(Tile tile, Snake snake) {
        return tile.getX() == snake.getHead().getX() && tile.getY() == snake.getHead().getY();
    }

    private void generateNewPosition(Tile tile) {
        //a little magic
        Random r = new Random();
        int x = wbezels + (r.nextInt(((width / TILESIZE - 1) - 0) + 1) * TILESIZE);
        int y = hbezels + (r.nextInt(((height / TILESIZE - 1) - 0) + 1) * TILESIZE);
        System.out.println("Bonus X: " + x);
        System.out.println("Bonus Y: " + y);
        bonus = new BonusTile(x, y);
    }

    public boolean saveScore(IDAO databaseHelper) {
        Context context = gameView.getContext();
        if (NetworkService.getInstance().isInternetAvailable(context)) {
            boolean result = databaseHelper.addScoreToPlayer(snake.lenght);
            //  if (CacheFactory.getInstance().isNewRecord(snake.lenght)) {
            NotifiService.createNotificationNewRecord(context);
            //  }
            databaseHelper.invalidAndRestartCache(context);
            return result;
        } else {
            return false;
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
