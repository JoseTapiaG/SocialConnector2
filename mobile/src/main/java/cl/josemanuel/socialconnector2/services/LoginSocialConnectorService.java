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
import java.util.Map;

import cl.josemanuel.socialconnector2.dialogs.Loading;
import cl.josemanuel.socialconnector2.dialogs.LoginSocialConnector;
import cl.josemanuel.socialconnector2.fragments.ServiceFragment;
import cl.josemanuel.socialconnector2.fragments.contacts.ContactsFragment;

public class LoginSocialConnectorService extends AsyncTask<Void, Void, Void> {

    private Context context;
    private Loading loading;
    private String username;
    private String password;
    private ServiceFragment serviceFragment;
    private String URL = "https://socialconnector.dcc.uchile.cl/api/login/";

    public LoginSocialConnectorService(Context context, Loading loading, String username, String password, ServiceFragment serviceFragment) {
        this.context = context;
        this.loading = loading;
        this.username = username;
        this.password = password;
        this.serviceFragment = serviceFragment;
    }

    @Override
    protected Void doInBackground(Void... params) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        loading.hideLoadingDialog();
        serviceFragment.onLoad("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImphdmllcmEudGFwaWEuZ0B1c2FjaC5jbCIsInVzZXJuYW1lIjoiamF2aWVyYS50YXBpYS5nQHVzYWNoLmNsIiwiZXhwIjoxNTIzODkwMDIyLCJ1c2VyX2lkIjoxM30.gN0uPsxpzJA56jKYkNVeZWCt9NLTSU0x5DwY54I01Ro");

        /*// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.hideLoadingDialog();
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error
                error.printStackTrace();
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("username", "javiera.tapia.g@usach.cl");
                params.put("password", "pass1234");
//                params.put("username", LoginSocialConnectorService.this.username);
//                params.put("password", LoginSocialConnectorService.this.password);
                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);*/
        return null;
    }
}
