package edu.csustan.cs3810.gacha_helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

// written by:
// tested by:
// debugged by:
// etc.

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToLogin(View v){
       Intent intent = new Intent(this, login.class);  //<----uncomment when login issue is fix
        //Intent intent = new Intent(this, Menu.class);
        this.startActivity(intent);
    }
    public void goToSignUp(View v){
        Intent intent = new Intent(this, Signup.class);
        // Intent intent = new Intent(this, Menu.class);
        this.startActivity(intent);
    }

    public void goToBuild(View v) {
        Intent intent = new Intent(this, Build.class);
        this.startActivity(intent);
    }



}