package cl.josemanuel.socialconnector2.fragments.album;

import android.app.DialogFragment;
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
import cl.josemanuel.socialconnector2.entities.MessageEntity;

import static cl.josemanuel.socialconnector2.activities.MainActivity.photoService;

public class AlbumFragment2 extends Fragment {

    private ArrayList<MessageEntity> messages;
    private MessageEntity currentMessage;
    private int index = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //messages = messageService.getMessages();
        messages = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_album2, container, false);
        setListeners(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

    public void changeMessage(int index) {
        //update currentMessage
        currentMessage = messages.get(index);

        //update contact name and text
        ((TextView) getView().findViewById(R.id.contact_name)).setText(currentMessage.getContact().getName());

        //search and set image of message
        ((ImageView) getView().findViewById(R.id.photo)).setImageBitmap(photoService.getBitmap(currentMessage.getPhoto()));
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

        view.findViewById(R.id.button_show_photo_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPhotoContentDialog();
            }
        });
    }

    private void showPhotoContentDialog() {
        DialogFragment photoContentDialog = new PhotoContentDialog();

        Bundle args = new Bundle();
        args.putSerializable("message", currentMessage);
        photoContentDialog.setArguments(args);
        photoContentDialog.show(getFragmentManager(), "missiles");
    }
}
