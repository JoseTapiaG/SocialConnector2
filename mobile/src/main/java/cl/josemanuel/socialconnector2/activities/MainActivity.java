package cl.josemanuel.socialconnector2.activities;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.dummy.ContactsDummy;
import cl.josemanuel.socialconnector2.dummy.MessagesDummy;
import cl.josemanuel.socialconnector2.fragments.ContentFragment;
import cl.josemanuel.socialconnector2.services.MessageService;
import cl.josemanuel.socialconnector2.services.PhotoService;
import github.ankyl.castscreen.CastScreenActivity;

import static cl.josemanuel.socialconnector2.constants.Constants.TEST;


public class MainActivity extends CastScreenActivity {

    // Photo Service
    public static PhotoService photoService;
    public static MessageService messageService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photoService = new PhotoService(this);
        messageService = new MessageService(this);
        initContentFragment();
        if(TEST){
            setTestEnv();
        }
    }

    private void setTestEnv(){
        ContactsDummy contactsDummy = new ContactsDummy(this);
        MessagesDummy messagesDummy = new MessagesDummy(this);

        contactsDummy.loadContacts();
        messagesDummy.loadMessages();
    }

    private void initContentFragment() {
        // Create new fragment and transaction
        ContentFragment contentFragment = new ContentFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.content_fragment, contentFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem castButtonMenuItem = menu.findItem(R.id.media_route_menu_item);
        super.prepareCastButton(castButtonMenuItem, "70D04A1B");
        return true;
    }
}
