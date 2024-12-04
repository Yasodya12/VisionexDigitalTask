Weather Summary API 🌤️
This project is a Spring Boot-based backend application that fetches weather data from the OpenWeather API and calculates a summary for a given city. It includes features such as average temperature, the hottest day, and the coldest day based on weather forecasts.

Features
🌍 Fetch weather data for a city using the OpenWeather API.
📊 Calculate average temperature, hottest day, and coldest day from forecast data.
🛡️ Robust error handling with custom exceptions.
📡 Built using Spring Boot and WebClient for RESTful API integration.
🧪 Includes unit testing with JUnit and Mockito.

API Endpoints
Get Weather Summary
Endpoint: GET /weather
Query Parameter: city - Name of the city to fetch weather data for.

Example Request: curl -X GET "http://localhost:8080/weather?city=London"

Example Response: {
  "city": "London",
  "averageTemperature": 15.5,
  "hottestDay": "2024-11-20",
  "coldestDay": "2024-11-18"
}

Error Responses
City Not Found (404):{
  "error": "City not found: UnknownCity"
}

API Error or Unexpected Error (500):{
  "error": "Error fetching data for city: London"
}

Project Structure
src
├── main
│   ├── java
│   │   ├── com.example.weather
│   │   │   ├── controller
│   │   │   │   └── WeatherController.java
│   │   │   ├── service
│   │   │   │   └── WeatherService.java
│   │   │   ├── exception
│   │   │   │   ├── CityNotFoundException.java
│   │   │   │   ├── ExternalApiException.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   └── model
│   │   │       └── WeatherData.java
│   ├── resources
│   │   └── application.properties
├── test
│   ├── java
│   │   ├── com.example.weather
│   │   │   └── WeatherServiceTest.java


Installation and Setup
Prerequisites
Java 17 or later.
Maven 3.6+.
OpenWeather API key.


Steps
Clone the repository: git clone https://github.com/yourusername/weather-summary-api.git
cd weather-summary-api

onfigure the OpenWeather API key in application.properties: openweather.api.key=your_api_key_here

Build and run the application:mvn clean install
mvn spring-boot:run

Access the application: Open your browser or use a tool like Postman to access:http://localhost:8080/weather?city=London

Technologies Used
Backend Framework: Spring Boot
HTTP Client: WebClient
Testing: JUnit, Mockito
JSON Parsing: org.json (JSONObject, JSONArray)
