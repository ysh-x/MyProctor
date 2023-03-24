package com.ysh.myproctor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Student extends AppCompatActivity {
    Intent i;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        i = getIntent();
        TextView proctorName = findViewById(R.id.proctor_name_holder);
        TextView studentName = findViewById(R.id.main_header_proctor);
        EditText messageContainer = findViewById(R.id.message_content);
        Button msgButton = findViewById(R.id.send_message_btn);

        String name = i.getStringExtra("name");
        String id = i.getStringExtra("id");
        String proctorNme = i.getStringExtra("proctor");

        proctorName.setText(i.getStringExtra("proctor"));
        studentName.setText("Welcome Back, "+i.getStringExtra("name"));

        msgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageContainer.getText().toString();
                RTDBRead fB = new RTDBRead();
                boolean add = fB.addAMessage(proctorNme,id,message);
                if(add == true) {
                    Toast.makeText(getApplicationContext(),"Message Delivered To Proctor", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}