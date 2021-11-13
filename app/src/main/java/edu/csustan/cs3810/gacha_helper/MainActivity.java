package edu.csustan.cs3810.gacha_helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToLogin(View v){
        Intent intent = new Intent(this, login.class);
        this.startActivity(intent);
    }
    public void goToSignUp(View v){
        Intent intent = new Intent(this, Signup.class);
        this.startActivity(intent);
    }

}