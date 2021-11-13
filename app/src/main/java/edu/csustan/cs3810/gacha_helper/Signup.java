package edu.csustan.cs3810.gacha_helper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    }

    public void signupButtonPressed(View view){ //when the user presses the sign up button
        signupUser();
    }

    private void signupUser(){
        //user sign up procedures
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        System.out.println(email);
        System.out.println(password);

        //TODO Fix crash issue

        //TODO get user register into firebase

        goToMenu();
    }

    private void goToMenu(){ //Takes the user to navigation menu
        Intent intent = new Intent(this, Menu.class);
        this.startActivity(intent);
    }

}