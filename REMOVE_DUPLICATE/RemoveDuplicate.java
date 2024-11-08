import java.util.Arrays;

// Driver Class
public class RemoveDuplicate {
    // Function to remove duplicates from an arary
    public static int[] removeDuplicates(int[] arr) {
        // Sort the array
        Arrays.sort(arr);
        // Initialize the j pointer
        int j = 0;
        // Start iterating the array from the 1st index
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[j]) {
                j++;
                // Move unique element to the next position
                arr[j] = arr[i];
            }
        }
        // Return copy of array upto index j without duplicates
        return Arrays.copyOf(arr, j + 1);
    }

      // Main Function
    public static void main(String[] args) {
        // Initialize an array
        int[] arr = {1,2,2,3,7,4,4,5,7,6};
        // Remove duplicates from the array
        int[] result = removeDuplicates(arr);
        // Print the original array
        System.out.println("Original Array: "+Arrays.toString(arr));
         // Print the updated array
        System.out.println("Array without Duplicates: " +Arrays.toString(result));
    }
}
