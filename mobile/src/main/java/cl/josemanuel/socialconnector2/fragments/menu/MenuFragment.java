package cl.josemanuel.socialconnector2.fragments.menu;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cl.josemanuel.socialconnector2.R;

import static cl.josemanuel.socialconnector2.activities.MainActivity.messageService;
import static cl.josemanuel.socialconnector2.activities.MainActivity.photoService;


public class MenuFragment extends Fragment {

    public MenuHandler currentMenuHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        setMenuListeners(view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ScheduledExecutorService scheduleCheckMessagesTaskExecutor = Executors.newScheduledThreadPool(5);
        scheduleCheckMessagesTaskExecutor.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
//                        Check messages
                        long l = messageService.countNewMessages();
                        if (l > 0) {
                            updateNewMessagesMenu(view, View.VISIBLE);
                        } else {
                            updateNewMessagesMenu(view, View.GONE);
                        }
//                      Check photos
                        long p = photoService.countNewPhotos();
                        if (p > 0) {
                            updateNewPhotosMenu(view, View.VISIBLE);
                        } else {
                            updateNewPhotosMenu(view, View.GONE);
                        }
                    }
                }, 5L, 5L, TimeUnit.SECONDS);
    }

    private void updateNewMessagesMenu(final View view, final int visibility) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.findViewById(R.id.newMessagesCounter).setVisibility(visibility);
            }
        });
    }

    private void updateNewPhotosMenu(final View view, final int visibility) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.findViewById(R.id.newPhotosCounter).setVisibility(visibility);
            }
        });
    }

    private void setMenuListeners(View view) {
        view.findViewById(R.id.skypeButton).setOnClickListener(new SkypeMenuHandler(this));
        view.findViewById(R.id.albumButton).setOnClickListener(new AlbumMenuHandler(this));
        view.findViewById(R.id.sendMessageButton).setOnClickListener(new SendMessageMenuHandler(this));
        view.findViewById(R.id.newPhotosButton).setOnClickListener(new NewPhotosMenuHandler(this));
        view.findViewById(R.id.newMessagesButton).setOnClickListener(new NewMessagesMenuHandler(this));
        view.findViewById(R.id.settingsButton).setOnClickListener(new SettingsMenuHandler(this));
    }
}
