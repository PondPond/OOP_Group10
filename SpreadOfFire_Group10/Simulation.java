import java.util.Random;
/**
 * This class contain logic of spreading of fire.
 * 
 * @author (Project Group 10) 
 * @version (21/10/2014)
 */
public class Simulation
{
    private Cell cell[][];
    private Grid g;
    private double probCatch, probTree, probBurning,probLightning;
    private int numTree, delay, step ,burnStep[][];
    private String direction;
    public static final int NONE = 0;
    public static final int LOW = 1;
    public static final int HIGH = 2;
    private int speed;
    private boolean checkBurning[][],checkLightning[][];
    private boolean lightning,stop;
    private Random rand = new Random();

    /**
     * Constructor for objects of class Simulation
     *
     * @param g
     * @param numTree
     * @param probCatch 
     * @param probTree 
     * @param probBurning 
     * @param probLightning 
     * @param lightning 
     * @param direction
     * @param speed 
     */

    public Simulation(Grid g ,int numTree , double probCatch , double probTree ,double probBurning, double probLightning,boolean lightning,String direction,int speed){
        this.g = g;
        this.numTree = numTree; 
        this.probCatch = probCatch;
        this.probTree = probTree;
        this.probBurning = probBurning; 
        this.probLightning = probLightning;
        this.lightning = lightning;
        this.direction = direction;
        this.speed = speed;

        stop = false;
        burnStep = new int[numTree][numTree];
        checkBurning= new boolean[numTree][numTree];
        checkLightning = new boolean[numTree][numTree];

        initForest();
    }

