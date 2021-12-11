package edu.csustan.cs3810.gacha_helper;


//written by: Dhominic Abenes
//tested by: Dhominic Abenes, Corey Edh
//debugged by: Dhominic Abenes, Corey Edh


import static android.content.ContentValues.TAG;

import android.accounts.AccountManagerFuture;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.auth.User;

import org.w3c.dom.Document;

import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.security.Key;
import java.sql.SQLXML;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Future;

public class test_level<var> extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Map<Integer, UserBuild> levelBuild = new HashMap();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_level);
    }

    public void pullUserBuild(View view){
        getUserArtifactBuild(new OnArtifactInfoRecievedListener() {
            @Override
            public void onArtifactInfoRecieved(Map<String, Object> results) {
                TextView onArtifactInfoReceived  = (TextView) findViewById(R.id.onArtifactInfoReceieved);
                onArtifactInfoReceived.setText("");
                String allartifacts = "";

                for(String builds : results.keySet()){

                    ArrayList<Object> buildartifact = (ArrayList) results.get(builds);

                    for(Object artifact : buildartifact){

                        Map<String, ?> maptest = (Map<String, ?>) artifact;

                        int counter = 0;

                        UserBuild test = new UserBuild(maptest);
                        levelBuild.put(counter,test);
                        System.out.println(test.getArtifactName());

                        allartifacts += (levelBuild.get(counter).getArtifactName().toString() + "\n"); //remove to string
                        counter -=1;

                        onArtifactInfoReceived.setText(allartifacts);
                    }

                }

            }
        });
    }

    public  void pullSands(View view) {

        getUserArtifactBuild(new OnArtifactInfoRecievedListener() {
            @Override
            public void onArtifactInfoRecieved(Map<String, Object> results) {
                TextView onArtifactInfoReceived = (TextView) findViewById(R.id.onArtifactInfoReceieved);
                onArtifactInfoReceived.setText("");

                for(String builds : results.keySet()){

                    ArrayList<Object> buildartifact = (ArrayList) results.get(builds);

                    DocumentReference documentReference = db.collection("Users Build").document("getUid");
                    documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(documentSnapshot.exists()) {

                                ArrayList<String> arrayList = new ArrayList<String>();
                                arrayList = (ArrayList) documentSnapshot.get("9DnFBp63wbght9Xsq7YsVhC6wp63");

                                for(int i=0; i < arrayList.size(); i++){

                                }
                            }
                        }
                    });


                    for(Object artifact : buildartifact){
                        Map<String, ?> maptest = (Map<String, ?>) artifact;

                        int counter =0;

                        UserBuild test = new UserBuild(maptest);
                        levelBuild.put(counter,test);
                        System.out.println(test.getArtifactName());

                        String artifacts = (levelBuild.get(counter).getArtifactName() + "\n");

                        counter +=1;

                        onArtifactInfoReceived.setText(artifacts);

                        System.out.println(artifacts);
                    }
                }
            }
        });

    }






    public void pullPlume(View view) {
        getUserArtifactBuild(new OnArtifactInfoRecievedListener() {
            @Override
            public void onArtifactInfoRecieved(Map<String, Object> results) {
                TextView onArtifactInfoReceived = (TextView) findViewById(R.id.onArtifactInfoReceieved);
                onArtifactInfoReceived.setText("");

                for(String builds : results.keySet()){

                    ArrayList<Object> buildartifact = (ArrayList) results.get(builds);

                    for(Object artifact : buildartifact){
                        Map<String, ?> maptest = (Map<String, ?>) artifact;

                        int counter =0;

                        UserBuild test = new UserBuild(maptest);
                        levelBuild.put(counter,test);
                        System.out.println(test.getArtifactName());

                        String artifacts = (levelBuild.get(counter).getArtifactName().toString() + "\n");
                        counter +=1;

                        onArtifactInfoReceived.setText(artifacts);
                    }
                }
            }
        });
    }

    public void pullFlower(View view){
        getUserArtifactBuild(new OnArtifactInfoRecievedListener() {
            @Override
            public void onArtifactInfoRecieved(Map<String, Object> results) {
                TextView onArtifactInfoReceived = (TextView) findViewById(R.id.onArtifactInfoReceieved);
                onArtifactInfoReceived.setText("");

                for(String builds : results.keySet()){

                    ArrayList<Object> buildartifact = (ArrayList) results.get(builds);

                    for(Object artifact : buildartifact){
                        Map<String, ?> maptest = (Map<String, ?>) artifact;

                        int counter =0;

                        UserBuild test = new UserBuild(maptest);
                        levelBuild.put(counter,test);
                        System.out.println(test.getArtifactName());

                        String artifacts = (levelBuild.get(counter).getArtifactName().toString() + "\n");
                        counter +=1;

                        onArtifactInfoReceived.setText(artifacts);
                    }
                }
            }
        });
    }

    public void pullGoblet(View view){
        getUserArtifactBuild(new OnArtifactInfoRecievedListener() {
            @Override
            public void onArtifactInfoRecieved(Map<String, Object> results) {
                TextView onArtifactInfoReceived = (TextView) findViewById(R.id.onArtifactInfoReceieved);
                onArtifactInfoReceived.setText("");

                for(String builds : results.keySet()){

                    ArrayList<Object> buildartifact = (ArrayList) results.get(builds);

                    for(Object artifact : buildartifact){
                        Map<String, ?> maptest = (Map<String, ?>) artifact;

                        int counter =0;

                        UserBuild test = new UserBuild(maptest);
                        levelBuild.put(counter,test);
                        System.out.println(test.getArtifactName());

                        String artifacts = (levelBuild.get(counter).getArtifactName().toString() + "\n");
                        counter +=1;

                        onArtifactInfoReceived.setText(artifacts);
                    }
                }
            }
        });
    }

    public void pullCirclet(View view){
        getUserArtifactBuild(new OnArtifactInfoRecievedListener() {
            @Override
            public void onArtifactInfoRecieved(Map<String, Object> results) {
                TextView onArtifactInfoReceived = (TextView) findViewById(R.id.onArtifactInfoReceieved);
                onArtifactInfoReceived.setText("");

                for(String builds : results.keySet()){

                    ArrayList<Object> buildartifact = (ArrayList) results.get(builds);

                    for(Object artifact : buildartifact){
                        Map<String, ?> maptest = (Map<String, ?>) artifact;

                        int counter =0;

                        UserBuild test = new UserBuild(maptest);
                        levelBuild.put(counter,test);
                        System.out.println(test.getArtifactName());

                        String artifacts = (levelBuild.get(counter).getArtifactName().toString() + "\n");
                        counter +=1;

                        onArtifactInfoReceived.setText(artifacts);
                    }
                }
            }
        });
    }



    public void LevelUp(View view){
        getUserArtifactBuild(new OnArtifactInfoRecievedListener() {
            @Override
            public void onArtifactInfoRecieved(Map<String, Object> results) {
                TextView displaystats = (TextView) findViewById(R.id.displaystats);


                for(String builds : results.keySet()) {
                    Map<String,Object> data = new HashMap<>();
                    data.put("artifactLevel", FieldValue.increment(1));



                    System.out.println("New Level Up: " );
                    displaystats.setText("Artifact Enhanced!");
                }
            }
        });



    }

    interface OnArtifactInfoRecievedListener {
        void onArtifactInfoRecieved(Map<String, Object> results);
    }




public void getUserArtifactBuild(OnArtifactInfoRecievedListener listener){

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DocumentReference docRef = db.collection("Users Build").document(user.getUid());
    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Map<String, Object> SomeTypeVar = (Map<String, Object>) document.getData(); //Gets all main Stat data from firebase
                            listener.onArtifactInfoRecieved(SomeTypeVar);
                } else {
                    Log.d(TAG, "No Such Document");
                }
            } else {
                Log.d(TAG, "Get Failed with ", task.getException());
            }
        }
    });
}}