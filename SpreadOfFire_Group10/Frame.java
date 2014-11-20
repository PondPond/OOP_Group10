import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
/**
 * Write a description of class TestFrame here.
 * 
 * @author (Project Group 10) 
 * @version (21/10/2014)
 */
public class Frame
{
    JFrame frame;
    Controller controller;
    Simulation simulation;
    Grid myGrid;

    public Frame(){
        frame = new JFrame("Spreading Fire"); 
        frame.setBackground(Color.BLACK);
        frame.setSize(810,600);      
        frame.setLayout(new GridLayout());
        controller = new Controller();
        frame.add(controller);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    public static void main(String args[])  {
        new Frame();

    }
}
