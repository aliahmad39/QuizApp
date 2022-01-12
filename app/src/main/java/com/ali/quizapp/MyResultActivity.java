package com.ali.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ali.quizapp.databinding.ActivityMyResultBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;


public class MyResultActivity extends AppCompatActivity {
ActivityMyResultBinding binding;
    int POINTS =10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMyResultBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        int correctAnswers=getIntent().getIntExtra("correct",0);
        int totalQuestions=getIntent().getIntExtra("total",0);
//for points
        long points=correctAnswers*POINTS;
       binding.score.setText(String.format("%d/%d",correctAnswers,totalQuestions));
        binding.earnedCoins.setText(String.valueOf(points));

        FirebaseFirestore database = FirebaseFirestore.getInstance();

        database.collection("Users")
                .document(FirebaseAuth.getInstance().getUid())
                .update("coins", FieldValue.increment(points));

    }

    public void RestartQuiz(View view) {

       startActivity(new Intent(MyResultActivity.this , QuizActivity.class).putExtra("catId" , QuizActivity.catId));
       finish();
    }
}