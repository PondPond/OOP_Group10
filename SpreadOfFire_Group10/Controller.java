import java.net.URI;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller extends JPanel
{

    private Simulation simulation;
    private Grid myGrid;
    private JLabel setting;
    private JLabel probCatchText;
    private JLabel probTreeText;
    private JLabel probBurningText1;
    private JLabel probBurningText2;
    private JLabel probCatchVal;
    private JLabel probTreeVal;
    private JLabel probBurnVal;
    private JButton autoButton;
    private JButton stepButton;
    private JButton resetButton;
    private JButton linkButton;
    private JButton showValueButton;
    private JSlider probCatchSlider;
    private JSlider probTreeSlider;
    private JSlider probBurningSlider;
    private JPanel buttonArea1;
    private JPanel buttonArea2;
    private boolean Click;

    public Controller(){
        Click = false;
        buttonArea1 = new JPanel();
        buttonArea1.setLayout(new GridLayout(2,2,5,5));
        buttonArea2 = new JPanel();
        buttonArea2.setLayout(new FlowLayout());
        setLayout(new GridLayout());
        myGrid = new Grid();
        setting = new JLabel("Spread fire simulation");
        buttonArea2.add(setting);       
        simulation = new Simulation(myGrid, 23, 0.1, 1.0, 0.0);
        simulation.initForest();
        myGrid.add(buttonArea2);
        linkButton();
        myGrid.setShowValue(false);
        addAutoButton();
        addStepButton();
        addResetButton();
        showValueButton();
        myGrid.add(buttonArea1);
        addProbCatch();
        addProbTree();
        addProbBurning();
        add(myGrid);

      
    }

    private void linkButton(){
        linkButton = new JButton();
        linkButton.setText("help?");
        linkButton.setCursor(Cursor.getPredefinedCursor(12));
        linkButton.setRolloverEnabled(true);
        linkButton.setHorizontalAlignment(SwingConstants.RIGHT);
        linkButton.setForeground(Color.BLUE);
        linkButton.setOpaque(false);
        linkButton.setContentAreaFilled(false);
        linkButton.setBorderPainted(false);
        buttonArea2.add(linkButton);

        linkButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    Click = true;
                    if(Desktop.isDesktopSupported()){
                        Desktop desktop = Desktop.getDesktop();
                        if(Click){
                            linkButton.setForeground(Color.RED);
                            Click = false;

                        }else{
                            linkButton.setForeground(Color.BLUE);
                        }

                        try {

                            String url ="https://github.com/PondPond/OOP_Group10/wiki/Manual";

                            Desktop dt = Desktop.getDesktop();
                            URI uri = new URI(url);
                            dt.browse(uri.resolve(uri));

                        } catch (Exception ex) {
                        }
                    }                        else{
                    }
                }
            });

    }

    public void onClick(boolean click){
        this.Click = click;

    }

    private void addAutoButton(){
        //Create auto button
        autoButton=new JButton("Auto");
        //Add the button to the myPanel
        buttonArea1.add(autoButton);

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
        buttonArea1.add(stepButton);

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
        buttonArea1.add(resetButton);
        resetButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    simulation = new Simulation(myGrid,simulation.getNumTree(),simulation.getProbCatch(),simulation.getProbTree(),simulation.getProbBurning());
                    simulation.initForest();
                    myGrid.setStep(0);
                    myGrid.setShowValue(false);
                }
            });

    }
    
     private void showValueButton(){
        showValueButton = new JButton("Show Value");
        buttonArea1.add(showValueButton);
        showValueButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    myGrid.setShowValue(true);
                    myGrid.repaint();

                }
            });
    }

    private void addProbCatch(){
        probCatchText = new JLabel("Identify probability that the tree will catch fire");
        myGrid.add (probCatchText);
        probCatchVal = new JLabel("probCatch : 0.0%");
        myGrid.add (probCatchVal);
        probCatchSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        probCatchSlider.setCursor(Cursor.getPredefinedCursor(12));
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
        probTreeVal = new JLabel("probTree : 100.0%");
        myGrid.add (probTreeVal);
        probTreeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        probTreeSlider.setCursor(Cursor.getPredefinedCursor(12));

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
        probBurnVal = new JLabel("probBurning : 0.0%");
        myGrid.add (probBurnVal);
        probBurningSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        probBurningSlider.setCursor(Cursor.getPredefinedCursor(12));

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
}
