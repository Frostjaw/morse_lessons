package com.example.myapplication.activities.lessons;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

public class Lesson12Activity extends LessonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson12);

        super.launchBottomNavigation();
    }

    @Override
    public void openLearning(View view){
        Intent intent = new Intent(this, Lesson12LearningActivity.class);
        startActivity(intent);
    }

    @Override
    public void openReception(View view){
        Intent intent = new Intent(this, Lesson12ReceptionActivity.class);
        startActivity(intent);
    }

    @Override
    public void openTransmission(View view){
        Intent intent = new Intent(this, Lesson12TransmissionActivity.class);
        startActivity(intent);
    }

    @Override
    public void next(View view){
        //Intent intent = new Intent(this, Lesson13Activity.class);
        //startActivity(intent);
    }
}
