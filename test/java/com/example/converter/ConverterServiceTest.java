package com.example.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConverterServiceTest {
    private ConverterService service;

    @BeforeEach
    void setUp() {
        service = new ConverterService();
    }

    @Test
    void testCelsiusToFahrenheit() {
        assertEquals(32.0, service.celsiusToFahrenheit(0), 0.001);
        assertEquals(212.0, service.celsiusToFahrenheit(100), 0.001);
        assertEquals(-40.0, service.celsiusToFahrenheit(-40), 0.001);
    }

    @Test
    void testFahrenheitToCelsius() {
        assertEquals(0.0, service.fahrenheitToCelsius(32), 0.001);
        assertEquals(100.0, service.fahrenheitToCelsius(212), 0.001);
    }

    @Test
    void testKilometersToMiles() {
        assertEquals(6.21371, service.kilometersToMiles(10), 0.001);
    }

    @Test
    void testNegativeDistanceThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> service.kilometersToMiles(-5));
    }
}

