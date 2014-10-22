
package gridtable;

import javax.swing.*;
import java.awt.*;

public class GridTable {
    public static void main(String[] args) {
        Frame f = new Frame();
        Table t = new Table();
        f.setSize(400,400);
        f.setLocationRelativeTo(null);
        f.add(t);
        f.setVisible(true);
        
    }
    
}
