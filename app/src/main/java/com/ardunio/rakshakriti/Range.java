package com.ardunio.rakshakriti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class Range extends AppCompatActivity {

Button save;
Spinner source_spinner,destination_spinner;
    String names[] = {"GZS CCET","Fauji Chowk Bathinda","Goniana","Model Town Phase ","St.Xavier's School ","st. joseph's convent sec school","Bathinda Junction railway station"};
    Double lat[] = {30.172887,30.210942,30.3191 ,30.194219,30.203149,30.200650,30.212796};
    Double lng[] = {74.924903,74.945322,74.9146,74.963253,74.959783,74.947874,74.933115};
    Double destinationLat,destinationLng,sourceLat,sourceLng;

@Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range);
        save=findViewById(R.id.Save);
       source_spinner=findViewById(R.id.source);
       destination_spinner=findViewById(R.id.Destination);
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,names);
    destination_spinner.setAdapter(adapter);
    source_spinner.setAdapter(adapter);
    source_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            sourceLat = lat[i];
            sourceLng = lng[i];
            System.out.println("Range.onItemSelected " + i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });

    destination_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            destinationLat = lat[i];
            destinationLng = lng[i];
            System.out.println("Range.onItemSelected " + i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });
    save.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Range.this,Directionmaps.class);
            intent.putExtra("srcLat",sourceLat);
            intent.putExtra("srcLon",sourceLng);
            intent.putExtra("destLat",destinationLat);
            intent.putExtra("destLon",destinationLng);
            startActivity(intent);
        }
    });

    }





}
