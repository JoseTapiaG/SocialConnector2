package cl.josemanuel.socialconnector2.settings.socialnetwork;

import android.view.View;
import android.widget.Toast;

/**
 * Created by farodrig on 12-03-18.
 */

public class SocialNetworkSetup {

    String id;
    String name;
    String pass;
    boolean requireText;
    boolean connected;
    Function<View, String, Void> listener = (view, pass) ->{
        Toast toast = Toast.makeText(view.getContext(), "Default Message", Toast.LENGTH_SHORT);
        toast.show();
        return null;
    };


    public SocialNetworkSetup (String id, String name){
        this.id = id;
        this.name = name;
        connected = false;
        requireText = true;
    }

    //Getters
    public String getId(){ return id; }
    public String getName(){ return name; }
    public String getPass(){ return pass; }
    public String getIcon(){ return "ic_" + id; }
    public boolean getRequireText(){ return requireText; }
    public boolean getConnected(){ return connected; }
    public Function<View, String, Void> getListener(){ return listener; }

    //Setters
    public SocialNetworkSetup setId(String id) {this.id = id; return this;}
    public SocialNetworkSetup setName(String name) {this.name = name; return this;}
    public SocialNetworkSetup setPass(String pass) {this.pass = pass; return this;}
    public SocialNetworkSetup setRequireText(boolean requireText) {
        this.requireText = requireText;
        return this;
    }
    public SocialNetworkSetup setListener(Function<View, String, Void> listener) {
        this.listener = listener;
        return this;
    }
    public SocialNetworkSetup setConnected(boolean connected) {
        this.connected = connected;
        return this;
    }
}

@FunctionalInterface
interface Function<One, Two, Three> {
    Three apply(One one, Two two);
}