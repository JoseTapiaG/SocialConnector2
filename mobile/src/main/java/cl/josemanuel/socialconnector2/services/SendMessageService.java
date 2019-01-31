package cl.josemanuel.socialconnector2.services;

import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import cl.josemanuel.socialconnector2.dialogs.Loading;
import cl.josemanuel.socialconnector2.entities.LoginSC;
import cl.josemanuel.socialconnector2.services.clients.LoginServiceClient;
import cl.josemanuel.socialconnector2.services.clients.SendMessageServiceClient;
import cl.josemanuel.socialconnector2.services.clients.TokenServiceClient;

public class SendMessageService extends AsyncTask<Void, Void, Void> implements TokenServiceClient {

    private Context context;
    private Loading loading;
    private SendMessageServiceClient sendMessageServiceClient;
    private String URL = "https://socialtranslator.dcc.uchile.cl/community/sendMessage/";
    private String message;

    public SendMessageService(Context context, Loading loading, String message, SendMessageServiceClient sendMessageServiceClient) {
        this.context = context;
        this.loading = loading;
        this.message = message;
        this.sendMessageServiceClient = sendMessageServiceClient;
    }

    @Override
    protected Void doInBackground(Void... params) {
        if(false){
            sendMessage("");
        } else {
            (new TokenService(context, this)).execute();
        }

        return null;
    }

    private void sendMessage(String token) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        loading.hideLoadingDialog();

        // Request a string response from the provided URL.
        Request<String> stringRequest = new MultiPartRequest(token, Request.Method.POST, URL, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error
                error.printStackTrace();
            }
        },new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.hideLoadingDialog();
                sendMessageServiceClient.onMessageSent();
            }
        }, null, message
                );

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public void onTokenReceived(String token) {
        sendMessage(token);
    }

    @Override
    public void onErrorToken() {
        loading.hideLoadingDialog();
    }

    private class MultiPartRequest extends Request<String> {

        private MultipartEntity entity = new MultipartEntity();

        private static final String FILE_PART_NAME = "file";
        private static final String STRING_PART_NAME = "text";

        private final Response.Listener<String> mListener;
        private final File mFilePart;
        private final String mStringPart;
        private String token;


        public MultiPartRequest(String token, int method, String url, Response.ErrorListener listener, Response.Listener<String> mListener, File mFilePart, String mStringPart) {
            super(method, url, listener);
            this.token = token;
            this.mListener = mListener;
            this.mFilePart = mFilePart;
            this.mStringPart = mStringPart;
            buildMultipartEntity();
        }

        private void buildMultipartEntity()
        {
            try
            {
                entity.addPart("fromUser", new StringBody("jose.wt@gmail.com"));
                entity.addPart("toUser",new StringBody("zuny.chincolco@gmail.com"));
                entity.addPart("content", new StringBody("Test 123123"));
            }
            catch (Exception e)
            {
                VolleyLog.e("UnsupportedEncodingException");
            }
        }

        @Override
        public Map<String, String> getHeaders() {
            Map<String, String> params = new HashMap<>();
            params.put("Authorization", "JWT " + token);
            return params;
        }

        @Override
        public String getBodyContentType()
        {
            return entity.getContentType().getValue();
        }

        @Override
        public byte[] getBody()
        {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try
            {
                entity.writeTo(bos);
            }
            catch (IOException e)
            {
                VolleyLog.e("IOException writing to ByteArrayOutputStream");
            }
            return bos.toByteArray();
        }

        @Override
        protected Response<String> parseNetworkResponse(NetworkResponse response)
        {
            return Response.success("Uploaded", getCacheEntry());
        }

        @Override
        protected void deliverResponse(String response)
        {
            mListener.onResponse(response);
        }
    }
}
