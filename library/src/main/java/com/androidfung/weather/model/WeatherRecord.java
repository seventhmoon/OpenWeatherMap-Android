package com.androidfung.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fung.lam on 02/09/2015.
 * for openweathermap webservice call
 */
public class WeatherRecord {

    @SerializedName("temp")
    float temperature;
    @SerializedName("pressure")
    int pressure;
    @SerializedName("humidity")
    float humidity;
    @SerializedName("temp_min")
    float minimumTemperature;
    @SerializedName("temp_max")
    float maximunTemperature;

    public float getTemperature() {
        return temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getMinimumTemperature() {
        return minimumTemperature;
    }

    public float getMaximunTemperature() {
        return maximunTemperature;
    }
}
