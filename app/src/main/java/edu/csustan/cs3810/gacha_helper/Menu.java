package edu.csustan.cs3810.gacha_helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    //TODO Make all the buttons' action into one action

    //TODO Have button take user's to chosen activity

    public void goToFarmArtifact(View view){ //if user presses Farm Artifact Button

    }

    public void goToLevelup(View view){ //if user presses Level Up Button

    }

    public void goToRecommend(View view){ //if user presses Recommend Button

    }

    public void goToViewSavedBuilds(View view){ // if user presses View Saved Button

    }

    public void goToCreateABuild(View view){ // if user presses Create a Build Button
        Intent intent = new Intent(this, Build.class);
        this.startActivity(intent);

    }


}