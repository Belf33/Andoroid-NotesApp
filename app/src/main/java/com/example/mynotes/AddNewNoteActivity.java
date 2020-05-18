package com.example.mynotes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

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
    MainViewModel viewModel;

    int priority = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.hide();

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

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
            Note note = new Note(titleText, descriptionText, dayOfWeek, priority);
            viewModel.insertNote(note);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Title and description should be filled", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isFilled(String title, String description) {
        return !title.isEmpty() && !description.isEmpty();
    }
}
