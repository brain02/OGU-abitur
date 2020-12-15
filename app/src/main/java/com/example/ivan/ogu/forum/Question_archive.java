package com.example.ivan.ogu.forum;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ivan.ogu.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Question_archive extends Fragment {



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
        getActivity().setTitle("Архив вопросов");
        Toast.makeText(getActivity(),"Подождите, идёт загрузка...",Toast.LENGTH_LONG).show();
        View view = inflater.inflate(R.layout.list_view2, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // определение данных
        lv = (ListView) view.findViewById(R.id.listView2);
        // запрос к нашему отдельному поток на выборку данных
        new  NewThread().execute();
        // Добавляем данные для ListView
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item2, R.id.pro_item2, titleList);

    }

    public class NewThread extends AsyncTask<String, Void, String> {

        // Метод выполняющий запрос в фоне, в версиях выше 4 андроида, запросы в главном потоке выполнять
        // нельзя, поэтому все что вам нужно выполнять - выносим в отдельный тред
        @Override
        protected String doInBackground(String... arg) {
            // класс который захватывает страницу
            Document doc;
            try {
// определяем откуда будем воровать данные
doc = Jsoup.connect("http://oreluniver.ru/pk/Question_archive").get();
// задаем с какого места, я выбрал заголовке статей
fragcontent = doc.select(".page-content");
content = fragcontent.select("p");
// чистим наш аррей лист для того что бы заполнить
titleList.clear();
// и в цикле захватываем все данные какие есть на странице
for (Element contents : content) {
    // записываем в аррей лист
    titleList.add(contents.text());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // ничего не возвращаем потому что я так захотел)
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            // после запроса обновляем листвью
            lv.setAdapter(adapter);
        }
    }



}
