package edu.csustan.cs3810.gacha_helper;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class test_build extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ArrayList<UserBuild> userBuilds = new ArrayList<UserBuild>();

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

    public void createUserBuildButtonPressed(View view){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        System.out.println(userBuilds);

        //TODO Upload userBuilds to Firebase
        ArrayList<UserBuild> data = new ArrayList<UserBuild>();
        db.collection("Users Build").document(user.getUid()).set(data);

    }

    //Gets the Artifacts that the user's has selected and puts in in an Arraylist of usersBuild (this is going to be the Build of the user's)
    private void createUserBuild(String Artifact){

        //Getting the Artifact's Main Stats
        getArtifactInfo(Artifact, "Main Stat", new OnArtifactInfoRecievedListener(){
            @Override
            public void onArtifactInfoRecieved(String results) {

                //Results from this are the Main Stats of the Artifact
                String mainstat = results;
                System.out.println("This is main stat" + mainstat);
                //Flower of Life doesnt have a substat, so it skips this.

                if(Artifact!= "Flower of Life"){

                    //Getting the artifact's sub stats
                    getArtifactInfo(Artifact, "Sub Stat", new OnArtifactInfoRecievedListener() {
                        @Override
                        public void onArtifactInfoRecieved(String results) {
                            String substat = results;
                            System.out.println("This is the sub stat " + substat);

                            if(Artifact == "Flower of Life"){
                                substat = null;
                            }

                            UserBuild build = new UserBuild(Artifact, 1, mainstat, substat);
//
//                            build.ArtifactName = Artifact;
//                            build.ArtifactMainStat = mainstat;
//                            if (build.ArtifactName == "Flower of Life") {
//                                build.ArtifactSubStat = null;
//                            }else{
//                                build.ArtifactSubStat = substat;
//                            }

                            //Adds this to the user's build
                            userBuilds.add(build);
                        }
                    });
                }

            }
        });
    }



    //Get stat chances of getting pick, and picks the stat from that percentage
    private String getStatChance(Map<String, Double> statChance){

        Map<Double,ArrayList<String>> chances = new HashMap<>(); //  <--------- Uncomment this to the next line
        ArrayList<Double> chancesKeys = new ArrayList<Double>();

        for (String value : statChance.keySet()){
            //Map Key is a Double, to store all the stats with the same chances in one key.
            //Value is equal key, to retrieve the names of the artifact stats
            double d = 0.0;

            if (statChance.get(value) instanceof Number){ //Some values are long (thanks firebase), so inorder to convert it to a double you have to do this
                d = ((Number) statChance.get(value)).doubleValue(); //or else you get an error
            }else{
                System.out.println("this is wrong " + statChance.get(value));
            }

            double id = d;

            if(chances.get(id) == null){
                chances.put(id,new ArrayList<String>());
            }
            chances.get(id).add(value); //
        }

        double randomizer = Math.random() * 100;
        Random random = new Random();

        String statPicked = null;

        System.out.println("It has to be less than");
        System.out.println(randomizer);
        //Checks to see if the percent of the stat is smaller than the random number.

        Double overallPercent = 0.0;

        do {
        for(Double key : chances.keySet()){

            ArrayList<String> stats = (ArrayList<String>) chances.get(key).clone();

            for(String samePercentStats : stats){
                overallPercent+= key;
            }
            System.out.println(stats.toString() + " overall percentage is " + overallPercent.toString());


            if(randomizer <= overallPercent){
                statPicked = stats.get(random.nextInt(stats.size()));
                return statPicked;
            }
        }} while(statPicked != null);

        return statPicked;
   }

   //Needed to return Data in getArtifactInfo, because it has to wait for the data from firebase before it can return it.
   interface OnArtifactInfoRecievedListener {
        void onArtifactInfoRecieved(String results);
   }

    //Get Artifact Information from Firebase, then passes it to StatChances
    public void getArtifactInfo(String Artifact, String Stat, OnArtifactInfoRecievedListener listener){

        DocumentReference docRef = db.collection("Artifacts").document(Artifact);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                      //  Log.d(TAG, "DocumentSnapshot data: " + document.get("Main Stat"));

                        Map<String, Double> statData = (Map<String, Double>) document.get(Stat); //Gets all main Stat data from firebase

                        listener.onArtifactInfoRecieved(getStatChance(statData));


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