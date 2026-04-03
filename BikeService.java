import java.time.LocalDateTime;
import java.util.*;

public class BikeService {
    private BikeDatabase bikeDatabase;
    private Deque<ERyderLog> logStack;      
    private Queue<BikeRequest> requestQueue;

    public BikeService(BikeDatabase bikeDatabase) {
        this.bikeDatabase = bikeDatabase;
        logStack = new ArrayDeque<>();
        requestQueue = new ArrayDeque<>();
    }

    public List<Bike> findAvailableBikes() {
        List<Bike> available = new ArrayList<>();
        for (Bike b : bikeDatabase.getAllBikes()) {
            if (b.isAvailable()) available.add(b);
        }
        return available;
    }

    public boolean validateLocation(String location) {
        return location != null && !location.trim().isEmpty();
    }
    public boolean reserveBike(String bikeId, String userEmail, String location) {
        Bike bike = bikeDatabase.findBikeById(bikeId);
        if (bike != null && bike.isAvailable()) {
            bike.setAvailable(false);
            String logId = "BR" + System.currentTimeMillis();
            String event = "Bike " + bikeId + " rented by " + userEmail + " from " + location;
            logStack.push(new ERyderLog(logId, event, LocalDateTime.now()));
            return true;
        } else {
            BikeRequest req = new BikeRequest(userEmail, location, LocalDateTime.now());
            requestQueue.offer(req);
            return false;
        }
    }
    public void releaseBike(String bikeId) {
        Bike bike = bikeDatabase.findBikeById(bikeId);
        if (bike != null) {
            bike.setAvailable(true);
            String logId = "TE" + System.currentTimeMillis();
            String event = "Trip ended for bike " + bikeId;
            logStack.push(new ERyderLog(logId, event, LocalDateTime.now()));
            processNextRequest();
        }
    }
    public void addLogForTripStart(String bikeId, String userEmail, String location) {
        String logId = "TS" + System.currentTimeMillis();
        String event = "Trip started for bike " + bikeId + " by " + userEmail + " at " + location;
        logStack.push(new ERyderLog(logId, event, LocalDateTime.now()));
    }
    public void viewSystemLogs() {
        if (logStack.isEmpty()) {
            System.out.println("No logs.");
            return;
        }
        for (ERyderLog log : logStack) {
            System.out.println(log);
        }
    }

    public Queue<BikeRequest> getRequestQueue() {
        return requestQueue;
    }
    private void processNextRequest() {
        if (requestQueue.isEmpty()) return;
        List<Bike> available = findAvailableBikes();
        if (available.isEmpty()) return;
        BikeRequest next = requestQueue.poll();
        Bike bike = available.get(0);  
        reserveBike(bike.getId(), next.getUserEmail(), next.getLocation());
        addLogForTripStart(bike.getId(), next.getUserEmail(), next.getLocation());
        System.out.println("Assigned bike " + bike.getId() + " to waiting user " + next.getUserEmail());
    }
}