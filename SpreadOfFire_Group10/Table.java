

package gridtable;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author PondPond
 */
public class Table extends JPanel{
   private int cellSize;
   private int cellNum; //จำนวนcell
   private int a[][];
   
   public Table(){
       a= new int[20][20];
       cellSize = 20;
       cellNum = 31;     
   }
   
   public void paintComponent(Graphics g){ 
        super.paintComponent(g);
        for(int i=0; i<a.length-1; i++){
            for(int j=0 ; j<a.length-1; j++){
             g.fillRect((i+1)*cellSize,(j+1)*cellSize,cellSize-2,cellSize-2);
            
            }
            }   
   }
}
