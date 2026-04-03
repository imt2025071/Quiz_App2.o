package com.example.quizora;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizApp.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_QUESTIONS = "questions";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_OPT_A = "option_a";
    public static final String COLUMN_OPT_B = "option_b";
    public static final String COLUMN_OPT_C = "option_c";
    public static final String COLUMN_OPT_D = "option_d";
    public static final String COLUMN_ANSWER = "correct_answer";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_QUESTIONS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_QUESTION + " TEXT, " +
                COLUMN_OPT_A + " TEXT, " +
                COLUMN_OPT_B + " TEXT, " +
                COLUMN_OPT_C + " TEXT, " +
                COLUMN_OPT_D + " TEXT, " +
                COLUMN_ANSWER + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        onCreate(db);
    }

    // Insert a new question into the database
    public long insertQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION, question.getQuestionText());
        values.put(COLUMN_OPT_A, question.getOptionA());
        values.put(COLUMN_OPT_B, question.getOptionB());
        values.put(COLUMN_OPT_C, question.getOptionC());
        values.put(COLUMN_OPT_D, question.getOptionD());
        values.put(COLUMN_ANSWER, question.getCorrectAnswer());

        long result = db.insert(TABLE_QUESTIONS, null, values);
        db.close();
        return result; // Returns -1 if an error occurred
    }

    // Retrieve all questions from the database
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_QUESTIONS;
        
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                question.setQuestionText(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QUESTION)));
                question.setOptionA(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OPT_A)));
                question.setOptionB(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OPT_B)));
                question.setOptionC(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OPT_C)));
                question.setOptionD(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OPT_D)));
                question.setCorrectAnswer(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ANSWER)));
                
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return questionList;
    }
}
