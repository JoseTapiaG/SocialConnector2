package cl.josemanuel.socialconnector2.services.clients;

public interface GmailConfigureClient {

    void onLoadCheckPage(String response);
    void onError(String response);

}
