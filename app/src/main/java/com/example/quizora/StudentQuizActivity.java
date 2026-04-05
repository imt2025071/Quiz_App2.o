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
    private View llQuestionContainer;

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
        llQuestionContainer = findViewById(R.id.llQuestionContainer);

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
        String selectedAnswer = selectedRadioButton.getText().toString().trim();

        Question currentQuestion = questionList.get(currentQuestionIndex);
        String correctAnswer = currentQuestion.getCorrectAnswer().trim();

        boolean isCorrect = false;
        if (selectedAnswer.equalsIgnoreCase(correctAnswer)) {
            isCorrect = true;
        } else if (correctAnswer.equalsIgnoreCase("A") && selectedId == R.id.rbOptionA) {
            isCorrect = true;
        } else if (correctAnswer.equalsIgnoreCase("B") && selectedId == R.id.rbOptionB) {
            isCorrect = true;
        } else if (correctAnswer.equalsIgnoreCase("C") && selectedId == R.id.rbOptionC) {
            isCorrect = true;
        } else if (correctAnswer.equalsIgnoreCase("D") && selectedId == R.id.rbOptionD) {
            isCorrect = true;
        }

        if (isCorrect) {
            score++;
        }

        currentQuestionIndex++;
        
        if (currentQuestionIndex < questionList.size()) {
            // Fade out, update, fade in
            llQuestionContainer.animate()
                    .alpha(0f)
                    .translationX(-50f)
                    .setDuration(250)
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            displayQuestion();
                            llQuestionContainer.setTranslationX(50f);
                            llQuestionContainer.animate()
                                    .alpha(1f)
                                    .translationX(0f)
                                    .setDuration(250)
                                    .start();
                        }
                    }).start();
        } else {
            finishQuiz();
        }
    }

    private void finishQuiz() {
        Intent intent = new Intent(StudentQuizActivity.this, ResultActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL_QUESTIONS", questionList.size());
        startActivity(intent);
        finish();
    }
}
