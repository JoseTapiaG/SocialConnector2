package cl.josemanuel.socialconnector2.services;

import android.content.Context;

import java.util.ArrayList;

import cl.josemanuel.socialconnector2.database.ContactDB;
import cl.josemanuel.socialconnector2.database.MessageDB;
import cl.josemanuel.socialconnector2.entities.ContactEntity;
import cl.josemanuel.socialconnector2.entities.MessageEntity;

public class MessageService {

    MessageDB messageDB;

    public MessageService(Context context) {
        messageDB = new MessageDB(context);
    }

    public ArrayList<MessageEntity> getMessages(){
        return messageDB.getMessages();
    }

    public long countNewMessages(){
        return messageDB.countNewMessages();
    }
}
