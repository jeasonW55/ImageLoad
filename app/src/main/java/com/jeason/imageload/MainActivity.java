package com.jeason.imageload;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import download.base.AbstractHttpRequest;
import download.base.ICallback;
import download.base.Request;
import download.base.RequestBuilder;
import download.base.RequestMethod;
import download.base.UnitUrl;
import download.extension.FileRequest;
import threadPool.ThreadPool;

import static download.base.RequestMethod.GET;

public class MainActivity extends AppCompatActivity implements ICallback<Bitmap>, UnitUrl, View.OnClickListener {

    private ImageView mLoadImageView;

    private Handler mUIHandler;

    private Runnable mShowImage = new Runnable() {
        int index = 0;
        @Override
        public void run() {
            request(index++);
            if(index == imageUrlList.size()){
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
        initImageUrl();

        mUIHandler.postDelayed(mShowImage, 0);

        mLoadImageView.setOnClickListener(this);
    }

    private void initView() {
        mLoadImageView = findViewById(R.id.load_image);
    }

    private void request(int i) {
        AbstractHttpRequest fileRequest = new RequestBuilder<FileRequest, Bitmap>(FileRequest.class)
                .init()
                .setRequestUrl(imageUrlList.get(i))
                .setRequestCallback(MainActivity.this)
                .setRequestMethod(GET)
                .builder();
        ThreadPool.getSingleThreadPool(new Runnable() {
            @Override
            public void run() {
                ByteArrayOutputStream byteArrayOutputStream = fileRequest.connect();
                byte[] bitmapByte = byteArrayOutputStream.toByteArray();
                Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapByte,0, bitmapByte.length);
                mUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onSuccess(bitmap);
                    }
                });
            }
        });
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

    @Override
    public void onClick(View v) {
        Toast.makeText(this, mLoadImageView.toString(), Toast.LENGTH_LONG).show();
    }
}