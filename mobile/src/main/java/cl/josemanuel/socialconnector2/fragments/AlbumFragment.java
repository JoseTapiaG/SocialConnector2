package cl.josemanuel.socialconnector2.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
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
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView messageTextView = (TextView) view.findViewById(R.id.message_text);
        messageTextView.setMovementMethod(new ScrollingMovementMethod());
        changeMessage(0);
    }

    public void updateView(View view) {
        if (index == 0) {
            view.findViewById(R.id.prevButton).setVisibility(View.INVISIBLE);
        } else {
            view.findViewById(R.id.prevButton).setVisibility(View.VISIBLE);
        }

        if (index == messages.size() - 1) {
            view.findViewById(R.id.nextButton).setVisibility(View.INVISIBLE);
        } else {
            view.findViewById(R.id.nextButton).setVisibility(View.VISIBLE);
        }
    }

    public void changeMessage(int index){
        //update currentMessage
        currentMessage = messages.get(index);

        //update contact name and text
        ((TextView) getView().findViewById(R.id.contact_name)).setText(currentMessage.getContact().getName());

        TextView messageTextView = (TextView) getView().findViewById(R.id.message_text);
        messageTextView.setText(currentMessage.getText());
        messageTextView.scrollTo(0,0);


        //search and set image of message
        ((ImageView) getView().findViewById(R.id.photo)).setImageBitmap(photoService.getPhoto(currentMessage.getPhoto()));
        updateView(getView());
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
