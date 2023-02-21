package fr.lernejo.travelsite;

public class Temperature {

    private final String date;
    private final double temperature;

    public Temperature(String date, double temperature) {
        this.date = date;
        this.temperature = temperature;
    }

    public Temperature() {
        this.date = null;
        this.temperature = 0;
    }

    public String getDate() {
        return this.date;
    }

    public double getTemperature() {
        return this.temperature;
    }
}
