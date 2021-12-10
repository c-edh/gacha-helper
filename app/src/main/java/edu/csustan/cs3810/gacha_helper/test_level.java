package edu.csustan.cs3810.gacha_helper;


//written by: Dhominic Abenes
//tested by: Dhominic Abenes, Corey Edh
//debugged by: Dhominic Abenes, Corey Edh


import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class test_level extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();


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

                for(String builds : results.keySet()){

                    ArrayList<Object> buildartifact = (ArrayList) results.get(builds);

                    for(Object artifact : buildartifact){

                        Map<String, ?> maptest = (Map<String, ?>) artifact;

                        UserBuild test = new UserBuild(maptest);
                        System.out.println(test.getArtifactName());





                    }


                }



              ///  System.out.println(results); //chnage to nested for looop to both display and increment
            }
        });
    }

    interface OnArtifactInfoRecievedListener {
        void onArtifactInfoRecieved(Map<String, Object> results);
    }




private void getUserArtifactBuild(OnArtifactInfoRecievedListener listener){

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