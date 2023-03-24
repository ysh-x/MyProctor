package com.ysh.myproctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class Boot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);
        Button clickButton = findViewById(R.id.login_button);
        RTDBRead read = new RTDBRead();

        EditText id = findViewById(R.id.id);
        EditText password = findViewById(R.id.password);



        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idText = id.getText().toString();
                String passwordText = password.getText().toString();

                if (idText.isEmpty() == true|| passwordText.isEmpty() == true) {
                    Toast.makeText(getApplicationContext(), "ID / Password is Empty", Toast.LENGTH_LONG).show();
                }
                else {
                     Map<String,Object> user = (Map<String, Object>) read.getUsersData(idText);

                      if(user == null) {
                          Toast.makeText(getApplicationContext(),"Check Your Internet.",Toast.LENGTH_LONG).show();
                    }
                    else {
                        if(user.get("Key").toString().equals(passwordText) && user.get("Role").toString().equals("Student")){

                            Intent i = new Intent(Boot.this, Student.class);
                            i.putExtra("name",user.get("Name").toString());
                            i.putExtra("proctor",user.get("Proctor").toString());
                            i.putExtra("id",idText);
                            startActivity(i);
                        }
                        else if(user.get("Key").toString().equals(passwordText) && user.get("Role").toString().equals("Proctor")) {
                                Intent i = new Intent(Boot.this, ProctorBoot.class);
                                i.putExtra("name", user.get("Name").toString());
                                i.putExtra("proctor", user.get("Proctor").toString());
                                i.putExtra("id", idText);
                                startActivity(i);
                            }
                        else {
                            Toast.makeText(getApplicationContext(),"Invalid User", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }
}