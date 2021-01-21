package FrogGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Клас, в който има методи за визуализиране на дъската както и инициализиране на масив от обекти.
 */
public class GameBoard extends JFrame implements MouseListener {

        public Object[][] figureCollection;
        public Object selectedFigure;
        public static int oldRow;
        public static int oldCol;

        public GameBoard() {

            this.setSize(500, 500);
            this.setVisible(true);
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.addMouseListener(this);

            this.figureCollection = new Object[5][5];

            this.figureCollection[0][0] = (new Figures(0,0, Color.YELLOW, false));
            this.figureCollection[0][1] = (new Figures(0,1, Color.YELLOW, false));
            this.figureCollection[0][2] = (new Figures(0,2, Color.YELLOW, false));
            this.figureCollection[0][3] = (new Figures(0,3, Color.YELLOW, false));
            this.figureCollection[0][4] = (new Figures(0,4, Color.YELLOW, true));

            this.figureCollection[4][0] = (new Figures(4,0, Color.GREEN, true));
            this.figureCollection[4][1] = (new Figures(4,1, Color.GREEN, false));
            this.figureCollection[4][2] = (new Figures(4,2, Color.GREEN, false));
            this.figureCollection[4][3] = (new Figures(4,3, Color.GREEN, false));
            this.figureCollection[4][4] = (new Figures(4,4, Color.GREEN, false));

        }

        /**
        *  Метод, който прихваща натискане на бутона на мишката.
        */
        @Override
        public void mouseClicked(MouseEvent e) {

            int row = this.getDimensionsBasedOnCoordinates(e.getY());
            int col = this.getDimensionsBasedOnCoordinates(e.getX());

            if(this.isThereFigure(row, col) && this.selectedFigure == null) {
                this.selectedFigure = this.getFigure(row, col);
                oldRow = row;
                oldCol = col;
            }
            else if(this.selectedFigure != null && this.isThereFigure(row,col)) {
                JOptionPane.showMessageDialog(null,"Вече има фигура на тази позиция, изберете друга!",
                        "Невалиден ход", JOptionPane.WARNING_MESSAGE);
            }
            else if (this.selectedFigure != null) {
                Figures fig = (Figures)this.selectedFigure;
                boolean isLeader = fig.isLeader();
                moveFigures(row,col,isLeader);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        /**
        *  Метод, който ни връща обект от колекцията по зададени ред и колона.
        */
        public Object getFigure(int row, int col) {
            return this.figureCollection[row][col];
        }

        /**
        *  Метод, който ни връща дали на дадени ред и колона има фигура.
        */
        public boolean isThereFigure(int row, int col) {
            return this.getFigure(row, col) != null;
        }

        /**
        *  Метод, чрез който визуализираме фигурите по дадени ред и колона.
        */
        private void renderFigures(Graphics g, int row, int col) {
            if(this.isThereFigure(row,col)) {
                Figures fig = (Figures)this.getFigure(row,col);
                fig.render(g);
            }
        }

        /**
        *  Метод, чрез който визуализираме полетата по дадени ред и колона.
        */
        private void renderTiles(Graphics g, int row, int col) {
            Tiles tile = new Tiles(row, col);
            tile.renderBoard(g);
        }

        private int getDimensionsBasedOnCoordinates(int coordinates) {
            return coordinates / Tiles.TILE_SIZE;
        }

        /**
        *  Метод, чрез който изчертаваме игралната дъска и нейните елементи.
        */
        @Override
        public void paint(Graphics g) {

            for(int row = 0; row < 5; row++) {
                for(int col = 0; col < 5; col++) {

                    this.renderTiles(g,row,col);
                    this.renderFigures(g,row,col);

                }
            }
        }

        /** Метод, чрез който проверяваме какъв вид фигура искаме да придвижим и чрез който задаваме правилата на движение
        *  на Гардовете и Лидерите
        *
        * @param row  ред от дъската
        * @param col колона от дъската
        * @param isLeader атрибут на фигрурите, чрез който ги идентифицираме
        */
        public void moveFigures(int row, int col, boolean isLeader) {

            if (!isLeader) {
                if(row == oldRow + 1 && col == oldCol) {
                    Figures fig = (Figures) this.selectedFigure;
                    fig.move(row, col);
                    updateGameboard(row, col);
                } else if(row == oldRow - 1 && col == oldCol) {
                    Figures fig = (Figures)this.selectedFigure;
                    fig.move(row, col);
                    updateGameboard(row, col);
                } else if(row == oldRow && col == oldCol + 1) {
                    Figures fig = (Figures)this.selectedFigure;
                    fig.move(row, col);
                    updateGameboard(row, col);
                } else if(row == oldRow && col == oldCol - 1) {
                    Figures fig = (Figures)this.selectedFigure;
                    fig.move(row, col);
                    updateGameboard(row, col);
                } else {
                    JOptionPane.showMessageDialog(null,"Невъзможен ход, изберете друга позиция!",
                                                  "Грешка", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                moveLeaders(row,col);
            }
        }

        /**
        * Метод, чрез който задаваме правилата за движение на Лидерите.
        * ( в този случай най-близкото до обявените правила )
        */
        public void moveLeaders(int row, int col) {
            if(row > oldRow && col == oldCol || row < oldRow && col == oldCol) {
                Figures fig = (Figures)this.selectedFigure;
                fig.move(row, col);
                updateGameboard(row, col);
            } else if (row == oldRow && col > oldCol || row == oldRow && col < oldCol) {
                Figures fig = (Figures)this.selectedFigure;
                fig.move(row, col);
                updateGameboard(row, col);
            } else {
                JOptionPane.showMessageDialog(null,"Невъзможен ход, изберете друга позиция!",
                        "Грешка", JOptionPane.WARNING_MESSAGE);
            }
        }

        /**
        *  Метод, чрез който опресняваме позицията и визуализацията на фигурите на дъската.
        */
        public void updateGameboard(int row, int col) {
            this.figureCollection[row][col] = this.selectedFigure;
            this.figureCollection[oldRow][oldCol] = null;
            this.selectedFigure = null;
            this.repaint();
        }
    }

