package com.company.a5month.data.remote;

import com.company.a5month.data.model.MainResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("/data/2.5/weather")
    Call<MainResponse> getDegree(@Query("lat") String lat,
                                 @Query("lon") String lon
            , @Query("appid") String apiKey
            , @Query("units") String units);
}
