package com.pau.todolist;

public class Note {

    private String mTitle;      //All variables will be private and only will modified through setters and getters.
    private String mDescription;

    private boolean mToDo;
    private boolean mIdea;
    private boolean mImportant;

    public String getTitle() {         //Alt + insert -> Creating getters and setters
        return mTitle;                  //We remove the m from the method name because of a convention.
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public boolean isToDo() {
        return mToDo;
    }

    public void setToDo(boolean mToDo) {
        this.mToDo = mToDo;
    }

    public boolean isIdea() {
        return mIdea;
    }

    public void setIdea(boolean mIdea) {
        this.mIdea = mIdea;
    }

    public boolean isImportant() {
        return mImportant;
    }

    public void setImportant(boolean mImportant) {
        this.mImportant = mImportant;
    }
}
