import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java FlightAnalyzer <jsonFilPath");
            System.exit(1);
        }

        String jsonFilePath = args[0];

        try {
            FlightAnalyzer.analyzeFlightData(jsonFilePath);
        } catch (IOException | ParseException e) {
            System.out.println("Error processing JSON file: " + e.getMessage());
        }
    }
}