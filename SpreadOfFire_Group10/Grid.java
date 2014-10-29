import java.awt.*;
import javax.swing.*;
/**
 * Write a description of class TestGrid here.
 * 
 * @author (Project Group 10)
 * @version (21/10/2014)
 */
public class Grid extends JPanel
{
    private Cell cell [][] ;
    private int cellSize;
    private boolean showVal;

    public Grid(){        
        cellSize = 15;
        setLayout(new FlowLayout(5, 650, 4));
        showVal = false;
    }

    public void Update(Cell cell[][]){
        this.cell = cell;
        repaint();
    }
    
      public int getStep(){
        return this.step;
    }

    public void setStep(){
        this.step = this.step+1;
    }

    public void setStep(int step){
        this.step = this.step;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0 ; i< cell.length;i++){
            for(int j = 0; j < cell.length ; j++ ){
                if(cell[i][j]!=null){
                    int x = (i+1)*20;
                    int y = (j+1)*20;
                    if(cell[i][j].getState() == Cell.EMPTY){
                        g.setColor(Color.YELLOW);
                    }
                    else if(cell[i][j].getState() == Cell.TREE){
                        g.setColor(Color.GREEN);
                    }
                    else{
                        g.setColor(Color.RED);
                    }
                    g.fillRect(x,y,20,20);
                    g.setColor(Color.BLACK);
                    if(showVal){

                        if (cell[i][j].getState() == Cell.EMPTY) {
                            g.drawString("0" , x+7 , y+14  );
                        } else if (cell[i][j].getState() == Cell.TREE) {
                            g.drawString("1" , x+7 , y+14 );
                        } else {
                            g.drawString("2" , x+7 , y+14 );
                        }

                        g.drawRect(x , y, 20, 20);
                    }

                }
            }
        }
    }

    public int getcellSize(){
        return cellSize;
    }

    public void setcellSize(int cellSize){
        this.cellSize = cellSize;
    }

    public void setShowVal(boolean showVal) {
        this.showVal = showVal;
    }

}
