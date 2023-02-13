package fr.lernejo.travelsite;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record User(@Email String userMail,
                   @NotNull @NotBlank String userName,
                   @NotNull Weather weatherExpectation,
                   @javax.validation.constraints.Size(min = 0, max = 40) int minimumTemperatureDistance) {

    //code here

}
