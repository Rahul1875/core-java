package src.main.java.org.example;

import java.util.Arrays;

public class MoveZerosToEnd {

    public static void main(String[] args) {

        int[] arr = new int[] {1,0,2,0,5,0,0,3};
        int n = arr.length;
        int count =0;
        for (int i =0; i<n; i++) {
            if (arr[i] != 0) {
                arr[count++] = arr[i];
            }
        }
        while (count<n) arr[count++] =0;

        Arrays.stream(arr).forEach(System.out::print);


    }
}
