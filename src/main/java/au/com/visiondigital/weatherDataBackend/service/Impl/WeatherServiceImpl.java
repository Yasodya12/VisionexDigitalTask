package au.com.visiondigital.weatherDataBackend.service.Impl;

import au.com.visiondigital.weatherDataBackend.dto.WeatherData;
import au.com.visiondigital.weatherDataBackend.exeption.CityNotFoundException;
import au.com.visiondigital.weatherDataBackend.exeption.ExternalApiException;
import au.com.visiondigital.weatherDataBackend.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final WebClient webClient;

    @Cacheable(value = "weatherSummary", key = "#city", unless = "#result == null", cacheManager = "cacheManager")
    @Async
    public WeatherData getWeatherSummary(String city) {
        System.out.println("inside api funtion");
        String apiResponse = fetchWeatherData(city);
        System.out.println("output in api service layer "+apiResponse);
        if (apiResponse == null) throw new CityNotFoundException(city);

        WeatherData weatherData = parseWeatherData(apiResponse);
        System.out.println("this is weather data "+weatherData);
        return weatherData;
    }

    private String fetchWeatherData(String city) {
        try {
            return webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/data/2.5/forecast")
                            .queryParam("q", city)
                            .queryParam("appid", "bcca5f50ea7f5be3a7cc1761b31b3dfa")
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (Exception e) {
            throw new ExternalApiException("Error fetching data for city: " + city, e);
        }
    }

    private WeatherData parseWeatherData(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray weatherList = jsonObject.getJSONArray("list");

        double totalTemperature = 0.0;
        double maxTemperature = Double.MIN_VALUE;
        double minTemperature = Double.MAX_VALUE;
        String hottestDay = null;
        String coldestDay = null;

        for (int i = 0; i < weatherList.length(); i++) {
            JSONObject weatherEntry = weatherList.getJSONObject(i);

            // Extract temperature (convert from Kelvin to Celsius)
            double temperature = weatherEntry.getJSONObject("main").getDouble("temp") - 273.15;

            // Extract date
            String date = weatherEntry.getString("dt_txt").split(" ")[0]; // Extract only the date part

            // Sum temperatures for average calculation
            totalTemperature += temperature;

            // Check for hottest day
            if (temperature > maxTemperature) {
                maxTemperature = temperature;
                hottestDay = date;
            }

            // Check for coldest day
            if (temperature < minTemperature) {
                minTemperature = temperature;
                coldestDay = date;
            }
        }

        // Calculate average temperature
        double averageTemperature = totalTemperature / weatherList.length();

        // Construct WeatherSummary object
        return new WeatherData(
                jsonObject.getJSONObject("city").getString("name"),
                averageTemperature,
                hottestDay,
                coldestDay
        );
    }


}
