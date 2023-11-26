package com.zetcode;
import java.awt.*;

public class Tile {
    public final int WIDTH = 40;
    public final int HEIGHT = 40;
    private int positionX;
    private int positionY;
    private Color color = new Color(0, 0, 0);


    private char colorSymbol;

    private Graphics2D g2d = null;

    public Tile (int tilePositionX, int tilePositionY, char tileColor){
        positionX = tilePositionX;
        positionY = tilePositionY;
        colorSymbol=tileColor;
        setTileColor(tileColor);
    }
    public void setTileColor(char tileConfigChar) {
        switch (tileConfigChar) {
            case 'R' -> color=new Color(150, 0, 0);
            case 'G' -> color=new Color(0, 150, 0);
            case 'B' -> color=new Color(0, 0, 150);
            case 'Y' -> color=new Color(229, 226, 94);
        }
    }
    public void setG2d (Graphics2D graphics2D){
        g2d=graphics2D;
    }
    public void setTilePositionX(int tilePositionX){
        positionX = tilePositionX;
    }
    public void setTilePositionY(int tilePositionY){
        positionY = tilePositionY;
    }

    public int getTilePositionX (){
        return positionX;
    }
    public int getTilePositionY (){
        return positionY;
    }

    public char getColorSymbol (){
        return colorSymbol;
    }

    public void draw(){
        g2d.setPaint(color);

        g2d.fillRect(positionX, positionY, WIDTH, HEIGHT);
    }
}
