import java.net.URI;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
/*
 * Controller class is consist of JButton,JLabel,
 * JSlider,JCheckbox and JComboBox
 * to control execution of program 
 */
public class Controller extends JPanel
{
    private Simulation simulation;
    private Grid myGrid;
    private JLabel setting;                                                                                                                          
    private JLabel probCatchVal;
    private JLabel probTreeVal;
    private JLabel probBurnVal;
    private JLabel probLightVal;
    private JLabel directionText;
    private JLabel windSpeedText;
    private JLabel setSizeText;
    private JButton autoButton;
    private JButton stepButton;
    private JButton resetButton;
    private JButton linkButton;
    private JButton directButton;
    private JButton showValueButton;
    private JCheckBox lightNing;
    private JCheckBox smallSize;
    private JCheckBox largeSize;
    private JSlider probCatchSlider;
    private JSlider probTreeSlider;
    private JSlider probBurningSlider;
    private JSlider probLightningSlider;
    private JSlider windSpeedSlider;
    private JPanel buttonArea1;
    private JPanel buttonArea2;
    private JPanel buttonArea3;
    private JPanel buttonArea4;
    private boolean Click, isLarge = true;
    /*
     * constructor - create controller of panel 
     */
    public Controller(){
        Click = false;
        buttonArea1 = new JPanel();
        buttonArea1.setLayout(new GridLayout(2,2,5,5));
        buttonArea2 = new JPanel();
        buttonArea2.setLayout(new FlowLayout());
        buttonArea3 = new JPanel();
        buttonArea3.setLayout(new FlowLayout());
        setLayout(new GridLayout());
        buttonArea4 = new JPanel();
        buttonArea4.setLayout(new FlowLayout());
        myGrid = new Grid();
        setting = new JLabel("Spread fire simulation");
        buttonArea2.add(setting);

        simulation = new Simulation(myGrid, 281, 0.0, 1.0, 0.0,0.0,false,"No",2);
        simulation.initForest();
        myGrid.add(buttonArea2);
        linkButton();
        myGrid.setShowValue(false);
        addAutoButton();
        addStepButton();
        addResetButton();
        showValueButton();
        myGrid.add(buttonArea1);

        forestSize();
        addProbCatch();
        addProbTree();
        addProbBurning();
        addProbLightning();
        add(myGrid);
        direction();
        windSpeed();

    }

    /*
     * link button - create to show user manual.
     */
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

                            String url ="https://github.com/PondPond/OOP_Group10/wiki/Software-Documentation-(V2.0)";

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

    /*
     * auto button - program can run automatically.
     */

