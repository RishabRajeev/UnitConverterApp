package com.example.converter;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/convert")

public class ConverterController {
    private final ConverterService service;

    public ConverterController(ConverterService service) {
        this.service = service;
    }

    @GetMapping("/c-to-f")
    public Map<String, Object> cToF(@RequestParam double value) {
        return Map.of("input_celsius", value, "result_fahrenheit", service.celsiusToFahrenheit(value));
    }

    @GetMapping("/f-to-c")
    public Map<String, Object> fToC(@RequestParam double value) {
        return Map.of("input_fahrenheit", value, "result_celsius", service.fahrenheitToCelsius(value));
    }

    @GetMapping("/km-to-miles")
    public Map<String, Object> kmToMiles(@RequestParam double value) {
        return Map.of("input_km", value, "result_miles", service.kilometersToMiles(value));
    }
}
