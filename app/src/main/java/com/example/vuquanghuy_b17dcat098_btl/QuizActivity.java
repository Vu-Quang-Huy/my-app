package com.example.vuquanghuy_b17dcat098_btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuquanghuy_b17dcat098_btl.databinding.ActivityQuizBinding;
import com.example.vuquanghuy_b17dcat098_btl.model.Question;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    ActivityQuizBinding binding;

    ArrayList<Question> questions;
    Question question;
    int index=0;
    CountDownTimer timer;
    int correctAnswers = 0;
    FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        questions = new ArrayList<>();


//        questions.add(new Question("huy la ai","Huy","Tu","ti","ma","Huy"));
//        questions.add(new Question("huy la ai","Huy","Tu","ti","ma","ma"));
        database = FirebaseFirestore.getInstance();
        final String catID = getIntent().getStringExtra("catID");


        database
                .collection("categories")
                .document(catID)
                .collection("questions")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot snapshot:queryDocumentSnapshots){
                    Question question= snapshot.toObject(Question.class);
                    questions.add(question);
                }
                Collections.shuffle(questions);
                setNextQuestion();
            }
        });
        resetTimer();


        binding.btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(QuizActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    void resetTimer(){
        timer=new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                binding.timer.setText((millisUntilFinished/1000)+"");
            }

            @Override
            public void onFinish() {

            }
        };
    }

    void showAnswer() {
        if(question.getAnswer()!=null){
            if (question.getAnswer().equals(binding.textDA1.getText().toString()))
                binding.textDA1.setBackground(getResources().getDrawable(R.drawable.option_right));
            else if (question.getAnswer().equals(binding.textDA2.getText().toString()))
                binding.textDA2.setBackground(getResources().getDrawable(R.drawable.option_right));
            else if (question.getAnswer().equals(binding.textDA3.getText().toString()))
                binding.textDA3.setBackground(getResources().getDrawable(R.drawable.option_right));
            else if (question.getAnswer().equals(binding.textDA4.getText().toString()))
                binding.textDA4.setBackground(getResources().getDrawable(R.drawable.option_right));
        }
        else
            System.out.println(question.getAnswer()+"null?");
    }

    void setNextQuestion() {
        if(timer!=null){
            timer.cancel();
        }
        timer.start();
        if(index <questions.size()){
            binding.questionCounter.setText(String.format("%d/%d",(index+1),questions.size()));
            question= questions.get(index);
            binding.textQuestion.setText(question.getQuestion());
            binding.textDA1.setText(question.getOption1());
            binding.textDA2.setText(question.getOption2());
            binding.textDA3.setText(question.getOption3());
            binding.textDA4.setText(question.getOption4());
        }
    }

    void checkAnswer(TextView textView) {
        String selectedAnswer = textView.getText().toString();
        if(selectedAnswer.equals(question.getAnswer())) {
            correctAnswers++;
            textView.setBackground(getResources().getDrawable(R.drawable.option_right));
            binding.textDA1.setEnabled(false);
            binding.textDA2.setEnabled(false);
            binding.textDA3.setEnabled(false);
            binding.textDA4.setEnabled(false);
        } else {
           showAnswer();
            textView.setBackground(getResources().getDrawable(R.drawable.option_wrong));
            binding.textDA1.setEnabled(false);
            binding.textDA2.setEnabled(false);
            binding.textDA3.setEnabled(false);
            binding.textDA4.setEnabled(false);
        }
    }

    void reset() {
        binding.textDA1.setBackground(getResources().getDrawable(R.drawable.option_unselected));
        binding.textDA2.setBackground(getResources().getDrawable(R.drawable.option_unselected));
        binding.textDA3.setBackground(getResources().getDrawable(R.drawable.option_unselected));
        binding.textDA4.setBackground(getResources().getDrawable(R.drawable.option_unselected));
        binding.textDA1.setEnabled(true);
        binding.textDA2.setEnabled(true);
        binding.textDA3.setEnabled(true);
        binding.textDA4.setEnabled(true);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textDA1:
            case R.id.textDA2:
            case R.id.textDA3:
            case R.id.textDA4:

                TextView selected = (TextView) view;
                checkAnswer(selected);
                break;

            case R.id.btnNext:
                reset();
                if(index <= questions.size()) {
                    index++;
                    setNextQuestion();
                } else {
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("correct", correctAnswers);
                    intent.putExtra("total", questions.size());
                    startActivity(intent);
                    Toast.makeText(this, "Quiz Finished.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }











}