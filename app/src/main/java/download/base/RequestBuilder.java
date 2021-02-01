package download.base;

public class RequestBuilder<T extends AbstractHttpRequest, V> {

    private T mRequest;

    private Class<T> tClass;

    public RequestBuilder(Class<T> tClass) {
        this.tClass = tClass;
    }


    public RequestBuilder<T, V> init() {
        try {
            mRequest = tClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if (mRequest != null) {
            mRequest.setRequest(new Request());
        }
        return this;
    }

    public RequestBuilder<T, V> setRequestUrl(String url) {
        mRequest.getRequest().setUrl(url);
        return this;
    }

    public RequestBuilder<T, V> setRequestContent(String content) {
        mRequest.getRequest().setContent(content);
        return this;
    }

    public RequestBuilder<T, V> setRequestCallback(ICallback<V> iCallback) {
        mRequest.getRequest().setCallback(iCallback);
        return this;
    }

    public RequestBuilder<T, V> setRequestMethod(RequestMethod method) {
        if (method == null) {
            method = RequestMethod.GET;
        }
        mRequest.getRequest().setMethod(method);
        return this;
    }

    public AbstractHttpRequest builder() {
        return mRequest;
    }

}
