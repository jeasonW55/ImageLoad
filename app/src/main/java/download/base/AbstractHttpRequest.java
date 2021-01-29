package download.base;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class AbstractHttpRequest<T> {

    public static final String METHOD_GET = "GET";

    public static final String POST = "POST";

    public static final String DELETE = "DELETE";

    public static final String PUT = "PUT";

    /**
     *
     * @return
     */
    public abstract T connect(Request request);

    /**
     *
     * @param request
     * @return
     */
    protected ByteArrayOutputStream parseNetResult(Request request) {
        URL url;
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            url = new URL(request.getUrl());
            connection = (HttpURLConnection)(url.openConnection());
            switch (request.getMethod()) {
                case GET:
                    connection.setRequestMethod(METHOD_GET);
                    break;
                default:
                    break;
            }
            inputStream = connection.getInputStream();
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] readByte = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(readByte)) != -1) {
                byteArrayOutputStream.write(readByte, 0, length);
            }
            return byteArrayOutputStream;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(connection != null) {
                    connection.disconnect();
                }
                if(inputStream != null) {
                    inputStream.close();
                }
                if(byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
