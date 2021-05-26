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

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}