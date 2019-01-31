package cl.josemanuel.socialconnector2.services.clients;

import java.util.List;

import cl.josemanuel.socialconnector2.entities.PhotoEntity;

public interface TokenServiceClient {

    void onTokenReceived(String token);
    void onErrorToken();

}
