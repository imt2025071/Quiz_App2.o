package com.example.quizora;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RoleSelectionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection);

        Button btnTeacher = findViewById(R.id.btnTeacher);
        Button btnStudent = findViewById(R.id.btnStudent);

        btnTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoleSelectionActivity.this, TeacherConfigActivity.class);
                startActivity(intent);
            }
        });

        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Placeholder for student view
                Toast.makeText(RoleSelectionActivity.this, "Student interface coming soon!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
