public class ERyder {
    // 私有变量
    private String bikeID;
    private int batteryLevel;
    private boolean isAvailable;
    private double kmDriven;
    public ERyder() {
        bikeID = "";
        batteryLevel = 0;
        isAvailable = true;
        kmDriven = 0.0;
    }
    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, double kmDriven) {
        this.bikeID = bikeID;
        this.batteryLevel = batteryLevel;
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
    }
    public void setBatteryLevel(int batteryLevel) {
        if(batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        }
    }
    public String getBikeID() {
        return bikeID;
    }
    
    public int getBatteryLevel() {
        return batteryLevel;
    }
    
    public boolean getIsAvailable() {
        return isAvailable;
    }
    
    public double getKmDriven() {
        return kmDriven;
    }
    public void ride() {
        if(batteryLevel > 0 && isAvailable) {
            System.out.println("Bike is available for ride");
        } else {
            System.out.println("Bike is not available");
        }
    }
    public void printBikeDetails() {
        System.out.println("Bike ID: " + bikeID);
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Available: " + isAvailable);
        System.out.println("Km Driven: " + kmDriven + " km");
    }
}