package cl.josemanuel.socialconnector2.fragments.album;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.dialogs.Loading;
import cl.josemanuel.socialconnector2.entities.ContactEntity;
import cl.josemanuel.socialconnector2.entities.MessageEntity;
import cl.josemanuel.socialconnector2.entities.PhotoEntity;
import cl.josemanuel.socialconnector2.services.MessageService;
import cl.josemanuel.socialconnector2.services.clients.MessageServiceClient;


public class AlbumFragment extends PhotoFragment implements MessageServiceClient{

    Loading loadingMessages;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutRes = R.layout.fragment_album;
        photos = new ArrayList<>();

        ContactEntity contact = (ContactEntity) getArguments().getSerializable("contact");
        loadingMessages = new Loading(getActivity());
        loadingMessages.showLoadingDialog("Cargando mensajes");
        (new MessageService(getActivity(), loadingMessages, this, contact.getId() + "")).execute();
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

    }
}
