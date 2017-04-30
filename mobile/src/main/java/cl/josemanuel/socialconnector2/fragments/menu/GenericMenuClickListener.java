package cl.josemanuel.socialconnector2.fragments.menu;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.View;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.fragments.ContentFragment;

/**
 * Created by jose on 30-04-17.
 */

public class GenericMenuClickListener implements View.OnClickListener{

    MenuFragment menuFragment;

    public GenericMenuClickListener(MenuFragment menuFragment) {
        this.menuFragment = menuFragment;
    }

    @Override
    public void onClick(View v) {
        changeActivity(new ContentFragment());
    }

    protected void changeActivity(Fragment fragment) {
        FragmentTransaction transaction = menuFragment.getFragmentManager().beginTransaction();

        transaction.replace(R.id.content_fragment, fragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }
}
