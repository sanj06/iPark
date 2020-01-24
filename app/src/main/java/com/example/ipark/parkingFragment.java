package com.example.ipark;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

class parkingFragment extends androidx.fragment.app.Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        System.out.println("Parking class");
        View v = inflater.inflate(R.layout.fragment_findparking, container, false);

        Button newPage = (Button)v.findViewById(R.id.button2);
        newPage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ToMap.class);
                startActivity(intent);
            }
        });
        return v;


        //return inflater.inflate(R.layout.fragment_findparking, null);
    }



}
