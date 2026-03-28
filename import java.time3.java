import java.time.LocalDateTime;


public class Bike {
    private String bikeID;
    private boolean isAvailable;
    private int batteryLevel;
    private LocalDateTime lastUsedTime;
    private String location;

    public Bike(String bikeID, boolean isAvailable, int batteryLevel, LocalDateTime lastUsedTime, String location) {
        this.bikeID = bikeID;
        this.isAvailable = isAvailable;
        this.batteryLevel = batteryLevel;
        this.lastUsedTime = lastUsedTime;
        this.location = location;
    }

    public String getBikeID() { 
        return bikeID; 
    }
    

    public boolean isAvailable() { 
        return isAvailable; 
    }

    public int getBatteryLevel() { 
        return batteryLevel; 
    }

    public LocalDateTime getLastUsedTime() { 
        return lastUsedTime; 
    }
    

    public String getLocation() { 
        return location; 
    }
    

 
    public String toString() {
        return "Bike{" +
                "bikeID='" + bikeID + '\'' +
                ", isAvailable=" + isAvailable +
                ", batteryLevel=" + batteryLevel +
                ", lastUsedTime=" + lastUsedTime +
                ", location='" + location + '\'' +
                '}';
    }
}