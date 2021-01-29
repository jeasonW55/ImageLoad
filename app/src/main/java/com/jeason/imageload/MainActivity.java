package com.jeason.imageload;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import download.base.ICallback;
import download.base.Request;
import download.base.RequestMethod;
import download.extension.FileRequest;

public class MainActivity extends AppCompatActivity implements ICallback<Bitmap> {

    private ImageView mLoadImageView;

    private Handler mUIHandler;

    private static ArrayList<String> urlList = new ArrayList<>();

    static {
        urlList.add("https://t7.baidu.com/it/u=1956604245,3662848045&fm=193&f=GIF");
        urlList.add("https://t7.baidu.com/it/u=2529476510,3041785782&fm=193&f=GIF");
        urlList.add("https://t7.baidu.com/it/u=727460147,2222092211&fm=193&f=GIF");
        urlList.add("https://t7.baidu.com/it/u=2511982910,2454873241&fm=193&f=GIF");
        urlList.add("https://t7.baidu.com/it/u=825057118,3516313570&fm=193&f=GIF");
    }

    private Runnable mShowImage = new Runnable() {
        int index = 0;
        @Override
        public void run() {
            requestImage(index++);
            if(index == urlList.size()){
                index = 0;
            }
            mUIHandler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUIHandler = new Handler(getMainLooper());
        initView();
        mUIHandler.postDelayed(mShowImage, 1000);
    }

    private void initView() {
        mLoadImageView = findViewById(R.id.load_image);
    }

    public void requestImage(int i) {
        Request request = new Request();
        request.setUrl(urlList.get(i));
        request.setMethod(RequestMethod.GET);
        request.setCallback(this);
        FileRequest<ByteArrayOutputStream> fileRequest = new FileRequest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ByteArrayOutputStream byteArrayOutputStream = fileRequest.connect(request);
                byte[] bitmapByte = byteArrayOutputStream.toByteArray();
                Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapByte,0, bitmapByte.length);
                mUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onSuccess(bitmap);
                    }
                });
            }
        }).start();

    }

    @Override
    public void onSuccess(Bitmap bitmap) {
        mLoadImageView.setImageBitmap(bitmap);
    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        mUIHandler.removeCallbacks(mShowImage);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mUIHandler.removeCallbacks(mShowImage);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUIHandler.removeCallbacks(mShowImage);
    }
}