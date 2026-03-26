package com.example.graded.exercise.one;
import java.util.*;

public class AdminPanel {
    private List<RegisteredUsers> registeredUsersList = new ArrayList<>();

    public void userManagementOptions(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("\nWelcome to E-Ryder Administrator Panel. What do you want to do?");
            System.out.println("1. Add New Users");
            System.out.println("2. View Registered Users");
            System.out.println("3. Remove Registered Users");
            System.out.println("4. Update Registered Users");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice){
                case 1:
                    addNewUsers(scanner);
                    break;
                case 2:
                    viewRegisteredUsers();
                    break;
                case 3:
                    removeRegisteredUsers(scanner);
                    break;
                case 4:
                    updateRegisteredUsers(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addNewUsers(Scanner scanner){
        System.out.print("How many users would you like to add? ");
        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Enter details for user " + (i + 1) + " ---");

            System.out.print("Full Name: ");
            String fullName = scanner.nextLine();

            System.out.print("Email Address: ");
            String email = scanner.nextLine();

            System.out.print("Date of Birth (YYYY-MM-DD): ");
            String dob = scanner.nextLine();

            System.out.print("Card Number: ");
            long cardNum = scanner.nextLong();
            scanner.nextLine();

            System.out.print("Card Expiry Date (MM/YY): ");
            String expiry = scanner.nextLine();

            System.out.print("Card Provider (Visa/Mastercard/Amex): ");
            String provider = scanner.nextLine();

            System.out.print("CVV: ");
            int cvv = scanner.nextInt();
            scanner.nextLine();

            System.out.print("User Type (Regular User / VIP User): ");
            String userType = scanner.nextLine();

            String[] trips = new String[3];
            for (int j = 0; j < 3; j++) {
                System.out.println("Trip " + (j + 1) + " details");
                System.out.print("Date (YYYY-MM-DD): ");
                String tripDate = scanner.nextLine();
                System.out.print("Source: ");
                String source = scanner.nextLine();
                System.out.print("Destination: ");
                String dest = scanner.nextLine();
                System.out.print("Fare : ");
                double fare = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Feedback : ");
                String feedback = scanner.nextLine();

                StringBuilder tripDesc = new StringBuilder();
                tripDesc.append("Date: ").append(tripDate).append(", Source: ").append(source).append(", Destination: ").append(dest).append(", Fare (€): ").append(fare).append(", Feedback: ").append(feedback);
                trips[j] = tripDesc.toString();
            }

            RegisteredUsers user = new RegisteredUsers(fullName, email, dob, cardNum, expiry, provider, cvv, userType, trips);
            registeredUsersList.add(user);
        }
        System.out.println(count + " user(s) added successfully.");
    }

    private void viewRegisteredUsers(){
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to display.");
            return;
        }
        System.out.println("\n Registered Users ");
        for (RegisteredUsers user : registeredUsersList) {
            System.out.println(user);
           
        }
    }

    private void removeRegisteredUsers(Scanner scanner){
        if (registeredUsersList.isEmpty()){
            System.out.println("No registered users to remove.");
            return;
        }
        System.out.print("Enter email address of user to remove: ");
        String email = scanner.nextLine();

        Iterator<RegisteredUsers> iterator = registeredUsersList.iterator();
        boolean found = false;
        while(iterator.hasNext()){
            RegisteredUsers user = iterator.next();
            if(user.getEmailAddress().equals(email)){
                iterator.remove();
                found = true;
                System.out.println("User with email " + email + " removed.");
                break;
            }
        }
        if(!found){
            System.out.println("No user found with this email address.");
        }
    }

    private void updateRegisteredUsers(Scanner scanner){
        if(registeredUsersList.isEmpty()){
            System.out.println("No registered users to update.");
            return;
        }
        System.out.print("Enter email address of user to update: ");
        String email = scanner.nextLine();

        RegisteredUsers targetUser = null;
        for (RegisteredUsers user : registeredUsersList){
            if(user.getEmailAddress().equals(email)){
                targetUser = user;
                break;
            }
        }
        if(targetUser == null){
            System.out.println("No user found with this email address.");
            return;
        }

        System.out.println("Leave blank to keep current value (for numbers, enter 0 to keep).");

        System.out.print("New full name (current: " + targetUser.getFullName() + "): ");
        String input = scanner.nextLine();
        if (!input.isEmpty()) {
            targetUser.setFullName(input);
        }

        System.out.print("New email address (current: " + targetUser.getEmailAddress() + "): ");
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            targetUser.setEmailAddress(input);
        }

        System.out.print("New date of birth (current: " + targetUser.getDateOfBirth() + "): ");
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            targetUser.setDateOfBirth(input);
        }

        System.out.print("New card number (current: " + targetUser.getCardNumber() + "): ");
        input = scanner.nextLine();
        if (!input.isEmpty() && !input.equals("0")) {
            targetUser.setCardNumber(Long.parseLong(input));
        }

        System.out.print("New card expiry date (current: " + targetUser.getCardExpiryDate() + "): ");
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            targetUser.setCardExpiryDate(input);
        }

        System.out.print("New card provider (current: " + targetUser.getCardProvider() + "): ");
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            targetUser.setCardProvider(input);
        }

        System.out.print("New CVV (current: " + targetUser.getCvv() + "): ");
        input = scanner.nextLine();
        if (!input.isEmpty() && !input.equals("0")) {
            targetUser.setCvv(Integer.parseInt(input));
        }

        System.out.print("New user type (current: " + targetUser.getUserType() + "): ");
        input = scanner.nextLine();
        if (!input.isEmpty()) {
            targetUser.setUserType(input);
        }

        System.out.println("User updated successfully.");
    }
}