package com.example.ivan.ogu.cabinet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ivan.ogu.R;

import java.util.ArrayList;

public class Alternative extends Fragment {


    ListView listView;
    QueryLogin queryLogin;
    public static String selectedItem = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Список направлений");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.list_view, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.listView1);
        queryLogin = new QueryLogin();

        ArrayList<String> nameProfile = new ArrayList<>();

        for (int i = 0; i < queryLogin.profilName.size(); i++) {
            nameProfile.add(queryLogin.profilName.get(i) + "\n" + queryLogin.profilCode.get(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item, R.id.pro_item, nameProfile);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                selectedItem =  queryLogin.profilCode.get(position);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, new QuerySelection());
                ft.commit();
            }
        });
    }


    public String selectProf(){
        return selectedItem;
    }



}
