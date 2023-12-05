package com.zetcode;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


class Surface extends JPanel implements ActionListener {

    private final int DELAY = 10;
    private Timer timer;
    private char[][] boardConfig;
    private final Ball ball;
    private Tile[][] tiles;

    public Surface() {
        try {
            boardConfig = getBoard();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        initTimer();
        ball = new Ball();
        initTiles();


    }

    private void initTiles() {
        tiles = new Tile[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j] = new Tile(i * 45, j * 45, boardConfig[j][i]);

            }
        }
    }


    private void initTimer() {

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public Timer getTimer() {

        return timer;
    }


    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        ball.setG2d(g2d);


        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j].setG2d(g2d);
                tiles[i][j].changeColor();
                tiles[i][j].draw();
            }
        }


        ball.setBallColor(new Color(150, 0, 150));
        ball.calculatePosition(tiles);
        ball.draw();

    }

    private char[][] getBoard() throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader("./board.txt")));
        int rows = 8;
        int columns = 8;
        char[][] myArray = new char[rows][columns];
        int j = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            for (int i = 0; i < line.length(); i++) {
                myArray[j][i] = line.charAt(i);
            }
            j++;
        }
        System.out.println(Arrays.deepToString(myArray));
        return myArray;
    }


    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}

public class BasicShapesEx extends JFrame {

    public BasicShapesEx() {

        initUI();
    }

    private void initUI() {

        add(new Surface());

        setTitle("Basic shapes");
        setSize(380, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                BasicShapesEx ex = new BasicShapesEx();
                ex.setVisible(true);
            }
        });
    }
}