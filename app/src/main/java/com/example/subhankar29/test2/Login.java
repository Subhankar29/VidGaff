package com.example.subhankar29.test2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;


    //defining view objects
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignup;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonSignup = (Button) findViewById(R.id.buttonSignup);

        buttonSignup.setOnClickListener(this);

    }

    public void signin(View view){
        Intent myIntent = new Intent(Login.this, LogIn_activity.class);
        Login.this.startActivity(myIntent);

    }

    private void registerUser(){

        String email = editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //display some message here
                    Toast.makeText(Login.this,"Successfully registered",Toast.LENGTH_LONG).show();
                }else{
                    //display some message here
                    Toast.makeText(Login.this,"Registration Error",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });

    }

    @Override
    public void onClick(View v) {
      if(v==buttonSignup)
          registerUser();
    }
}
