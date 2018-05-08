package cl.josemanuel.socialconnector2.services.clients;

import java.util.List;

import cl.josemanuel.socialconnector2.entities.ContactEntity;
import cl.josemanuel.socialconnector2.entities.MessageEntity;
import cl.josemanuel.socialconnector2.entities.PhotoEntity;

public interface MessageServiceClient {

    void onLoadMessages(List<PhotoEntity> messages);
    void onErrorLoadMessages();

}
