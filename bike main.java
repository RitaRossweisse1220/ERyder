public class Main {
    public static void main(String[] args) {
        BikeDatabase bikeDb = new BikeDatabase();
        bikeDb.addBike(new Bike("B001", "City Bike", "Downtown"));
        bikeDb.addBike(new Bike("B002", "Mountain Bike", "Park"));
        bikeDb.addBike(new Bike("B003", "Road Bike", "Downtown"));
        RegisteredUsers usersDb = new RegisteredUsers();
        BikeService bikeService = new BikeService(bikeDb);
        RentalService rentalService = new RentalService(bikeService);
        UserService userService = new UserService(usersDb);
        AdminPanel panel = new AdminPanel(userService, bikeService, rentalService);
        panel.start();
    }
}