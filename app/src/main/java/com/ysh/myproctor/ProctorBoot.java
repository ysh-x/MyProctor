package com.ysh.myproctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ProctorBoot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proctor_boot);
        Intent i = getIntent();
        final FirebaseDatabase database = FirebaseDatabase.getInstance("https://myproctor-931e3-default-rtdb.asia-southeast1.firebasedatabase.app");

        TextView proctorName = findViewById(R.id.main_header_proctor);

        String name = i.getStringExtra("name");
        String id = i.getStringExtra("id");
        String proctorNme = i.getStringExtra("proctor");

        proctorName.setText("Welcome Back, "+i.getStringExtra("name"));
        final DatabaseReference ref = database.getReference();
        ref.child("Proctors").child(proctorNme).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                    System.out.println("Error");
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));System.out.println(task.getResult().getValue().toString());
                    Map<String,String> data = (Map<String,String>) task.getResult().getValue();

                    for(Map.Entry<String,String> k : data.entrySet()) {
                        System.out.println("\n\n"+k.getKey()+"-"+k.getValue());
                        TextView key = new TextView(getApplicationContext(), null, 0, R.style.key);
                        TextView value = new EditText(getApplicationContext(), null, 0, R.style.value);
                        LinearLayout ll = findViewById(R.id.msg_container);

                        key.setText(k.getKey());
                        value.setText(k.getValue());

                        ll.addView(key);
                        ll.addView(value);

                    }
                }
            }
        });

    }
}