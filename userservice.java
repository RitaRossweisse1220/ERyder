import java.util.List;

public class UserService {
    private RegisteredUsers registeredUsers;

    public UserService(RegisteredUsers registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    public void addUser(String userId) {
        if (!registeredUsers.userExists(userId)) {
            registeredUsers.addUser(userId);
        }
    }

    public void removeUser(String userId) {
        registeredUsers.removeUser(userId);
    }

    public void updateUser(String oldId, String newId) {
        if (registeredUsers.userExists(oldId) && !registeredUsers.userExists(newId)) {
            registeredUsers.removeUser(oldId);
            registeredUsers.addUser(newId);
        }
    }

    public List<String> retrieveUsers() {
        return registeredUsers.getAllUsers();
    }
}