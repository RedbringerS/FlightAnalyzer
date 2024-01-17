import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightAnalyzer {

    public static void analyzeFlightData(String jsonFilePath) throws IOException, ParseException {
        //используем try-with-resources для автоматического закрытия FileReader
        try (FileReader fileReader = new FileReader(jsonFilePath)) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(fileReader, JsonObject.class);
            JsonArray tickets = jsonObject.getAsJsonArray("tickets");

            calculateMinFlightTime(tickets);
            calculatePriceDifference(tickets);
        }
    }

    private static void calculateMinFlightTime(JsonArray tickets) {
        System.out.println("Минимальное время полета между городами Владивосток и Тель-Авив для каждого авиаперевозчика: ");

        //Группировка билетов по авиаперевозчику
        Map<String, List<JsonObject>> groupedByCarrier = new HashMap<>();

        for (int i = 0; i < tickets.size(); i++) {
            JsonObject ticket = tickets.get(i).getAsJsonObject();
            String carrier = ticket.get("carrier").getAsString();

            groupedByCarrier.computeIfAbsent(carrier, k -> new ArrayList<>()).add(ticket);
        }

        //Нахождение минимального времени полета для каждого авиаперевозчика
        groupedByCarrier.forEach((carrier, carrierTickets) -> {
            int minFlightTime = carrierTickets.stream()
                    .mapToInt(ticket -> ticket.get("stops").getAsInt())
                    .min()
                    .orElse(0);

            System.out.println(carrier + ": " + minFlightTime + " пересадок");
        });
    }

    private static void calculatePriceDifference(JsonArray tickets) {
        System.out.println("\nРазница между средней ценой и медианой для полета между городами Владивосток и Тель-Авив:");

        //извлечение цен на билеты
        List<Integer> prices = new ArrayList<>();

        for (int i = 0; i < tickets.size(); i++) {
            JsonObject ticket = tickets.get(i).getAsJsonObject();
            int price  = ticket.get("price").getAsInt();
            prices.add(price);
        }

        //Расчет средней цены
        double averagePrice = prices.stream()
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(0.0);

        // Расчет медианы
        double median = calculateMedian(prices);

        //Вывод разницы между средней ценой и медианой
        System.out.println("Средняя цена: " + averagePrice);
        System.out.println("Медиана: " + median);
        System.out.println("Разница: " + (averagePrice - median));
    }

    private static double calculateMedian(List<Integer> prices) {
        List<Integer> sortedPrices = prices.stream()
                .sorted()
                .toList();

        int size = sortedPrices.size();
        return (size % 2 == 0)
                ? (sortedPrices.get(size / 2 - 1) + sortedPrices.get(size / 2)) / 2.0
                : sortedPrices.get(size / 2);
    }
}