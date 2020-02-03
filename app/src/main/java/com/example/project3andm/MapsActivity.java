package com.example.project3andm;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.lang.reflect.Field;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private JSONArray array;
    private String plop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        try{
            for (int i=0; i < MainActivity.arrets.length(); i++)
            {
                JSONObject a = MainActivity.arrets.getJSONObject(i);
                String plop = new String();
                plop += "id : " + a.getString("street_name") + "\nBus : "+a.getString("buses")+"\ndistance : "+a.getString("distance");

                Log.d("lat", a.getString("lat"));
                Log.d("lon", a.getString("lon"));

                LatLng marq = new LatLng(Double.parseDouble(a.getString("lat")), Double.parseDouble(a.getString("lon")));
                mMap.addMarker(new MarkerOptions().position(marq).title(plop));

                /*Log.d(MainActivity.arrets(i).getString("buses"));*/
            }
        }catch(Exception e){
            Log.d("Debug", ("Exception in NetClientGet:- " + e));
        }

        LatLng Barcelone = new LatLng(41.3833, 2.1833);
        mMap.addMarker(new MarkerOptions().position(Barcelone).title("Barcelone"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Barcelone));
        mMap.setMinZoomPreference(13);
    }
}
