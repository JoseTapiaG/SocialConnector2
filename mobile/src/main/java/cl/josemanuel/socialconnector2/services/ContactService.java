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

public class ContactService extends AsyncTask<Void, Void, Void> {

    private final String URL = "https://socialconnector.dcc.uchile.cl/api/myfamily/";
    private Context context;
    private Loading loading;
    private ContactsFragment contactsFragment;
    private String token;

    public ContactService(Context context, Loading loading, ContactsFragment ContactsFragment, String token) {
        this.context = context;
        this.loading = loading;
        contactsFragment = ContactsFragment;
        this.token = token;
    }

    @Override
    protected Void doInBackground(Void... params) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        contactsFragment.setContacts(processContacts("[\n" +
                "    {\n" +
                "        \"relationship\": \"\",\n" +
                "        \"rol\": \"\",\n" +
                "        \"favorite_media\": \"\",\n" +
                "        \"affective_closeness\": \"\",\n" +
                "        \"physical_closeness\": \"\",\n" +
                "        \"user\": \"jose.wt@gmail.com\",\n" +
                "        \"avatar\": null,\n" +
                "        \"first_name\": \"José\",\n" +
                "        \"last_name\": \"Tapia\",\n" +
                "        \"gender\": null,\n" +
                "        \"birthday\": null,\n" +
                "        \"age_group\": null,\n" +
                "        \"created_date\": \"2018-01-09T13:40:28.096934Z\",\n" +
                "        \"phone\": null,\n" +
                "        \"phone2\": \"\",\n" +
                "        \"address\": null,\n" +
                "        \"is_active\": true,\n" +
                "        \"social_networks\": [],\n" +
                "        \"hash\": \"da5ef5bf749e8ac2b1f28205ebbcd3fc60c7afbe76c3861385e28a53\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"relationship\": \"\",\n" +
                "        \"rol\": \"\",\n" +
                "        \"favorite_media\": \"\",\n" +
                "        \"affective_closeness\": \"\",\n" +
                "        \"physical_closeness\": \"\",\n" +
                "        \"user\": \"zuny.chincolco@gmail.com\",\n" +
                "        \"avatar\": null,\n" +
                "        \"first_name\": \"Zunilda\",\n" +
                "        \"last_name\": \"Gonzalez\",\n" +
                "        \"gender\": \"female\",\n" +
                "        \"birthday\": \"01-01\",\n" +
                "        \"age_group\": -1,\n" +
                "        \"created_date\": \"2018-01-21T15:49:11.517134Z\",\n" +
                "        \"phone\": \"+56988647736\",\n" +
                "        \"phone2\": \"\",\n" +
                "        \"address\": \"\",\n" +
                "        \"is_active\": true,\n" +
                "        \"social_networks\": [],\n" +
                "        \"hash\": \"e9bb68cc7b195449682843288bb2de3de382ec6baccc24eb1551ad90\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"relationship\": \"\",\n" +
                "        \"rol\": \"\",\n" +
                "        \"favorite_media\": \"\",\n" +
                "        \"affective_closeness\": \"\",\n" +
                "        \"physical_closeness\": \"\",\n" +
                "        \"user\": \"tapia.leo@gmail.com\",\n" +
                "        \"avatar\": null,\n" +
                "        \"first_name\": \"Victor\",\n" +
                "        \"last_name\": \"Tapia\",\n" +
                "        \"gender\": \"male\",\n" +
                "        \"birthday\": \"01-01\",\n" +
                "        \"age_group\": 3,\n" +
                "        \"created_date\": \"2018-01-21T15:47:59.813453Z\",\n" +
                "        \"phone\": \"+56941362228\",\n" +
                "        \"phone2\": \"\",\n" +
                "        \"address\": \"\",\n" +
                "        \"is_active\": true,\n" +
                "        \"social_networks\": [1,2,3],\n" +
                "        \"hash\": \"18477c280c2edae582c80185855a04ee95e5537ebff068b76353964c\"\n" +
                "    }\n" +
                "]"));

        /*// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.hideLoadingDialog();
                        contactsFragment.setContacts(processContacts(response));
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
                params.put("Authorization", "JWT" + token);
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);*/
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
