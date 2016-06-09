package com.androidfung.weather.network;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

/*
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
*/

/**
 * @author http://qiita.com/tomoima525/items/8e77c4cfe51339974545
 */
public class MultipartRequest extends Request<String> {
    public MultipartRequest(String url, Response.ErrorListener listener) {
        super(url, listener);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    @Override
    protected void deliverResponse(String response) {

    }
/*
    MultipartEntityBuilder entity = MultipartEntityBuilder.create();
    HttpEntity httpEntitiy;
    private final Response.Listener<String> mListener;
    private final Map<String, String> mStringParts;
    private final Map<String, File> mFileParts;

    public MultipartRequest(String url, Response.Listener<String> listener,
                            Response.ErrorListener errorListener,
                            Map<String, String> stringParts, Map<String, File> fileParts) {
        super(Method.POST, url, errorListener);

        mListener = listener;
        mStringParts = stringParts;
        mFileParts = fileParts;
        buildMultipartEntity();
    }

    private void buildMultipartEntity() {
        entity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        //送信するリクエストを設定する
        //StringData
        for (Map.Entry<String, String> entry : mStringParts.entrySet()) {
            entity.addTextBody(entry.getKey(), entry.getValue());

        }
        //File Data
        for (Map.Entry<String, File> entry : mFileParts.entrySet()) {
            entity.addPart(entry.getKey(), new FileBody(entry.getValue()));
        }
        httpEntitiy = entity.build();
    }

    @Override
    public String getBodyContentType() {
        return httpEntitiy.getContentType().getValue();
    }

    public HttpEntity getEntity() {
        return httpEntitiy;
    }


    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        return Response.success("Uploaded", getCacheEntry());

    }

    //リスナーにレスポンスを返す
    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }
    */
}