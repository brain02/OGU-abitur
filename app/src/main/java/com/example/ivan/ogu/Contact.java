package com.example.ivan.ogu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class Contact extends Fragment implements OnMapReadyCallback {

    GoogleMap mMap;
    View mView;
    MapView mMapView;

    public Contact() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Контакты");
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fr_contact, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) mView.findViewById(R.id.map);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
//создаем координаты для позиции камеры  52.975738, 36.061820
        LatLng positions = new LatLng(52.975738, 36.061820);
//перемещаем камеру и оттдаляем ее
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(positions, 15));
//Добавляем маркер с местоположением на приемноё комиссии
        mMap.addMarker(new MarkerOptions().position(positions).title("Приёмная комиссия"));

    }
}
