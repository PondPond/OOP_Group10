
/**
 * Cell class that contain state of cell .
 * 
 * @author (Project Group 10) 
 * @version (21/11/2014)
 */
public class Cell
{
    public static final int EMPTY = 0;
    public static final int TREE = 1;
    public static final int BURNING = 2;
    private int state;

    /**
     * Constructor - use TREE state to create cell 
     */
    public Cell(int state){
        this.state = TREE;
    }

    
    /**
     * get the state of cell
     */
    public int getState(){
        return this.state;
    }

    /**
     * set the state of cell
     */
    public void setState(int state){
        this.state = state;
    }
    
    
    
}
