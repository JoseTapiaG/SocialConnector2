package cl.josemanuel.socialconnector2.services.clients;

import java.util.List;

import cl.josemanuel.socialconnector2.entities.ContactEntity;

public interface TelegramCodeClient {

    void onTelegramCodeSended();
    void onErrorSendTelegramCode();

    void onTelegramCheckSended();
}
