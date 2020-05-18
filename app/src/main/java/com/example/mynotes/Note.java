package com.example.mynotes;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    private int dayOfWeek;
    private int priority;

    public Note(int id, String name, String description, int dayOfWeek, int priority) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dayOfWeek = dayOfWeek;
        this.priority = priority;
    }

    @Ignore
    public Note(String name, String description, int dayOfWeek, int priority) {
        this.name = name;
        this.description = description;
        this.dayOfWeek = dayOfWeek;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public int getPriority() {
        return priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public static String getDayAsString(int position) {

        switch (position) {
            case 0:
                return "monday" ;
            case 1:
                return "tuesday" ;
            case 2:
                return "wednesday" ;
            case 3:
                return "thursday" ;
            case 4:
                return "friday" ;
            case 5:
                return "saturday" ;
            default:
                return "sunday" ;
        }
    }
}
