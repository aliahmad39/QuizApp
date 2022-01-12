package com.ali.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ali.quizapp.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    FirebaseAuth Auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Auth=FirebaseAuth.getInstance();

        dialog=new ProgressDialog(this);
        dialog.setMessage("logging In...");

//agr koi user phly sy hi login hai to usy bara bar login na krna py
       // if(Auth.getCurrentUser() !=null){
          //  startActivity(new Intent(loginActivity.this,MainActivity.class));
          //  finish();
       // }

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,pass,forgotPass;
                email=binding.emailBox.getText().toString();
                pass=binding.passwordBox.getText().toString();
                forgotPass=binding.forgotPassword.getText().toString();
                dialog.show();

                Auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dialog.dismiss();
                        if (task.isSuccessful()){


                          startActivity(new Intent(loginActivity.this,MainActivity.class));
                          finish();

                        }else {
                            dialog.dismiss();
                            Toast.makeText(loginActivity.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

binding.createNewBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(loginActivity.this,SignupActivity.class));
    }
});
binding.forgotPassword.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(loginActivity.this,ForgotPassword.class));
        finish();

    }
});
    }
}