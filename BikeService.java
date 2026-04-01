import java.util.List;
import java.util.stream.Collectors;
public class BikeService{
    private BikeDatabase bikeDatabase;
    public BikeService(BikeDatabase bikeDatabase) {
        this.bikeDatabase = bikeDatabase;
    }
    public List<Bike> findAvailableBikes() {
        return bikeDatabase.getAllBikes().stream()
                .filter(Bike::isAvailable)
                .collect(Collectors.toList());
    }
    private boolean validateLocation(String location) {
        return location != null && !location.isEmpty();
    }
    public boolean reserveBike(String bikeId){
        Bike bike = bikeDatabase.getBikeById(bikeId);
        if (bike != null && bike.isAvailable()) {
            bike.setAvailable(false);
            
            return true;
        }
        return false;
    
    }
    public void releaseBike(String bikeId) {
        Bike bike = bikeDatabase.getBikeById(bikeId);
        if (bike != null) {
            bike.setAvailable(true);
           
        }
    }
}