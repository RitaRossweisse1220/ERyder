public class RegularUser extends RegisteredUsers{
    public RegularUser(String fullName, String emailAddress, String phoneNumber) {
        super(fullName, emailAddress, phoneNumber);
    }
    @Override
    public double calculateFare(double baseFare){
        return super.calculateFare(baseFare);
    }
    @Override
    public void displayUserInfo(){
        System.out.println("Regular User ");
    }
}