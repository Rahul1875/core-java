package src.main.java.org.example;

public class SecondLargestElement {

    public static void  main(String[] args) {

        int[] arr = {12, 35, 1, 10, 34,1};

        if(arr.length < 2) System.out.println("-1");

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for(int value : arr) {
            if(value > first) {
                second = first;
                first = value;

            } else if(value > second && value != first) {
                second = value;
            }
        }
        System.out.println(second);
    }

}
