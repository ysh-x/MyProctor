package com.ysh.myproctor;

import android.util.Log;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.Key;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RTDBRead {
    final FirebaseDatabase database = FirebaseDatabase.getInstance("https://myproctor-931e3-default-rtdb.asia-southeast1.firebasedatabase.app");
    Map<String,Object> data;

    public Map<String,Object> getUsersData(String id) {

        DatabaseReference ref = database.getReference();
        ref.child("Users").child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    data = (Map<String,Object>) task.getResult().getValue();
                }
            }
        });
        return data;
    }

    boolean addAMessage(String proctor,String userId, String msg) {
        String path = "/Proctors/"+proctor;
        DatabaseReference ref = database.getReference();
        ref.child(path).child(userId).setValue(msg);
        return true;
    }

    public Map<String,Object> getProcteesData(String proctor) {

        DatabaseReference ref = database.getReference();
        ref.child("Proctors").child(proctor).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    data = (Map<String,Object>) task.getResult().getValue();
                }
            }
        });
        return data;
    }
}
