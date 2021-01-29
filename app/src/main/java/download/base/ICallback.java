package download.base;

public interface ICallback<T> {
    void onSuccess(T result);
    void onFailure(Exception e);
}
