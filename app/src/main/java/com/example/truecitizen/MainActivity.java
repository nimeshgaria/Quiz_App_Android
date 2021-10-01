package com.example.truecitizen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.truecitizen.databinding.ActivityMainBinding;
import com.example.truecitizen.model.Question;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;


    private final Question[] questionBank = new Question[] {
            //create/instantiate question objects
            new Question(R.string.question_polo, false), //correct: 27
            new Question(R.string.question_khalsa, true),
            new Question(R.string.question_baseball, false),           //and add more!

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

         binding.questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());
         binding.trueButton.setOnClickListener(view -> checkAnswer(true));
        binding.falseButton.setOnClickListener(view -> checkAnswer(false));
        binding.nextButton.setOnClickListener(view -> {
            currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length;
            updateQuestion();
        });

        binding.prevButton.setOnClickListener( view -> {
            if (currentQuestionIndex > 0 ) {
                currentQuestionIndex = (currentQuestionIndex - 1) % questionBank.length;
                updateQuestion();
            }
        });

    }
    private void checkAnswer (boolean userChoseCorrect){
        boolean answerIsCorrect = questionBank[currentQuestionIndex].isAnswerTrue();
        int messageId;

        if(answerIsCorrect == userChoseCorrect) {
            messageId = R.string.correct_answer;
        }
        else {
            messageId = R.string.wrong_answer;
        }
        Snackbar.make(binding.questionTextView, messageId, Snackbar.LENGTH_SHORT)
                .show();

    }

    private void updateQuestion() {
        binding.questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());
    }
}
