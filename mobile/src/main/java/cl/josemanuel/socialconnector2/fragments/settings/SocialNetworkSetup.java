package cl.josemanuel.socialconnector2.fragments.settings;

import android.view.View;

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
    View.OnClickListener sendListener = null;


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
    public View.OnClickListener getSendListener() { return sendListener; }

    //Setters
    public SocialNetworkSetup setId(String id) {this.id = id; return this;}
    public SocialNetworkSetup setName(String name) {this.name = name; return this;}
    public SocialNetworkSetup setPass(String pass) {this.pass = pass; return this;}
    public SocialNetworkSetup setExtra(String extra) {this.extra = extra; return this;}
    public SocialNetworkSetup setConnected(boolean connected) {
        this.connected = connected;
        return this;
    }
    public SocialNetworkSetup setSendListener(View.OnClickListener listener) {
        this.sendListener = listener;
        return this;
    }
}
