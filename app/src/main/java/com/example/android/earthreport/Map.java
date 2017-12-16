package com.example.android.earthreport;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by root on 11/18/17.
 */

public class Map extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getSupportActionBar().setHomeButtonEnabled(true);


        //Retrieve the content view theat renders the map
        setContentView(R.layout.map_activity);

        //Get the SupportMapFragment and request notification
        // when the map is ready to be used
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Bundle bundle = getIntent().getExtras();
        double longitude = bundle.getDouble("LONGITUDE");
        double latitude = bundle.getDouble("LATITUDE");
        String cityName = bundle.getString("CITY");

        // Add the marker city, country
        // and move the map camera to the same location
        LatLng location = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions()
                .position(location)
                .title(cityName)
                .snippet(" Magnitude: 6.5" +
                        " Time: 03:03 pm " +
                        " Depth: 19.79"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));

    }

}
