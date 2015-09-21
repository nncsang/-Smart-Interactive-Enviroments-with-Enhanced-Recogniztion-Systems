package com.example.restclient;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.webkit.WebView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fragment2 extends Fragment{
    private WebView mWebView = null;
    private Activity mActivity = null;
    private AsyncHttpClient mClient = null;
    private String mUrl = "https://www.google.com/search?q=";
    private String mQuery = "Eiffel";

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_2, container, false);
	}

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mWebView = (WebView) getView().findViewById(R.id.webView);
//        mClient = new AsyncHttpClient();
//        mClient.get(mUrl + mQuery, new AsyncHttpResponseHandler(){
//            @Override
//            public void onSuccess(String response) {
//                mWebView.loadUrl(mUrl + mQuery);
//            }
//        });
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }

}
