package cl.josemanuel.socialconnector2.services;

import java.util.ArrayList;

import cl.josemanuel.socialconnector2.fragments.settings.SocialNetworkSetup;

/**
 * Created by farodrig on 12-03-18.
 */

public class SocialNetworkService {


    public static ArrayList<SocialNetworkSetup> getSocialNetworks() {
        ArrayList<SocialNetworkSetup> socialNetworks = new ArrayList<SocialNetworkSetup>();
        socialNetworks.add(new SocialNetworkSetup("telegram", "Telegram").setConnected(true));
        socialNetworks.add(new SocialNetworkSetup("skype", "Skype"));
        socialNetworks.add(new SocialNetworkSetup("gmail", "Gmail").setConnected(true));
        return socialNetworks;
    }
}
