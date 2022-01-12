package com.ali.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.quizapp.databinding.ActivityQuizBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    ActivityQuizBinding binding;

    ArrayList<Question> questions;
    int index = 0;
    Question question;
    CountDownTimer timer;
    FirebaseFirestore database;
    int correctAnswers = 0;

    public static String catId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        questions = new ArrayList<>();
        database = FirebaseFirestore.getInstance();

        catId = getIntent().getStringExtra("catId");
      //  Toast.makeText(this, catId, Toast.LENGTH_SHORT).show();

        Random random = new Random();
        final int rand = random.nextInt(10);

        database.collection("categories")
                .document(catId)
                .collection("questions")
                .whereGreaterThanOrEqualTo("index", rand)
                .orderBy("index")
                .limit(5).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(queryDocumentSnapshots.getDocuments().size() < 5) {
                    database.collection("categories")
                            .document(catId)
                            .collection("questions")
                            .whereLessThanOrEqualTo("index", rand)
                            .orderBy("index")
                            .limit(5).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for(DocumentSnapshot snapshot : queryDocumentSnapshots) {
                                Question question = snapshot.toObject(Question.class);
                                questions.add(question);
                            }
                            setNextQuestion();
                        }
                    });
                } else {
                    for(DocumentSnapshot snapshot : queryDocumentSnapshots) {
                        Question question = snapshot.toObject(Question.class);
                        questions.add(question);
                    }
                    setNextQuestion();
                }
            }
        });



        resetTimer();

    }

    void resetTimer() {
        timer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                binding.timer.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {

            }
        };
    }

    void showAnswer() {
        if(question.getAnswer().equals(binding.option1.getText().toString()))
            binding.option1.setBackground(getResources().getDrawable(R.drawable.option_right));
        else if(question.getAnswer().equals(binding.option2.getText().toString()))
            binding.option2.setBackground(getResources().getDrawable(R.drawable.option_right));
        else if(question.getAnswer().equals(binding.option3.getText().toString()))
            binding.option3.setBackground(getResources().getDrawable(R.drawable.option_right));
        else if(question.getAnswer().equals(binding.option4.getText().toString()))
            binding.option4.setBackground(getResources().getDrawable(R.drawable.option_right));
    }

    void setNextQuestion() {
        if(timer != null)
            timer.cancel();

        timer.start();
        if(index < questions.size()) {
            binding.questionCounter.setText(String.format("%d/%d", (index+1), questions.size()));
            question = questions.get(index);
            binding.question.setText(question.getQuestion());
            binding.option1.setText(question.getOption1());
            binding.option2.setText(question.getOption2());
            binding.option3.setText(question.getOption3());
            binding.option4.setText(question.getOption4());
        }
    }

    void checkAnswer(TextView textView) {
        String selectedAnswer = textView.getText().toString();
        try{
            if(selectedAnswer.equals(question.getAnswer())) {
                correctAnswers++;
                textView.setBackground(getResources().getDrawable(R.drawable.option_right));
            } else {
                showAnswer();
                textView.setBackground(getResources().getDrawable(R.drawable.option_wrong));
            }
        }catch(Exception e){
            Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show();
        }

    }

    void reset() {
        binding.option1.setBackground(getResources().getDrawable(R.drawable.option_unselected));
        binding.option2.setBackground(getResources().getDrawable(R.drawable.option_unselected));
        binding.option3.setBackground(getResources().getDrawable(R.drawable.option_unselected));
        binding.option4.setBackground(getResources().getDrawable(R.drawable.option_unselected));
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.option_1:
                if(timer!=null)
                    timer.cancel();
                TextView selected1 = (TextView) view;
                checkAnswer(selected1);
                break;
            case R.id.option_2:
                if(timer!=null)
                    timer.cancel();
                TextView selected2 = (TextView) view;
                checkAnswer(selected2);
                break;
            case R.id.option_3:
                if(timer!=null)
                    timer.cancel();
                TextView selected3 = (TextView) view;
                checkAnswer(selected3);
                break;
            case R.id.option_4:
                if(timer!=null)
                    timer.cancel();
                TextView selected4 = (TextView) view;
                checkAnswer(selected4);
                break;
            case R.id.nextBtn:
                reset();
                if(index < questions.size()-1) {

                    index++;
                    setNextQuestion();
                } else {

                  Intent intent = new Intent(QuizActivity.this, MyResultActivity.class);
                   intent.putExtra("correct", correctAnswers);
                   intent.putExtra("total", questions.size());
                   startActivity(intent);
                   finish();
                    Toast.makeText(this, "Quiz Finished.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void quitQuiz(View view) {
        finish();
    }
}