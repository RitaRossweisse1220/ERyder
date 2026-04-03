import java.util.Arraylist;
import java.util.List;
import java.util.UUID;
public class RentalService{
    private List<ActiveRental> activeRentals = new ArrayList<>();
    private BikeService bikeService;
    public RentalService(BikeService bikeService){
        this.bikeService = bikeService;
    }
    public boolean startRental(String userId, String bikeId){
        if(bikeService.isBikeAvailable(bikeId)){
            String rentalId = UUID.randomUUID().toString();
            activeRentals.add(new ActiveRental(rentalId, userId, bikeId));
            bikeService.addLogForTripStart(userId, bikeId);
            return true;
        }
        return false;
    }
        
        public boolean endRental(String rentalId){
            ActiveRental rental = findActiveRental(rentalId);
            if(rental != null){
                activeRentals.remove(rental);
                bikeService.addLogForTripEnd(rentalId);
                return true;
            }
            return false;
        }
    
    
    
    public boolean cancelRental(String rentalId){
        return endRental(rentalId);
    }
    public List<ActiveRental>trackActiveRentals(){
        return activeRentals;
 
    }
    private ActiveRental findActiveRental(String rentalId){
        for(ActiveRental rental : activeRentals){
            if(rental.getRentalId().equals(rentalId)){
                return rental;
            }
        }
        return null;
    }
}