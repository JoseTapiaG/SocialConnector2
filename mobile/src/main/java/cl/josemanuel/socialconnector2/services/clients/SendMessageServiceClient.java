package cl.josemanuel.socialconnector2.services.clients;

public interface SendMessageServiceClient {

    void onMessageSent();
    void onErrorSendMessage();

}
