import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<RegisteredUsers> registeredUsersList;

    public UserService() {
        registeredUsersList = new ArrayList<>();
    }

    public RegisteredUsers addUser(String fullName, String emailAddress, String phoneNumber, String userType) {
        RegisteredUsers newUser;
        if (userType != null && userType.equalsIgnoreCase("VIP")) {
            newUser = new VIPUser(fullName, emailAddress, phoneNumber);
        } else {
            newUser = new RegularUser(fullName, emailAddress, phoneNumber);
        }
        registeredUsersList.add(newUser);
        return newUser;
    }

    public void removeUser(String emailAddress) {
        RegisteredUsers user = findUserByEmail(emailAddress);
        if (user != null) {
            registeredUsersList.remove(user);
        }
    }

    public void updateUser(String emailAddress, String newFullName, String newPhoneNumber) {
        RegisteredUsers user = findUserByEmail(emailAddress);
        if (user != null) {
            user.setFullName(newFullName);
            user.setPhoneNumber(newPhoneNumber);
        }
    }

    public List<RegisteredUsers> retrieveUsers() {
        return registeredUsersList;
    }

    public RegisteredUsers findUserByEmail(String emailAddress) {
        for (RegisteredUsers user : registeredUsersList) {
            if (user.getEmailAddress().equalsIgnoreCase(emailAddress)) {
                return user;
            }
        }
        return null;
    }
}