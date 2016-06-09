package com.androidfung.weather.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by fung.lam on 31/03/2015.
 * for parsing the json return by gson
 */
public class CustomGsonObjectRequest<T> extends GsonRequest<T> {

    //    private final Gson gson = new GsonBuilder()
//            .setDateFormat("yyyy/MM/dd HH:mm:ss aa").create();
    private final Gson gson = super.getGson();
    //    private final Class<T> clazz;
    private final Type mTypeToken;

    /**
     * Make a request and return a parsed object from JSON.
     *
     * @param method        POST or GET
     * @param url           URL of the request to make
     * @param typeToken     Relevant class object, for Gson's reflection
     * @param formData      the form data to be sent
     * @param listener      what to do when success
     * @param errorListener what to do when error
     */
    public CustomGsonObjectRequest(int method, @NonNull String url, @NonNull Type typeToken, @Nullable Map<String, String> formData, @NonNull Response.Listener<T> listener, @NonNull Response.ErrorListener errorListener) {
        super(method, url, formData, listener, errorListener);
        mTypeToken = typeToken;

    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            Log.d(TAG, json);

//            String formattedJson = StringEscapeUtils.unescapeJson(json);
//            if (formattedJson.startsWith("\"")) {
//                formattedJson = formattedJson.substring(1, formattedJson.length() - 1);
//                if (formattedJson.startsWith("[")) {
//                    formattedJson = formattedJson.substring(1, formattedJson.length() - 1);
//                }
//            }
//
//            Log.d("Gson", formattedJson);

            return Response.success((T) gson.fromJson(json, mTypeToken),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

}
