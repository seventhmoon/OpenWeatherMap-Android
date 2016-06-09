### Welcome to OpenWeatherMap-Android 
This is a wrapper of [OpenWeatherMap](http://openweathermap.org) for Android platform using Volley.

### Sample Application
Sample Application is included in [app](https://github.com/seventhmoon/OpenWeatherMap-Android/tree/master/app)
	
### How to use
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
