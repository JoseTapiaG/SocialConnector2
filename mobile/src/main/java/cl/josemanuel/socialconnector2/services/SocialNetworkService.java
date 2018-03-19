package cl.josemanuel.socialconnector2.services;

import java.util.ArrayList;

import cl.josemanuel.socialconnector2.fragments.settings.socialnetwork.GmailSetup;
import cl.josemanuel.socialconnector2.fragments.settings.socialnetwork.SocialNetworkSetup;
import cl.josemanuel.socialconnector2.fragments.settings.socialnetwork.TelegramSetup;

/**
 * Created by farodrig on 12-03-18.
 */

public class SocialNetworkService {


    public static ArrayList<SocialNetworkSetup> getSocialNetworks() {
        ArrayList<SocialNetworkSetup> socialNetworks = new ArrayList<SocialNetworkSetup>();
        socialNetworks.add(new TelegramSetup("Telegram").setConnected(true));
        socialNetworks.add(new SocialNetworkSetup("skype", "Skype"));
        socialNetworks.add(new GmailSetup("Gmail"));
        return socialNetworks;
    }

    public static String configure(String sn, String code) throws Exception{
        if (sn.compareTo("telegram") == 0){
            throw new Exception();
        }
        String url = "http://www.google.cl";
        return url;
    }
}
