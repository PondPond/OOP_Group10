import java.awt.*;
import javax.swing.*;
/**
 * Write a description of class TestFrame here.
 * 
 * @author (Project Group 10) 
 * @version (21/10/2014)
 */
public class Frame
{
    JFrame frame;
    Controller c;
    Simulation simulation;
    Grid myGrid;
    public Frame(){
        frame = new JFrame("Spreading Fire");  
        frame.setSize(800,600);
        myGrid = new Grid();
        //frame.setLayout(new GridLayout(1,2));
        simulation = new Simulation(myGrid, 25, 1.0, 1.0, 0.0);
        c = new Controller(simulation,myGrid);
        frame.add(myGrid); 
        myGrid.setShowVal(true);
        frame.setVisible(true);
        
    }
    
    
    public static void main(String args[]){
        new Frame();
        
    }
}
