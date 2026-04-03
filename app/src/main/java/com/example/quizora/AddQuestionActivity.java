package com.example.quizora;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddQuestionActivity extends Activity {

    private EditText etQuestionText, etOptionA, etOptionB, etOptionC, etOptionD, etCorrectAnswer;
    private Button btnSaveQuestion, btnFinishAdding;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        dbHelper = new DatabaseHelper(this);

        etQuestionText = findViewById(R.id.etQuestionText);
        etOptionA = findViewById(R.id.etOptionA);
        etOptionB = findViewById(R.id.etOptionB);
        etOptionC = findViewById(R.id.etOptionC);
        etOptionD = findViewById(R.id.etOptionD);
        etCorrectAnswer = findViewById(R.id.etCorrectAnswer);

        btnSaveQuestion = findViewById(R.id.btnSaveQuestion);
        btnFinishAdding = findViewById(R.id.btnFinishAdding);

        btnSaveQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveQuestion();
            }
        });

        btnFinishAdding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close activity when done
            }
        });
    }

    private void saveQuestion() {
        String qText = etQuestionText.getText().toString().trim();
        String optA = etOptionA.getText().toString().trim();
        String optB = etOptionB.getText().toString().trim();
        String optC = etOptionC.getText().toString().trim();
        String optD = etOptionD.getText().toString().trim();
        String correct = etCorrectAnswer.getText().toString().trim();

        if (qText.isEmpty() || optA.isEmpty() || optB.isEmpty() || optC.isEmpty() || optD.isEmpty() || correct.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Question question = new Question(qText, optA, optB, optC, optD, correct);
        long result = dbHelper.insertQuestion(question);

        if (result != -1) {
            Toast.makeText(this, "Question saved successfully!", Toast.LENGTH_SHORT).show();
            // Clear fields for the next question
            etQuestionText.setText("");
            etOptionA.setText("");
            etOptionB.setText("");
            etOptionC.setText("");
            etOptionD.setText("");
            etCorrectAnswer.setText("");
        } else {
            Toast.makeText(this, "Failed to save question", Toast.LENGTH_SHORT).show();
        }
    }
}
