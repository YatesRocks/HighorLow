import java.io.InputStreamReader;
import java.util.Random;
import java.util.function.Function;
import java.io.BufferedReader;

public class Main {
    private static <T> T get_valid_input(String prompt, Function<String, T> parser) {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print(prompt);
            try {
                String input = scan.readLine();
                return parser.apply(input);
            } catch (NumberFormatException e) {
                System.err.println(e); // TODO: Use logger instead of sys err
                System.out.println("Error parsing input. Please try again.");
            } catch (Exception e) {
                System.err.println("Error occurred: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Random gen = new Random();
        int number_to_guess = gen.nextInt(10) + 1; // 1-10 inclusive
        Integer user_guess;

        // Make sure the users guess is in range
        while (true) {
            user_guess = get_valid_input("Guess an integer number between 1-10 inclusive: ", Integer::parseInt);
            if (!(user_guess > 10 || user_guess < 1)) break;
            System.out.println("Your guess was outside the range. Make sure your number is between 1-10 inclusive");
        }

        switch (user_guess.compareTo(number_to_guess)) {
            case -1: System.out.println("Too low!"); break;
            case 0: System.out.println("Spot on!"); break;
            case 1: System.out.println("Too high!"); break;
        }
    }
}