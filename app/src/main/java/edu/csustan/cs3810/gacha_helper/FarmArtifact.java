package edu.csustan.cs3810.gacha_helper;

// written by: Corey Edh
// tested by: Corey Edh
// debugged by: Corey Edh

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FarmArtifact extends AppCompatActivity {

    private ArrayList<String> artifactStatList;
    private RecyclerView recyclerView;
    private recyclerAdapter.RecyclerViewClickListener listener;

    private CreateBuild artifactInfo = new CreateBuild();

    private Map<String, Double> artifact;

    private int count = 1;

    private String statSelect = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_artifact);
        recyclerView = findViewById(R.id.recyclerView);
        artifactStatList = new ArrayList<>();
    }
    public void getFlowerStats(View view){
        artifactStatList.clear();
        StatList("Flower of Life");
    }

    //Circlet Button
    public void getCircletOfLogosStats(View view){
        artifactStatList.clear();
        StatList("Circlet of Logos");

    }
    //Goblet Button
    public void getGobletOfEonothemStats(View view){
        artifactStatList.clear();
        StatList("Goblet of Eonothem");
    }


    //Feather
    public void getPlumeOfDeathStats(View view){
        artifactStatList.clear();
        StatList("Plume of Death");
    }
    //Sands of Eon Button
    public void getSandsOfEonStats(View view){
        artifactStatList.clear();
        StatList("Sands of Eon");
    }

    private void StatList(String Artifact){
        getArtifactInfoFarm(Artifact, new OnArtifactInfoRecievedListener(){
            @Override
            public void onArtifactInfoRecieved(Map<String, Double> results) {
                artifact = results;
                for(String stat : results.keySet()){
                    System.out.println(stat);
                    setStat(stat);
                    //artifactStatList.add(stat);
                }
            }
        });

    }



    public void FarmButton(View view){
        Farm();
    }

    private void Farm(){
        TextView resin = (TextView) findViewById(R.id.resinCountTextView);
        if (statSelect.equals("")){
            resin.setText("Click a stat");
            return;
        }
        String FarmStat = statSelect.toString();
        String stat = artifactInfo.getStatChance(artifact);
       // System.out.println(stat);
        if(stat.equals(FarmStat)){
            resin.setText("Resin Needed: \n" + count);
            count = 1;
        }else{
            count+=1;
            Farm();
        }
    }




    //Adds the artifacts stats to the list
    private void setStat(String stats) {
        //add stats to artifactStat list here

        //artifactStatList.add(stat) <- example

            artifactStatList.add(stats);
//        artifactStatList.add("test2");
//        artifactStatList.add("test3");
        setAdapter();


    }

    private void setAdapter() {
        setOnClickListner();
        recyclerAdapter adapter = new recyclerAdapter(artifactStatList, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);


    }

    private void setOnClickListner() {
        listener = new recyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                statSelect = artifactStatList.get(position).toString();
                System.out.println(statSelect);
            }
        };
    }

    interface OnArtifactInfoRecievedListener{
        void onArtifactInfoRecieved(Map<String, Double> results);
    }

    private void getArtifactInfoFarm(String Artifact, OnArtifactInfoRecievedListener listener){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Artifacts").document(Artifact);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        //Gets all main Stat data from firebase
                        Map<String, Double> statData = (Map<String, Double>) document.get("Main Stat");

                        //The listener
                        listener.onArtifactInfoRecieved(statData);


                    } else {
                        Log.d(TAG, "No such document");
                        listener.onArtifactInfoRecieved(null);
                    }


                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                    //artifactsInUserBuildTextView.setText("An error has occurred, check your connection");
                }
            }
        });
    }







}

