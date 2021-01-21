package FrogGame;

import java.awt.*;

public class Tiles {

    private int row;
    private int col;
    public static final int TILE_SIZE = 100;

    /**
     *  Конструктор за полетата.
     */

    public Tiles (int row, int col) {

        this.row = row;
        this.col = col;
    }

    /**
     *  Метод, чрез който визуализираме и оцветяваме отделните полета на дъската.
     */

    public void renderBoard(Graphics g)  {

        int tileX = this.col * this.TILE_SIZE;
        int tileY = this.row * this.TILE_SIZE;

        Color nRed = new Color(254, 98, 70);
        Color nBlack = new Color(67,67,67);
        Color nGrey = new Color(170,170,170);

        if(this.row == 0 && this.col == 0 || this.row == 0 && this.col == 4 ||
           this.row == 4 && this.col == 1 || this.row == 4 && this.col == 3)
        {
            g.setColor(nRed);
        }

        if (this.row == 0 && this.col == 1 || this.row == 0 && this.col == 3 ||
            this.row == 4 && this.col == 0 || this.row == 4 && this.col == 4)
        {
            g.setColor(nBlack);
        }
        if (this.row == 1 && this.col == 0 || this.row == 1 && this.col == 1 ||
            this.row == 1 && this.col == 3 || this.row == 1 && this.col == 4 ||
            this.row == 3 && this.col == 0 || this.row == 3 && this.col == 1 ||
            this.row == 3 && this.col == 3 || this.row == 3 && this.col == 4)
        {
            g.setColor(nGrey);
        }

        if (this.row == 0 && this.col == 2 || this.row == 1 && this.col == 2 ||
            this.row == 2 && this.col == 0 || this.row == 2 && this.col == 1 ||
            this.row == 2 && this.col == 2 || this.row == 2 && this.col == 3 ||
            this.row == 2 && this.col == 4 || this.row == 3 && this.col == 2 ||
            this.row == 4 && this.col == 2)
        {
            g.setColor(Color.WHITE);
        }

        g.fillRect(tileX,tileY,TILE_SIZE, TILE_SIZE);
        drawBorders(g);

        g.setColor(nGrey);
        g.fillOval(225,225,50,50);
    }

    /**
     *  Метод, чрез който рисуваме границите на отделните полета.
     */
    public void drawBorders(Graphics g) {

        g.setColor(Color.black);
        g.drawRect(0,0,100,100);
        g.drawRect(100,0,100,100);
        g.drawRect(200,0,100,100);
        g.drawRect(300,0,100,100);
        g.drawRect(400,0,100,100);

        g.drawRect(0,100,100,100);
        g.drawRect(100,100,100,100);
        g.drawRect(200,100,100,100);
        g.drawRect(300,100,100,100);
        g.drawRect(400,100,100,100);

        g.drawRect(0,200,100,100);
        g.drawRect(100,200,100,100);
        g.drawRect(200,200,100,100);
        g.drawRect(300,200,100,100);
        g.drawRect(400,200,100,100);

        g.drawRect(0,300,100,100);
        g.drawRect(100,300,100,100);
        g.drawRect(200,300,100,100);
        g.drawRect(300,300,100,100);
        g.drawRect(400,300,100,100);

        g.drawRect(0,400,100,100);
        g.drawRect(100,400,100,100);
        g.drawRect(200,400,100,100);
        g.drawRect(300,400,100,100);
        g.drawRect(400,400,100,100);
    }

}