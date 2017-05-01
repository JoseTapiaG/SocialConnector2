package cl.josemanuel.socialconnector2.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.constants.Constants;
import cl.josemanuel.socialconnector2.dummy.MessagesDummy;
import cl.josemanuel.socialconnector2.entities.MessageEntity;

import static cl.josemanuel.socialconnector2.activities.MainActivity.photoService;

public class AlbumFragment extends Fragment {

    private ArrayList<MessageEntity> messages;
    private MessageEntity currentMessage;
    private int index = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Constants.TEST) {
            setTestEnv();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_album, container, false);
        setListeners(view);
        updateView(view);
        return view;
    }

    public void updateView(View view) {
        if (index == 0) {
            view.findViewById(R.id.nextButton).setVisibility(View.INVISIBLE);
        }

        if (index == messages.size()) {
            view.findViewById(R.id.nextButton).setVisibility(View.INVISIBLE);
        }
    }

    public void changeMessage(int index){
        //update currentMessage
        currentMessage = messages.get(index);

        //update contact name and text
        ((TextView) getView().findViewById(R.id.contact_name)).setText(currentMessage.getContact().getName());
        ((TextView) getView().findViewById(R.id.message_text)).setText(currentMessage.getText());

        //search and set image of message
        ((ImageView) getView().findViewById(R.id.photo)).setImageBitmap(photoService.getPhoto(currentMessage.getPhoto()));
    }

    private void setListeners(View view) {
        view.findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMessage(++index);
            }
        });

        view.findViewById(R.id.prevButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMessage(--index);
            }
        });
    }

    private void setTestEnv() {
        MessagesDummy messagesDummy = new MessagesDummy(getActivity());
        messages = messagesDummy.get3Messages();
    }
}
