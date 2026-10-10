package com.example.converter;
import org.springframework.stereotype.Service;

@Service 
public class ConverterService{

     public double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    public double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public double kilometersToMiles(double km) {
        if (km < 0) {
            throw new IllegalArgumentException("Distance cannot be negative");
        }
        return km * 0.621371;
    }

    public double milesToKilometers(double miles) {
        if (miles < 0) {
            throw new IllegalArgumentException("Distance cannot be negative");
        }
        return miles / 0.621371;
}
}