    // create the initial forest
    public void initForest(){
        cell = new Cell[numTree][numTree];
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                cell[i][j] = new Cell(Cell.TREE);
                if(rand.nextDouble() < probTree){
                    if(rand.nextDouble()<getProbBurning()){
                        cell[i][j].setState(Cell.BURNING); 
                    }else{
                        cell[i][j].setState(Cell.TREE);
                    }
                } else{
                    cell[i][j].setState(Cell.EMPTY);
                }

                if (i == 0 || i == cell.length-1  || j == 0 || j == cell.length-1 ) {
                    cell[i][j].setState(Cell.EMPTY);
                }
                else if  (i == cell.length/2 && j == cell.length/2) {
                    cell[i][j].setState(Cell.BURNING);
                }

            }
        }
        update();
    }

    /* 
     *  This method will calculate probability of probCatch,
     *  identify wind direction and wind speed
     */
    public void Spread(){
        for(int i = 1 ; i<cell.length-1 ; i++){
            for (int j = 1 ; j<cell.length-1 ; j++){
                if(cell[i][j].getState() == Cell.BURNING && checkBurning[i][j] == false && checkLightning[i][j] == false){
                    cell[i][j].setState(0);
                    //wind direction is NORTH and wind speed is LOW
                    if(getDirection().equals("NORTH") && getSpeed() == LOW){ 
                        if(cell[i-1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch ){
                            cell[i-1][j].setState(Cell.BURNING);
                            checkBurning[i-1][j]=true;
                        }
                        if(cell[i+1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i+1][j].setState(Cell.BURNING);
                            checkBurning[i+1][j]=true;
                        }
                        if(cell[i][j+1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i][j+1].setState(Cell.BURNING);
                            checkBurning[i][j+1]=true;
                        }
                        if(cell[i][j-1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i][j-1].setState(Cell.BURNING);
                            checkBurning[i][j-1]=true;
                            if(cell[i][j-2].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                                cell[i][j-2].setState(Cell.BURNING);
                                checkBurning[i][j-2]=true;
                            }
                        }

                    }
                    //wind direction is NORTH and wind speed is HIGH
                    else if(getDirection().equals("NORTH") && getSpeed() == HIGH){
                        if(cell[i-1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch ){
                            cell[i-1][j].setState(Cell.BURNING);
                            checkBurning[i-1][j]=true;
                        }
                        if(cell[i+1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i+1][j].setState(Cell.BURNING);
                            checkBurning[i+1][j]=true;
                        }
                        if(cell[i][j-1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i][j-1].setState(Cell.BURNING);
                            checkBurning[i][j-1]=true;
                            if(cell[i][j-2].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                                cell[i][j-2].setState(Cell.BURNING);
                                checkBurning[i][j-2]=true;
                                if(cell[i][j-3].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                                    cell[i][j-3].setState(Cell.BURNING);
                                    checkBurning[i][j-3]=true;
                                }
                            }

                        }

                    }
                    //wind direction is SOUTH and wind speed is LOW
                    else if(getDirection().equals("SOUTH") && getSpeed() == LOW){
                        if(cell[i-1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch ){
                            cell[i-1][j].setState(Cell.BURNING);
                            checkBurning[i-1][j]=true;
                        }
                        if(cell[i+1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i+1][j].setState(Cell.BURNING);
                            checkBurning[i+1][j]=true;
                        }
                        if(cell[i][j+1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i][j+1].setState(Cell.BURNING);
                            checkBurning[i][j+1]=true;
                            if(cell[i][j+2].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                                cell[i][j+2].setState(Cell.BURNING);
                                checkBurning[i][j+2]=true;
                            }
                        }

                        if(cell[i][j-1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i][j-1].setState(Cell.BURNING);
                            checkBurning[i][j-1]=true;

                        }

                    }
                    //wind direction is SOUTH and wind speed is HIGH
                    else if(getDirection().equals("SOUTH") && getSpeed() == HIGH){
                        if(cell[i-1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch ){
                            cell[i-1][j].setState(Cell.BURNING);
                            checkBurning[i-1][j]=true;
                        }
                        if(cell[i+1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i+1][j].setState(Cell.BURNING);
                            checkBurning[i+1][j]=true;
                        }
                        if(cell[i][j+1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i][j+1].setState(Cell.BURNING);
                            checkBurning[i][j+1]=true;
                            if(cell[i][j+2].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                                cell[i][j+2].setState(Cell.BURNING);
                                checkBurning[i][j+2]=true;
                                if(cell[i][j+3].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                                    cell[i][j+3].setState(Cell.BURNING);
                                    checkBurning[i][j+3]=true;
                                }
                            }
                        }

                    }
                    //wind direction is EAST and wind speed is LOW
                    else if(getDirection().equals("EAST") && getSpeed() == LOW){
                        if(cell[i-1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch ){
                            cell[i-1][j].setState(Cell.BURNING);
                            checkBurning[i-1][j]=true;
                        }
                        if(cell[i+1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i+1][j].setState(Cell.BURNING);
                            checkBurning[i+1][j]=true;
                            if(cell[i+2][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                                cell[i+2][j].setState(Cell.BURNING);
                                checkBurning[i+2][j]=true;
                            }
                        }
                        if(cell[i][j+1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i][j+1].setState(Cell.BURNING);
                            checkBurning[i][j+1]=true;

                        }

                        if(cell[i][j-1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i][j-1].setState(Cell.BURNING);
                            checkBurning[i][j-1]=true;

                        }
                    }
                    //wind direction is EAST and wind speed is HIGH
                    else if(getDirection().equals("EAST") && getSpeed() == HIGH){
                        if(cell[i+1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i+1][j].setState(Cell.BURNING);
                            checkBurning[i+1][j]=true;
                            if(cell[i+2][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                                cell[i+2][j].setState(Cell.BURNING);
                                checkBurning[i+2][j]=true;
                                if(cell[i+3][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                                    cell[i+3][j].setState(Cell.BURNING);
                                    checkBurning[i+3][j]=true;
                                }
                            }
                        }
                        if(cell[i][j+1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i][j+1].setState(Cell.BURNING);
                            checkBurning[i][j+1]=true;

                        }

                        if(cell[i][j-1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i][j-1].setState(Cell.BURNING);
                            checkBurning[i][j-1]=true;

                        }
                    }
                    //wind direction is WEST and wind speed is LOW 
                    else if(getDirection().equals("WEST") && getSpeed() == LOW){
                        if(cell[i-1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch ){
                            cell[i-1][j].setState(Cell.BURNING);
                            checkBurning[i-1][j]=true;
                            if(cell[i-2][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                                cell[i-2][j].setState(Cell.BURNING);
                                checkBurning[i-2][j]=true;
                            }
                        }
                        if(cell[i][j+1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i][j+1].setState(Cell.BURNING);
                            checkBurning[i][j+1]=true;

                        }

                        if(cell[i][j-1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i][j-1].setState(Cell.BURNING);
                            checkBurning[i][j-1]=true;

                        }
                        if(cell[i+1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i+1][j].setState(Cell.BURNING);
                            checkBurning[i+1][j]=true;
                        }
                    }   
                    //wind direction is WEST and wind speed is HIGH
                    else if(getDirection().equals("WEST") && getSpeed() == HIGH){
                        if(cell[i-1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch ){
                            cell[i-1][j].setState(Cell.BURNING);
                            checkBurning[i-1][j]=true;
                            if(cell[i-2][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                                cell[i-2][j].setState(Cell.BURNING);
                                checkBurning[i-2][j]=true;
                                if(cell[i-3][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                                    cell[i-3][j].setState(Cell.BURNING);
                                    checkBurning[i-3][j]=true;
                                }
                            }
                        }//North
                        if(cell[i][j+1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i][j+1].setState(Cell.BURNING);
                            checkBurning[i][j+1]=true;

                        }//East

                        if(cell[i][j-1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i][j-1].setState(Cell.BURNING);
                            checkBurning[i][j-1]=true;

                        }//West
                    }   
                    else {
                        if(cell[i-1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch ){
                            cell[i-1][j].setState(Cell.BURNING);
                            checkBurning[i-1][j]=true;
                        }//North
                        if(cell[i+1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i+1][j].setState(Cell.BURNING);
                            checkBurning[i+1][j]=true;
                        }//South
                        if(cell[i][j+1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i][j+1].setState(Cell.BURNING);
                            checkBurning[i][j+1]=true;
                        }//East
                        if(cell[i][j-1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i][j-1].setState(Cell.BURNING);
                        }//West

                    }

                }

            }
        }
        g.setStep();
    }

    /* 
     *  this method is allow program run automatically
     */ 
    public void run(){
        while(!checkFire() && !stop){
            spreadFire();
        }
    }

    /*  
     *  this method will calculate probLightning and display execution of program
     *  
     */
    public void spreadFire(){
        try{
            if(!checkFire()){
                Spread();
                if(lightning){
                    int x = (int) (Math.random() * (getNumTree() - 2) + 1);
                    int y = (int) (Math.random() * (getNumTree() - 2) + 1);

                    if(cell[x][y].getState() == Cell.TREE && rand.nextDouble() <= probLightning){
                        cell[x][y].setState(Cell.BURNING);
                        checkLightning[x][y] = true;
                    }
                    else if(cell[x][y].getState() == Cell.TREE && rand.nextDouble() <= probLightning*probCatch){
                        cell[x][y].setState(Cell.BURNING);
                        checkLightning[x][y] = true;
                    }
                    else{
                        if(cell[x][y].getState()!= Cell.EMPTY){
                            cell[x][y].setState(Cell.TREE);
                        }
                    }
                }
            }
            resetCheck();
            resetCheckLightning();
            g.Update(cell);
            Thread.sleep(50);
        }catch(Exception e){

        }
    }
    
    /*
     * this method will check burnnig tree in forest
     */

    public boolean checkFire(){
        for (int i = 1; i < cell.length; i++) {
            for (int j = 1; j < cell[0].length; j++) {
                if(cell[i][j].getState() == Cell.BURNING){
                    return false;
                }
            }
        }
        return  true;
    }

    /*
     * This method will check direction of burning tree
     */
    public void resetCheck(){
        for (int i = 1; i < cell.length - 1; i++) {
            for (int j = 1; j < cell[0].length - 1; j++) { 
                checkBurning[i][j] = false ;
            }
        }
    }

    /*
     * After the tree is stuck by lightning, this method will check lightning
     * and wait until lightning execute 5 times then the tree will catch fire 
     */
    public void resetCheckLightning(){
        for (int i = 1; i < cell.length - 1; i++) {
            for (int j = 1; j < cell[0].length - 1; j++) { 
                if(checkLightning[i][j] == true){
                    burnStep[i][j]++;
                    if(burnStep[i][j]-5 == 0 ){
                        checkLightning[i][j] = false; 
                        cell[i][j].setState(Cell.BURNING);
                        burnStep[i][j] = 0;

                    }
                }

            }
        }
    }

    public Cell[][] getCell() {
        return cell;
    }

    public double getProbCatch() {
        return probCatch;
    }

    public void setProbCatch(double probCatch) {
        this.probCatch = probCatch;
    }

    public double getProbTree() {
        return probTree;
    }

    public void setProbTree(double probTree) {
        this.probTree = probTree;
    }

    public double getProbBurning() {
        return probBurning;
    }

    public void setProbBurning(double probBurning) {
        this.probBurning = probBurning;
    }

    public double getProbLightning() {
        return probLightning;
    }

    public void setProbLightning(double probLightning) {
        this.probLightning = probLightning;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getNumTree() {
        return numTree;
    }

    public void setNumTree(int numTree) {
        this.numTree = numTree;
    }

    public boolean getLightNing() {
        return this.lightning;
    } 

    public void setLightNing(boolean lightning) {
        this.lightning = lightning;
    }

    public boolean getStop() {
        return this.stop;
    } 

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public String getDirection() {
        return this.direction;
    } 

    public void setDirection(String direction) {
        this.direction =  direction;
    } 

    public int getSpeed() {
        return this.speed;
    } 

    public void setSpeed(int speed) {
        this.speed = speed;
    } 

    public void update() {
        g.Update(cell);
    }

}
