
/**
 * Write a description of class TestCell here.
 * 
 * @author (Project Group 10) 
 * @version (21/10/2014)
 */
public class Cell
{
    public static final int EMPTY = 0;
    public static final int TREE = 1;
    public static final int BURNING = 2;
    private int state;

    public Cell(int state){
        this.state = TREE;
    }

    public int getState(){
        return this.state;
    }

    public void setState(int state){
        this.state = state;
    }
    
    
    
}
