package au.com.visiondigital.weatherDataBackend.service;

import au.com.visiondigital.weatherDataBackend.dto.WeatherData;
import au.com.visiondigital.weatherDataBackend.service.Impl.WeatherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    private WeatherServiceImpl weatherService; // Replace with your actual service implementation class

    @BeforeEach
    void setUp(){
        weatherService=new WeatherServiceImpl(webClient);
    }

    @Test
    void checkStatus(){

        weatherService.getWeatherSummary("London");
    }

//    @Test
//    void checkFuntionTwo(){
//
//        WeatherData weather = new WeatherData("Colombo", 30.5, "2024-07-15", "2024-12-01");
//
//        WeatherData weather2 = new WeatherData("Galle", 30.5, "2024-07-14", "2024-12-02");
//
//        // Accessing object properties
//
//        when(webClient.get()).thenReturn(requestHeadersUriSpec);
//        when(requestHeadersUriSpec.uri("get")).thenReturn(requestHeadersSpec);
//        when(responseSpec.bodyToFlux(WeatherData.class)).thenReturn(Flux.just(weather,weather2));
//        String result = weatherService.fetchWeatherData("London");
//
//
//        assertNotNull(result);
//        assertEquals("Expected Response", result);
//
//
//    }

}
