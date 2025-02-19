import javax.swing.*;
import java.awt.*;

public class subGrid extends JPanel {

    public subGrid(int subGridNum, int subGridSize) {

        setLayout(new GridLayout(subGridSize,subGridSize));
        setBorder(BorderFactory.createLineBorder(new Color(30, 80, 138)));

       for (int i = 0; i < subGridSize; i++){
           for (int j = 0; j < subGridSize; j++){
               int[] gammaPair = gamma(subGridNum, subGridSize*i + j, subGridSize );
               Cell cell = new Cell(gammaPair[0], gammaPair[1]);
               add(cell);
           }
       }

    }

    public Cell getCell(int G){
        return (Cell) this.getComponent(G);
    }

    public static int[] gamma(int subGridNum, int cellNum, int d){

        int row = d * (subGridNum / d) + cellNum / d ;
        int col = d * (subGridNum % d) + cellNum % d;

        return new int[]{row,col};
    }

}
