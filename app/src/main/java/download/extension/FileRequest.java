package download.extension;

import download.base.AbstractHttpRequest;
import download.base.Request;


public class FileRequest<T> extends AbstractHttpRequest {

    @Override
    public T connect(Request request) {
        return (T)parseNetResult(request);
    }

}
