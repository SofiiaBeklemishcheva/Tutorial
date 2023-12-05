package com.zetcode;


import java.awt.*;

public class Ball {

    private final int WIDTH = 20;
    private final int HEIGHT = 20;
    private Graphics2D g2d = null;

    private Color ballColor = new Color(0, 0, 0);

    private int ballPositionX = 5;
    private int ballSpeedX = 2;
    private int ballPositionY = 45;
    private int ballSpeedY = 2;

    public void setBallColor(Color color) {
        ballColor = color;
    }

    public void setG2d(Graphics2D graphics2D) {
        g2d = graphics2D;
    }

    private int lastHitI = -1;
    private int lastHitJ = -1;

    public void calculatePosition(Tile[][] tiles) {
        boolean swappedX = false;
        boolean swappedY = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!swappedX && !swappedY && (i != lastHitI || j != lastHitJ)) {
                    if (collisionCheckX(tiles[i][j])) {
                        swappedX = true;
                        lastHitI = i;
                        lastHitJ = j;
                        tiles[i][j].setState(Tile.State.TRANSFORMING);
                        tiles[i][j].setNewColor(Tile.COLOR_BLUE);
                    }

                    if (collisionCheckY(tiles[i][j])) {
                        swappedY = true;
                        lastHitI = i;
                        lastHitJ = j;
                        tiles[i][j].setState(Tile.State.TRANSFORMING);
                        tiles[i][j].setNewColor(Tile.COLOR_BLUE);
                    }
                }
            }
        }

        if (ballPositionX > 355 - WIDTH || ballPositionX < 0 || swappedX) {
            ballSpeedX = -ballSpeedX;
        }

        if (ballPositionY > 355 - WIDTH || ballPositionY < 0 || swappedY) {
            ballSpeedY = -ballSpeedY;
        }

        if (ballPositionX > 355 - WIDTH || ballPositionX < 0 || ballPositionY > 355 - WIDTH || ballPositionY < 0) {
            lastHitI = -1;
            lastHitJ = -1;
        }

        ballPositionX += ballSpeedX;
        ballPositionY += ballSpeedY;
    }

    public void draw() {
        g2d.setPaint(ballColor);

        g2d.fillOval(ballPositionX, ballPositionY, WIDTH, HEIGHT);
    }

    public boolean collisionCheck(Tile tile) {
        if (((ballPositionX <= tile.getTilePositionX() + tile.WIDTH && ballPositionX >= tile.getTilePositionX() ||
                ballPositionX + WIDTH >= tile.getTilePositionX() && ballPositionX <= tile.getTilePositionX()) &&
                (ballPositionY <= tile.getTilePositionY() + tile.WIDTH && ballPositionY >= tile.getTilePositionY() ||
                        ballPositionY + WIDTH >= tile.getTilePositionY() && ballPositionY <= tile.getTilePositionY())) &&
                tile.getColorSymbol() == 'G') {
            System.out.println("Collision");
            return true;
        }

        return false;
    }

    public boolean collisionCheckXLeft(Tile tile) {
        if ((tile.getTilePositionX() < ballPositionX + WIDTH && tile.getTilePositionX() > ballPositionX) &&
                (tile.getTilePositionY() < ballPositionY + HEIGHT / 2 && tile.getTilePositionY() + tile.HEIGHT > ballPositionY + HEIGHT / 2) &&
                tile.getColorSymbol() == 'G') {
            System.out.println("Left");
            return true;
        }
        return false;
    }

    public boolean collisionCheckXRight(Tile tile) {
        if ((tile.getTilePositionX() + tile.WIDTH < ballPositionX + WIDTH && tile.getTilePositionX() + tile.WIDTH > ballPositionX) &&
                (tile.getTilePositionY() < ballPositionY + HEIGHT / 2 && tile.getTilePositionY() + tile.HEIGHT > ballPositionY + HEIGHT / 2) &&
                tile.getColorSymbol() == 'G') {
            System.out.println("Right");
            return true;
        }
        return false;
    }

    public boolean collisionCheckX(Tile tile) {
        return collisionCheckXLeft(tile) || collisionCheckXRight(tile);
    }

    public boolean collisionCheckYTop(Tile tile) {
//        System.out.print("==== ");
//        System.out.println(ballPositionY + HEIGHT);
        if ((tile.getTilePositionY() < ballPositionY + HEIGHT && tile.getTilePositionY() > ballPositionY) &&
                (tile.getTilePositionX() < ballPositionX + WIDTH / 2 && tile.getTilePositionX() + tile.WIDTH > ballPositionX + WIDTH / 2) &&
                tile.getColorSymbol() == 'G') {
            System.out.println("Top ");
//            System.out.print(tile.getTilePositionY());
//            System.out.print(" ");
//            System.out.println(ballPositionY + HEIGHT);
            return true;
        }
        return false;
    }

    public boolean collisionCheckYBottom(Tile tile) {
        if ((tile.getTilePositionY() + tile.HEIGHT < ballPositionY + HEIGHT && tile.getTilePositionY() + tile.HEIGHT > ballPositionY) &&
                (tile.getTilePositionX() < ballPositionX + WIDTH / 2 && tile.getTilePositionX() + tile.WIDTH > ballPositionX + WIDTH / 2) &&
                tile.getColorSymbol() == 'G') {
            System.out.println("Bottom ");
//            System.out.print(tile.getTilePositionY() + tile.HEIGHT);
//            System.out.print(" ");
//            System.out.println(ballPositionY);
            return true;
        }
        return false;
    }

    public boolean collisionCheckY(Tile tile) {
        return collisionCheckYTop(tile) || collisionCheckYBottom(tile);
    }


    public void figuresPosition(int setTilePositionX, int setTilePositionY) {
        int tileEdgePositionX1 = 0;
        int tileEdgePositionY1 = 0;

        int tileEdgePositionX2 = 0;
        int tileEdgePositionY2 = -40;

        int tileEdgePositionX3 = 40;
        int tileEdgePositionY3 = 0;

        int tileEdgePositionX4 = 40;
        int tileEdgePositionY4 = -40;

        int tileEdge1 = (tileEdgePositionY1 - tileEdgePositionY3) / (tileEdgePositionX1 - tileEdgePositionX3) + (tileEdgePositionY1 - (((tileEdgePositionY1 - tileEdgePositionY3) / (tileEdgePositionX1 - tileEdgePositionX3)) * tileEdgePositionX1));
        int tileEdge2 = (tileEdgePositionY1 - tileEdgePositionY2) / (tileEdgePositionX1 - tileEdgePositionX2) + (tileEdgePositionY1 - (((tileEdgePositionY1 - tileEdgePositionY2) / (tileEdgePositionX1 - tileEdgePositionX2)) * tileEdgePositionX1));
        int tileEdge3 = (tileEdgePositionY2 - tileEdgePositionY4) / (tileEdgePositionX2 - tileEdgePositionX4) + (tileEdgePositionY2 - (((tileEdgePositionY2 - tileEdgePositionY4) / (tileEdgePositionX2 - tileEdgePositionX4)) * tileEdgePositionX2));
        int tileEdge4 = (tileEdgePositionY3 - tileEdgePositionY4) / (tileEdgePositionX3 - tileEdgePositionX4) + (tileEdgePositionY3 - (((tileEdgePositionY3 - tileEdgePositionY4) / (tileEdgePositionX3 - tileEdgePositionX4)) * tileEdgePositionX3));

        int ballR = (int) Math.sqrt((ballPositionX - 20) ^ 2 + (ballPositionY - 20) ^ 2);

    }


}
