package me.gerald.goofy;

import me.gerald.goofy.entity.entities.BallEntity;
import me.gerald.goofy.entity.entities.EnemyEntity;
import me.gerald.goofy.entity.entities.PlayerEntity;
import me.gerald.goofy.listeners.MouseDragListener;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Goofy extends JPanel implements Runnable {
    //Screen setting stuff
    public final int screenWidth = 750;
    public final int screenHeight = 500;
    //Class stuff
    private final Goofy instance;
    //Game stuff
    Thread thread;
    //Player stuff
    PlayerEntity player = new PlayerEntity();
    MouseDragListener dragListener = new MouseDragListener(player);
    //Enemy stuff
    List<EnemyEntity> enemyEntityList = new LinkedList<>();
    //Ball stuff
    BallEntity ball = new BallEntity();

    public Goofy() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addMouseMotionListener(dragListener);
        this.setFocusable(true);
        this.instance = this;
        int amountInRow = 0;
        int collum = 0;
        for (int i = 0; i < 50; i++) {
            EnemyEntity enemy = new EnemyEntity(30 + (amountInRow * 70), 15 + (collum * 35));
            amountInRow++;
            if (amountInRow == 10) {
                collum++;
                amountInRow = 0;
            }
            enemyEntityList.add(enemy);
        }
        start();
    }

    @Override
    public void run() {
        int TARGET_FPS = 60;
        double drawInterval = 1000000000f / TARGET_FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while (thread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
            if (timer >= 1000000000) {
                timer = 0;
            }
        }
    }

    public void update() {
        if (ball.x <= 0) {
            ball.hVelocity = 0.5f;
        } else if (ball.x + ball.getWidth() >= screenWidth) {
            ball.hVelocity = -0.5f;
        } else if (ball.y <= 0) {
            ball.vVelocity = 0.5f;
        } else if ((ball.y + ball.getHeight()) >= screenHeight) {
            resetGame();
        }
        if (ball.x >= player.x
                && (ball.x + ball.getWidth()) <= (player.x + player.getWidth())
                && (ball.y + ball.getHeight()) >= player.y) {
            ball.vVelocity = -1;
        }
        for (EnemyEntity enemy : enemyEntityList) {
            if (!enemy.rendering) continue;
            if ((ball.x + ball.getWidth()) >= enemy.x
                    && (ball.x + ball.getWidth()) <= (enemy.x + enemy.getWidth())
                    && ball.y >= enemy.y
                    && ball.y <= enemy.y + enemy.getHeight()) {
                enemy.rendering = false;
                Random random = new Random();
                float hVeloFloat = random.nextFloat(2) - 1;
                ball.vVelocity = ball.vVelocity * -1;
                ball.hVelocity = hVeloFloat;
            }
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawRect((int) player.x, (int) player.y, player.getWidth(), player.getHeight());
        ball.updateCoords();
        graphics2D.drawRect((int) ball.x, (int) ball.y, ball.getWidth(), ball.getHeight());
        for (EnemyEntity enemy : enemyEntityList) {
            if (enemy.rendering)
                graphics2D.drawRect((int) enemy.x, (int) enemy.y, enemy.getWidth(), enemy.getHeight());
        }
        graphics2D.dispose();
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    public void resetGame() {
        this.player = new PlayerEntity();
        this.removeMouseMotionListener(dragListener);
        this.dragListener = new MouseDragListener(player);
        this.addMouseMotionListener(dragListener);
        this.ball = new BallEntity();
        for (EnemyEntity enemy : enemyEntityList) {
            if (!enemy.rendering) enemy.rendering = true;
        }
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public Goofy getGoofyAhGame() {
        return instance;
    }
}
