package com.coinbkt.ngangklung;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

public class AboutActivity extends AppCompatActivity {

    WebView wv;
    String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_about);
        WebView myWebView = new WebView(AboutActivity.this);
        setContentView(myWebView);
        Intent i = getIntent();
        link = i.getStringExtra("link");
        myWebView.loadUrl(link);

    }

}
