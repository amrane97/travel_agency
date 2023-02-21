package fr.lernejo.travelsite;

import java.util.List;

public class TemperatureCountry {

    public final String country;
    public final List<Temperature> temperatures;


    public TemperatureCountry(String country, List<Temperature> temperatures) {
        this.country = country;
        this.temperatures = temperatures;
    }

    public TemperatureCountry() {
        this.country = null;
        this.temperatures = null;
    }
}
