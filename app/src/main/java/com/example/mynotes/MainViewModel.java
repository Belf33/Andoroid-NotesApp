package com.example.mynotes;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static NotesDatabase notesDatabase;
    private LiveData<List<Note>> notesList;


    public MainViewModel(@NonNull Application application) {
        super(application);

        notesDatabase = NotesDatabase.getInstance(getApplication());
        notesList = notesDatabase.notesDao().getAllNotes();
    }

    public LiveData<List<Note>> getNotesList() {
        return notesList;
    }

    public void insertNote(Note note) {
        new InsertTask().execute(note);
    }

    public void deleteNote(Note note) {
        new DeleteTask().execute(note);
    }

    public void deleteAllNotes() {
        new DeleteAllTask().execute();
    }

    private static class InsertTask extends AsyncTask<Note, Void, Void> {

        @Override
        protected Void doInBackground(Note... notes) {
            if (notes != null && notes.length > 0) {
                notesDatabase.notesDao().insertNote(notes[0]);
            }
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<Note, Void, Void> {
        @Override
        protected Void doInBackground(Note... notes) {
            if (notes != null && notes.length > 0) {
                notesDatabase.notesDao().deleteNote(notes[0]);
            }
            return null;
        }
    }

    private static class DeleteAllTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            notesDatabase.notesDao().getAllNotes();
            return null;
        }
    }
}
