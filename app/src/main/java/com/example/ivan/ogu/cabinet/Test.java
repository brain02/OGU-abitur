package com.example.ivan.ogu.cabinet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.ivan.ogu.R;
import com.example.ivan.ogu.Univer;

public class Test extends Fragment {

    View view;
    WebView webView;
    ProgressBar progressBar;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Личный кабинет");
        view = inflater.inflate(R.layout.my_browser, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //инициализация объектов
        progressBar = view.findViewById(R.id.progressBar);
        webView     = view.findViewById(R.id.my_browser);

        //Загрузка сайта
        progressBar.setVisibility(View.GONE);
        String htmlCode = "<html></head><center>\n" +
                "<h4>Результаты вступительных испытаний</h4>\n" +
                "\n" +
                "Русский язык : 84 <br>\n" +
                "Математика : 60 <br>\n" +
                "Информатика и ИКТ : 78 <br>\n" +
                "</center></html>";
        webView.loadData(htmlCode,"text/html; charset=utf-8", "UTF-8");;
        //webView.loadUrl("http://oreluniver.ru");

    }
}