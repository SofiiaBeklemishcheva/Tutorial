package com.zetcode;


import java.awt.*;

public class Ball {

    private final int WIDTH = 40;
    private final int HEIGHT = 40;
    private Graphics2D g2d = null;

    private Color ballColor = new Color(0, 0, 0);

    private int ballPositionX = 5;
    private int ballSpeedX = 1;
    private int ballPositionY = 45;
    private int ballSpeedY = 1;

    public void setBallColor(Color color) {
        ballColor = color;
    }

    public void setG2d(Graphics2D graphics2D) {
        g2d = graphics2D;
    }

    public void calculatePosition(Tile [][] tiles) {
        boolean swappedX = false;
        boolean swappedY = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!swappedX){
                    if (collisionCheckX(tiles [i][j])) {
                        swappedX = true;
                    }
                }

                if (!swappedY){
                    if (collisionCheckY(tiles [i][j])) {
                        swappedY = true;
                    }
                }
            }
        }

        if (ballPositionX > 315 || ballPositionX < 0 || swappedX) {
            ballSpeedX = -ballSpeedX;
        }
        if (ballPositionY > 315 || ballPositionY < 0 || swappedY) {
            ballSpeedY = -ballSpeedY;
        }
        ballPositionX += ballSpeedX;
        ballPositionY += ballSpeedY;
    }

    public void draw() {
        g2d.setPaint(ballColor);

        g2d.fillOval(ballPositionX, ballPositionY, 40, 40);
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

    public boolean collisionCheckX(Tile tile) {
        if ((ballPositionX <= tile.getTilePositionX() + tile.WIDTH && ballPositionX >= tile.getTilePositionX() ||
                ballPositionX + WIDTH >= tile.getTilePositionX() && ballPositionX <= tile.getTilePositionX()) &&
                tile.getColorSymbol() == 'G') {
            return true;
        }

        return false;
    }
    public boolean collisionCheckY(Tile tile) {
        if ((ballPositionY <= tile.getTilePositionY() + tile.WIDTH && ballPositionY >= tile.getTilePositionY() ||
                        ballPositionY + WIDTH >= tile.getTilePositionY() && ballPositionY <= tile.getTilePositionY()) &&
                tile.getColorSymbol() == 'G') {
            return true;
        }

        return false;
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
