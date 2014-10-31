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

    public Simulation(Grid g ,int numTree , double probCatch , double probTree ,double probBurning ){
        this.g = g;
        this.numTree = numTree; 
        this.probCatch = probCatch;
        this.probTree = probTree;
        this.probBurning = probBurning; 
        initForest();
    }

    public void initForest(){
        cell = new Cell[numTree][numTree];
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                cell[i][j] = new Cell(Cell.TREE);
                if (i == 0 || i == cell.length-1  || j == 0 || j == cell.length-1 ) {
                    cell[i][j].setState(Cell.EMPTY);
                }
                else if  (i == cell.length/2 && j == cell.length/2) {
                    cell[i][j].setState(Cell.BURNING);
                }
                else{
                    cell[i][j].setState(Cell.TREE); 
                }

            }
        }
        update();
    }
    
     public void Spread(){
        for(int i = 1 ; i<cell.length-1 ; i++){
            for (int j = 1 ; j<cell.length-1 ; j++){
                if(cell[i][j].getState() == Cell.BURNING && check[i][j] == false){
                    cell[i][j].setState(0);
                    if(cell[i-1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch ){
                        cell[i-1][j].setState(Cell.BURNING);
                        check[i-1][j]=true;
                    }//North
                    if(cell[i+1][j].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                        cell[i+1][j].setState(Cell.BURNING);
                        check[i+1][j]=true;
                    }//South
                    if(cell[i][j+1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                        cell[i][j+1].setState(Cell.BURNING);
                        check[i][j+1]=true;
                    }//East
                    if(cell[i][j-1].getState() == Cell.TREE && rand.nextDouble() <= probCatch){
                        cell[i][j-1].setState(Cell.BURNING);
                        check[i][j-1]=true;
                    }//West

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
                check[i][j] = false ;
                
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
