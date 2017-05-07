package cl.josemanuel.socialconnector2.fragments.album;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cl.josemanuel.socialconnector2.R;
import cl.josemanuel.socialconnector2.entities.MessageEntity;

public class PhotoContentDialog extends DialogFragment {

    MessageEntity message;

    private void onViewInflated(View view) {
        message = (MessageEntity) getArguments().getSerializable("message");

        //update contact name and text
        ((TextView) view.findViewById(R.id.contact_name)).setText(message.getContact().getName());

        TextView messageTextView = (TextView) view.findViewById(R.id.message_text);
        messageTextView.setText(message.getText());
        messageTextView.setMovementMethod(new ScrollingMovementMethod());
        messageTextView.scrollTo(0,0);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_message, null);
        onViewInflated(view);
        builder.setView(view)
                // Add action buttons
                .setNeutralButton(R.string.close_dialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }

}
