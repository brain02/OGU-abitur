package com.example.ivan.ogu.inform;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ivan.ogu.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Inform_spec extends Fragment {

    String htmlCode;
    WebView myWeb;
    ProgressBar progressBar;
    // благодоря этому классу мы будет разбирать данные на куски
    public Elements content;
    public Elements fragcontent;

    // то в чем будем хранить данные пока не передадим адаптеру
    public ArrayList<String> titleList = new ArrayList<String>();
    // Listview Adapter для вывода данных
    private ArrayAdapter<String> adapter;
    // List view
    private ListView lv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            getActivity().setTitle("Подробно о специальностях");
            Toast.makeText(getActivity(),"Подождите... Идёт загрузка!",Toast.LENGTH_LONG).show();
            View view = inflater.inflate(R.layout.my_browser, container, false);

        return view;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // определение данных
        progressBar = view.findViewById(R.id.progressBar);
        myWeb     = view.findViewById(R.id.my_browser);
        //Включить JavaScript
        myWeb.getSettings().setJavaScriptEnabled(true);
        // Для работы ссылок на сайте
        Inform_spec.myWebclient webViewClient = new Inform_spec.myWebclient();
        myWeb.setWebViewClient(webViewClient);
        new  NewThread().execute();
        //Загрузка сайта
    }

    @SuppressLint("StaticFieldLeak")
    public class NewThread extends AsyncTask<String, Void, String> {
        // Метод выполняющий запрос в фоне, в версиях выше 4 андроида, запросы в главном потоке выполнять
        // нельзя, поэтому все что вам нужно выполнять - выносите в отдельный тред
        @Override
        protected String doInBackground(String... arg) {
            // класс который захватывает страницу
            Document doc;
            try {
                // определяем откуда будем воровать данные
                doc = Jsoup.connect("http://oreluniver.ru/pk/oop/bak").get();
                // задаем с какого места, я выбрал заголовке статей
                content = doc.select(".page-content");
                htmlCode = "<html></head>"+content.html()+"</body></html>";
            } catch (IOException e) {
                e.printStackTrace();
            }
            // ничего не возвращаем потому что я так захотел)
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            // после запроса обновляем листвью
            myWeb.loadUrl("http://oreluniver.ru/pk/oop/bak");
            //myWeb.loadData(htmlCode,"text/html; charset=utf-8", "UTF-8");
        }
    }


    public class myWebclient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
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



