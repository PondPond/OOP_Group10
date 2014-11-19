import java.util.Random;
/**
 * Write a description of class Simulation here.
 * 
 * @author (Project Group 10) 
 * @version (21/10/2014)
 */
public class Simulation
{
    private Cell cell[][];
    private Grid g;
    private double probCatch, probTree, probBurning;
    private int numTree, delay, step;
    private boolean checkBurning[][],checkLightning[][];
    private boolean lightning;
    private Random rand = new Random();

    /**
     * Constructor for objects of class Simulation
     *
     * @param g
     * @param numTree
     * @param probCatch 
     * @param probTree 
     * @param probBurning 
     */

    public Simulation(Grid g ,int numTree , double probCatch , double probTree ,double probBurning, double probLightning,boolean lightning,String direction,int speed){
        this.g = g;
        this.numTree = numTree; 
        this.probCatch = probCatch;
        this.probTree = probTree;
        this.probBurning = probBurning; 
        this.lightning = lightning;
        this.direction = direction;
        this.speed = speed;

        burnStep = new int[numTree][numTree];
        checkBurning = new boolean[numTree][numTree];
        checkLightning = new boolean[numTree][numTree];
        initForest();
    }

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
    
   
 public void Spread(){
        for(int i = 1 ; i<cell.length-1 ; i++){
            for (int j = 1 ; j<cell.length-1 ; j++){
                if(cell[i][j].getState() == Cell.BURNING && checkBurning[i][j] == false && checkLightning[i][j] == false){
                    cell[i][j].setState(0);
                    if(getDirection().equals("NORTH") && getSpeed() == LOW){
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
                            checkBurning[i][j-1]=true;
                            if(cell[i][j-2].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                                cell[i][j-2].setState(Cell.BURNING);
                                checkBurning[i][j-2]=true;
                            }
                        }//West

                    }

                    else if(getDirection().equals("NORTH") && getSpeed() == HIGH){
                        if(cell[i-1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch ){
                            cell[i-1][j].setState(Cell.BURNING);
                            checkBurning[i-1][j]=true;
                        }//North
                        if(cell[i+1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i+1][j].setState(Cell.BURNING);
                            checkBurning[i+1][j]=true;
                        }//South
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

                        }//West

                    }

                    else if(getDirection().equals("SOUTH") && getSpeed() == LOW){
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
                            if(cell[i][j+2].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                                cell[i][j+2].setState(Cell.BURNING);
                                checkBurning[i][j+2]=true;
                            }
                        }//East

                        if(cell[i][j-1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i][j-1].setState(Cell.BURNING);
                            checkBurning[i][j-1]=true;

                        }//West

                    }

                    else if(getDirection().equals("SOUTH") && getSpeed() == HIGH){
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
                            if(cell[i][j+2].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                                cell[i][j+2].setState(Cell.BURNING);
                                checkBurning[i][j+2]=true;
                                if(cell[i][j+3].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                                    cell[i][j+3].setState(Cell.BURNING);
                                    checkBurning[i][j+3]=true;
                                }
                            }
                        }//East

                    }
                    else if(getDirection().equals("EAST") && getSpeed() == LOW){
                        if(cell[i-1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch ){
                            cell[i-1][j].setState(Cell.BURNING);
                            checkBurning[i-1][j]=true;
                        }//North
                        if(cell[i+1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i+1][j].setState(Cell.BURNING);
                            checkBurning[i+1][j]=true;
                            if(cell[i+2][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                                cell[i+2][j].setState(Cell.BURNING);
                                checkBurning[i+2][j]=true;
                            }
                        }//South
                        if(cell[i][j+1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i][j+1].setState(Cell.BURNING);
                            checkBurning[i][j+1]=true;

                        }//East

                        if(cell[i][j-1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i][j-1].setState(Cell.BURNING);
                            checkBurning[i][j-1]=true;

                        }//West
                    }

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
                        }//South
                        if(cell[i][j+1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i][j+1].setState(Cell.BURNING);
                            checkBurning[i][j+1]=true;

                        }//East
                        
                        if(cell[i][j-1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i][j-1].setState(Cell.BURNING);
                            checkBurning[i][j-1]=true;

                        }//West
                    }

                    else if(getDirection().equals("WEST") && getSpeed() == LOW){
                        if(cell[i-1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch ){
                            cell[i-1][j].setState(Cell.BURNING);
                            checkBurning[i-1][j]=true;
                            if(cell[i-2][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                                cell[i-2][j].setState(Cell.BURNING);
                                checkBurning[i-2][j]=true;
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
                        if(cell[i+1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                            cell[i+1][j].setState(Cell.BURNING);
                            checkBurning[i+1][j]=true;
                        }//South
                    }   

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
   public void run(){
        while(!checkFire()){
            spreadFire();
        }
    }
    
   public void spreadFire(){
        try{
            if(!checkFire()){
                Spread();
            }
            resetCheck();
            g.Update(cell);
            Thread.sleep(50);
        }catch(Exception e){

        }
    }
    
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
    
    public void resetCheck(){
        for (int i = 1; i < cell.length - 1; i++) {
            for (int j = 1; j < cell[0].length - 1; j++) { 
                checkBurning[i][j] = false ;
                
          }
       }
    }
    
    public void autoSpread(final int i, final int j) {
        Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (i < cell.length - 1 && i > 0 && j < cell.length - 1 && j > 0) {
                            if (rand.nextDouble() < getProbCatch() && cell[i][j].getState() == Cell.TREE) {
                                cell[i][j].setState(Cell.BURNING);
                            }
                            try {
                                Thread.sleep(100);
                                if (cell[i][j].getState() == Cell.BURNING) {
                                    cell[i][j].setState(0);
                                    if (cell[i + 1][j].getState() == Cell.TREE) {
                                        autoSpread(i + 1, j);
                                    }
                                    if (cell[i - 1][j].getState() == Cell.TREE) {
                                        autoSpread(i - 1, j);
                                    }
                                    if (cell[i][j + 1].getState() == Cell.TREE) {
                                        autoSpread(i, j + 1);
                                    }
                                    if (cell[i][j - 1].getState() == Cell.TREE) {
                                        autoSpread(i, j - 1);
                                    }
                                }
                            } catch (InterruptedException e) {
                            }

                        }
                        update();
                    }
                });
        t.start();
    }
    
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

    public void update() {
        g.Update(cell);
    }

}
