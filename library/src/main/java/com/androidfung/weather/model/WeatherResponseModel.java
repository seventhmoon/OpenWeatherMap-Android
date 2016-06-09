package com.androidfung.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fung.lam on 02/09/2015.
 * for openweathermap webservice call
 */
public class WeatherResponseModel {

//    @SerializedName("coord")
//    Coordaniate coordaniate;
//
//    @SerializedName("weather")
//    Weather weather;

//    @SerializedName("base")
//    String base;

    @SerializedName("main")
    WeatherRecord weatherRecord;

    public WeatherRecord getWeatherRecord() {
        return weatherRecord;
    }
    //    @SerializedName("visibility")
//    long visibility;





}
