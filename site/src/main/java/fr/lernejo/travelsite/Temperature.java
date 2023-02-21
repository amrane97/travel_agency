package fr.lernejo.travelsite;

public class Temperature {

    public final String date;
    public final double temperature;

    public Temperature(String date, double temperature) {
        this.date = date;
        this.temperature = temperature;
    }

    public Temperature() {
        this.date = null;
        this.temperature = 0;
    }

}
