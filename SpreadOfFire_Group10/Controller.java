import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Controller
{

    private Simulation simulation;
    private Grid myGrid;

    public Controller(Simulation simulation ,Grid myGrid){
        this.simulation= simulation;
        this.myGrid = myGrid;

        addAutoButton();
        addStepButton();
        addResetButton();
    }

    
    private void addAutoButton(){
        //Create the button
        JButton autoButton=new JButton("Auto");
        //Add the button to the myPanel
        myGrid.add(autoButton);
    }

    private void addStepButton(){
        //Create the button
        JButton stepButton=new JButton("Step");
        //Add the button to the myPanel
        myGrid.add(stepButton);
    }

    private void addResetButton(){
        //Create the button
        JButton resetButton=new JButton("Reset");
        //Add the button to the myPanel
        myGrid.add(resetButton);
    }
}
