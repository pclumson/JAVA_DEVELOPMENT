// Sample Java OOP Car Class

public class Car {

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
        System.out.println("Car model:" + model);
        System.out.println("Car year:" + year);

    }

    public static void main(String[] args)
    {

         Car c = new Car("Infinity", "C550", 2023);

         c.displayCar();
    }






}
