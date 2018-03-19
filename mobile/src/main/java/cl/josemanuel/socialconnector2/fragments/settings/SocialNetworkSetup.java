package cl.josemanuel.socialconnector2.fragments.settings;

import android.view.View;
import android.widget.Toast;

import cl.josemanuel.socialconnector2.R;

/**
 * Created by farodrig on 12-03-18.
 */

public class SocialNetworkSetup {

    String id;
    String name;
    String pass;
    String extra;
    String icon;
    boolean connected;
    public SocialNetworkButtonOnClick<View, String, Void> onClick;


    public SocialNetworkSetup (String id, String name){
        this.id = id;
        this.name = name;
        connected = false;
    }

    //Getters
    public String getId(){ return id; }
    public String getName(){ return name; }
    public String getPass(){ return pass; }
    public String getExtra(){ return extra; }
    public String getIcon(){ return "ic_" + id; }
    public boolean getConnected(){ return connected; }

    //Setters
    public SocialNetworkSetup setId(String id) {this.id = id; return this;}
    public SocialNetworkSetup setName(String name) {this.name = name; return this;}
    public SocialNetworkSetup setPass(String pass) {this.pass = pass; return this;}
    public SocialNetworkSetup setExtra(String extra) {this.extra = extra; return this;}
    public SocialNetworkSetup setConnected(boolean connected) {
        this.connected = connected;
        return this;
    }

    public void defaultButtonClick(View view, String pass){
        Toast.makeText(view.getContext(), "Hello world " + pass, Toast.LENGTH_SHORT).show();
    }
}

@FunctionalInterface
interface SocialNetworkButtonOnClick <A, B, R> {
    //R is like Return, but doesn't have to be last in the list nor named R.
    public R apply (A a, B b);
}
