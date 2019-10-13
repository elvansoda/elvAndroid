package com.example.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class CCTV extends AppCompatActivity {
    public Button button;
    public Button warning;
    private WebView mWebView;
    private String myUrl = "http://192.168.11.34:8080/?action=stream";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cctv);
        mWebView = findViewById(R.id.CCTV);
        button = (Button) findViewById(R.id.mainbutton4);
        warning=findViewById(R.id.warning);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(myUrl); // 접속 URL
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClientClass());
        mWebView.getSettings().setBuiltInZoomControls(true); //zoom허용
        mWebView.getSettings().setSupportZoom(true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {   //메인으로가기
                Intent intent = new Intent(CCTV.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent); //액티비티 이동
            }

        });
        warning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {   //메인으로가기
                Intent intent = new Intent(CCTV.this, Warning.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent); //액티비티 이동
            }

        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("check URL", url);
            view.loadUrl(url);
            return true;


        }
    }
}

