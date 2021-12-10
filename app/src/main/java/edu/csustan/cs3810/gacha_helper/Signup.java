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
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = (EditText) findViewById(R.id.editTextTextPassword);
    }

    public void signupButtonPressed(View view){ //when the user presses the sign up button
        signupUser();
    }

    private void signupUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()){ //checks if email is empty
            editTextEmail.setError("Required to type in Email");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){ //checks
            editTextEmail.setError("Enter valid email address.");
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            return;
        }

        if(password.length()<6){
            editTextPassword.setError("Password needs to be at least 6 characters");
            return;
        }

        //TODO Fix login error, it won't login user

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //user is login in
                    System.out.println("User is login");


                    goToMenu(); //Takes the user to the menu
                }
                else{
                    System.out.println("Failed login");
                    editTextEmail.setError("Email already register");
                    //TODO Notify user the login failed

                    // Toast.makeText(loginActivity.this, "Failed to login, wrong email or password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void goToMenu(){ //Takes the user to navigation menu
        Intent intent = new Intent(this, Menu.class);
        this.startActivity(intent);
    }

}