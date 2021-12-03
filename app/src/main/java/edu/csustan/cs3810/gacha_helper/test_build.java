package edu.csustan.cs3810.gacha_helper;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.errorprone.annotations.Var;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class test_build extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_build);

    }

    public void getFlowerBuild(View view){
        getBuildInfo("Flower of Life");
    }

    public void getCircletOfLogos(View view){
        getBuildInfo("Circlet of Logos");
    }

    public void getGobletOfEonothem(View view){
        getBuildInfo("Goblet of Eonothem");
    }

    public void getPlumeOfDeath(View view){
        getBuildInfo("Plume of Death");
    }

    public void getSandsOfEon(View view){
        getBuildInfo("Sands of Eon");
    }

    private void getStatsData(Map<String, Long> data){

        //TODO Loop through the map, get all the keys and values of it.
        //System.out.println(data);
        TextView buildText = (TextView) findViewById(R.id.buildText);
        String StatString = "";

        for ( String key : data.keySet() ) {
            System.out.println(key); //prints the stat
            StatString += (key + ": " + data.get(key) + "\n");
        }
       // buildText.setText(StatString);
       // statChance(data); <--------------------------Uncomment this line too
    }

 private void statChance(Map<String, Long> statChance){
//        Map<Long,ArrayList<String>> chances = null;   <--------- Uncomment this to the next line
//        ArrayList<Long> chancesKeys = null;
//
//        for (String value : statChance.keySet()){
//            //Map Key is a Double, to store all the stats with the same chances in one key.
//            //Value is equal key, to retrieve the names of the artifact stats
//
//            Long id = statChance.get(value);
//
//            System.out.println(id);
//
//            chances.put(id, null);
//
//            if(chances.get(id) == null ){
//                chances.put(id,new ArrayList<String>());
//            }
//
//            chances.get(id).add(value); //
//        }
//
//        double randomizer = Math.random() * 100;
//        Random random = new Random();
//
//        String statPicked = null;                     <--------- Uncomment above this

//        for(Double key : chances.keySet()){
//
//            ArrayList<String> stats = (ArrayList<String>) chances.get(key).clone();
//            Double overallPercent = null;
//
//            for(String samePercentStats : stats){
//                overallPercent+= key;
//            }
//
//            if(randomizer < overallPercent){
//                statPicked = stats.get(random.nextInt(stats.size()));
//            }
//        }

        //System.out.println("the random stat that was picked is " +statPicked);


   } 



    private void getBuildInfo(String Artifact){

        DocumentReference docRef = db.collection("Artifacts").document(Artifact);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                      //  Log.d(TAG, "DocumentSnapshot data: " + document.get("Main Stat"));

                        Map<String, Long> mainstatData = (Map<String, Long>) document.get("Main Stat"); //Gets all main Stat data from firebase
                        getStatsData(mainstatData);

//                        if(Artifact!= "Flower of Life"){
//                            Map<String, Long> substatData = (Map<String, Long>) document.get("Sub Stat");
//                            getStatsData(substatData);
//                        }


                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
}