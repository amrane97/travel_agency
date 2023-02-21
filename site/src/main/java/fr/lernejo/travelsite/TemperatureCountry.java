package fr.lernejo.travelsite;

import java.util.List;

public class TemperatureCountry {

    private final String country;
    private final List<Temperature> temperatures;


    public TemperatureCountry(String country, List<Temperature> temperatures) {
        this.country = country;
        this.temperatures = temperatures;
    }

    public TemperatureCountry() {
        this.country = null;
        this.temperatures = null;
    }

    public String getCountry() {
        return this.country;
    }

    public List<Temperature> getTemperatures() {
        return this.temperatures;
    }
}
