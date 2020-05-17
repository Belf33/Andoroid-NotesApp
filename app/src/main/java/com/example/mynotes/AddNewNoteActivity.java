package com.example.mynotes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class AddNewNoteActivity extends AppCompatActivity {

    EditText title;
    EditText description;
    RadioGroup radioGroup;
    Spinner spinner;
    Button createButton;
    NotesDbHelper notesDbHelper;
    SQLiteDatabase database;

    int priority = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null)
            actionBar.hide();

        notesDbHelper = new NotesDbHelper(this);
        database  = notesDbHelper.getWritableDatabase();
        title = findViewById(R.id.newNotetitle);
        description = findViewById(R.id.newNotedescription);
        spinner = findViewById(R.id.daysOfWeekSpinner);
        radioGroup = findViewById(R.id.priorityRadioButtonGroup);
        createButton = findViewById(R.id.createButton);
    }

    public void createNewNote(View view) {
        String titleText = title.getText().toString().trim();
        String descriptionText = description.getText().toString().trim();
        int dayOfWeek = spinner.getSelectedItemPosition();
        int id = radioGroup.getCheckedRadioButtonId();

        RadioButton selectedRadio = findViewById(id);

        priority = Integer.parseInt(selectedRadio.getText().toString());

        if (isFilled(titleText, descriptionText)) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(NotesContract.NotesEntry.COLUMN_TITLE, titleText);
            contentValues.put(NotesContract.NotesEntry.COLUMN_DESCRIPTION, descriptionText);
            contentValues.put(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK, dayOfWeek);
            contentValues.put(NotesContract.NotesEntry.COLUMN_PRIORITY, priority);

            database.insert(NotesContract.NotesEntry.TABLE_NAME, null, contentValues);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else Toast.makeText(getApplicationContext(), "Check if all Fields are filled", Toast.LENGTH_SHORT).show();
    }

    private boolean isFilled(String title, String description) {
        if(title.isEmpty() || description.isEmpty())
            return false;
        return true;
    }
}
