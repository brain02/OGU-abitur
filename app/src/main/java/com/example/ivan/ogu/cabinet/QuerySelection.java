package com.example.ivan.ogu.cabinet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ivan.ogu.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class QuerySelection extends Fragment {

    Spinner spinner;
    WebView webView;
    String htmlCode;
    String numItem ;
    String codeProfile = new ListProfil().selectProf();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Рейтинг");
        return    inflater.inflate(R.layout.web_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinner = (Spinner) view.findViewById(R.id.spinner);
        webView = view.findViewById(R.id.web_view);

        String[] sort = {   "Сортировка по убыванию",
                            "Сортировка по оригиналу",
                            "Сортировка по согласию" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,sort);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                numItem = ""+position;
                if (numItem.equals("0")) {
                    sortR1 (numItem);
                }
                if (numItem.equals("1")) {
                    sortR2 (numItem);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

    }

    public void sortR1 (String numItems){

        QuerySelect querySelect1  = new QuerySelect();
        querySelect1.start(codeProfile,numItems);

        try {
            querySelect1.join();

        } catch (InterruptedException ie) {
            Log.e("pass 0 ", ie.getMessage());
        }
        htmlCode = "<html></head>"+querySelect1.htmlRes()+"</body></html>";
        webView.loadData(htmlCode,"text/html; charset=utf-8", "UTF-8");;
    }

    public void sortR2 (String numItems){

        Toast.makeText(getActivity(),numItem,Toast.LENGTH_SHORT).show();

    }

    public class QuerySelect extends Thread {

        public String result = null;
        public  String code = null;
        String highLight = new QueryLogin().resReceipt();
        String numItem;
        InputStream inputStream = null;
        String line = null;
        public void run() {
            // создаем лист для отправки запросов
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            // один параметр, если нужно два и более просто добоовляем также
            nameValuePairs.add(new BasicNameValuePair("receipt",highLight)); // Находим и выделяем в списке абитуриента
            nameValuePairs.add(new BasicNameValuePair("code", code));
            nameValuePairs.add(new BasicNameValuePair("numItem",numItem)); // Находим и выделяем в списке абитуриента


            // подключаемся к php запросу и отправляем в него расписку и номер телефона
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://edm-core.ru/ivan/dip/querySelection.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                inputStream = entity.getContent();
                Log.e("pass 11", "///");
            } catch (Exception e) {
                Log.e("Fail 11", e.toString());
            }

            // получаем ответ от php запроса
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null){
                    sb.append(line + "\n");
                }
                inputStream.close();
                result = sb.toString();
                Log.e("pass 22", "connection success" + result);
            } catch (Exception e){
                Log.e("Fail 22", e.toString());
            }

        }

        // принемаем id при запуске потока
        public void start(String coder, String num){
            this.code = coder;
            this.numItem = num;
            this.start();
        }

        public String htmlRes(){
            return result;
        }
    }






}
