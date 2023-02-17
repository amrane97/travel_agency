package fr.lernejo.prediction;

import org.springframework.lang.NonNull;

import java.util.List;

public record TemperatureCountry(@NonNull String country, @NonNull List<Temperature> temperatures) {
}
