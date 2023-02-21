package fr.lernejo.travelsite;

import org.springframework.http.ResponseEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface PredictionEngineClient {

    @GET("/api/temperature")
    @Headers("Accept:application/json")
    Call<TemperatureCountry> getTemperaturesCountry(@Query("country") String country);

}
