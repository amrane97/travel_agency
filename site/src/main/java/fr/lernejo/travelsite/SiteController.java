package fr.lernejo.travelsite;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SiteController {

    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @PostMapping("/api/inscription")
    public List<User> createUser (@RequestBody @Valid User user) {
        return this.siteService.addUser(user);
    }

    @GetMapping("/api/travels")
    public List<Country> getDestinations(@RequestParam String username) {
        return this.siteService.getDestinationsCountries(username);
    }

}
