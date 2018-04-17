package cl.josemanuel.socialconnector2.services.clients;

public interface ContactServiceClient<T> {

    void onLoadContacts(T response);
    void onErrorLoadContacts(T response);

}
