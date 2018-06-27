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
import cl.josemanuel.socialconnector2.services.clients.MessageServiceClient;

public class MessageService extends AsyncTask<Void, Void, Void> {

    private final String URL = "https://guarded-retreat-96811.herokuapp.com/family/getMessages/";
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
        // Instantiate the RequestQueue.
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
                params.put("user", "farodrig");
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

    private List<PhotoEntity> proccessMessages(String response) {
        Gson gson = new Gson();
        Type contactsType = new TypeToken<List<MessageSC>>() {
        }.getType();
        ArrayList<MessageSC> messagesCS = gson.fromJson(response, contactsType);

        ArrayList<PhotoEntity> messages = new ArrayList<>();
        for (MessageSC messageSC : messagesCS){
            PhotoEntity photo = new PhotoEntity(
                    "",
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
}
