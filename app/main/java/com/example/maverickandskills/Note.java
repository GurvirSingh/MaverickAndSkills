package com.example.maverickandskills;

public class Note {
    private String title;
    private String description;
    private String date;
    private String time;
    private int priority;

    public Note() {
        //empty constructor needed
    }

    public Note(String title, String description,String date,String time) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.priority = 1;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}