package cl.josemanuel.socialconnector2.services;

import android.content.Context;
import android.os.AsyncTask;

import java.util.concurrent.TimeUnit;

import cl.josemanuel.socialconnector2.database.ContactDB;
import cl.josemanuel.socialconnector2.fragments.contacts.ContactsFragment;

public class ContactService extends AsyncTask<ContactsFragment, Void, Void>{

    ContactDB contactDB;

    public ContactService(Context context, ContactsFragment ContactsFragment, String token) {
        contactDB = new ContactDB(context);
    }

    @Override
    protected Void doInBackground(ContactsFragment... fragments) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fragments[0].setContacts(contactDB.getContacts());

        return null;
    }
}
