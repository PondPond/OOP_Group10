import java.awt.*;
import javax.swing.*;
/**
 * Write a description of class DrawBlock here.
 * 
 * @author (your name) 
 * @version 17/10/2014
 */
public class Grid extends JPanel
{ 
    private Cell cell[][];
    private int cellSize = 20;

    public Grid(){
        cell = new Cell[20][20];
        for(int i = 0 ; i<cell.length ; i++){
            for(int j = 0 ; j<cell.length ;j++){
                cell[i][j] = new Cell();
            }
        }
    }

    public void Update(Cell cell[][]){
        this.cell = cell;
        repaint();
    }

    public void paintComponent(Graphics g){ 
        super.paintComponent(g);
        for(int i=0; i<20; i++){
            for(int j=0 ; j<20; j++){
                if(cell[i][j]!=null){
                    int x = (i+1)*cellSize;
                    int y = (j+1)*cellSize;
                    if(cell[i][j].getState() == Cell.EMPTY){
                        g.setColor(Color.YELLOW);
                    }

                    else if(cell[i][j].getState() == Cell.TREE){
                        g.setColor(Color.GREEN);
                    }
                    else{
                        g.setColor(Color.RED);
                    }
                    g.fillRect(x+2,y+2,cellSize-2,cellSize-2);
                }
            }
        }
    }

}
