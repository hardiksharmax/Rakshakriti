package com.ardunio.rakshakriti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class usersetting extends AppCompatActivity {
    Button deactivate;
    TextView username,phonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersetting);

        deactivate=findViewById(R.id.deactivate);
        username=findViewById(R.id.username);
        phonenumber=findViewById(R.id.PhoneNumber);


        deactivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.setVisibility(View.GONE);
                phonenumber.setVisibility(View.GONE);
            }
        });
    }
}
