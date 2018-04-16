package cl.josemanuel.socialconnector2.fragments;

public interface ServiceFragment<T> {

    void onLoad(T response);
    void onError(T response);

}