    private void addAutoButton(){
        //Create auto button
        autoButton=new JButton("Auto");
        //Add the button to the myPanel
        buttonArea1.add(autoButton);

        autoButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    simulation.setStop(false);
                    smallSize.setEnabled(false);
                    largeSize.setEnabled(false);
                    autoButton.setEnabled(false);
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

    /*
     * step button - program can run step by step.
     */
    private void addStepButton(){
        //Create step button
        stepButton=new JButton("Step");
        //Add the button to the myPanel
        buttonArea1.add(stepButton);

        stepButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    simulation.spreadFire();

                }
            });
    }

    /*
     * reset button - reset the field.
     */
    private void addResetButton(){
        //Create reset button
        resetButton=new JButton("Reset");
        //Add the button to the myPanel
        buttonArea1.add(resetButton);
        resetButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    simulation.setStop(true);
                    simulation = new Simulation(myGrid,simulation.getNumTree(),simulation.getProbCatch(),simulation.getProbTree(),
                        simulation.getProbBurning(),simulation.getProbLightning(),simulation.getLightNing(),simulation.getDirection(),simulation.getSpeed());
                    simulation.initForest();
                    myGrid.setStep(0);
                    myGrid.setShowValue(false);
                    smallSize.setEnabled(true);
                    largeSize.setEnabled(true);
                    autoButton.setEnabled(true);
                }
            });

    }

    /*
     * showvalue button - show value of each cell.
     */
    private void showValueButton(){
        showValueButton = new JButton("Show Value");
        buttonArea1.add(showValueButton);
        showValueButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(!isLarge){
                        if(!myGrid.isShowValue()){
                            myGrid.setShowValue(true);
                        }else{
                            myGrid.setShowValue(false);
                        }
                    }
                    myGrid.repaint();
                }
            });
    }

    /*
     * create size of forest such as small and large.
     */
    private void forestSize(){
        setSizeText = new JLabel("Set forest Size :");
        ButtonGroup group = new ButtonGroup();
        buttonArea3.add(setSizeText);
        smallSize = new JCheckBox("Small");
        buttonArea3.add(smallSize);
        largeSize = new JCheckBox("Large");
        buttonArea3.add(largeSize);
        group.add(smallSize);
        group.add(largeSize);
        smallSize.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    isLarge = false;
                    if(smallSize.isSelected()){
                        myGrid.setCellSize(23);
                        simulation.setNumTree(23);
                        simulation = new Simulation(myGrid,simulation.getNumTree(),simulation.getProbCatch(),simulation.getProbTree(),
                            simulation.getProbBurning(),simulation.getProbLightning(),simulation.getLightNing(),simulation.getDirection(),simulation.getSpeed());
                        simulation.initForest();
                        myGrid.setStep(0);
                    }
                }
            });
        largeSize.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    isLarge = true;
                    if(largeSize.isSelected()){
                        myGrid.setCellSize(2);
                        simulation.setNumTree(281);
                        simulation = new Simulation(myGrid,simulation.getNumTree(),simulation.getProbCatch(),simulation.getProbTree(),
                            simulation.getProbBurning(),simulation.getProbLightning(),simulation.getLightNing(),simulation.getDirection(),simulation.getSpeed());
                        simulation.initForest();
                        myGrid.setStep(0);

                    }
                }
            });
        myGrid.add(buttonArea3);
    }

    /*
     * use JLabel to add text JSlider to show probability of the tree will catch fire.
     */
    private void addProbCatch(){
        probCatchVal = new JLabel("probCatch : 0.0%");
        myGrid.add (probCatchVal);
        probCatchSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0); // default of ProbCatch is 0
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

    /*
     * use JLabel to add text JSlider to show probability of density of the tree when the simulation begins.
     */
    private void addProbTree(){
        probTreeVal = new JLabel("probTree : 100.0%");
        myGrid.add (probTreeVal);
        probTreeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100); // default of ProbTree is 100
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

    /*
     * use JLabel to add text and JSlider to show probability of burning of the tree when the simulation begins.
     */
    private void addProbBurning(){       
        probBurnVal = new JLabel("probBurning : 0.0%");
        myGrid.add (probBurnVal);
        probBurningSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0); // default of ProbCatch is 0
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

    /*
     * use JLabel to add text and JSlider to show probability that the tree is struck by lightning.
     */
    private void addProbLightning(){
        lightNing = new JCheckBox("Lightning");
        myGrid.add(lightNing);
        probLightVal = new JLabel("probLightning : 0.0%");
        myGrid.add (probLightVal);
        probLightningSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        probLightningSlider.setCursor(Cursor.getPredefinedCursor(12));

        lightNing.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(lightNing.isSelected()){
                        simulation.setLightNing(true); 
                    }else{
                        simulation.setLightNing(false);
                    }
                }
            });
        probLightningSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    if (probLightningSlider.getValueIsAdjusting()) {
                        probLightVal.setText("ProbLightning : " + (double)probLightningSlider.getValue() + " %");
                        simulation.setProbLightning((double) probLightningSlider.getValue() / 100);
                    }
                }

            }
        );
        myGrid.add(probLightningSlider);

    }

    @SuppressWarnings("unchecked")

    /*
     * create direction of wind (North,East,West,South)
     * use JLabel to add text and JCombobox to set wind direction
     */

    private void direction(){
        directionText = new JLabel("Set wind directon :");
        final  DefaultComboBoxModel Direction = new  DefaultComboBoxModel();
        Direction.addElement("NO");
        Direction.addElement("NORTH");// set wind direction to NORTH
        Direction.addElement("EAST");// set wind direction to NEAST
        Direction.addElement("WEST"); // set wind direction to WEST
        Direction.addElement("SOUTH");// set wind direction to SOUTH

        final JComboBox windDirect= new JComboBox(Direction);    
        windDirect.setSelectedIndex(0);

        directButton = new JButton("Ok");
        windDirect.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { 
                    String data = "";
                    if (windDirect.getSelectedIndex() != -1) {                     
                        simulation.setDirection((String)windDirect.getItemAt
                            (windDirect.getSelectedIndex()));

                    }              
                }
            }); 

        buttonArea4.add(directionText);
        buttonArea4.add(windDirect);
        myGrid.add(buttonArea4);
    }

    /*
     * create speed of wind (NONE,LOW,and HIGH)
     * use JLabel to add text and JSlider to display wind speed
     */

    private void windSpeed(){
        windSpeedSlider = new JSlider(JSlider.HORIZONTAL, 0, 2, 0); // slide only horizontal min = 0(LOW) max = 2(HIGH)
        windSpeedSlider.setMinorTickSpacing(1); // change only 1 value
        windSpeedSlider.setMajorTickSpacing(1);
        windSpeedSlider.setPaintTicks(true);
        windSpeedSlider.setPaintLabels(true);
        windSpeedText = new JLabel("Set wind speed : NONE");
        myGrid.add(windSpeedText);

        windSpeedSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) { 
                    if (windSpeedSlider.getValueIsAdjusting()) { // if sliding wind JSlider
                        if (windSpeedSlider.getValue() == 0) { // if sliding to 0
                            windSpeedText.setText("Set wind speed : NONE "); // set the the of wind spreed to none 
                        } else if (windSpeedSlider.getValue() == 1) { // if sliding to 1
                            windSpeedText.setText("Set wind speed : LOW "); // set the the of wind spreed to low
                        } else { // if sliding to 2
                            windSpeedText.setText("Set wind speed : HIGH "); // set the the of wind spreed to high
                        }
                        simulation.setSpeed(windSpeedSlider.getValue()); // set the vlue of wind speed by value of wind speed JSlider                }
                    }
                }
            });
        myGrid.add(windSpeedSlider);
    }
}
