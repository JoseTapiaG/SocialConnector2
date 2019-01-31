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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.josemanuel.socialconnector2.database.ContactDB;
import cl.josemanuel.socialconnector2.database.MessageDB;
import cl.josemanuel.socialconnector2.dialogs.Loading;
import cl.josemanuel.socialconnector2.entities.ContactEntity;
import cl.josemanuel.socialconnector2.entities.ContactSC;
import cl.josemanuel.socialconnector2.entities.MessageEntity;
import cl.josemanuel.socialconnector2.entities.MessageSC;
import cl.josemanuel.socialconnector2.entities.PhotoEntity;
import cl.josemanuel.socialconnector2.services.clients.ContactServiceClient;
import cl.josemanuel.socialconnector2.services.clients.LoginServiceClient;
import cl.josemanuel.socialconnector2.services.clients.MessageServiceClient;
import cl.josemanuel.socialconnector2.services.clients.TokenServiceClient;

public class MessageService extends AsyncTask<Void, Void, Void> implements TokenServiceClient{

    private final String URL = "https://socialtranslator.dcc.uchile.cl/community/getMessages/";
    private Context context;
    private Loading loading;
    private MessageServiceClient messageServiceClient;

    public MessageService(Context context, Loading loading, MessageServiceClient messageServiceClient, String token) {
        this.context = context;
        this.loading = loading;
        this.messageServiceClient = messageServiceClient;
    }

    @Override
    protected Void doInBackground(Void... params) {
        if(false){
            getMessages("");
        } else {
            (new TokenService(context, this)).execute();
        }

        return null;
    }

    private void getMessages(String token) {
        RequestQueue queue = Volley.newRequestQueue(context);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.hideLoadingDialog();
                        messageServiceClient.onLoadMessages(proccessMessages(response));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error
                loading.hideLoadingDialog();
                error.printStackTrace();
                messageServiceClient.onErrorLoadMessages();
            }
        }) {
            @Override
            public byte[] getBody() {
                HashMap<String, String> params = new HashMap<>();
                params.put("user", "jose.wt@gmail.com");
                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("Authorization", "JWT " + token);
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private List<PhotoEntity> proccessMessages(String response) {
        Gson gson = new Gson();
        Type contactsType = new TypeToken<List<MessageSC>>() {
        }.getType();
        ArrayList<MessageSC> messagesCS = gson.fromJson(response, contactsType);

        ArrayList<PhotoEntity> messages = new ArrayList<>();
        for (MessageSC messageSC : messagesCS){
            PhotoEntity photo = new PhotoEntity(
                    messageSC.getFile(),
                    "",
                    new Date()
            );
            MessageEntity message = new MessageEntity(
                    messageSC.getContent(),
                    new Date(),
                    messageSC.isAck()
            );
            ContactEntity contact = new ContactEntity("Test", "test@test.cl", "test");
            photo.setContact(contact);
            photo.setMessage(message);
            messages.add(photo);
        }

        return messages;
    }

    @Override
    public void onTokenReceived(String token) {
        getMessages(token);
    }

    @Override
    public void onErrorToken() {
        loading.hideLoadingDialog();
    }
}
