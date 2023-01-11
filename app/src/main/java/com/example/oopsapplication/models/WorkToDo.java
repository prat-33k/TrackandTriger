package com.example.oopsapplication.models;

public class WorkToDo {
    String details;
    String description;

    public WorkToDo(String details, String description) {
        this.details = details;
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
