
    public class Main {
    public static void main(String[] args) {
        ERyder bike1 = new ERyder();
        System.out.println("");
        bike1.printBikeDetails();
        
        System.out.println();  
        
        ERyder bike2 = new ERyder("B001", 80, true, 120.5);
        System.out.println("");
        System.out.println("：");
        bike2.ride();
        System.out.println("\n：");
        bike2.printBikeDetails();
    }
}

