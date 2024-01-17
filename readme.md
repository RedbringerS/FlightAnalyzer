# Flight Analyzer

Flight Analyzer — это программа Java, которая анализирует данные полета из файла JSON. Он рассчитывает минимальное время полета между Владивостоком и Тель-Авивом для каждой авиакомпании и вычисляет разницу между средней ценой и медианной ценой на рейсы по этому маршруту.

## Usage

To use the Flight Analyzer, follow these steps:

1. Clone the repository:

   ```bash
   git clone <repository_url>
   ```

2. Navigate to the project directory:

   ```bash
   cd FlightAnalyzer/libs/
   ```

3. Run the program with the path to the JSON file as a command-line argument:

   ```bash
   java -jar FlightAnalyzer.jar <jsonFilePath>
   ```

4. Replace <jsonFilePath> with the actual path to your JSON file.
   Assuming your JSON file is named tickets.json, the command would be:

   ```bash
   java -jar FlightAnalyzer.jar tickets.json
   ```

JSON File Format
Ensure your JSON file follows the structure below:

   ```json
   {
     "tickets": [
       {
         "origin": "VVO",
         "origin_name": "Владивосток",
         "destination": "TLV",
         "destination_name": "Тель-Авив",
         "departure_date": "12.05.18",
         "departure_time": "16:20",
         "arrival_date": "12.05.18",
         "arrival_time": "22:10",
         "carrier": "TK",
         "stops": 3,
         "price": 12400
       }
     ]
   }
   ```

Вывод:
Программа выведет минимальное время полета для каждого перевозчика между Владивостоком и Тель-Авивом и разницу между средней ценой и медианной ценой для всех рейсов.

Зависимости:
Программа использует библиотеку json-simple для анализа JSON. Обязательно включите библиотеку в свой путь к классам.