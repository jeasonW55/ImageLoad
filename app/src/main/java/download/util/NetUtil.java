package download.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import download.base.AbstractHttpRequest;
import download.base.Request;

public final class NetUtil {

    public static ByteArrayOutputStream getByteStream(Request request) {
        URL url;
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            url = new URL(request.getUrl());
            connection = (HttpURLConnection)(url.openConnection());
            switch (request.getMethod()) {
                case GET:
                    connection.setRequestMethod(AbstractHttpRequest.METHOD_GET);
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
