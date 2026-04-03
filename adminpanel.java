import java.util.List;
import java.util.Scanner;

public class AdminPanel {
    private UserService userService;
    private BikeService bikeService;
    private RentalService rentalService;

    public AdminPanel(UserService userService, BikeService bikeService, RentalService rentalService) {
        this.userService = userService;
        this.bikeService = bikeService;
        this.rentalService = rentalService;
    }
    private void managePendingRequests(Scanner scanner) {
    while (true) {
        System.out.println(" Manage Pending Bike Requests ");
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
            System.out.println("\n=== Admin Panel ===");
            System.out.println("1. Add User");
            System.out.println("2. List Users");
            System.out.println("3. Remove User");
            System.out.println("4. List Available Bikes");
            System.out.println("5. Start Rental");
            System.out.println("6. End Rental");
            System.out.println("7. List Active Rentals");
            System.out.println("8. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("User ID: ");
                    String userId = scanner.nextLine();
                    userService.addUser(userId);
                    System.out.println("User added.");
                    break;
                case 2:
                    List<String> users = userService.retrieveUsers();
                    System.out.println("Users: " + users);
                    break;
                case 3:
                    System.out.print("User ID to remove: ");
                    String removeId = scanner.nextLine();
                    userService.removeUser(removeId);
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
                    System.out.print("User ID: ");
                    String rentUserId = scanner.nextLine();
                    System.out.print("Bike ID: ");
                    String rentBikeId = scanner.nextLine();
                    if (rentalService.startRental(rentUserId, rentBikeId)) {
                        System.out.println("Rental started.");
                    } else {
                        System.out.println("Rental failed.");
                    }
                    break;
                case 6:
                    System.out.print("Rental ID: ");
                    String rentalId = scanner.nextLine();
                    if (rentalService.endRental(rentalId)) {
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
                    System.out.println("Exiting.");
                    scanner.close();
                    return;
                case 9:
                    System.out.println(" System Logs ");
                    bikeService.viewSystemLogs();
                    break;
                case 10:
                    managePendingRequests(scanner);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}