package com.ardunio.rakshakriti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class Accounts extends AppCompatActivity {
    TextView devicesetting,usersetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);


        devicesetting=findViewById(R.id.devicesetting);
        usersetting=findViewById(R.id.usersetting);



        devicesetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Accounts.this,DeviceSetting.class);
                startActivity(intent);
            }
        });
        usersetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Accounts.this,usersetting.class);
                startActivity(intent1);
            }
        });
    }
}
