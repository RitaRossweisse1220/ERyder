public class ERyder {
    private String bikeID;
    private int batteryLevel;
    private boolean isAvailable;
    private double Kmdriven;
public ERyder() {
    bikeID = "";
    batteryLevel = 100;
    isAvailable = true;
    Kmdriven = 0.0;
}
public ERyder(String bikeID,int batteryLevel,boolean isAvailable,double Kmdriven){
    this.bikeID=bikeID;
    this.batteryLevel=batteryLevel;
    this.isAvailable=isAvailable;
    this.Kmdriven=Kmdriven;
}
public void  setbatterylevel(int batteryLevel){
        if (batteryLevel >=0 && batteryLevel <=100){
            this.batteryLevel=batteryLevel;
        }
    }
public String getbikeID(){
    return bikeID;
}
    public int getbatteryLevel(){
    return batteryLevel;
}
    public boolean isAvailable(){
    return isAvailable;
}
    public double getKmdriven(){
    return Kmdriven;
}    
public void ride(){
    if(isAvailable && batteryLevel > 0){
        System.out.println("bike s available");

}
else{
    System.out.println("bike is not available");                            
    }
public void printBikeDetails(){
    System.out.println("bikeID: " + bikeID);
    System.out.println("batteryLevel: " + batteryLevel);
    System.out.println("isAvailable: " + isAvailable);
    System.out.println("Kmdriven: " + Kmdriven);
    }
}
