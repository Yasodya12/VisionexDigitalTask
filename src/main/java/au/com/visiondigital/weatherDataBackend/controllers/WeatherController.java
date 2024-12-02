package au.com.visiondigital.weatherDataBackend.controllers;

import au.com.visiondigital.weatherDataBackend.dto.WeatherData;
import au.com.visiondigital.weatherDataBackend.exeption.CityNotFoundException;
import au.com.visiondigital.weatherDataBackend.exeption.ExternalApiException;
import au.com.visiondigital.weatherDataBackend.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
@CrossOrigin
public class WeatherController {

   private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public ResponseEntity<WeatherData> getWeatherSummary(@RequestParam String city) {
        try {
            WeatherData summary = weatherService.getWeatherSummary(city);
            return ResponseEntity.ok(summary);
        } catch (CityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (ExternalApiException ex) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

}
