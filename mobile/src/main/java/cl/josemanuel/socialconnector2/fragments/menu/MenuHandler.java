package cl.josemanuel.socialconnector2.fragments.menu;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.fragments.ContentFragment;
import cl.josemanuel.socialconnector2.fragments.menu.MenuFragment;


public abstract class MenuHandler implements View.OnClickListener{

    MenuFragment menuFragment;
    View currentView;
    int backgroundDrawable;

    public MenuHandler(MenuFragment menuFragment) {
        this.menuFragment = menuFragment;
    }

    @Override
    public void onClick(View v) {
        if(menuFragment.currentMenuHandler == this)
            return;

        v.setBackground(ContextCompat.getDrawable(menuFragment.getActivity(), backgroundDrawable));
        currentView = v;
        if (menuFragment.currentMenuHandler != null) {
            menuFragment.currentMenuHandler.removeActiveDrawable();
        }
        menuFragment.currentMenuHandler = this;
    }

    protected void changeActivity(Fragment fragment) {
        FragmentTransaction transaction = menuFragment.getFragmentManager().beginTransaction();

        transaction.replace(R.id.content_fragment, fragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    protected abstract void removeActiveDrawable();
}
