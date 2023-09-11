import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int minRange = 1;
        int maxRange = 100;
        int attempts = 10; // You can change the number of attempts as needed
        int round = 1;
        int score = 0;

        System.out.println("Welcome to the Number Game!");

        do {
            int targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int remainingAttempts = attempts;
            System.out.println("Round " + round);
            System.out.println("Guess the number between " + minRange + " and " + maxRange);

            while (remainingAttempts > 0) {
                System.out.print("Enter your guess (" + remainingAttempts + " attempts left): ");
                int userGuess = scanner.nextInt();

                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the correct number: " + targetNumber);
                    score++;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Your guess is too low. Try again.");
                } else {
                    System.out.println("Your guess is too high. Try again.");
                }

                remainingAttempts--;
            }

            System.out.println("Round " + round + " is over.");
            System.out.println("Your current score: " + score);

            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain = scanner.next();

            if (!playAgain.equalsIgnoreCase("yes")) {
                System.out.println("Thanks for playing! Your final score: " + score);
                break;
            }

            round++;
        } while (true);

        scanner.close();
    }
}
