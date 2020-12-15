package com.example.ivan.ogu.forum;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ivan.ogu.R;

import java.util.Arrays;
import java.util.List;

public class Forum extends Fragment {


    public Forum() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Задать вопрос");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fr_forum, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnArchive = view.findViewById(R.id.btnArchive);
        Button btnSend = view.findViewById(R.id.btnSend);
        final EditText editEmail = view.findViewById(R.id.editEmail);

        editEmail.setText("ivko_02@mail.ru");

        //Архив вопросов
        btnArchive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment question_archive = new Question_archive();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_main,question_archive);
                ft.commit();
            }
        });
        //Отправить
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"Вы ещё не зарегистрированы!",Toast.LENGTH_LONG).show();
                String emailSubject ="Абитуриент: " + ((TextView) view.findViewById(R.id.editSubject)).getText().toString();
                String emailBody = "<h1>" + editEmail.getText().toString() +"</h1>" + ((TextView) view.findViewById(R.id.editBody)).getText().toString();
                new SendMailTask(getActivity()).execute(emailSubject, emailBody);
            }
        });
    }
}
