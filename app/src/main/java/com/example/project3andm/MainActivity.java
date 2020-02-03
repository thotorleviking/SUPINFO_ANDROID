package com.example.project3andm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Station> mesStations;
    private MyStationsAdapter monAdapter;
    private JSONObject json;
    private JSONObject jsonData;
    private JSONArray array;
    private ProgressBar prog;
    static JSONArray arrets;

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button11 = (Button) findViewById(R.id.button3);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent1);
            }
        });



        mRecyclerView = (RecyclerView)findViewById(R.id.myRecyclerView);

        mesStations = new ArrayList<>();
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    InputStream is = new URL("http://barcelonaapi.marcpous.com/bus/nearstation/latlon/%2041.3985182/2.1917991/1.json").openStream();
                    try {
                        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                        String jsonText = readAll(rd);
                        json = new JSONObject(jsonText);
                    } finally {
                        is.close();
                    }
                     jsonData = json.getJSONObject("data");
                     Log.d("Debug", jsonData.toString());
                     array = jsonData.getJSONArray("nearstations");
                     arrets  = array;
                     for (int i=0; i < array.length(); i++)
                     {
                        JSONObject a = array.getJSONObject(i);
                        String plop = new String();
                        plop += "Arret : " + a.getString("street_name") + "\nBus : "+a.getString("buses");

                         mesStations.add(new Station(plop));
                     }
                } catch (Exception e) {
                    Log.d("Debug", ("Exception in NetClientGet:- " + e));
                }
            }
        });
        thread.start();
        while (thread.isAlive())
        {}
        /*mesStations.add(new Station("La super Station"));
        mesStations.add(new Station("La Station nul"));
        mesStations.add(new Station("La Station moyenne"));
        mesStations.add(new Station("La super Station"));
        mesStations.add(new Station("La Station nul"));
        mesStations.add(new Station("La Station moyenne"));
        mesStations.add(new Station("La super Station"));
        mesStations.add(new Station("La Station nul"));
        mesStations.add(new Station("La Station moyenne"));
        mesStations.add(new Station("La super Station"));
        mesStations.add(new Station("La Station nul"));
        mesStations.add(new Station("La Station moyenne"));
        mesStations.add(new Station("La super Station"));
        mesStations.add(new Station("La Station nul"));
        mesStations.add(new Station("La Station moyenne"));
        mesStations.add(new Station("La super Station"));
        mesStations.add(new Station("La Station nul"));
        mesStations.add(new Station("La Station moyenne"));
        mesStations.add(new Station("La super Station"));
        mesStations.add(new Station("La Station nul"));
        mesStations.add(new Station("La Station moyenne"));
        mesStations.add(new Station("La super Station"));
        mesStations.add(new Station("La Station nul"));
        mesStations.add(new Station("La Station moyenne"));*/

        monAdapter = new MyStationsAdapter(mesStations);

        prog = findViewById(R.id.progressBar2);
        prog.setVisibility(View.GONE);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(monAdapter);
    }
}
