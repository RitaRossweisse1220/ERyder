import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
public class UserRegistration {
    public static final double VIP_DISCOUNT_UNDER_18_BIRTHDAY = 25;
    public static final double VIP_DISCOUNT_UNDER_18 = 20.0;
    public static final double VIP_BASE_FEE = 100.0;
    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardProvider;
    private String cardExpiryDate;
    private double feeToCharge;
    private int cvv;
    private String userType;
    private boolean emailVaild = false;
    private boolean minorAndBirthday = false;
    private boolean minor = false;
    private boolean ageVaild = false;
    private boolean cardNumberVaild = false;
    private boolean cardStillVaild = false;
    private boolean vaildCVV = false; 
    public void registerUser() {
        Scanner input = new Scanner(System.in);
        System.out.println("1.register as a regular user");
        System.out.println("2.register as a VIP user");
        System.out.print("Enter your choice(1 or 2): ");
        int choice = input.nextInt();
        input.nextLine();
        if( choice == 1) {
            userType = "Regular User";
        } else if (choice == 2) {
            userType = "VIP User";
        } 
        System.out.print("Enter your full name: ");
        fullName = input.nextLine();
        System.out.print("Enter your email address: ");
        emailAddress = input.nextLine();
        emailVaild = analyseEmail(emailAddress);
        System.out.print("Enter your date of birth (YYYY-MM-DD): ");
        dateOfBirth = input.nextLine();
        LocalDate dob = LocalDate.parse(dateOfBirth);
        ageVaild = analyseAge(dob);
        System.out.print("Enter your card number(Visa/Mastercard/Amex only): ");
        cardNumber = input.nextLong();
        input.nextLine();
        System.out.print("Enter card expiry date (MM/YY): ");
        cardExpiryDate = input.nextLine();
        cardStillVaild = analyseCardExpiryDate(cardExpiryDate);
        System.out.print("Enter CVV: ");
        cvv = input.nextInt();
        input.nextLine(); 
        vaildCVV = analyseCVV(cvv);
        input.close();
    }
    
    private boolean analyseEmail(String email){
        if(email.contains("@") && email.contains(".")){
            System.out.println("Email is valid.");
            return true;
        }
        else {
            System.out.println("Invalid email address. Going back to the start of the registration.");
            registration();
            return false;
        }
            
    }
    private boolean analyseAge(LocalDate dob){
        LocalDate today = LocalDate.now();
        int age = Period.between(dob, today).getYears();
        boolean isBirthday = (dob.getMonth() == today.getMonth()) && (dob.getDayOfMonth() == today.getDayOfMonth());
        if (age<=12 || age >=120){
            System.out.println("Looks like you are either too young or already dead.Sorry,you can't be our user.Have a nice day.");
            System.exit(0);
            return false;
        }
        if (userType.equals("VIP User")){
            if(isBirthday && age >=13 && age <=18){
                System.out.println("Happy Birthday!");
                System.out.println("You get 25% discount on the VIP subscription fee for being born today and being under 18!");
                minorAndBirthday = true;
            }else if (!isBirthday && age >=13 && age <=18){
                System.out.println("You get 20% discount on the VIP subscription fee for being under 18!");
                minor = true;
            } 

        }
        ageVaild = true;
        return true;
    }
        private boolean analyseCardNumber(long cardNumber){
            int len = cardNumStr.length();
            String cardNumStr = String.valueOf(cardNumber);
            int firstTwoDigits = Integer.parseInt(cardNumStr.substring(0, 2));
            int firstFourDigits =0;
            if (cardNumStr.length() >= 4) {
                firstFourDigits = Integer.parseInt(cardNumStr.substring(0, 4));
            }
            if ((len == 13 || len == 16)&& cardNumStr.startsWith("4")){
                cardProvider = "Visa";
                return true;
            }
            else if ((len == 16 && firstTwoDigits >=51 && firstTwoDigits <= 55)|| firstFourDigits >=2221 && firstFourDigits <=2720){
                cardProvider = "Mastercard";
                return true;
            }
            else if (len == 15 && (firstTwoDigits == 34 || firstTwoDigits == 37)){
                cardProvider = "Amex";
                return true;
            }
            else{
                System.out.println("Sorry, but we accept only VISA, MasterCard, or American Express cards. Please try again with a valid card.Going back to the start of the registration.");
                registration();
                return false;
            }
        private boolean analyseCardExpiryDate(String expiry){
            int month = Integer.parseInt(expiry.substring(0, 2));
            int year = Integer.parseInt(expiry.substring(3, 5)) + 2000;
            LocalDate today = LocalDate.now();
            int currentYear = today.getYear();
            int currentMonth = today.getMonthValue();
            if (year > currentYear){
                System.out.println("The card is still valid");
            
                return true;
            }
            else if (year == currentYear && month > currentMonth){
                System.out.println("The card is still valid");
                return true;
            }
            else{
                System.out.println("Sorry, your card has expired. Please use a different card.Going back to the start fo the registration process…");
                registration();
                return false;
            }
        }
        private boolean analyseCVV(int cvvNumber){
            String cvvStr = String.valueOf(cvvNumber);
                if (cardProvider.equals("American Express")&& cvvStr.length() == 4){
                    System.out.println("card CVV is valid.");
                    return true;
                }
                else if (cardProvider.equals("Visa") && cvvStr.length() == 3){
                    System.out.println("card CVV is valid.");
                    return true;
                }
                else if (cardProvider.equals("Mastercard") && cvvStr.length() == 3){
                    System.out.println("card CVV is valid.");
                    return true;
                }
                else{
                    System.out.println("Invalid CVV for the given card.Going back to the start of the registration process.");
                    registration();
                    return false;
                }
        }
    }
}