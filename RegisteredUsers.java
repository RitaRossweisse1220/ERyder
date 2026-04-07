import java.util.ArrayList;
import java.util.List;

public class RegisteredUsers {
    private String fullName;
    private String emailAddress;
    private String phoneNumber;

    private static List<RegisteredUsers> allUsers = new ArrayList<>();

    public RegisteredUsers(String fullName, String emailAddress, String phoneNumber) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        allUsers.add(this);
    }

    public String getFullName() { return fullName; }
    public String getEmailAddress() { return emailAddress; }
    public String getPhoneNumber() { return phoneNumber; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public static List<RegisteredUsers> getAllUsers() {
        return allUsers;
    }

    public static RegisteredUsers findByEmail(String email) {
        for (RegisteredUsers user : allUsers) {
            if (user.getEmailAddress().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    public double calculateFare(double baseFare) {
        return baseFare;
    }

    public void displayUserType() {
        System.out.println("Regular User");
    }
}