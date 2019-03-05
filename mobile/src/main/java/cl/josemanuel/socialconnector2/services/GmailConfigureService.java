package cl.josemanuel.socialconnector2.services;

import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

import cl.josemanuel.socialconnector2.services.clients.GmailConfigureClient;
import cl.josemanuel.socialconnector2.services.clients.TelegramCodeClient;

public class GmailConfigureService extends AsyncTask<Void, Void, Void> {

    private Context context;
    private GmailConfigureClient gmailConfigureClient;
    private String URL = "https://socialtranslator.dcc.uchile.cl/family/configure/";

    public GmailConfigureService(Context context, GmailConfigureClient gmailConfigureClient) {
        this.context = context;
        this.gmailConfigureClient = gmailConfigureClient;
    }

    @Override
    protected Void doInBackground(Void... params) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
//        contactServiceClient.onLoadContacts("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImphdmllcmEudGFwaWEuZ0B1c2FjaC5jbCIsInVzZXJuYW1lIjoiamF2aWVyYS50YXBpYS5nQHVzYWNoLmNsIiwiZXhwIjoxNTIzODkwMDIyLCJ1c2VyX2lkIjoxM30.gN0uPsxpzJA56jKYkNVeZWCt9NLTSU0x5DwY54I01Ro");

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        gmailConfigureClient.onLoadCheckPage(response);
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
            public byte[] getBody() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("user", "jose.tapia");
                params.put("social", "gmail");

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
}
