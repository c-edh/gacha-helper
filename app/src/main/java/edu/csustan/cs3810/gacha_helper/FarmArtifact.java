package edu.csustan.cs3810.gacha_helper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class FarmArtifact extends AppCompatActivity {

    private ArrayList<String> artifactStatList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_artifact);
        recyclerView = findViewById(R.id.recyclerView);
        artifactStatList = new ArrayList<>();
        setStat();
        setAdapter();

    }
    public void getFlowerStats(View view){
        createUserBuild("Flower of Life");
    }
    //Circlet Button
    public void getCircletOfLogosStats(View view){

    }
    //Goblet Button
    public void getGobletOfEonothemStats(View view){

    }
    //Feather
    public void getPlumeOfDeathStats(View view){

    }
    //Sands of Eon Button
    public void getSandsOfEonStats(View view){

    }





    private void setStat(ArrayList<String> Stats) {
        //add stats to artifactStat list here

        //artifactStatList.add(stat) <- example

        artifactStatList.add("test");
        artifactStatList.add("test2");
        artifactStatList.add("test3");


    }

    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(artifactStatList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);


    }


}

