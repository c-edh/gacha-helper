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
import java.util.HashMap;
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
        createUserBuild("Flower of Life");
    }

    public void getCircletOfLogos(View view){
        createUserBuild("Circlet of Logos");
    }

    public void getGobletOfEonothem(View view){
        createUserBuild("Goblet of Eonothem");
    }

    public void getPlumeOfDeath(View view){
        createUserBuild("Plume of Death");
    }

    public void getSandsOfEon(View view){
        createUserBuild("Sands of Eon");
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
       buildText.setText(StatString);
       //statChance(data); //<--------------------------Uncomment this line too
    }

    private void createUserBuild(String Artifact){

        getBuildInfo(Artifact, "Main Stat", new OnBuildInfoRecievedListener(){
            @Override
            public void onBuildInfoRecieved(String results) {
                String mainstat = results;
                System.out.println("This is main stat" + mainstat);

                //Flower of Life doesnt have a substat, so it skips this.
                if(Artifact!= "Flower of Life"){
                    getBuildInfo(Artifact, "Sub Stat", new OnBuildInfoRecievedListener() {
                        @Override
                        public void onBuildInfoRecieved(String results) {
                            String substat = results;
                            System.out.println("This is the sub stat " + substat);
                        }
                    });
                }

            }
        });

    }

    private String getStatChance(Map<String, Double> statChance){


        Map<Double,ArrayList<String>> chances = new HashMap<>(); //  <--------- Uncomment this to the next line
        ArrayList<Double> chancesKeys = new ArrayList<Double>();

        for (String value : statChance.keySet()){
            //Map Key is a Double, to store all the stats with the same chances in one key.
            //Value is equal key, to retrieve the names of the artifact stats
            System.out.println(value);
            double d = 0.0;

            if (statChance.get(value) instanceof Number){ //Some values are long (thanks firebase), so inorder to convert it to a double you have to do this
                d = ((Number) statChance.get(value)).doubleValue(); //or else you get an error
            }else{
                System.out.println("this is wrong " + statChance.get(value));
            }

            double id = d;

            System.out.println(id);



            if(chances.get(id) == null){
                chances.put(id,new ArrayList<String>());
            }

            chances.get(id).add(value); //
        }

        double randomizer = Math.random() * 100;
        Random random = new Random();

        String statPicked = null;

        for(Double key : chances.keySet()){

            ArrayList<String> stats = (ArrayList<String>) chances.get(key).clone();
            Double overallPercent = 0.0;

            for(String samePercentStats : stats){
                overallPercent+= key;
            }

            if(randomizer <= overallPercent){
                statPicked = stats.get(random.nextInt(stats.size()));
                break;
            }
        }

//        System.out.println("the random stat that was picked is " +statPicked);
        return statPicked;

   }

   interface OnBuildInfoRecievedListener{
        void onBuildInfoRecieved(String results);
   }


    public void getBuildInfo(String Artifact, String Stat, OnBuildInfoRecievedListener listener){

        DocumentReference docRef = db.collection("Artifacts").document(Artifact);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                      //  Log.d(TAG, "DocumentSnapshot data: " + document.get("Main Stat"));

                        Map<String, Double> statData = (Map<String, Double>) document.get(Stat); //Gets all main Stat data from firebase

                        listener.onBuildInfoRecieved(getStatChance(statData));


                    } else {
                        Log.d(TAG, "No such document");
                        return;
                    }


                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
}