package com.nandohusni.sayaguru.ui.chooselocation;

import android.Manifest;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nandohusni.sayaguru.R;
import com.nandohusni.sayaguru.base.BaseActivity;
import com.nandohusni.sayaguru.ui.home.model.DataItem;
import com.nandohusni.sayaguru.ui.search.DetailRequestActivity;
import com.nandohusni.sayaguru.utils.Constans;
import com.nandohusni.sayaguru.utils.GPSTracker;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback {



    private GoogleMap mMap;

    private DataItem data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);


        }


    }

    private void initIntent() {

        data= (DataItem) getIntent().getSerializableExtra(Constans.id);

        setMarket(data);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        initIntent();

    }


    private void setMarket(DataItem data) {

        LatLng latLng = new LatLng(Double.parseDouble(data.getOrderLat()), Double.parseDouble(data.getOrderLon()));

        mMap.addMarker(new MarkerOptions().position(latLng).title(data.getOrderAlamat()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));

    }



}

