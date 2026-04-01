import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BracketChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter parentheses string: ");
        String input = scanner.nextLine();
        scanner.close();

        Deque<Character> stack = new ArrayDeque<>();
        boolean balanced = true;

        for (char c : input.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    balanced = false;
                    break;
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) {
                    balanced = false;
                    break;
                }
            }
        }

        if (balanced && stack.isEmpty()) {
            System.out.println("Balanced and paired");
        } else {
            System.out.println("Not balanced or paired");
        }
    }
}