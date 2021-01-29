package download.base;

public class Request {

    private String mUrl;

    private RequestMethod mMethod;

    private String mContent;

    private ICallback mCallback;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public RequestMethod getMethod() {
        return mMethod;
    }

    public void setMethod(RequestMethod mMethod) {
        this.mMethod = mMethod;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public ICallback getCallback() {
        return mCallback;
    }

    public void setCallback(ICallback mCallback) {
        this.mCallback = mCallback;
    }
}
