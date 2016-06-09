package com.androidfung.weather;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.androidfung.weather.model.WeatherResponseModel;
import com.androidfung.weather.network.CustomGsonObjectRequest;
import com.example.android.sunshine.app.R;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by fung.lam on 09/09/2015.
 * API Helper for Open Weather Map API
 */
public class OpenWeatherMapApiHelper {

    private static final String HOST = "http://api.openweathermap.org";
    private static final String GET_WEATHER_ENDPOINT = "/data/2.5/weather";

    private RequestQueue mRequestQueue;
    private String mAppId;


//    public OpenWeatherMapApiHelper(@NonNull RequestQueue requestQueue) {
//        mRequestQueue = requestQueue;
//    }

    public OpenWeatherMapApiHelper(@NonNull Context context){
        mRequestQueue = Volley.newRequestQueue(context);
        mAppId = context.getResources().getString(R.string.api_key);
    }

    public OpenWeatherMapApiHelper(@NonNull RequestQueue requestQueue, @NonNull String appId) {
        mRequestQueue = requestQueue;
        mAppId = appId;
    }

    //convert the map of params to "key1=value1&key2=value2" style
    private static String toUrlParams(@NonNull Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            try {
                sb.append("&").append(key).append("=").append(URLEncoder.encode(params.get(key).toString(), "utf-8"));

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString().substring(1); //removing the heading '&'
    }

    /**
     * Send the Get Weather API request
     * @param listener what to do if success
     * @param errorListener what to do if failed
     * @return volley request
     */
    public Request getWeather(@NonNull Response.Listener<WeatherResponseModel> listener, @NonNull Response.ErrorListener errorListener) {
        TreeMap<String, Object> params = new TreeMap<>();
        params.put("units", "metric");
        params.put("appid", mAppId);

        String url = HOST + GET_WEATHER_ENDPOINT + "?" + toUrlParams(params);

        Type type = new TypeToken<WeatherResponseModel>() {
        }.getType();

        CustomGsonObjectRequest gsonReq = new CustomGsonObjectRequest<>(Request.Method.GET,
                url, type, null, listener, errorListener);


        // Adding request to request queue
        return mRequestQueue.add(gsonReq);

    }

    /**
     * Send the Get Weather API request
     * @param latitude latitude of location
     * @param longitude longitude of location
     * @param listener what to do if success
     * @param errorListener what to do if failed
     * @return volley request
     */
    public Request getWeather(double latitude, double longitude, @NonNull Response.Listener<WeatherResponseModel> listener, @NonNull Response.ErrorListener errorListener) {
        TreeMap<String, Object> params = new TreeMap<>();
        params.put("units", "metric");
        params.put("lat", latitude);
        params.put("lon", longitude);
        params.put("appid", mAppId);

        String url = HOST + GET_WEATHER_ENDPOINT + "?" + toUrlParams(params);

        Type type = new TypeToken<WeatherResponseModel>() {
        }.getType();

        CustomGsonObjectRequest gsonReq = new CustomGsonObjectRequest<>(Request.Method.GET,
                url, type, null, listener, errorListener);


        // Adding request to request queue
        return mRequestQueue.add(gsonReq);

    }

}
