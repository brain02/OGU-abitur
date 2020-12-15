package com.example.ivan.ogu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class Calendar extends Fragment {
    TextView dateText;
    CalendarView calendarView;

    public Calendar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Календарь событий");
        View v = inflater.inflate(R.layout.fr_calendar, container, false);

        //find Object
        dateText =  v.findViewById(R.id.dateText);
        calendarView = v.findViewById(R.id.calendarView);
        //Вывод текущей даты
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String date = sdf.format(new Date(calendarView.getDate()));

        dateText.setText(date+": ");

        //Обработчики
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange( CalendarView view, int year, int month, int dayOfMonth) {
                dateText.setText(dayOfMonth + "/" + month + "/" + year + ":");
           //     Toast.makeText(getActivity(),dayOfMonth + "/" + month + "/" + year + ":",Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

}
