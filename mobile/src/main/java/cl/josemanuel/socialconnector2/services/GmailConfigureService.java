package cl.josemanuel.socialconnector2.services;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

import cl.josemanuel.socialconnector2.entities.ConfigureGmailSC;
import cl.josemanuel.socialconnector2.entities.TokenSC;
import cl.josemanuel.socialconnector2.services.clients.GmailConfigureClient;
import cl.josemanuel.socialconnector2.services.clients.TelegramCodeClient;
import cl.josemanuel.socialconnector2.services.clients.TokenServiceClient;

public class GmailConfigureService extends AsyncTask<Void, Void, Void> implements TokenServiceClient {

    private Context context;
    private GmailConfigureClient gmailConfigureClient;
    private String URL = "https://socialtranslator.dcc.uchile.cl/community/configure/";

    public GmailConfigureService(Context context, GmailConfigureClient gmailConfigureClient) {
        this.context = context;
        this.gmailConfigureClient = gmailConfigureClient;
    }

    @Override
    protected Void doInBackground(Void... params) {
        (new TokenService(context, this)).execute();
        return null;

    }

    @Override
    public void onTokenReceived(String token) {
        configureGmail(token);
    }

    @Override
    public void onErrorToken() {
        gmailConfigureClient.onError("");
    }

    @Nullable
    private Void configureGmail(String token) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
//        contactServiceClient.onLoadContacts("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImphdmllcmEudGFwaWEuZ0B1c2FjaC5jbCIsInVzZXJuYW1lIjoiamF2aWVyYS50YXBpYS5nQHVzYWNoLmNsIiwiZXhwIjoxNTIzODkwMDIyLCJ1c2VyX2lkIjoxM30.gN0uPsxpzJA56jKYkNVeZWCt9NLTSU0x5DwY54I01Ro");

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        gmailConfigureClient.onLoadCheckPage(getRedirect(response));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error
                gmailConfigureClient.onError("Error");
                error.printStackTrace();
            }
        }) {
            @Override
            public byte[] getBody() {
                HashMap<String, String> params = new HashMap<>();
                params.put("user", "jose.wt@gmail.com");
                params.put("social", "gmail");

                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "JWT " + token);
                return params;
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

    private String getRedirect(String response) {
        Gson gson = new Gson();
        ConfigureGmailSC configureGmailSC = gson.fromJson(response, ConfigureGmailSC.class);

        return configureGmailSC.getRedirectURL();
    }
}
