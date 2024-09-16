package src.main.java.org.example;

import java.util.Random;

public class Grid {

    private int[][] grid;
    private int rows;
    private int columns;
    private int size;

    int[] dx = {0, 0, 1, 1, 1, -1, -1, -1}; // 8 coordinates for neighbors
    int[] dy = {1, -1, 1, -1, 0, 0, 1, -1};

    /**
     * generate the initial Grid state
     */
    public Grid(int size) {
        this.size = size;
        grid = new int[size][size];

        // Note : Alive is denoted by 1
        //        Dead is denoted by 0
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                grid[i][j] = new Random().nextInt(2);
            }
        }

        this.rows = this.grid.length;
        this.columns = this.grid[0].length;
    }


    /**
     * get initial State
     */
    public int[][] initialState() {
        return this.grid;
    }

    /**
     * compute next Generation
     */
    public int[][] nextGeneration(int[][] currentGen) {
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {

                int live_neighbors = 0;
                for (int i=0; i<8; i++) {
                    int currX = row + dx[i];
                    int currY = col + dy[i];
                    if(checkValidNeighbor(currX, currY) && Math.abs(currentGen[currX][currY]) == 1) {
                        live_neighbors++;
                    }
                }

                if (currentGen[row][col] == 1 && (live_neighbors < 2 || live_neighbors > 3)) {
                    currentGen[row][col] = -1;
                }

                if (currentGen[row][col] == 0 && live_neighbors == 3) {
                    currentGen[row][col] = 2;
                }
            }
        }

        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {
                if (currentGen[row][col] >= 1)
                    currentGen[row][col] = 1;
                else
                    currentGen[row][col] = 0;
            }
        }
        return currentGen;
    }

    /**
     * check if Neighbor is valid
     */
    private boolean checkValidNeighbor(int x, int y) {
        return (x >= 0 && x < this.rows && y >= 0 && y < this.columns);
    }

}
