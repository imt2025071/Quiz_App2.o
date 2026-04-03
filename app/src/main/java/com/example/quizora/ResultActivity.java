package com.example.quizora;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        int score = getIntent().getIntExtra("SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);

        TextView tvScore = findViewById(R.id.tvScore);
        tvScore.setText("Your Score: " + score + " / " + totalQuestions);

        Button btnReturnHome = findViewById(R.id.btnReturnHome);
        btnReturnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, RoleSelectionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
