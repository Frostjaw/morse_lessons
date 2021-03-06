package com.example.myapplication.activities.trainer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.dialogs.ChooseCharactersDialog;
import com.example.myapplication.dialogs.HelpDialog;
import com.example.myapplication.utils.TransmissionChecker;

public class CharacterTransmissionTrainerActivity extends TrainerActivity
        implements ChooseCharactersDialog.OnCharactersSelectedListener {

    private int curCharacter;
    private TransmissionChecker transmissionChecker;
    private TextView answerTextView;
    private TextView characterTextView;
    private Button tapperButton;

    // Logs
    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_transmission_trainer);

        launchActivity();

        characterTextView = findViewById(R.id.character_textView);
        answerTextView = findViewById(R.id.answer_textView);

        transmissionChecker = new TransmissionChecker(answerTextView);
        // Listener для кнопки
        tapperButton = findViewById(R.id.key_button);
        setListenerOnTapperButton();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof ChooseCharactersDialog) {
            ChooseCharactersDialog dialog = (ChooseCharactersDialog) fragment;
            dialog.setOnCharactersSelectedListener(this);
        }

        if (fragment instanceof HelpDialog) {
            HelpDialog dialog = (HelpDialog) fragment;
            dialog.curCharacter = this.curCharacter;
        }
    }

    @Override
    public void onCharactersSelected(String[] selectedCharacters) {

        answerTextView.setText("");
        characterTextView.setText("");

        // selectedCharacters[0] - characters without punctuations marks
        // selectedCharacters[1] - punctuations marks

        char[] charArray = selectedCharacters[0].toCharArray();
        morseCodeGenerator.setCharacterPoolForGeneration(charArray);

        char[] punctuationMarksArray = selectedCharacters[1].toCharArray();
        morseCodeGenerator.addPunctuationMarksToPool(punctuationMarksArray);
    }

    public void showSettingsDialog(View view) {
        openChooseCharactersDialog(view);
    }

    public void showHelpDialog(View view) {
        if (!morseCodeGenerator.charactersPoolIsEmpty) {
            openHelpDialog(view);
        }else{
            answerTextView.setText("Выберите символы для тренировки");
        }
    }

    public void startTransmission(View view) {
        if (morseCodeGenerator.charactersPoolIsEmpty) {
            answerTextView.setText("Выберите символы для тренировки");
        }else{
            curCharacter = morseCodeGenerator.getRandomCharacterFromPool();
            characterTextView.setText(morseCodeGenerator.morseDictionary.get(curCharacter));
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListenerOnTapperButton(){
        tapperButton.setOnTouchListener(new View.OnTouchListener() {
            long startTime = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    startTime = System.currentTimeMillis();
                    morseCodeGenerator.start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    long totalTime = System.currentTimeMillis() - startTime;
                    if ((totalTime > 0) && (totalTime < 250)){ // погрешность
                        if (curCharacter != 0) {
                            transmissionChecker.check(curCharacter, 0);
                        }
                    }else if((totalTime > 300) && (totalTime < 1000)){
                        if (curCharacter != 0) transmissionChecker.check(curCharacter,1);
                    } else {
                        answerTextView.setText("Неверно");
                    }
                    morseCodeGenerator.stop();
                }
                return true;
            }
        });
    }
}
