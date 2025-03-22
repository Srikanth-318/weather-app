package com.example.weather.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.weather.model.WeatherResponse;

@Controller
public class WeatherController {

    @Value("${api.key}")
    private String apiKey;

    @GetMapping("/")
    public String getIndex(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "index";
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam("city") String city, Model model) {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appId=" + apiKey + "&units=metric";
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);
            WeatherResponse weatherResponse = response.getBody();

            if (weatherResponse != null) {
                model.addAttribute("city", weatherResponse.getName());
                model.addAttribute("country", weatherResponse.getSys().getCountry());
                model.addAttribute("weatherDescription", weatherResponse.getWeather().get(0).getDescription());
                model.addAttribute("temperature", weatherResponse.getMain().getTemp());
                model.addAttribute("humidity", weatherResponse.getMain().getHumidity());
                model.addAttribute("windSpeed", weatherResponse.getWind().getSpeed());

                String weatherIcon = "wi wi-own-" + weatherResponse.getWeather().get(0).getId();
                model.addAttribute("weatherIcon", weatherIcon);

                return "weather"; 
            }

        } catch (HttpClientErrorException e) {
            return "redirect:/?error=City not found. Please check the spelling and try again.";
        } catch (Exception e) {
            return "redirect:/?error=An unexpected error occurred. Please try again later.";
        }

        return "redirect:/?error=City not found. Please check the spelling and try again.";
    }
}