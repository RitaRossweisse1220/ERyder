import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
public class BikeRental{
    private boolean isRegisteredUser;
    private String emailAddress;
    private String location;
    private LocalDateTime tripStartTime;
    private String bikeID;
    private boolean locationValid;
    private UserRegistration userReg;
    private  ActiveRental  activeRental;
    private List< ActiveRental>activeRentalsList = new LinkedList<>();
    private String analyseRequest(){
        if (isRegisteredUser){
            System.out.println("Welcome back,"+ emailAddress+"!");

        }
        else{
            System.out.println("You're not our registered user. Please consider registering.");
            UserRegistration ur =  new UserRegistration();
            ur.registration();
        }
        return vaildateLocation(location);
    }
    private String vaildateLocation(String location){
        for(Bike bike : BikeDatabase.bikes){
            if(bike.getLocation().equals(location)&& bike.isAvailable()){
                System.out.println("A bike is available at the location you requested.");
                locationValid = true;
                return bike.getBikeID();
            
            }
        }
        System.out.println(" Sorry, no bikes are available at the location you requested. Please try again later.");
        locationValid = false;
        return null;
    }
    private void reserveBike(String bikeID) {
        if (bikeID == null) {
            System.out.println("Sorry, we're unable to reserve a bike at this time. Please try again later.");
            return;
        }

        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                tripStartTime = LocalDateTime.now();
                bike.setAvailable(false);
                bike.setLastUsedTime(tripStartTime);
                System.out.println("Reserving the bike with ID " + bikeID + ". Please follow the on-screen instructions to locate the bike and start your pleasant journey.");

                ActiveRental rental = new ActiveRental(bikeID, emailAddress, tripStartTime);
                activeRentalsList.add(rental);
                break;
            }
        }
    }

    public void viewActiveRentals() {
        if (activeRentalsList.isEmpty()) {
            System.out.println("No active rentals at the moment.");
        } else {
            for (ActiveRental rental : activeRentalsList) {
                System.out.println(rental);
            }
        }
    }

    private void removeTrip(String bikeID) {
        java.util.Iterator<ActiveRental> iterator = activeRentalsList.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            ActiveRental rental = iterator.next();
            if (rental.getBikeID().equals(bikeID)) {
                iterator.remove();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No active rental found for bike ID: " + bikeID);
            return;
        }

        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setAvailable(true);
                bike.setLastUsedTime(LocalDateTime.now());
                System.out.println("Your trip has ended. Thank you for riding with us.");
                break;
            }
        }
    }

    public void simulateApplicationInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("This is the simulation of the e-bike rental process.");

        System.out.print("Is registered user? (true/false): ");
        isRegisteredUser = scanner.nextBoolean();
        scanner.nextLine();

        System.out.print("Enter email address: ");
        emailAddress = scanner.nextLine();

        System.out.print("Enter location: ");
        location = scanner.nextLine();

        System.out.println("Simulating the analysis of the rental request.");
        String foundBikeID = analyseRequest();
        this.bikeID = foundBikeID;

        if (!locationValid) {
            scanner.close();
            return;
        }

        System.out.println("Simulating e-bike reservation...");
        reserveBike(bikeID);

        System.out.println("Displaying the active rentals...");
        viewActiveRentals();

        System.out.println("Simulating the end of the trip...");
        removeTrip(bikeID);

        System.out.println("Displaying the active rentals after trip end...");
        viewActiveRentals();

        scanner.close();
    }
}