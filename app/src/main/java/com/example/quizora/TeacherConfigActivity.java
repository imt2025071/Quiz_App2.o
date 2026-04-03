package com.example.quizora;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TeacherConfigActivity extends Activity {

    private EditText etSubject, etNumQuestions, etTimerMinutes;
    private Button btnSubmitConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_config);

        etSubject = findViewById(R.id.etSubject);
        etNumQuestions = findViewById(R.id.etNumQuestions);
        etTimerMinutes = findViewById(R.id.etTimerMinutes);
        btnSubmitConfig = findViewById(R.id.btnSubmitConfig);

        btnSubmitConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = etSubject.getText().toString().trim();
                String numQStr = etNumQuestions.getText().toString().trim();
                String timerStr = etTimerMinutes.getText().toString().trim();

                if (subject.isEmpty() || numQStr.isEmpty() || timerStr.isEmpty()) {
                    Toast.makeText(TeacherConfigActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    int numQ = Integer.parseInt(numQStr);
                    int timer = Integer.parseInt(timerStr);
                    
                    Toast.makeText(TeacherConfigActivity.this, 
                            "Quiz Saved! Subject: " + subject + ", " + numQ + " Qs, " + timer + " mins.", 
                            Toast.LENGTH_LONG).show();
                            
                    // Here you would proceed to the question creation UI
                    Intent intent = new Intent(TeacherConfigActivity.this, AddQuestionActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
