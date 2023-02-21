package fr.lernejo.travelsite;

import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class SiteService {

    private final List<User> users;
    private final PredictionEngineClient prediction;
    private final List<String> dataCountries;

    public SiteService(List<User> users, PredictionEngineClient prediction, List<String> dataCountries) {
        this.users = users;
        this.prediction = prediction;
        this.dataCountries = dataCountries;

        try {
            this.dataCountries.addAll(new String(this.getClass().getClassLoader().getResourceAsStream("countries.txt").readAllBytes(), StandardCharsets.UTF_8).lines().toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double getAverageTemperature (String country) {
        double myTemp = 10000;
        DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.ENGLISH));
        try {
            Call<TemperatureCountry> call = this.prediction.getTemperaturesCountry(country);
            if (call != null) {
                Response<TemperatureCountry> result = call.execute();
                TemperatureCountry tcResponse = result.body();
                if (result.isSuccessful() && tcResponse.country.equals(country)) {
                    myTemp = Double.parseDouble(df.format((tcResponse.temperatures.get(0).temperature + tcResponse.temperatures.get(1).temperature)/2));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myTemp;
    }

    public List<Country> getDestinationsCountries (String username) {

        List<Country> tmpList = new ArrayList<>();
        User user = findUserByUsername(username);
        double myTemp = getAverageTemperature(user.userCountry());
        double desiredTemperature = user.weatherExpectation().equals(Weather.WARMER) ? myTemp + user.minimumTemperatureDistance() : myTemp - user.minimumTemperatureDistance();

        for(String c : this.dataCountries) {
            if (user.weatherExpectation().equals(Weather.WARMER) && getAverageTemperature(c) >= desiredTemperature) {
                Country country = new Country(c, getAverageTemperature(c));
                tmpList.add(country);
            } else if (user.weatherExpectation().equals(Weather.COLDER) && getAverageTemperature(c) <= desiredTemperature) {
                Country country2 = new Country(c, getAverageTemperature(c));
                tmpList.add(country2);
            }
        }
        return tmpList;
    }

    public User findUserByUsername (String username) {
        for (User u : users) {
            if (u.userName().equals(username)) {
                return u;
            }
        }
        return null;
    }


    public List<User> addUser(User user) {
        this.users.add(user);
        return this.users;
    }
}
