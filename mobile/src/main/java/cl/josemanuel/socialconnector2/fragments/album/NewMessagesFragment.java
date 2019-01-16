package cl.josemanuel.socialconnector2.fragments.album;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.dialogs.Loading;
import cl.josemanuel.socialconnector2.entities.PhotoEntity;
import cl.josemanuel.socialconnector2.fragments.error.ClickError;
import cl.josemanuel.socialconnector2.services.MessageService;
import cl.josemanuel.socialconnector2.services.clients.MessageServiceClient;

public class NewMessagesFragment extends PhotoFragment implements MessageServiceClient {

    Loading loadingMessages;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutRes = R.layout.fragment_new_messages;
        photos = new ArrayList<>();

        loadingMessages = new Loading(getActivity());
        loadingMessages.showLoadingDialog("Cargando mensajes");
        (new MessageService(getActivity(), loadingMessages, this, "")).execute();
    }

    @Override
    public void onLoadMessages(List<PhotoEntity> messages) {
        photos = (ArrayList<PhotoEntity>) messages;

        if(!photos.isEmpty()){
            TextView messageTextView = (TextView) getActivity().findViewById(R.id.message_text);
            messageTextView.setMovementMethod(new ScrollingMovementMethod());
            changePhoto(0);
        }
    }

    @Override
    public void onErrorLoadMessages() {
        super.onError("En estos momentos no podemos atenderlo", R.color.background_new_photos,
                new ClickError() {

                    public void click() {

                    }
                });
    }
}
