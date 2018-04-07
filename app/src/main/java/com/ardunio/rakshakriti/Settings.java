package com.ardunio.rakshakriti;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Settings extends Fragment {
    TextView accountsTw,Range, Duration, Aboutus;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_settings,null);

        accountsTw=view.findViewById(R.id.Accounts);
        Range=view.findViewById(R.id.Range);
        Duration=view.findViewById(R.id.Duration);
        Aboutus=view.findViewById(R.id.Aboutus);
        accountsTw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Accounts.class);
                startActivity(intent);
            }
        });
        Range.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Range.class);
                startActivity(intent);
            }
        });
        Duration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Duration.class);
                startActivity(intent);
            }
        });
        Aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),About_Us.class);
                startActivity(intent);
            }
        });
        return view;

    }
}
