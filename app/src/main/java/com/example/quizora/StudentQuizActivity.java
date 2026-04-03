package com.example.quizora;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StudentQuizActivity extends Activity {

    private TextView tvQuestionCount, tvQuestionText;
    private RadioGroup rgOptions;
    private RadioButton rbOptionA, rbOptionB, rbOptionC, rbOptionD;
    private Button btnNextQuestion;

    private DatabaseHelper dbHelper;
    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_quiz);

        tvQuestionCount = findViewById(R.id.tvQuestionCount);
        tvQuestionText = findViewById(R.id.tvQuestionText);
        rgOptions = findViewById(R.id.rgOptions);
        rbOptionA = findViewById(R.id.rbOptionA);
        rbOptionB = findViewById(R.id.rbOptionB);
        rbOptionC = findViewById(R.id.rbOptionC);
        rbOptionD = findViewById(R.id.rbOptionD);
        btnNextQuestion = findViewById(R.id.btnNextQuestion);

        dbHelper = new DatabaseHelper(this);
        questionList = dbHelper.getAllQuestions();

        if (questionList.isEmpty()) {
            Toast.makeText(this, "No questions found! Teacher needs to add them first.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        displayQuestion();

        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questionList.size()) {
            Question q = questionList.get(currentQuestionIndex);
            tvQuestionCount.setText("Question " + (currentQuestionIndex + 1) + " of " + questionList.size());
            tvQuestionText.setText(q.getQuestionText());
            rbOptionA.setText(q.getOptionA());
            rbOptionB.setText(q.getOptionB());
            rbOptionC.setText(q.getOptionC());
            rbOptionD.setText(q.getOptionD());
            
            rgOptions.clearCheck();
            
            if (currentQuestionIndex == questionList.size() - 1) {
                btnNextQuestion.setText("Finish Quiz");
            } else {
                btnNextQuestion.setText("Next");
            }
        } else {
            finishQuiz();
        }
    }

    private void checkAnswer() {
        int selectedId = rgOptions.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRadioButton = findViewById(selectedId);
        String selectedAnswer = selectedRadioButton.getText().toString();

        Question currentQuestion = questionList.get(currentQuestionIndex);
        if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            score++;
        }

        currentQuestionIndex++;
        displayQuestion();
    }

    private void finishQuiz() {
        Intent intent = new Intent(StudentQuizActivity.this, ResultActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL_QUESTIONS", questionList.size());
        startActivity(intent);
        finish();
    }
}
