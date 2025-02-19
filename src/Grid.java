import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Grid extends JPanel implements KeyListener {

    Cell curr;
    int gridSize;
    int subGridSize;
    Stack<State> gameStates;

    public Grid(int width, int height, int gridSize, String gameLevel) {
        this.gridSize = gridSize;
        this.subGridSize = (int) Math.sqrt(gridSize);

        setPreferredSize(new Dimension(width,height));
        setLayout(new GridLayout(subGridSize, subGridSize));

        setBorder(BorderFactory.createLineBorder(new Color(30, 80, 138),2,true));

        for (int i = 0; i < gridSize; i++) {
            add(new subGrid(i , subGridSize));
        }

        fillBoard(gameLevel, gridSize);

        this.curr = getCell((int) Math.ceil(gridSize/2) ,(int) Math.ceil(gridSize/2) , subGridSize);
        curr.state = 1;
        highlight(2);
        gameStates = new Stack<>();

        setFocusable(true);
        addKeyListener(this);

    }

    public void highlight(int state){
        Cell cell1;
        Cell cell2;
        Cell cell3;
        for (int i = 0; i < gridSize; i++){

            cell1 = getCell(i, curr.col, subGridSize);
            if (cell1.state != 3){
                cell1.changeColour(state);
            }

            cell2 = getCell(curr.row, i, subGridSize);
            if (cell2.state != 3){
                cell2.changeColour(state);
            }

            subGrid subGrid = (subGrid) getComponent(beta(curr.row, curr.col, subGridSize));
            cell3 = subGrid.getCell(i);
            if (cell3.state != 3){
                cell3.changeColour(state);
            }

            curr.changeColour(curr.state);
        }
    }

    public Boolean isValid(int row , int col, int value, int gridSize){
        int d  = (int) Math.sqrt(gridSize) ;
        int B = beta(row,col,d);

        subGrid blockB = (subGrid) getComponent(B);

        for (int i = 0; i < gridSize; i++){
            if (i != alpha(row,col,d) && blockB.getCell(i).getValue() == value) return false;
            if (i != col && getCell(row,i,d).getValue() == value) return false;
            if (i != row && getCell(i,col,d).getValue() == value) return false;
        }


        return true;
    }

    public void restartGame(){
        State state;
        Cell cell;
        while (!gameStates.isEmpty()){
            state = gameStates.pop();
            cell = getCell(state.row, state.col, subGridSize);
            cell.setValue(0);
            cell.state = 0;
            cell.changeColour(0);
        }
        curr.state = 0;
        highlight(0);

        this.curr = getCell((int) Math.ceil(gridSize/2) ,(int) Math.ceil(gridSize/2) , subGridSize);
        curr.state = 1;
        highlight(2);
        setVisible(true);
    }

    public void pauseGame() {

        for (int i = 0; i < gridSize; i++){
            for (int j = 0; j < gridSize; j++){
                getCell(i, j, subGridSize).label.setVisible(false);
            }
        }
        highlight(0);
    }

    public void resumeGame(){
        for (int i = 0; i < gridSize; i++){
            for (int j = 0; j < gridSize; j++){
                getCell(i, j, subGridSize).label.setVisible(true);
            }
        }
        highlight(2);
    }

    public void fillBoard(String gameLevel, int gridSize){

        Random random = new Random();
        int level = 45;
        if (gameLevel.equals("EASY")){
            level = 25 + random.nextInt(10);
        }
        else if (gameLevel.equals("NORMAL")){
            level = 45 + random.nextInt(10);
        }
        else if (gameLevel.equals("HARD")){
            level = 65 + random.nextInt(10);
        }
        else if (gameLevel.equals("EXTREME")){
            level = 90 + random.nextInt(10);
        }

        for ( int i = 0 ; i < gridSize ; i++ ){
            fillBlock(i,level,gridSize);
        }
    }

    public int[][] gridToArray(Grid grid){

        int[][] array = new int[grid.gridSize][grid.gridSize];
        Cell curr;
        for (int i = 0; i < grid.gridSize; i++){
            for (int j = 0; j < grid.gridSize; j++){
                curr = grid.getCell(i,j,grid.subGridSize);
                array[i][j] = curr.getValue();
            }
        }
        return array;
    }

    public boolean fillBlock(int B, int gameLevel, int gridSize){

        Random random = new Random();

        int d = (int) Math.sqrt(gridSize);

        double count = gridSize *(1 -  (gameLevel / 100.00)) ;

        int G = -1;
        int R = -1;
        int C = -1;
        int value = -1;

        int tracker = 0;

        for (int i = 0; i < count; i++){
            G = random.nextInt(gridSize);
            int[] gammaPair = gamma(B,G,d);
            R = gammaPair[0];
            C = gammaPair[1];
            value = random.nextInt(gridSize) + 1;
            tracker = 0;

            while (!isValid(R,C,value,gridSize) && tracker < 81*81){
                value = random.nextInt(gridSize) + 1;
                tracker++;
            }
            Cell cell = getCell(R,C,d);
            cell.setValue(value);
            cell.isModifiable = false;
        }
        return tracker == 81*81;
    }

    public Cell getCell(int Row, int Col, int d){
        int[] tauPair = tau(Row ,Col, d);
        int B = tauPair[0];
        int G = tauPair[1];

        subGrid blockB = (subGrid) getComponent(B);
        Cell cellG = (Cell) blockB.getComponent(G);
        return cellG;

    }

    public int beta(int row, int col, int d){
        return row - row % d +  (col - col % d) / d;
    }

    public int alpha(int row, int col, int d){
        return d * (row % d) + col % d;
    }

    public static int[] gamma(int subGridNum, int cellNum, int d){

        int row = d * (subGridNum / d) + cellNum / d ;
        int col = d * (subGridNum % d) + cellNum % d;

        return new int[]{row,col};
    }

    public static int[] tau(int row, int col, int d){
        int subGridNum = row - row % d +  (col - col % d) / d;
        int cellNum = d * (row % d) + col % d;
        return new int[]{subGridNum,cellNum};
    }


    @Override
    public void keyTyped(KeyEvent e) {
        if (curr.isModifiable) {
            char c = e.getKeyChar();
            if (Character.isDigit(c) && c != '0') {

                if (!isValid(curr.row, curr.col, c - 48, gridSize)) {
                    curr.state = 3;
                    highlight(2);
                } else {
                    curr.state = 1;
                    highlight(2);
                }
                gameStates.push(new State(curr.row, curr.col, curr.getValue(), c - 48));
                curr.setValue(c - 48);

            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        highlight(0);

        switch (e.getKeyCode()) {

            case KeyEvent.VK_UP:
                curr = getCell((curr.row - 1 + gridSize) % gridSize, curr.col, subGridSize);
                break;

            case KeyEvent.VK_DOWN:
                curr = getCell((curr.row + 1) % gridSize, curr.col, subGridSize);
                break;

            case KeyEvent.VK_LEFT:
                curr = getCell(curr.row, (curr.col - 1 + gridSize) % gridSize, subGridSize);
                break;

            case KeyEvent.VK_RIGHT:
                curr = getCell(curr.row, (curr.col + 1) % gridSize, subGridSize);
                break;
        }
        if (curr.state != 3){
            curr.state = 1;
        }
        highlight(2);

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}