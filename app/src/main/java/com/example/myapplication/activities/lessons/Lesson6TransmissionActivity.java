package com.example.myapplication.activities.lessons;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.utils.Checker;
import com.example.myapplication.R;

public class Lesson6TransmissionActivity extends LessonTransmissionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson6_transmission);

        launchActivity();
        lowerCharacterBound = 21;
        answerTextView = findViewById(R.id.answerTextView);
        characterTextView = findViewById(R.id.characterTextView);
        checker = new Checker(answerTextView);
        // Listener для кнопки
        tapperButton = findViewById(R.id.keyButton);
        setListenerOnTapperButton();
    }

    @Override
    public void next(View view) {
        Intent intent = new Intent(this, Lesson7Activity.class);
        startActivity(intent);
    }
}