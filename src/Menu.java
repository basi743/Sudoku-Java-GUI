import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Menu extends JPanel {


    private JButton playButton;
    private JButton exitButton;
    private JButton DFSButton;
    private JPanel gameLevelsPanel;
    private JButton easyButton;
    private JButton normalButton;
    private JButton hardButton;
    private JButton extremeButton;

    private String gameLevel;

    private int gridSize;

    private Main mainFrame;

    public Menu() {

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        setBackground(new Color(202, 202, 202));

        JLabel label = new JLabel("Sudoku");

        gameLevelsPanel = new JPanel();
        gameLevelsPanel.setLayout(new FlowLayout());

        easyButton = new JButton("Easy");
        easyButton.setBackground(new Color(237, 237, 237));
        easyButton.setForeground(new Color(1, 63, 113));

        normalButton = new JButton("Normal");
        normalButton.setBackground(new Color(237, 237, 237));
        normalButton.setForeground(new Color(1, 63, 113));

        hardButton = new JButton("Hard");
        hardButton.setBackground(new Color(237, 237, 237));
        hardButton.setForeground(new Color(1, 63, 113));

        extremeButton = new JButton("Extreme");
        extremeButton.setBackground(new Color(237, 237, 237));
        extremeButton.setForeground(new Color(1, 63, 113));

        playButton = new JButton("Play");
        playButton.setPreferredSize(new Dimension(200, 54));
        playButton.setBackground(new Color(237, 237, 237));
        playButton.setForeground(new Color(1, 63, 113));
        playButton.setFont(new Font("Consolas", Font.BOLD, 16));


        exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(200, 54));
        exitButton.setBackground(new Color(237, 237, 237));
        exitButton.setForeground(new Color(1, 63, 113));
        exitButton.setFont(new Font("Consolas", Font.BOLD, 16));


        DFSButton = new JButton("Watch DFS Solver");
        DFSButton.setPreferredSize(new Dimension(200, 54));
        DFSButton.setBackground(new Color(237, 237, 237));
        DFSButton.setForeground(new Color(1, 63, 113));
        DFSButton.setFont(new Font("Consolas", Font.BOLD, 16));

        gameLevel = normalButton.getText();
        gridSize = 9;


        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLevel = "EASY";
                easyButton.setBackground(new Color(114, 160, 198));
                normalButton.setBackground(new Color(237, 237, 237));
                hardButton.setBackground(new Color(237, 237, 237));
                extremeButton.setBackground(new Color(237, 237, 237));
            }
        });

        normalButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               gameLevel = "NORMAL";
               easyButton.setBackground(new Color(237, 237, 237));
               normalButton.setBackground(new Color(114, 160, 198));
               hardButton.setBackground(new Color(237, 237, 237));
               extremeButton.setBackground(new Color(237, 237, 237));
           }
        });

        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLevel = "HARD";
                easyButton.setBackground(new Color(237, 237, 237));
                normalButton.setBackground(new Color(237, 237, 237));
                hardButton.setBackground(new Color(114, 160, 198));
                extremeButton.setBackground(new Color(237, 237, 237));
            }
        });

        extremeButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               gameLevel = "EXTREME";
               easyButton.setBackground(new Color(237, 237, 237));
               normalButton.setBackground(new Color(237, 237, 237));
               hardButton.setBackground(new Color(237, 237, 237));
               extremeButton.setBackground(new Color(114, 160, 198));
           }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setGameLevel(gridSize, gameLevel);
                mainFrame.showGame();
            }
        });

        DFSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setGameLevel(gridSize, gameLevel);
                mainFrame.showGame();

            }
        });

        gameLevelsPanel.add(easyButton);
        gameLevelsPanel.add(normalButton);
        gameLevelsPanel.add(hardButton);
        gameLevelsPanel.add(extremeButton);

        gameLevelsPanel.setVisible(true);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5,0,5,0);
        layout.setConstraints(gameLevelsPanel, constraints);
        add(gameLevelsPanel);

        constraints.gridy = 1;
        layout.setConstraints(playButton, constraints);
        add(playButton);

        constraints.gridy = 2;
        layout.setConstraints(DFSButton, constraints);
        add(DFSButton);

        constraints.gridy = 3;
        layout.setConstraints(exitButton, constraints);
        add(exitButton);

        setVisible(true);
    }

    public void setMainFrame(Main mainFrame){
        this.mainFrame = mainFrame;
    }






}
