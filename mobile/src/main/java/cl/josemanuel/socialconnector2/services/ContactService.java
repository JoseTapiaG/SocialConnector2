package cl.josemanuel.socialconnector2.services;

import android.content.Context;

import java.util.ArrayList;

import cl.josemanuel.socialconnector2.database.ContactDB;
import cl.josemanuel.socialconnector2.entities.ContactEntity;

public class ContactService {

    ContactDB contactDB;

    public ContactService(Context context) {
        contactDB = new ContactDB(context);
    }

    public ArrayList<ContactEntity> getContacts(){
        return contactDB.getContacts();
    }
}
