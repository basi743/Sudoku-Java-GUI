import javax.swing.*;

public class Main extends JFrame {

    private Menu menuPanel;
    public Game gamePanel;
    private String gameLevel;
    private int gridSize;

    public Main(){

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1386,736);
        setTitle("Sudoku");

        menuPanel = new Menu();
        menuPanel.setMainFrame(this);
        add(menuPanel);

        setVisible(true);


    }

    public void setGameLevel(int Size,String level){
        gameLevel = level;
        gridSize = Size;
    }

    public void showGame(){
        gamePanel = new Game(gridSize,gameLevel);
        gamePanel.setMainFrame(this);
        remove(menuPanel);
        add(gamePanel);
        revalidate();
        repaint();
        gamePanel.grid.requestFocus();
    }

    public void showMenu(){
        remove(gamePanel);
        gamePanel = null;
        add(menuPanel);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new Main();
    }
}
