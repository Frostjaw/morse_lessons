package com.example.myapplication.activities.lessons;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

public class Lesson9LearningActivity extends LessonLearningActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson9_learning);

        launchActivityWithBottomNavigation();
    }

    @Override
    public void play1(View view){
        morseCodeGenerator.play32();
    }

    @Override
    public void play2(View view){
        morseCodeGenerator.play33();
    }

    @Override
    public void play3(View view){
        morseCodeGenerator.play34();
    }

    @Override
    public void play4(View view){
        morseCodeGenerator.play35();
    }

    @Override
    public void play5(View view){
        morseCodeGenerator.play36();
    }

    @Override
    public void next(View view){
        Intent intent = new Intent(this, Lesson9ReceptionActivity.class);
        startActivity(intent);
    }
}
