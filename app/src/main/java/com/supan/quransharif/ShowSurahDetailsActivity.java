package com.supan.quransharif;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.TextView;

public class ShowSurahDetailsActivity extends AppCompatActivity {
    WebView webview;
    ProgressDialog progressDialog;
    String surahName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_show_surah_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_back);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Intent surahNumIntent = getIntent();
        String surahNumber = surahNumIntent.getStringExtra("surah_number");
        surahName = surahNumIntent.getStringExtra("surah_name");
        Log.d("SurahName", surahName);
        mTitle.setText(surahName);

        init(surahNumber);


    }

    private void init(final String surahNum) {

        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setBuiltInZoomControls(true);
        String surahUrl = "file:///android_asset/" + surahNum + ".html";

        webview.loadUrl(surahUrl);
        webview.requestFocus();

        progressDialog = new ProgressDialog(ShowSurahDetailsActivity.this);
        progressDialog.setMessage("Loading\n" + surahName);
        progressDialog.setCancelable(false);
        progressDialog.show();

        webview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                try {
                    progressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {

            finish();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}