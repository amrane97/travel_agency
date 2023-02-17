package fr.lernejo.prediction;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

public record Temperature(@DateTimeFormat @NonNull String date, @NonNull double temperature) {
}
