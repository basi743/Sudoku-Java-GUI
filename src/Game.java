import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Stack;
import java.time.*;

public class Game extends JPanel {

    public static final int width = 1382;
    public static final int height = 736;

    JPanel leftPanel,rightPanel,topPanel,bottomPanel;
    Grid grid;
    Main mainFrame;
    JLabel timeLabel, gameLevelLabel, titleLabel ;

    JButton pauseButton,restartButton,quitButton,solutionButton;


    public Game(int gridSize, String gameLevel) {
        // 1382 - 593 = 789
        // 736 - 598 = 138

        grid = new Grid(593, 598, gridSize, gameLevel);
        setupPanels();
        setupButtons();
        setupLabels();

        pauseButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               if (pauseButton.getText().equals("Pause")) {
                   pauseButton.setText("Resume");
                   grid.pauseGame();
               }
               else if (pauseButton.getText().equals("Resume")) {
                   pauseButton.setText("Pause");
                   grid.resumeGame();
                   grid.requestFocus();
               }
           }
        });

        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                grid.restartGame();
                grid.requestFocus();
                if (pauseButton.getText().equals("Resume")) {
                    grid.resumeGame();
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                mainFrame.showMenu();
            }
        });

        solutionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SudokuSolver sudokuSolver = new SudokuSolver();
                int[][] array = gridToArray(grid);
                sudokuSolver.SOLVE(array);

                for (int i = 0; i < array.length; i++){
                    for (int j = 0; j < array[0].length; j++){
                        grid.getCell(i,j,grid.subGridSize).setValue(array[i][j]);
                    }
                }

                grid.requestFocus();
            }
        });

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();

        topPanel.add(titleLabel);
        leftPanel.setLayout(layout);

        constraints.gridy = 0;
        constraints.gridx = 0;
        layout.setConstraints(timeLabel, constraints);
        leftPanel.add(timeLabel);

        constraints.gridy = 2;
        constraints.gridx = 0;
        layout.setConstraints(gameLevelLabel, constraints);
        leftPanel.add(gameLevelLabel);

        rightPanel.add(pauseButton);
        rightPanel.add(restartButton);
        rightPanel.add(quitButton);
        rightPanel.add(solutionButton);



        add(grid, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

    }

    public void setMainFrame(Main mainFrame) {
        this.mainFrame = mainFrame;
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

    private void setupPanels(){
        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(214, 217, 221));
        setLayout(new BorderLayout());

        topPanel = new JPanel();
        topPanel.setBackground(new Color(236, 236, 236));
        topPanel.setPreferredSize(new Dimension(1366, 69));

        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(236, 236, 236));
        bottomPanel.setPreferredSize(new Dimension(1366, 69));

        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(236, 236, 236));
        leftPanel.setPreferredSize(new Dimension(400, 598));

        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(236, 236, 236));
        rightPanel.setPreferredSize(new Dimension(389, 598));
    }

    private void setupButtons(){
        pauseButton = new JButton("Pause");
        pauseButton.setPreferredSize(new Dimension(200, 50));
        pauseButton.setBackground(new Color(197, 210, 228));
        pauseButton.setFont(new Font("Consolas", Font.BOLD, 14));
        pauseButton.setForeground(new Color(0, 12, 43));

        restartButton = new JButton("Restart");
        restartButton.setPreferredSize(new Dimension(200, 50));
        restartButton.setBackground(new Color(197, 210, 228));
        restartButton.setFont(new Font("Consolas", Font.BOLD, 14));
        restartButton.setForeground(new Color(0, 12, 43));

        quitButton = new JButton("Quit");
        quitButton.setPreferredSize(new Dimension(200, 50));
        quitButton.setBackground(new Color(197, 210, 228));
        quitButton.setFont(new Font("Consolas", Font.BOLD, 14));
        quitButton.setForeground(new Color(0, 12, 43));

        solutionButton = new JButton("Solution");
        solutionButton.setPreferredSize(new Dimension(200, 50));
        solutionButton.setBackground(new Color(197, 210, 228));
        solutionButton.setFont(new Font("Consolas", Font.BOLD, 14));
        solutionButton.setForeground(new Color(0, 12, 43));
    }

    private void setupLabels(){

        timeLabel = new JLabel("Time");
        gameLevelLabel = new JLabel("Level");
        titleLabel = new JLabel("9x9 Sudoku");

        timeLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18 ));
        gameLevelLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));

        timeLabel.setForeground(new Color(0,52,76));
        gameLevelLabel.setForeground(new Color(0,57,76));
        titleLabel.setForeground(new Color(0, 52, 76));

        timeLabel.setVerticalAlignment(SwingConstants.NORTH);
        gameLevelLabel.setVerticalAlignment(SwingConstants.NORTH);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

    }

}

