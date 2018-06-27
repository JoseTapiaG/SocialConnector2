package cl.josemanuel.socialconnector2.fragments.error;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cl.josemanuel.socialconnector2.R;

public class ErrorFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_error, container, false);

        // Error message
        String message = getArguments().getString("message");
        ((TextView)view.findViewById(R.id.messageError)).setText(message);

        // Background color
        int res_layout = getArguments().getInt("background_color");
        view.setBackgroundColor(getResources().getColor(res_layout));

        // Click error
        ClickError clickError = (ClickError) getArguments().getSerializable("clickError");
        view.findViewById(R.id.volverError).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clickError.click();
                    }
                }
        );
        return view;
    }

}
