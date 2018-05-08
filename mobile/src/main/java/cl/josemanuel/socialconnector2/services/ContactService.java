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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cl.josemanuel.socialconnector2.database.ContactDB;
import cl.josemanuel.socialconnector2.database.SocialConnectorContract;
import cl.josemanuel.socialconnector2.dialogs.Loading;
import cl.josemanuel.socialconnector2.entities.ContactEntity;
import cl.josemanuel.socialconnector2.entities.ContactSC;
import cl.josemanuel.socialconnector2.entities.PhotoEntity;
import cl.josemanuel.socialconnector2.fragments.contacts.ContactsFragment;
import cl.josemanuel.socialconnector2.services.clients.ContactServiceClient;

public class ContactService extends AsyncTask<Void, Void, Void> {

    private final String URL = "https://socialconnector.dcc.uchile.cl/api/myfamily/";
    private Context context;
    private Loading loading;
    private ContactServiceClient contactServiceClient;
    private String token;

    public ContactService(Context context, Loading loading, ContactServiceClient contactServiceClient, String token) {
        this.context = context;
        this.loading = loading;
        this.contactServiceClient = contactServiceClient;
        this.token = token;
    }

    @Override
    protected Void doInBackground(Void... params) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.hideLoadingDialog();
                        contactServiceClient.onLoadContacts(processContacts(response));
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error
                loading.hideLoadingDialog();
                error.printStackTrace();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("Authorization", "JWT " + token);
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        return null;
    }

    private List<ContactEntity> processContacts(String response) {
        Gson gson = new Gson();
        Type contactsType = new TypeToken<List<ContactSC>>() {
        }.getType();
        ArrayList<ContactSC> contactsCS = gson.fromJson(response, contactsType);

        ArrayList<ContactEntity> contacts = new ArrayList<>();
        for (ContactSC contactSC : contactsCS) {
            ContactEntity contact = new ContactEntity(
                    contactSC.getFirst_name() + " " + contactSC.getLast_name(),
                    contactSC.getUser(),
                    "");
            contact.setAvatar(new PhotoEntity(contactSC.getAvatar(), null, new Date()));
            contacts.add(contact);
        }

        return contacts;
    }
}
