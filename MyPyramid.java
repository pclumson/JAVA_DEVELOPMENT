
// import Car.java;



public class MyPyramid {

    public static class Car {

    // private car VariableElement
    private String make;
    private String model;
    private int year;

    // car constructor
    public Car (String make, String model, int year){

        this.make = make;
        this.model = model;
        this.year = year;

    }

    //Getter accessor for car make
    public String getCarMake(){

        return make;
    }

    //Getter accessor for car model
    public String getCarMofel(){

        return model;
    }

    //Getter accessor for car year
    public int getCarYear(){

        return year;
    }

    //Setter accessor to set car make
    public void setCarMake(String make){

         this.make = make;
    }

    // Setter accessor to set car model
    public void setCarModel(String model){

        this.model = model;
    }

    // Setter accessor to set car year
    public void setCarYear(int year){

        this.year = year;

    }

    // Helper function to display the car
    public void displayCar(){
        System.out.println("Car make :" + make);
        System.out.println("Car model :" + model);
        System.out.println("Car year :" + year);

    }

}

    // Method to display MyPyramid
    public static void displayMyPyramid(){

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

     public static void main (String[] args) {

             displayMyPyramid();

             // Creating a car object

             Car c = new Car("Mercedes", "C550", 2024);

             c.displayCar();


      }
}
