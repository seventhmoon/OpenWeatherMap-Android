package com.androidfung.weather.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;


/**
 * @param <T> Gson data model
 * @author fung.lam
 */
public abstract class GsonRequest<T> extends JsonRequest<T> {

    protected static final String PROTOCOL_CHARSET = "utf-8";

    /**
     * Content type for request.
     */
//    private static final String PROTOCOL_CONTENT_TYPE =
//            String.format("text/plain; charset=%s", PROTOCOL_CHARSET);

    private static final String PROTOCOL_CONTENT_TYPE =
            String.format("application/x-www-form-urlencoded");


    public static final String TAG = GsonRequest.class.getSimpleName();

    protected Gson getGson() {
        return gson;
    }

    private final Gson gson = new GsonBuilder()
//            .setDateFormat("M/d/yyyy h:mm:ss aa")
            .registerTypeAdapter(Date.class, new DateSerializer())
//            .registerTypeAdapter(Boolean.class, new BooleanSerializer())
//            .registerTypeAdapter(boolean.class, new BooleanSerializer())
            .create();
    //    private final Class<T> clazz;
//    private final Response.Listener<T> mListener;
//    private final Map<String, String> mFormData;


    /**
     * Make a request and return a parsed object
     *
     * @param url      URL of the request to make
     * @param formData map contains the parameters
     */
    public GsonRequest(int method, @NonNull String url, @Nullable Map<String, String> formData,
                       @NonNull Response.Listener<T> listener, @NonNull Response.ErrorListener errorListener) {
        super(method, url, getFormDataString(formData), listener, errorListener);

//        this.mListener = listener;
//        this.mFormData = formData;


//        Log.d(TAG, "formData: " + formData.toString());
    }

    /**
     * Make a request and return a parsed object
     *
     * @param url      URL of the request to make
     * @param formData map contains the parameters
     */
    public GsonRequest(int method, @NonNull String url, @Nullable String formData,
                       @NonNull Response.Listener<T> listener, @NonNull Response.ErrorListener errorListener) {


        super(method, url, formData, listener, errorListener);

//        this.mListener = listener;
//        this.mFormData = formData;


//        Log.d(TAG, "formData: " + formData.toString());
    }


//    @Override
//    protected void deliverResponse(T response) {
//        mListener.onResponse(response);
//    }

    @Override
    protected abstract Response<T> parseNetworkResponse(NetworkResponse response);


    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    private static String getFormDataString(Map<String, String> formData){

        StringBuilder params = new StringBuilder();
        if (formData != null) {
            for (String key : formData.keySet()) {
                params.append("&").append(key).append("=").append(formData.get(key));
            }

            return params.toString().substring(1);
        }else {
            return null;
        }



//        try {
//            String encodedBody = URLEncoder.encode(body, "utf-8");
//
//            Log.d(TAG, "getBody(): " + encodedBody);
//
//
//            return encodedBody;
//        } catch (UnsupportedEncodingException uee) {
//            Log.wtf("Volley", String.format("Unsupported Encoding while trying to get the bytes of %s using %s",
//                    body, PROTOCOL_CHARSET));
//            return null;
//        }
    }



    public class BooleanSerializer implements JsonSerializer<Boolean>, JsonDeserializer<Boolean> {

        @Override
        public JsonElement serialize(Boolean arg0, Type arg1, JsonSerializationContext arg2) {
            return new JsonPrimitive(arg0 ? 1 : 0);
        }

        @Override
        public Boolean deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
            return arg0.getAsInt() == 1 || arg0.getAsString().equals("1");
        }
    }

    public class DateSerializer implements JsonDeserializer<Date> {

        SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy h:mm:ss a", Locale.ENGLISH);

        @Override
        public Date deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {

            try {
                return df.parse(arg0.getAsString());
            } catch (ParseException e) {
                Log.d("Error", e.toString());
                return null;
            }

        }
    }
}