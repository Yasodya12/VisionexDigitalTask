package au.com.visiondigital.weatherDataBackend.service;

import au.com.visiondigital.weatherDataBackend.dto.WeatherData;
import au.com.visiondigital.weatherDataBackend.service.Impl.WeatherServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @Mock
    private WebClient webClient;

    @InjectMocks
    private WeatherService weatherService; // Replace with your actual service implementation class

    @Test
    void checkStatus(){
        weatherService.getWeatherSummary("London");
    }


}
