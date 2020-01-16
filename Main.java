import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    JButton[][] buttons;
    int[][] grid;

    public static void main(String[] args) {
        Main myMain = new Main();
        GameLogic myGL = new GameLogic();

        //setup
        JFrame frame = new JFrame("GameOfLife");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int WIDTH = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
        int HEIGHT = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
        frame.setSize(WIDTH, HEIGHT);
        JPanel panel = new JPanel();
        //grid setup
        GridLayout gridLayout = new GridLayout(myGL.getGridRows()+1, myGL.getGridColumns(), 1, 1);
        panel.setLayout(gridLayout);
        myMain.grid = new int[myGL.getGridRows()][myGL.getGridColumns()];
        for (int a = 0; a < myMain.grid.length; a++) {
            for (int b = 0; b < myMain.grid.length; b++) {
                myMain.grid[a][b] = 0;
            }
        }
        myMain.buttons = new JButton[myGL.getGridRows()][myGL.getGridColumns()];

        //button click: live <-> dead
        ActionListener tp = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myGL.updateCells(myMain.grid);

                for (int i = 0; i < myGL.getGridRows(); i++) {
                    for (int j = 0; j < myGL.getGridColumns(); j++) {
                        if (myMain.grid[i][j] == 1) {
                            myMain.buttons[i][j].setBackground(Color.YELLOW);
                        } else {
                            myMain.buttons[i][j].setBackground(Color.BLACK);
                        }
                    }
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException a) {
                    a.printStackTrace();
                }
                frame.revalidate();
                frame.repaint();
                frame.setVisible(true);
            }
        };
        //delay between each grid state
        Timer timer = new Timer(100, tp);
        timer.setRepeats(true);

        //start button
        JButton startButton = new JButton("START");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == startButton) {
                        timer.start();
                }
            }
        });

        //pause game
        JButton stopButton = new JButton("STOP");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == stopButton) {
                    timer.stop();
                }
            }
        });

        //calculate next state of game
        for (int i = 0; i < myGL.getGridRows(); i++) {
            for (int j = 0; j < myGL.getGridColumns(); j++) {
                int l = i;
                int m = j;
                JButton button = new Button().getButton();
                myMain.buttons[i][j] = button;
                myMain.buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == button) {
                            if (button.getBackground() == Color.YELLOW) {
                                button.setBackground(Color.BLACK);
                                myMain.grid[l][m] = 0;
                            } else {
                                button.setBackground(Color.YELLOW);
                                myMain.grid[l][m] = 1;
                            }
                        }
                    }
                });
                panel.add(myMain.buttons[i][j]);

                if (button.getBackground() == Color.BLACK) {
                    myMain.grid[i][j] = 0;
                } else {
                    myMain.grid[i][j] = 1;
                }
            }
        }

        frame.add(panel);
        panel.add(startButton);
        panel.add(stopButton);
        frame.setVisible(true);
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int i, int j, int k) {
        this.grid[i][j] = k;
    }
}
