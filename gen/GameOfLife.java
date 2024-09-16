package src.main.java.org.example;

import java.util.Scanner;

public class GameOfLife {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Gird Size");
        int girdSize = sc.nextInt();

        System.out.println("Generation Upto Applied Or Total Future Generation");
        int futureGen = sc.nextInt();

        Grid grid = new Grid(girdSize);
        int[][] currentGen = grid.initialState();
        System.out.println("Seeds in the System");
        printResults(currentGen);



        for (int i=0; i<futureGen; i++) {
            int[][] nextGen = grid.nextGeneration(currentGen);
            System.out.println(i+1 + " Generation");
            printResults(nextGen);
            currentGen = nextGen;
        }
    }

    /**
     * print Grid
     */
    private static void printResults(int[][] results) {

        // Note : Alive is denoted by 1
        //        Dead is denoted by 0

        for (int[] result : results) {
            for (int i : result) {
                System.out.print(i +" ");
            }
            System.out.println();
        }
    }
}
