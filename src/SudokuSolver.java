import java.util.*;

public class SudokuSolver {

    public boolean SOLVE(int[][] array) {
        Stack<int[]> currentCell = new Stack<>(); // Stores location of empty cells (row, col)
        Stack<int[]> prevMoves = new Stack<>();  // Stores previous decisions made in a cell (row, col, value)
        @SuppressWarnings("unchecked")
        Set<Integer>[][] domain = new HashSet[9][9]; // Stores possible values for each cell

        // Initialize the state of the grid and empty cells
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (array[i][j] == 0) {
                    currentCell.push(new int[]{i, j});
                    domain[i][j] = new HashSet<>();
                    for (int n = 1; n <= 9; n++) {
                        if (isValid(array, i, j, n)) {
                            domain[i][j].add(n);
                        }
                    }
                }
            }
        }

        int r, c;   // row, col
        boolean noSolution = false;
        boolean foundValidMove;

        while (!currentCell.isEmpty()) {
            int[] cell = currentCell.peek();
            r = cell[0];
            c = cell[1];

            foundValidMove = false;

            Iterator<Integer> iterator = domain[r][c].iterator();
            while (iterator.hasNext()) {
                int n = iterator.next();
                if (isValid(array, r, c, n) && array[r][c] < n) {
                    array[r][c] = n;
                    currentCell.pop();
                    prevMoves.push(new int[]{r, c});
                    foundValidMove = true;
                    break;
                }
            }

            // Backtrack if there's no valid entry for the current cell
            if (!foundValidMove) {
                array[r][c] = 0;
                if (!prevMoves.isEmpty()) {
                    int[] prevCell = prevMoves.pop();
                    r = prevCell[0];
                    c = prevCell[1];

                    currentCell.push(new int[]{r, c});
                } else {
                    noSolution = true;
                    break;
                }
            }
        }

        return !noSolution;
    }

    public boolean isValid(int[][] array, int row, int col, int value){

        for (int i = 0; i < array.length; i++){
            if (array[row][i] == value || array[i][col] == value) return false;
        }
        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (array[i][j] == value) return false;
            }
        }

        return true;
    }


}
