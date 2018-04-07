package com.ardunio.rakshakriti;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.IntentFilter;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.database.DatabaseReference;

import android.Manifest;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSendSMS;
    IntentFilter intentFilter;
    ImageView Liveloc, history, device, setting;
    private GoogleMap mMap;
    DecimalFormat f = new DecimalFormat("##.0000");
    static GoogleApiClient mGoogleApiClient;
    GoogleApiAvailability api;
    GPSTracker tracker;

    DatabaseReference rootRef;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    private static final String phoneNo = "+919915245772";
    private static final String message = "essdfsdf 73.9883833 30.232323";


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Liveloc = findViewById(R.id.location1);
        history = findViewById(R.id.history);
        device = findViewById(R.id.device);
        setting = findViewById(R.id.setting);

        history.setOnClickListener(this);
        device.setOnClickListener(this);
        setting.setOnClickListener(this);
        Liveloc.setOnClickListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayour, new LogoScreen()).commit();

    }

    @Override
    protected void onResume() {
        //---register the receiver--

//        registerReceiver(intentReceiver, intentFilter);
        super.onResume();
        /*rootRef.child("new").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("MainActivity.onDataChange " + dataSnapshot);
                LocationItem item = dataSnapshot.getValue(LocationItem.class);
                Double lat=0.0,lon=0.0;
                try {
                    lat = Double.valueOf(item.getLat());
                    lon = Double.valueOf(item.getLon());
                }catch (Exception e){

                }
                if (markerOptions==null)
                    markerOptions = new MarkerOptions();
                markerOptions.position(new LatLng(lat,lon));
                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lon), 18.0f));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        /*rootRef.child("new").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                System.out.println("MainActivity.onChildAdded " + dataSnapshot + ", " + s);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }

    @Override
    protected void onPause() {
        //---unregister the receiver--
//        unregisterReceiver(intentReceiver);
        super.onPause();
    }

    //---sends an SMS message to another device--
    protected void sendSMS(String lat, String lng) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        } else {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, "lat = " + lat + " lng = " + lng, null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent", Toast.LENGTH_LONG).show();
        }
    }

    /*@Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
                break;
            case LOCATION_PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    tracker = new GPSTracker(MainActivity.this);
                    if (tracker.canGetLocation()) {
                        Location location = tracker.getLocation();
                        if (location != null)
                            sendSMS(f.format(location.getLatitude()), f.format(location.getLongitude()));
                    }
                }
                break;
        }
    }*/

    //**************************************
    /*@Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1099);
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
    }

    public void requestLocationPermission() {

        if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_CODE);
        } else {
            tracker = new GPSTracker(MainActivity.this);
            if (tracker.canGetLocation()) {
                Location location = tracker.getLocation();
                System.out.println("MainActivity.requestLocationPermission " + location);
                if (location != null) {
                    sendSMS(f.format(location.getLatitude()), f.format(location.getLongitude()));

                }
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

       //
    }*/


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayour, new Settings()).commit();
                break;
            case R.id.device:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayour, new Devicde()).commit();
                break;
            case R.id.location1:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayour, new LocationFragment()).commit();
//Intent intent = new Intent(this,MapActivity.class);
//                startActivity(intent);

                break;
            case R.id.history:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayour, new History()).commit();
                break;
        }
    }
}
