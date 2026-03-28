import java.util.*;
public class AdminPanel{
    private List<RegisteredUsers> registeredUsersList = new ArrayList<>();
    public void userManagementOptions(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Welcome to E-Ryder Admininstrator Panel.What do you want to do?");
            System.out.println("1. Add New Users");
            System.out.println("2. View Registered Users");
            System.out.println("3. Remove Registered Users");
            System.out.println("4. Update Registered Users");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch(choice){
                case 1:
                    addNewUser(scanner);
                    break;
                case 2:
                    viewRegisteredUsers();
                    break;
                case 3:
                    removeRegisteredUsers(scanner);
                    break;
                case 4:
                    updateRegisteredUsers(scanner);
                    break;
                case 5:
                    System.out.println("Exiting");
                    return;
                case 6:   
                    BikeRental rental = new BikeRental();
                    rental.simulateApplicationInput();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private void addNewUser(Scanner scanner){
        System.out.println("How many users do you want to add?");
        int count = scanner.nextInt();
        scanner.nextLine(); 
        for(int i = 0; i < count; i++){
            System.out.println("\nEnter details for user " + (i + 1) + ":");
            System.out.print("Username: ");
            String userName = scanner.nextLine();
            System.out.print("Email Address: ");
            String userEmail = scanner.nextLine();
            System.out.print("dateOfBirth (YYYY-MM-DD): ");
            String dateOfBirth = scanner.nextLine();
            System.out.print("Card Number: ");
            long cardNumber = scanner.nextLong();
            scanner.nextLine();
            System.out.print("Card Expiry Date (MM/YY): ");
            String cardExpiryDate = scanner.nextLine();
            System.out.print("Card Provider(Visa/MasterCard/American Express): ");
            String cardProvider = scanner.nextLine();
            System.out.print("CVV: ");
            int cvv = scanner.nextInt();
            scanner.nextLine();
            System.out.print("User Type(Regular User/ VIP User): ");
            String userType = scanner.nextLine();
        }
        String[] trips = new String[3];
        for(int i = 0; i < 3; i++){
            System.out.println(" Trip " + (i + 1) + "details:");
            System.out.print("Trip Date (YYYY-MM-DD): ");
            String tripDate = scanner.nextLine();
            System.out.print("Source: ");
            String source = scanner.nextLine();
            System.out.print("Destination: ");
            String Destination = scanner.nextLine();
            System.out.print("Fare: ");
             double fare = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Feedback: ");
            String feedback = scanner.nextLine();
    }
}
}