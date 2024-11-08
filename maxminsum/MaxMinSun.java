import java.util.Arrays;

public class MaxMinSun {

    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1, 9};

        // Find maximum and minimum elements
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();

        // Calculate the sum
        int sum = max + min;

        System.out.println("Maximum element: " + max);
        System.out.println("Minimum element: " + min);
        System.out.println("Sum of maximum and minimum: " + sum);
    }
}
