public class GameLogic {


    private int gridColumns = 25;
    private int gridRows = 25;

    public void updateCells(int[][] grid) {

        int neighbours = 0;

        //copy given grid
        int[][] copyGrid = new int[grid.length][grid.length];
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {
                copyGrid[x][y] = grid[x][y];
            }
        }

        //calculate neighbors for each button
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                for (int a = -1; a < 2; a++) {
                    for (int b = -1; b < 2; b++) {
                        if (a == 0 && b == 0) ;
                        else if(i == 0 && j == 0);
                        else if (i == 0 && j == gridColumns) ;
                        else if (i == gridRows && j == 0) ;
                        else if (i == gridRows && j == gridColumns) ;
                        else if (i == 0) ;
                        else if (i == gridRows-1) ;
                        else if (j == 0) ;
                        else if (j == gridColumns-1) ;
                        else {
                            if (grid[i + a][j + b] == 1) {
                                neighbours += 1;
                            }
                        }
                    }
                    copyGrid[i][j] = getNextIteration(grid[i][j], neighbours);
                }
                //reset neighbours
                neighbours = 0;
            }
        }

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {
                grid[x][y] = copyGrid[x][y];
            }
        }
    }

    //get next iteration of cell (depending on number of neighbours)
    public int getNextIteration ( int i, int n){
        if (i == 1 && n < 2) {
            return 0;
        } else if (i == 1 && (n == 2 || n == 3)) {
            return 1;
        } else if (i == 1 && n > 3) {
            return 0;
        } else if (i == 0 && n == 3) {
            return 1;
        }
        return i;
    }

    public int getGridColumns () {
        return gridColumns;
    }

    public int getGridRows () {
        return gridRows;
    }
}
