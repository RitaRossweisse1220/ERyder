 import java.util.List;
import java.util.Scanner;

public class AdminPanel {
    private UserService userService;
    private BikeService bikeService;
    private RentalService rentalService;
    private RegisteredUsers currentUser;

    public AdminPanel(UserService userService, BikeService bikeService, RentalService rentalService) {
        this.userService = userService;
        this.bikeService = bikeService;
        this.rentalService = rentalService;
    }

    private void managePendingRequests(Scanner scanner) {
        while (true) {
            System.out.println("Manage Pending Bike Requests");
            System.out.println("1. View Queue");
            System.out.println("2. Update Queue");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int opt = scanner.nextInt();
            scanner.nextLine();
            if (opt == 1) {
                var queue = bikeService.getRequestQueue();
                if (queue.isEmpty()) System.out.println("Queue is empty.");
                else for (var req : queue) System.out.println(req);
            } else if (opt == 2) {
                var removed = bikeService.getRequestQueue().poll();
                if (removed != null) System.out.println("Removed: " + removed);
                else System.out.println("Queue is empty.");
            } else if (opt == 3) break;
            else System.out.println("Invalid choice.");
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(" Admin Panel ");
            System.out.println("1. Add User");
            System.out.println("2. List Users");
            System.out.println("3. Remove User");
            System.out.println("4. List Available Bikes");
            System.out.println("5. Start Rental");
            System.out.println("6. End Rental");
            System.out.println("7. List Active Rentals");
            System.out.println("8. View System Logs");
            System.out.println("9. Manage Pending Bike Requests");
            System.out.println("10. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Full name: ");
                    String fullName = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("User type (VIP/Regular): ");
                    String userType = scanner.nextLine();
                    currentUser = userService.addUser(fullName, email, phone, userType);
                    System.out.println("User added.");
                    break;
                case 2:
                    List<RegisteredUsers> users = userService.retrieveUsers();
                    System.out.println("Users:");
                    for (RegisteredUsers u : users) {
                        System.out.println(" - " + u.getFullName() + " (" + u.getEmailAddress() + ")");
                    }
                    break;
                case 3:
                    System.out.print("Email of user to remove: ");
                    String removeEmail = scanner.nextLine();
                    userService.removeUser(removeEmail);
                    System.out.println("User removed.");
                    break;
                case 4:
                    List<Bike> bikes = bikeService.findAvailableBikes();
                    System.out.println("Available bikes:");
                    for (Bike b : bikes) {
                        System.out.println(" - " + b.getId() + ": " + b.getModel());
                    }
                    break;
                case 5:
                    if (currentUser == null) {
                        System.out.println("No user selected. Please add a user first.");
                        break;
                    }
                    System.out.print("Bike ID: ");
                    String rentBikeId = scanner.nextLine();
                    System.out.print("Location: ");
                    String location = scanner.nextLine();
                    if (rentalService.startRental(currentUser.getEmailAddress(), rentBikeId, location)) {
                        System.out.println("Rental started.");
                    } else {
                        System.out.println("Rental failed.");
                    }
                    break;
                case 6:
                    if (currentUser == null) {
                        System.out.println("No user selected.");
                        break;
                    }
                    System.out.print("Rental ID: ");
                    String rentalId = scanner.nextLine();
                    if (rentalService.endRental(rentalId, currentUser)) {
                        System.out.println("Rental ended.");
                    } else {
                        System.out.println("Rental not found.");
                    }
                    break;
                case 7:
                    List<ActiveRental> rentals = rentalService.trackActiveRentals();
                    System.out.println("Active rentals:");
                    for (ActiveRental r : rentals) {
                        System.out.println(" - " + r.getRentalId() + ": user " + r.getUserId() + ", bike " + r.getBikeId());
                    }
                    break;
                case 8:
                    System.out.println(" System Logs ");
                    bikeService.viewSystemLogs();
                    break;
                case 9:
                    managePendingRequests(scanner);
                    break;
                case 10:
                    System.out.println("Exiting.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}