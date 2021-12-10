package edu.csustan.cs3810.gacha_helper;

//Corey Edh

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


public class login extends AppCompatActivity {

    EditText editTextEmail;             //email text
    EditText editTextPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        mAuth = FirebaseAuth.getInstance();
    }

    public void loginButtonPressed(View view){
        loginUser();
    }

    private void loginUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        Boolean loginchecker = true;

        //User didn't enter in a email address
        if (email.isEmpty()){ //checks if email is empty
            editTextEmail.setError("Enter in a valid Email address");
            loginchecker = false;
        }

        //User didn't enter in a valid email address
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){ //checks
            editTextEmail.setError("Enter in a valid email address.");
            loginchecker = false;
        }

        //If password is less than 6 characters long (Firebase needs the password to be at least 6 chars long)
        if(password.length()<6){
            editTextPassword.setError("Password is incorrect");
            loginchecker = false;
        }

        //User didn't type in password
        if(password.isEmpty()){
            editTextPassword.setError("Password is empty");
            loginchecker = false;
        }

        //If on or more if statements = false, it gives the user information on what went wrong.
        if(loginchecker == false){
            return;
        }

        //Sends the information to firebase, to authentic the user
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //If login is successful
                if(task.isSuccessful()){
                    //user is login in
                    System.out.println("User is login");
                    goToMenu(); //Takes the user to the menu
                }
                else{ //If Login failed
                    System.out.println("Failed login");
                    editTextEmail.setError("Incorrect Login");
                }
            }
        });

    }

    //Takes user to next screen
    private void goToMenu(){
        Intent intent = new Intent(this, Menu.class);
        this.startActivity(intent);
    }


}