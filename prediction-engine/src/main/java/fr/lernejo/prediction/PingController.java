package fr.lernejo.prediction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpClient;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@RestController
public class PingController {

    private final TemperatureService temperatureService;

    public PingController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping(path = "/api/ping")
    String ping() {
        return "OK";
    }

    @GetMapping("/api/temperature")
    ResponseEntity<TemperatureCountry> getTemperaturesOfCountry(@RequestParam String country) {

        try {

            List<TemperatureCountry> temperatureCountryList = new ArrayList<>();
            List<Temperature> temperatureList = new ArrayList<>();

            Instant now = Instant.now();
            ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault()); //convert instant to zone
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // pattern

            Temperature temperature = new Temperature(zonedDateTime.format(formatter), this.temperatureService.getTemperature(country));
            Temperature temperatureYesterday = new Temperature(
                now.minus(1, ChronoUnit.DAYS).atZone(ZoneId.systemDefault()).format(formatter),
                this.temperatureService.getTemperature(country));

            temperatureList.add(temperature);
            temperatureList.add(temperatureYesterday);

            TemperatureCountry temperatureCountry = new TemperatureCountry(country, temperatureList);

            return new ResponseEntity<>(temperatureCountry,HttpStatus.OK);

        } catch (UnknownCountryException e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

}
