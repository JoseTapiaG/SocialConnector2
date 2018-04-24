package cl.josemanuel.socialconnector2.services.clients;

public interface LoginServiceClient {

    void onLoadLogin(String response);
    void onErrorLogin(String response);

}
