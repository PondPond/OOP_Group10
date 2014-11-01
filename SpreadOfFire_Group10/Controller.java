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
        //Create auto button
        autoButton=new JButton("Auto");
        //Add the button to the myPanel
        myGrid.add(autoButton);

        autoButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    Thread t = new Thread(new Runnable() {
                                public void run(){
                                    try{
                                        simulation.run();
                                    }catch(Exception e){

                                    }
                                }
                            });
                    t.start();
                }
            });
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
        //Create reset button
        resetButton=new JButton("Reset");
        //Add the button to the myPanel
        myGrid.add(resetButton);
        resetButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    simulation = new Simulation(myGrid,simulation.getNumTree(),simulation.getProbCatch(),simulation.getProbTree(),simulation.getProbBurning());
                    simulation.initForest();
                    myGrid.setStep(0);
                    myGrid.setShowVal(false);
                }
            });

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
     private void addProbTree(){
        probTreeText = new JLabel("Identify density of tree in forest");
        myGrid.add (probTreeText);
        probTreeVal = new JLabel("probTree :");
        myGrid.add (probTreeVal);
        probTreeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);

        probTreeSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    if (probTreeSlider.getValueIsAdjusting()) {
                        probTreeVal.setText("ProbTree : " + (double)probTreeSlider.getValue() + " %");
                        simulation.setProbTree((double) probTreeSlider.getValue() / 100);
                    }
                }
            }
        );
        myGrid.add(probTreeSlider);
    }
    
      private void addProbBurning(){
        probBurningText1 = new JLabel("Identify density of tree in forest");
        probBurningText2 = new JLabel("when the simulation begins");
        myGrid.add (probBurningText1);
        myGrid.add (probBurningText2);
        probBurnVal = new JLabel("probBurning :");
        myGrid.add (probBurnVal);
        probBurningSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);

        probBurningSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    if ( probBurningSlider.getValueIsAdjusting()) {
                        probBurnVal.setText("ProbBurning : " + (double) probBurningSlider.getValue() + " %");
                        simulation.setProbBurning((double) probBurningSlider.getValue() / 100);
                    }
                }
            }
        );
        myGrid.add(probBurningSlider);
    }
     private void showValueButton(){
        valueButton = new JButton("show value");
        myGrid.add(valueButton);
        valueButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    myGrid.setShowVal(true);
                    myGrid.repaint();
                    
                }
            });
    }
}
