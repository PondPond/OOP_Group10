import java.awt.*;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller extends JPanel
{

    private Simulation simulation;
    private Grid myGrid;
    
    JButton autoButton;
    JButton stepButton;
    JButton resetButton;
    JButton valueButton;

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
    
    private void addAutoButton(){
        //Create auto button
        autoButton=new JButton("Auto");
        //Add the button to the myPanel
        myGrid.add(autoButton);
    }


    private void addStepButton(){
        //Create step button
        stepButton=new JButton("Step");
        //Add the button to the myPanel
        myGrid.add(stepButton);

        stepButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    simulation.spreadFire();
                    //System.out.println("Step : "+myGrid.getStep());
                }
            });
    }

    private void addResetButton(){
        //Create the button
        JButton resetButton=new JButton("Reset");
        //Add the button to the myPanel
        myGrid.add(resetButton);
    }
    
      private void addProbCatch(){
        probCatchText = new JLabel("Identify probability that the tree will catch fire");
        myGrid.add (probCatchText);
        probCatchVal = new JLabel("probCatch :");
        myGrid.add (probCatchVal);
        probCatchSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        probCatchSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    if (probCatchSlider.getValueIsAdjusting()) {
                        probCatchVal.setText("ProbCatch : " + (double)probCatchSlider.getValue() + " %");
                        simulation.setProbCatch((double) probCatchSlider.getValue() / 100);
                    }
                }
            }
        );
        myGrid.add(probCatchSlider);
    }
}
