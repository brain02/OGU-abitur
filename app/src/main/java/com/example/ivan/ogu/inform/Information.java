package com.example.ivan.ogu.inform;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivan.ogu.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Information extends Fragment {
    ///////////////////
    TextView textView;
    Intent intent;

    private String[] mGroupsArray = new String[] { "Правила приёма", "Индивидуальные достижения", "Направления подготовки и специальности" };//Оглавление

    private String[] mPriemArray = new String[] { "бакалавриат, специалитет, магистратура ", "aспирантура","подробно о правилах приёма 2019" }; //Правила приёма
    private String[] mIndividArray = new String[] { "бакалавриат, специалитет","магистратура", "aспирантура" };          //Индивидуальные достижения
    private String[] mNaprArray = new String[] { "специальности, бюджетные места, вступительные","подробно о специальностях"};//Направления подготовки и специальности

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Initial
        getActivity().setTitle("Полезная информация");
        View view = inflater.inflate(R.layout.fr_information, container, false);

        // find object
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Map<String,String> map;

        // коллекция для групп
        ArrayList<Map<String, String>> groupDataList = new ArrayList<>();
        // заполняем коллекцию групп из массива с названиями групп

        for (String group : mGroupsArray) {
            // заполняем список атрибутов для каждой группы
            map = new HashMap<>();
            map.put("groupName", group); // Название пунктов
            groupDataList.add(map);
        }

        // список атрибутов групп для чтения
        String groupFrom[] = new String[] { "groupName" };
        // список ID view-элементов, в которые будет помещены атрибуты групп
        int groupTo[] = new int[] { R.id.textGroup };

        // создаем общую коллекцию для коллекций элементов
        ArrayList<ArrayList<Map<String, String>>> сhildDataList = new ArrayList<>();

        // создаем коллекцию элементов для ПЕРВОЙ группы
        ArrayList<Map<String, String>> сhildDataItemList = new ArrayList<>();
        // заполняем список атрибутов для каждого элемента
        for (String month : mPriemArray) {
            map = new HashMap<>();
            map.put("informName", month); // название месяца
            сhildDataItemList.add(map);
        }

        // добавляем в коллекцию коллекций
        сhildDataList.add(сhildDataItemList);

        // создаем коллекцию элементов для ВТОРОЙ группы
        сhildDataItemList = new ArrayList<>();
        for (String month : mIndividArray) {
            map = new HashMap<>();
            map.put("informName", month);
            сhildDataItemList.add(map);
        }
        сhildDataList.add(сhildDataItemList);

        // создаем коллекцию элементов для ТРЕТЬЕЙ группы
        сhildDataItemList = new ArrayList<>();
        for (String month : mNaprArray) {
            map = new HashMap<>();
            map.put("informName", month);
            сhildDataItemList.add(map);
        }
        сhildDataList.add(сhildDataItemList);

        // список атрибутов элементов для чтения
        String childFrom[] = new String[] { "informName" };
        // список ID view-элементов, в которые будет помещены атрибуты
        // элементов
        int childTo[] = new int[] {R.id.textChild };

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                getContext(), groupDataList,R.layout.inf_group_view, groupFrom,
                groupTo, сhildDataList, R.layout.inf_child_view,
                childFrom, childTo);

        ExpandableListView expListView = (ExpandableListView) view.findViewById(R.id.expListView);
        expListView.setAdapter(adapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                switch (groupPosition) {             //Список
                    case 0:                          //Правила приёма
                        switch (childPosition) {     //список  под группы
                            case 0:                  //Бакалавр, магистратура, специалитетб
                                Toast.makeText(getActivity(),"Загрузка",Toast.LENGTH_SHORT).show();
                                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://oreluniver.ru/public/file/pk/rules/rules_2019.pdf"));
                                startActivity(intent);
                                break;
                            case 1:                  //Аспирантура
                                Toast.makeText(getActivity(),"Загрузка",Toast.LENGTH_SHORT).show();
                                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://oreluniver.ru/public/file/pk/rules/rules_asp_2019.pdf"));
                                startActivity(intent);
                                break;
                            case 2:                  //Подробно о правилах
                                Fragment fragment = new Inform_pravila();
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.replace(R.id.content_main,fragment);
                                ft.commit();
                                break;
                        }
                        break;
                    case 1:                          //Индивидуальные достижения
                        switch (childPosition) {     //список  подгруппы
                            case 0:                  //Бакалавр, специалитет
                                Toast.makeText(getActivity(),"Загрузка",Toast.LENGTH_SHORT).show();
                                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://oreluniver.ru/public/file/pk/rules/ind_dost_2019.pdf"));
                                startActivity(intent);
                                break;
                            case 1:                  //Магистратура
                                Toast.makeText(getActivity(),"Загрузка",Toast.LENGTH_SHORT).show();
                                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://oreluniver.ru/public/file/pk/rules/ind_dost_asp_2019.pdf"));
                                startActivity(intent);
                                break;
                            case 2:                  //Аспирантура
                                Toast.makeText(getActivity(),"Загрузка",Toast.LENGTH_SHORT).show();
                                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://oreluniver.ru/public/file/page/branch/livny/entrant/ind_dost_mag_2019.pdf"));
                                startActivity(intent);
                                break;
                        }
                        break;
                    case 2:                          //Направление
                        switch (childPosition) {     //список  под группы
                            case 0:                  //Бакалавр, магистратура, специалитетб
                                Toast.makeText(getActivity(),"Загрузка",Toast.LENGTH_SHORT).show();
                                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://oreluniver.ru/public/file/pk/oop/magazine2018.pdf"));
                                startActivity(intent);
                                break;
                            case 1:                  //Подробно о специальностях
                                Fragment fragment = new Inform_spec();
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.replace(R.id.content_main,fragment);
                                ft.commit();
                                break;
                        }
                        break;

                }

                return false;
            }
        });

    }
}
