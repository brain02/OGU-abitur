package com.example.ivan.ogu;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Univer extends Fragment {

    View view;
    WebView webView;
    ProgressBar progressBar;

    public Univer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Университет");
        view = inflater.inflate(R.layout.my_browser, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //инициализация объектов
        progressBar = view.findViewById(R.id.progressBar);
        webView     = view.findViewById(R.id.my_browser);

        //Включить JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        // Для работы ссылок на сайте
        myWebclient webViewClient = new myWebclient();
        webView.setWebViewClient(webViewClient);
        //Загрузка сайта
        webView.loadUrl("http://oreluniver.ru");

    }

    public class myWebclient extends WebViewClient {

        //Открывакт ссылку внутри браузера
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            return false;
        }
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

    }

}
