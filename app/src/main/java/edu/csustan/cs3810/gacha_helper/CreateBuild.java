package edu.csustan.cs3810.gacha_helper;

// written by:Corey Edh
// tested by: Corey Edh
// debugged by: Corey Edh


import static android.content.ContentValues.TAG;

import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
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

public class CreateBuild extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ArrayList<UserBuild> userBuilds = new ArrayList<UserBuild>();

    test_level Level = new test_level();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createbuild);

    }
    //Flower Button
    public void getFlowerBuild(View view){
        createUserBuild("Flower of Life");
    }
    //Circlet Button
    public void getCircletOfLogos(View view){
        createUserBuild("Circlet of Logos");
    }
    //Goblet Button
    public void getGobletOfEonothem(View view){
        createUserBuild("Goblet of Eonothem");
    }
    //Feather
    public void getPlumeOfDeath(View view){
        createUserBuild("Plume of Death");
    }
    //Sands of Eon Button
    public void getSandsOfEon(View view){
        createUserBuild("Sands of Eon");
    }


    //When user press Create Build Button (sends the build to Firebase)
    public void createUserBuildButtonPressed(View view){
        TextView artifactsInUserBuildTextView  = (TextView) findViewById(R.id.artifactsInUserBuildTextView);
        TextView numberOfArtifactsTextView  = (TextView) findViewById(R.id.numberOfArtifactsTextView);

        //Gets current users (so we can stored it with this users data)
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //Uses this to count how many other builds there are so it wont overwrite them. and create new ones.
        Level.getUserArtifactBuild(new test_level.OnArtifactInfoRecievedListener() {
            int count = 1;
            @Override
            public void onArtifactInfoRecieved(Map<String, Object> results) {
                for(String i : results.keySet()){
                    count +=1;
                }


        System.out.println(userBuilds);

        Map<String, Object> dataToFirebase = new HashMap<>();
        dataToFirebase.put("Build"+count, userBuilds);
        db.collection("Users Build").document(user.getUid()).update(dataToFirebase);

        artifactsInUserBuildTextView.setText("The Build was stored");

        userBuilds.clear();
        numberOfArtifactsTextView.setText("0/5 Artifacts");
        numberOfArtifactsTextView.setTextColor(Color.BLACK);
            }
        });

    }

    //Gets the Artifacts that the user's has selected and puts in in an Arraylist of usersBuild (this is going to be the Build of the user's)
    private void createUserBuild(String Artifact){
        TextView artifactsInUserBuildTextView  = (TextView) findViewById(R.id.artifactsInUserBuildTextView);
        TextView numberOfArtifactsTextView  = (TextView) findViewById(R.id.numberOfArtifactsTextView);

        //Builds are limited to 5 artifacts in a build, so if it has 5 it will not add another artifact
        if (userBuilds.size() > 4){
            numberOfArtifactsTextView.setTextColor(Color.RED);
            return;
        }

        //Getting the Artifact's Main Stats
        getArtifactInfo(Artifact, "Main Stat", new OnArtifactInfoRecievedListener(){
            @Override
            public void onArtifactInfoRecieved(String results) {

                //Results from this are the Main Stats of the Artifact
                String mainstat = results;
                System.out.println("This is main stat" + mainstat);



                //Getting the artifact's sub stats
                getArtifactInfo(Artifact, "Sub Stat", new OnArtifactInfoRecievedListener() {
                    @Override
                    public void onArtifactInfoRecieved(String results) {
                        String substat = results;
                        System.out.println("This is the sub stat " + substat);

                        //Flower doesnt have a substat
                        if(Artifact == "Flower of Life"){
                            substat = "none";
                        }

                        //Custom Object
                        UserBuild build = new UserBuild(Artifact,1,mainstat,substat);

                        //Adds this to the user's build
                        userBuilds.add(build);

                        //Below is just to display it on the phone
                        String userbuildsString = "";
                        int ArtifactCounter = 0;

                        //For all the artifacts in build, it loops through to display it on the phone
                        //So the user knows that the artifact they select was selected
                        for(UserBuild artifactbuild : userBuilds){

                            ArtifactCounter +=1;

                            userbuildsString += (ArtifactCounter + ")" + artifactbuild.getArtifactName().toString() +"- Main stat:" + artifactbuild.getArtifactMainStat()
                                    .toString() +", Sub Stat:" + artifactbuild.getArtifactSubStat().toString() + "; \n");

                            artifactsInUserBuildTextView.setText(userbuildsString);
                            numberOfArtifactsTextView.setText(ArtifactCounter + "/5 Artifacts");



                        }
                    }
                });
            }
        });
    }



    //Get stat chances of getting pick, and picks the stat from that percentage
    public String getStatChance(Map<String, Double> statChance){

        if(statChance == null){
            return null;
        }

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

       // System.out.println("It has to be less than");
        //System.out.println(randomizer);
        //Checks to see if the percent of the stat is smaller than the random number.

        Double overallPercent = 0.0;

        do {
        for(Double key : chances.keySet()){

            ArrayList<String> stats = (ArrayList<String>) chances.get(key).clone();

            for(String samePercentStats : stats){
                overallPercent+= key;
            }
          //  System.out.println(stats.toString() + " overall percentage is " + overallPercent.toString());


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
     // TextView artifactsInUserBuildTextView  = (TextView) findViewById(R.id.artifactsInUserBuildTextView);
        DocumentReference docRef = db.collection("Artifacts").document(Artifact);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        //Gets all main Stat data from firebase
                        Map<String, Double> statData = (Map<String, Double>) document.get(Stat);

                        //The listener
                        listener.onArtifactInfoRecieved(getStatChance(statData));


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