package com.zetcode;
import java.awt.*;

public class Tile {

 public enum State {
ACTIVE, TRANSFORMING, INACTIVE
    }
    private State state;
    public final int WIDTH = 40;
    public final int HEIGHT = 40;
    private int positionX;
    private int positionY;
    private Color color = new Color(0, 0, 0);
    public  static final Color COLOR_RED = new Color(150, 0, 0)  ;
    public  static final Color COLOR_GREEN = new Color(0, 150, 0) ;
    public  static final Color COLOR_BLUE = new Color(0, 0, 150)  ;
    public  static final Color COLOR_YELLOW = new Color(229, 226, 94)  ;


    private char colorSymbol;
  private Color newColor;

    private Graphics2D g2d = null;

    public Tile (int tilePositionX, int tilePositionY, char tileColor){
        positionX = tilePositionX;
        positionY = tilePositionY;
        colorSymbol=tileColor;
        setTileColor(tileColor);
        if (tileColor == 'G'){
            state = State.ACTIVE;
        }
        else{
            state = State.INACTIVE;
        }
    }
    public void setTileColor(char tileConfigChar) {
        switch (tileConfigChar) {
            case 'R' -> color=COLOR_RED;
            case 'G' -> color=COLOR_GREEN;
            case 'B' -> color=COLOR_BLUE;
            case 'Y' -> color=COLOR_YELLOW;
        }
    }


    public void setState (State tileState){
        state=tileState ;
    }
    public void setNewColor (Color tileColor){
        newColor= tileColor;
    }
    public void changeColor (){

        if(state==State.TRANSFORMING){
            if(color.getRed()>newColor.getRed()){
                color = new Color (color.getRed()-1,color.getGreen(),color.getBlue());
            } else if (color.getRed()<newColor.getRed()) {
                color = new Color (color.getRed()+1,color.getGreen(),color.getBlue());
            }
            if(color.getGreen()>newColor.getGreen()){
                color = new Color (color.getRed(),color.getGreen()-1,color.getBlue());
            } else if (color.getGreen()<newColor.getGreen()) {
                color = new Color (color.getRed(),color.getGreen()+1,color.getBlue());
            }
            if(color.getBlue()>newColor.getBlue()){
                color = new Color (color.getRed(),color.getGreen(),color.getBlue()-1);
            } else if (color.getBlue()<newColor.getBlue()) {
                color = new Color (color.getRed(),color.getGreen(),color.getBlue()+1);
            }
            if(color.getRed()==newColor.getRed()&&color.getBlue()==newColor.getBlue()&&color.getGreen()==newColor.getGreen() ){
                state = State.INACTIVE;
            }
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
