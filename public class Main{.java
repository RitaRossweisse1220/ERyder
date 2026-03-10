public class Main{
    public static void main(String[] args) {
        ERyder bike1 = new ERyder("Bike1", 100,true,0.0,"RitaRossweisse","1220");
        bike1.printRideDetails(30);
        ERyder bike2 = new ERyder("Bike2", 1220,true,0.0,"RitaRossweisse","1220");
        bike2.printRideDetails(1220);
    
    bike1.calculateFare(30);
    bike2.calculateFare(1220);  
    }
}