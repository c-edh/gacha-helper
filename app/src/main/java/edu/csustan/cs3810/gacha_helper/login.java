package edu.csustan.cs3810.gacha_helper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
        }

        if(password.length()<6){
            editTextPassword.setError("Password is wrong");
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //user is login in
                    System.out.println("User is login");
                }
                else{
                    System.out.println("Failed login");
                   // Toast.makeText(loginActivity.this, "Failed to login, wrong email or password", Toast.LENGTH_LONG).show();
                }
            }
        });


    }


}