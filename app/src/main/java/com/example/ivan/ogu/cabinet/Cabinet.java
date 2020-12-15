package com.example.ivan.ogu.cabinet;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.ivan.ogu.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Cabinet extends Fragment {

    Button btnRating;
    Button btnMyAlter;
    Button  btnTest;
    Button  btnIndivid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Личный кабинет");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fr_cabinet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnRating = view.findViewById(R.id.btnRating);
        btnMyAlter = view.findViewById(R.id.alternativ);
        btnTest = view.findViewById(R.id.btnTest);
        btnIndivid = view.findViewById(R.id.btnTest);

        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, new ListProfil());
                ft.commit();
            }

        });

        btnMyAlter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, new ListProfil());
                ft.commit();
            }
        });
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_main, new Test());
                ft.commit();
            }
        });


    }
}
