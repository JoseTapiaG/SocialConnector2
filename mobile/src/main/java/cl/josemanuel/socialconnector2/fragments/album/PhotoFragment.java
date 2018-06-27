package cl.josemanuel.socialconnector2.fragments.album;

import android.app.Fragment;
import android.app.FragmentTransaction;
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
import cl.josemanuel.socialconnector2.dummy.PhotoDummy;
import cl.josemanuel.socialconnector2.entities.ContactEntity;
import cl.josemanuel.socialconnector2.entities.PhotoEntity;
import cl.josemanuel.socialconnector2.fragments.error.ClickError;
import cl.josemanuel.socialconnector2.fragments.error.ErrorFragment;

import static cl.josemanuel.socialconnector2.activities.MainActivity.photoService;

public abstract class PhotoFragment extends Fragment {

    protected ArrayList<PhotoEntity> photos;
    protected PhotoEntity currentPhoto;
    protected int index = 0;
    protected int layoutRes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(layoutRes, container, false);
        setListeners(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(!photos.isEmpty()){
            TextView messageTextView = (TextView) view.findViewById(R.id.message_text);
            messageTextView.setMovementMethod(new ScrollingMovementMethod());
            changePhoto(0);
        }
    }

    public void updateView(View view) {
        if (index == 0) {
            view.findViewById(R.id.prevButton).setVisibility(View.INVISIBLE);
        } else {
            view.findViewById(R.id.prevButton).setVisibility(View.VISIBLE);
        }

        if (index == photos.size() - 1) {
            view.findViewById(R.id.nextButton).setVisibility(View.INVISIBLE);
        } else {
            view.findViewById(R.id.nextButton).setVisibility(View.VISIBLE);
        }
    }

    public void changePhoto(int index){
        //update currentMessage
        currentPhoto = photos.get(index);

        //update contact name and text
        ((TextView) getView().findViewById(R.id.contact_name)).setText(currentPhoto.getContact().getName());

        TextView messageTextView = (TextView) getView().findViewById(R.id.message_text);
        messageTextView.setText(currentPhoto.getMessage().getText());
        messageTextView.scrollTo(0,0);


        //search and set image of message
        ((ImageView) getView().findViewById(R.id.photo)).setImageBitmap(photoService.getBitmap(currentPhoto));
        updateView(getView());

        photoService.updatePhotoSeenState(currentPhoto.getId());
    }

    private void setListeners(View view) {
        view.findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePhoto(++index);
            }
        });

        view.findViewById(R.id.prevButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePhoto(--index);
            }
        });
    }

    protected void onError(String message, int background_color, ClickError clickError){
        FragmentTransaction transaction = this.getFragmentManager().beginTransaction();
        ErrorFragment errorFragment = new ErrorFragment();

        Bundle args = new Bundle();
        args.putString("message", message);
        args.putSerializable("clickError", clickError);
        args.putInt("background_color", background_color);
        errorFragment.setArguments(args);

        transaction.replace(R.id.content_fragment, errorFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
