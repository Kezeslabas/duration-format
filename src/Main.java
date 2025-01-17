import formatter.DurationFormatter;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {

    /**
     * Simple app the waits for a user input (seconds) and displays it in human readable format.
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a duration in seconds: ");
        String secondsString = scanner.nextLine();

        int seconds = Integer.parseInt(secondsString);
        Duration duration = Duration.of(seconds, ChronoUnit.SECONDS);

        DurationFormatter durationFormatter = new DurationFormatter();
        String result = durationFormatter.format(duration);

        System.out.println(result);

    }
}