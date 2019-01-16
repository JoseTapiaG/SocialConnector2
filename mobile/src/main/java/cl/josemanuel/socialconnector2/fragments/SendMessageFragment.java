package cl.josemanuel.socialconnector2.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.dialogs.Loading;
import cl.josemanuel.socialconnector2.entities.PhotoEntity;
import cl.josemanuel.socialconnector2.services.ContactService;
import cl.josemanuel.socialconnector2.services.MessageService;
import cl.josemanuel.socialconnector2.services.SendMessageService;
import cl.josemanuel.socialconnector2.services.clients.MessageServiceClient;
import cl.josemanuel.socialconnector2.services.clients.SendMessageServiceClient;

public class SendMessageFragment extends Fragment implements SendMessageServiceClient {

    private static final int REQUEST_CODE = 1;
    private Loading loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        loading = new Loading(getActivity());

        return inflater.inflate(R.layout.fragment_send_message, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.voice_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.showLoadingDialog("Enviando mensaje");
                (new SendMessageService(getActivity(), loading, "test", SendMessageFragment.this)).execute();
                //startVoiceRecognitionActivity();
            }
        });
    }

    private void startVoiceRecognitionActivity() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-CL");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "es-CL");
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            if (matches.size() > 0) {
                updateView(getView(), matches.get(0));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateView(View view, String message) {
        ((TextView) view.findViewById(R.id.send_message_text)).setText(message);
    }

    @Override
    public void onMessageSent() {

    }

    @Override
    public void onErrorSendMessage() {

    }
}
