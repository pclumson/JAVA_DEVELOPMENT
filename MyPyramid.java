
public class MyPyramid {
     public static void main (String[] args) {

              System.out.println("Hello Prince!");
        
        int rows = 10, k = 0;

        for (int i = 1; i <= rows; ++i, k = 0)
        {
            for(int space = 1; space <= rows - i; ++space)
            {
                System.out.print(" ");
            }

            while (k != 2 * i - 1)
            {
                System.out.print("*");
                ++k;
            }

            System.out.println();
        }

      }
}