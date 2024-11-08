// sample gris search class

class GridSearch {

    public boolean searchGrid(int[][] grid, int target){

        for (int row = 0; row < grid.length; row++){

            for (int col = 0; col < grid[row].length; col++){

                if (grid[row][col] == target){
                    System.out.println("Target:" +" " + target + " "+ "found at position:(" + row + ","+ col + ")");
                    return true;
                }
            }
        }
        System.out.println("Target" + target + "is not found !" );
        return false;

    }

    public static void main(String[] args){

        GridSearch gS = new GridSearch();

        int[][] grid = {

            {1, 2, 3},
            {4, 7, 8},
            {1, 2, 5}
        };

        int target = 8;

        gS.searchGrid(grid, target);

    }

}
