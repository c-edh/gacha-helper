package edu.csustan.cs3810.gacha_helper;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.model.Values;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class test_level extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_level);
    }

    public void getFlowerInfo(View view){
        getArtifactInfo("Flower of Life");}

        public void getCircletInfo(View view){
        getArtifactInfo("Circlet of Logos"); }

     public void getGobletInfo(View view){
        getArtifactInfo("Goblet of Eonothem");
     }

     public void getPlumeInfo(View view){
        getArtifactInfo("Plume of Death");
     }

     public void getSandsInfo(View view){
        getArtifactInfo("Sands of Eon");
     }

     public void pullUserBuild(View view){

     }

    private void getStatsData(Map<String, Long> data){

        //TODO Loop through the map, get all the keys and values of it.
        //System.out.println(data);
        TextView displaystats = (TextView) findViewById(R.id.displaystats);
        String StatString = "";

        for ( String key : data.keySet() ) {
            System.out.println(key); //prints the stat
            StatString += (key + ": " + data.get(key) + "\n");
        }
        displaystats.setText(StatString);
        //statChance(data); //<--------------------------Uncomment this line too
    }

private void getArtifactInfo(String Artifact){
    DocumentReference docRef = db.collection("Artifact Values").document(Artifact);
    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Map<String, Long> mainstatData = (Map<String, Long>) document.get("Main Stat");
                    getStatsData(mainstatData);
                } else {
                    Log.d(TAG, "No Such Document");
                }
            } else {
                Log.d(TAG, "Get Failed with ", task.getException());
            }
        }
    });
}}