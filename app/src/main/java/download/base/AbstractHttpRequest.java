package download.base;

import java.io.ByteArrayOutputStream;

import download.util.NetUtil;

public abstract class AbstractHttpRequest {

    public static final String METHOD_GET = "GET";

    public static final String POST = "POST";

    public static final String DELETE = "DELETE";

    public static final String PUT = "PUT";

    private Request mRequest;

    /**
     *
     * @return
     */
    public ByteArrayOutputStream connect() {
        return parseNetResult(mRequest);
    }

    /**
     *
     * @param request
     * @return
     */
    protected ByteArrayOutputStream parseNetResult(Request request) {
        return NetUtil.getByteStream(request);
    }

    public void setRequest(Request request) {
        this.mRequest = request;
    }

    public Request getRequest() {
        return mRequest;
    }
}
