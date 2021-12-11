package edu.csustan.cs3810.gacha_helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

// written by:
// tested by: neha
// debugged by:
// etc.

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    //TODO Make all the buttons' action into one action

    //TODO Have button take user's to chosen activity

    public void goToFarmArtifact(View view){ //if user presses Farm Artifact Button
        Intent intent = new Intent (this, FarmArtifact.class);
        this.startActivity(intent);
    }

    public void goToLevelup(View view){ //if user presses Level Up Button
        Intent intent = new Intent (this, test_level.class);
        this.startActivity(intent);
    }

    public void goToRecommend(View view){ //if user presses Recommend Button
    Intent intent = new Intent (this, test_recommend.class);
    this.startActivity(intent);
    }

    public void goToViewSavedBuilds(View view){ // if user presses View Saved Button
        Intent intent = new Intent (this, Saved.class);
        this.startActivity(intent);
    }

    public void goToCreateABuild(View view){ // if user presses Create a Build Button
        Intent intent = new Intent(this, CreateBuild.class);
        this.startActivity(intent);

    }


}