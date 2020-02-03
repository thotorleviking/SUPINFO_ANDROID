package com.example.project3andm;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class APICallThread implements Runnable {
    String name;
    Thread t;

    APICallThread(String thread) {
        name = thread;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
        t.start();
    }

    public void run() {
            try {
                URL url = new URL("http://barcelonaapi.marcpous.com/bus/nearstation/latlon/%2041.3985182/2.1917991/1.json");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP Error code : "
                            + conn.getResponseCode());
                }
                Log.d("OUI", "run: COUCOU");
                InputStreamReader in = new InputStreamReader(conn.getInputStream());

                BufferedReader br = new BufferedReader(in);
                String output;
                Log.d("Debug","Coucou");
                while ((output = br.readLine()) != null) {
                    Log.d("Debug",output);
                }
                conn.disconnect();
            } catch (Exception e) {
                System.out.println("Exception in NetClientGet:- " + e);
            }
        System.out.println(name + " exiting.");
    }
}

