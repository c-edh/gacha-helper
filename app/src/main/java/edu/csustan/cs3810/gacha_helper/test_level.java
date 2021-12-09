//written by: Dhominic Abenes
//tested by: Dhominic Abenes, Corey Edh
//debugged by: Dhominic Abenes, Corey Edh
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    ArrayList<UserBuild> userBuilds = new ArrayList<UserBuild>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_level);
    }

     public void pullUserBuild(View view) {
         TextView pulledUserBuildTextView  = (TextView) findViewById(R.id.pulledUserBuild);

         FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
}

   public void getUserArtifactBuild(String Artifact, String Stat, CreateBuild.OnArtifactInfoRecievedListener listener){

       FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        DocumentReference docRef = db.collection("Users Build").document(user.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //  Log.d(TAG, "DocumentSnapshot data: " + document.get("Main Stat"));

                        Map<String, ArrayList<UserBuild>> SomeTypeVar = (Map<String,ArrayList<UserBuild>>) document.get(Stat); //Gets all main Stat data from firebase


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