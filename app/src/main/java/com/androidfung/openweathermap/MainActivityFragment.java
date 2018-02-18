package com.androidfung.openweathermap;

import android.os.Bundle;
import android.support.annotation.NonNull;
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

import org.w3c.dom.Text;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private TextView mTextViewMessage;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mTextViewMessage = rootView.findViewById(R.id.textview_message);

        OpenWeatherMapApiHelper helper = new OpenWeatherMapApiHelper(getActivity(), "API_KEY_HERE");
        helper.getWeather(22.25, 114.1667,
                this::showInformation,
                this::showError
        );

        return rootView;
    }

    private void showInformation(WeatherResponseModel response){
        mTextViewMessage.setText(getString(R.string.Result, response.getWeatherRecord().getTemperature(),response.getWeatherRecord().getHumidity()));
    }

    private void showError(VolleyError error){
        mTextViewMessage.setText(error.toString());
    }
}
