package com.androidfung.openweathermap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.androidfung.weather.OpenWeatherMapApiHelper;
import com.androidfung.weather.model.WeatherRecord;
import com.androidfung.weather.model.WeatherResponseModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textViewMessage = (TextView) rootView.findViewById(R.id.textview_message);

        OpenWeatherMapApiHelper helper = new OpenWeatherMapApiHelper(getContext());
        helper.getWeather(22.25, 114.1667, new Response.Listener<WeatherResponseModel>() {
            @Override
            public void onResponse(WeatherResponseModel response) {
                WeatherRecord record = response.getWeatherRecord();
                textViewMessage.setText("Temperature: " + record.getTemperature() + "\nHumidity: " + record.getHumidity());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textViewMessage.setText(error.toString());
            }
        });

        return rootView;
    }
}
