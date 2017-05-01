package cl.josemanuel.socialconnector2.fragments.albumFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.constants.Constants;
import cl.josemanuel.socialconnector2.dummy.MessagesDummy;
import cl.josemanuel.socialconnector2.entities.MessageEntity;

public class AlbumFragment extends Fragment {

    public ArrayList<MessageEntity> messages;
    public MessageEntity currentMessage;
    public int index = 0;

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

    private void setListeners(View view) {
        view.findViewById(R.id.nextButton).setOnClickListener(new NextClickListener(this));
    }

    private void setTestEnv() {
        MessagesDummy messagesDummy = new MessagesDummy(getActivity());
        messages = messagesDummy.get3Messages();
    }
}
