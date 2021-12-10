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
            public void onArtifactInfoRecieved(Object results) {
                TextView onArtifactInfoReceived  = (TextView) findViewById(R.id.onArtifactInfoReceieved);
                onArtifactInfoReceived.setText("");

                FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
                CollectionReference applicationsRef = rootRef.collection("Users Build");
                DocumentReference applicationIdRef = applicationsRef.document("getUid");
                applicationIdRef.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();

                        if (document.exists()) {
                            List<Map<String, Object>> users = (List<Map<String, Object>>)document.get("users");
                        }

                    }
                });

                System.out.println(results); //chnage to nested for looop to both display and increment
            }
        });
    }

    interface OnArtifactInfoRecievedListener {
        void onArtifactInfoRecieved(Object results);
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