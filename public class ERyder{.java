public class ERyder{
    public static final String COMPANY_NAME = "ERyder";
    public static final double BASE_FARE = 1.0;
    public static final double PER_MINUTE_FARE = 0.5;
    public final String LINKED_ACCOUNT;
    public final String LINKED_PHONE_NUMBER;
    private int totalUsagelMinutes;
    private double totalFare;
public ERyder(String bikeID,int batteryLevel,boolean isAvailable,double Kmdriven,String linkedAccount, String linkedPhoneNumber){
    this.bikeID=bikeID;
    this.batteryLevel=batteryLevel;
    this.isAvailable=isAvailable;
    this.Kmdriven=Kmdriven;
    this.LINKED_ACCOUNT = linkedAccount;
    this.LINKED_PHONE_NUMBER = linkedPhoneNumber;
}
public void printRideDetails(int UsagelMinutes) {
        totalUsagelMinutes = UsagelMinutes;
        totalFare = calculateFare(UsagelMinutes);
    System.out.println("Company_Name: " + COMPANY_NAME);
    System.out.println("Base_Fare: " + BASE_FARE);
    System.out.println("Per_Minute_Fare: " + PER_MINUTE_FARE);
    System.out.println("Linked_Account: " + LINKED_ACCOUNT);
    System.out.println("Linked_Phone_Number: " + LINKED_PHONE_NUMBER);
    System.out.println("Total_Usage_Minutes: " + totalUsagelMinutes);
    System.out.println("Total_Fare: " + totalFare);
}
private double calculateFare(int UsagelMinutes) {
    return BASE_FARE + (PER_MINUTE_FARE * UsagelMinutes);
}
}