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

    public void update() {
        g.Update(cell);
    }
    
    
}

