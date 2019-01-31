package cl.josemanuel.socialconnector2.services;

import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cl.josemanuel.socialconnector2.dialogs.Loading;
import cl.josemanuel.socialconnector2.entities.ContactEntity;
import cl.josemanuel.socialconnector2.entities.MessageEntity;
import cl.josemanuel.socialconnector2.entities.MessageSC;
import cl.josemanuel.socialconnector2.entities.PhotoEntity;
import cl.josemanuel.socialconnector2.entities.TokenSC;
import cl.josemanuel.socialconnector2.services.clients.MessageServiceClient;
import cl.josemanuel.socialconnector2.services.clients.TokenServiceClient;

public class TokenService extends AsyncTask<Void, Void, Void> {

    private final String URL = "https://socialtranslator.dcc.uchile.cl/auth/jwt/token/";
    private Context context;
    private TokenServiceClient tokenServiceClient;

    public TokenService(Context context, TokenServiceClient tokenServiceClient) {
        this.context = context;
        this.tokenServiceClient = tokenServiceClient;
    }

    @Override
    protected Void doInBackground(Void... params) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tokenServiceClient.onTokenReceived(proccessToken(response));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error
                error.printStackTrace();
                tokenServiceClient.onErrorToken();
            }
        }) {
            @Override
            public byte[] getBody() {
                HashMap<String, String> params = new HashMap<>();
                params.put("username", "jose.wt@gmail.com");
                params.put("password", "socialconnector");
                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        return null;
    }

    private String proccessToken(String response) {
        Gson gson = new Gson();
        Type tokenType = new TypeToken<TokenSC>() {
        }.getType();
        TokenSC token = gson.fromJson(response, tokenType);

        return token.getAccess();
    }
}
