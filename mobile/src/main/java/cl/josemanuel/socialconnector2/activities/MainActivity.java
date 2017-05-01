package cl.josemanuel.socialconnector2.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.fragments.ContentFragment;
import cl.josemanuel.socialconnector2.services.PhotoService;


public class MainActivity extends Activity {

    // Photo Service
    public static PhotoService photoService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photoService = new PhotoService(this);
        initContentFragment();
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
}
