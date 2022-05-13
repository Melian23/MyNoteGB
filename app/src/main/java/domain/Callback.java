package domain;

public interface Callback <T> {

    void onSuccess (T result);

    void onError (Throwable exception);
}
