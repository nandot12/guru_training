package com.nandohusni.training_guru.ui.chooselocation;

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
import com.nandohusni.training_guru.R;
import com.nandohusni.training_guru.base.BaseActivity;
import com.nandohusni.training_guru.ui.search.DetailRequestActivity;
import com.nandohusni.training_guru.utils.Constans;
import com.nandohusni.training_guru.utils.GPSTracker;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback {


    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 2;
    @BindView(R.id.searchLocations)
    TextView searchLocations;
    @BindView(R.id.mapBtnlanjut)
    Button mapBtnlanjut;
    private GoogleMap mMap;
    private Double lat;
    private Double lon;
    private String name;
    private String id;

    private String packet;
    private String jenjang;

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

        initIntent();
    }

    private void initIntent() {

        id = getIntent().getStringExtra(Constans.id);
        packet = getIntent().getStringExtra(Constans.packet);
        jenjang = getIntent().getStringExtra(Constans.jenjang);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        } else {

            gpss();

        }


    }

    private void gpss() {

        GPSTracker gps = new GPSTracker(c);

        if (gps.canGetLocation()) {

            lat = gps.getLatitude();
            lon = gps.getLongitude();

            setMarket();
            name = generateNameLocations(lat, lon);

            searchLocations.setText(name);


        }

    }

    private void setMarket() {

        LatLng latLng = new LatLng(lat, lon);

        mMap.addMarker(new MarkerOptions().position(latLng).title(name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));

    }

    private String generateNameLocations(Double lat, Double lon) {

        String name = "";

        Geocoder geo = new Geocoder(c);
        try {
            List<Address> list = geo.getFromLocation(lat, lon, 1);
            name = list.get(0).getAddressLine(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return name;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            gpss();
        }
    }

    @OnClick({R.id.searchLocations, R.id.mapBtnlanjut})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.searchLocations:

                searchLocationss();
                break;
            case R.id.mapBtnlanjut:

                next();
                break;
        }
    }

    private void next() {

        Intent move = new Intent(MapsActivity.this, DetailRequestActivity.class);
        move.putExtra(Constans.id, id);
        move.putExtra(Constans.jenjang, jenjang);
        move.putExtra(Constans.packet, packet);
        move.putExtra(Constans.lat, String.valueOf(lat));
        move.putExtra(Constans.lon, String.valueOf(lon));
        move.putExtra(Constans.nameLocation, name);
        startActivity(move);


    }

    private void searchLocationss() {

        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                lat = place.getLatLng().latitude;
                lon = place.getLatLng().longitude;

                setMarket();

                name = Objects.requireNonNull(place.getAddress()).toString();
                searchLocations.setText(name);
            }
        }
    }
}

