package com.example.ivan.ogu.cabinet;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class QueryLogin extends Thread {

    public static ArrayList<String> profilName = new ArrayList<>();
    public static ArrayList<String> profilCode = new ArrayList<>();

    //Переменные для сохранение данных абитуриента
    public static String receipt;
    public static String first_name;
    public static String last_name;

    String phone = null;
    Boolean state = false;

    InputStream inputStream = null;
    String result = null;
    String line = null;
    JSONArray jsonArray = null;

    public void run() {
        // создаем лист для отправки запросов
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        // один параметр, если нужно два и более просто добоовляем также
        nameValuePairs.add(new BasicNameValuePair("receipt", receipt));
        nameValuePairs.add(new BasicNameValuePair("phone", phone));

            // подключаемся к php запросу и отправляем в него расписку и номер телефона
            try {
                HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("http://edm-core.ru/ivan/dip/queryLogin.php");
                        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
                        HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();
                inputStream = entity.getContent();
                Log.e("pass 1", "connection success ");
            } catch (Exception e) {
                Log.e("Fail 1", e.toString());
            }

            // получаем ответ от php запроса в формате json
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();
                        while ((line = reader.readLine()) != null){
                            sb.append(line + "\n");
                        }
                    inputStream.close();
                result = sb.toString();
                Log.e("pass 2", "connection success" + result);
            } catch (Exception e){
                Log.e("Fail 2", e.toString());
            }

            // обрабатываем полученный json
            try {
JSONObject jsonObj = new JSONObject(result);
    // Получаем массив из нашего объекта
    jsonArray = jsonObj.getJSONArray("abiturts");
Log.e("- pass 3", jsonArray.toString());
// чистим наш аррей лист для того что бы заполнить
profilName.clear();
profilCode.clear();
for (int i = 0; i < jsonArray.length(); i++) {
    JSONObject json_data = jsonArray.getJSONObject(i);
    //Создаем некоторые переменные для дальнейшей работы
        profilName.add(json_data.getString("name"));
        profilCode.add(json_data.getString("code"));
            first_name = json_data.getString("first_name");
            last_name = json_data.getString("last_name");
        state = true;
    Log.e("- pass 3", "true" +last_name);
                }

            } catch (Exception e) {
                state = false;
                Log.e("Fail 3", e.toString());
            }

    }

    // принемаем id при запуске потока
    public void start(String idr, String idp){
        this.receipt = idr;
        this.phone = idp;
        this.start();
    }

    // Расписка студента
    public String resReceipt (){
        return  receipt;
    }

    //Код направления
    public String resFirstName (){
        return  first_name;
    }

    //Код направления
    public String resLasrName (){
        return  last_name;
    }

}
