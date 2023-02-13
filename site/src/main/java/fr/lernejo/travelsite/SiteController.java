package fr.lernejo.travelsite;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SiteController {

    private final List<User> listUsers = new ArrayList<>();
    private final List<Country> listCountries = new ArrayList<>();

    @GetMapping("/api/travels")
    public List<Country> getDestinations(@RequestParam("username") String username) {

        List<Country> tmpList = new ArrayList<>();
        Country country = new Country("mexique", 12.9);
        Country country2 = new Country("spain", 32.9);
        listCountries.add(country);
        listCountries.add(country2);
        User myUser = new User("aa@aa.fr", "aa", Weather.WARMER,25);

        for (Country c : listCountries) {
            if (myUser.weatherExpectation().equals(Weather.WARMER) && myUser.minimumTemperatureDistance() > c.temperature()) {
                tmpList.add(c);
            } else if (myUser.weatherExpectation().equals(Weather.COLDER) && myUser.minimumTemperatureDistance() < c.temperature()) {
                tmpList.add(c);
            }
        }
        return tmpList;
    }


    @PostMapping("/api/createUser")
    public User createUser (User user) {
        return user;
    }

}
