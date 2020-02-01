package com.example.ipark;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.android.volley.VolleyLog.TAG;


class parkingFragment extends androidx.fragment.app.Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard



        System.out.println("Parking class");
        final View v = inflater.inflate(R.layout.fragment_findparking, container, false);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("sensor0/status");
        //DatabaseReference myRef2 = database.getReference("status");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                value = value == "0" ? "Free" : "Occupied";
                System.out.println("Value is: " + value);
                TextView tv = v.findViewById(R.id.slotnumber);
                tv.setText("Slot 1 : " + value);
//                int i = 1;
//                StringBuilder sb = new StringBuilder();
//                for (DataSnapshot child : dataSnapshot.getChildren())
//                {
//                    String value = child.getValue(String.class);
//                    value = value == "0" ? "Free" : "Occupied";
//                    System.out.println("Value is: " + value);
//                    sb.append("Slot" + i + value + '\n');
//                    i++;
//                }
//                TextView tv = v.findViewById(R.id.slotnumber);
//                tv.setText(sb.toString());

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {


                Log.w(TAG, "Failed to read value.", databaseError.toException());


            }

        });
//





        Button newPage = v.findViewById(R.id.button2);
        newPage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(getActivity(), ToMap.class);
                startActivity(intent); */





                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Select the area");

// add a list
                String[] animals = {"Library", "Auditorium", "International Hostel"};
                builder.setItems(animals, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                            {
                                Uri gmmIntentUri = Uri.parse("google.navigation:q=12.752062,80.195979");
                                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                                mapIntent.setPackage("com.google.android.apps.maps");
                                startActivity(mapIntent);
                                break;
                            }
                            case 1:
                            {
                                Uri gmmIntentUri = Uri.parse("google.navigation:q=12.752868, 80.199889");
                                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                                mapIntent.setPackage("com.google.android.apps.maps");
                                startActivity(mapIntent);
                                break;
                            }
                            case 2:
                            {
                                Uri gmmIntentUri = Uri.parse("google.navigation:q=12.749466, 80.198812");
                                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                                mapIntent.setPackage("com.google.android.apps.maps");
                                startActivity(mapIntent);
                                break;
                            }
                        }
                    }
                });
                builder.setNegativeButton("Cancel", null);
// create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();



            }
        });
        return v;


        //return inflater.inflate(R.layout.fragment_findparking, null);
    }



}
