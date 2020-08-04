package com.pau.todolist;

import org.json.JSONException;
import org.json.JSONObject;

public class Note {

    private String mTitle;      //All variables will be private and only will modified through setters and getters.
    private String mDescription;

    private boolean mToDo;
    private boolean mIdea;
    private boolean mImportant;

    private static final String JSON_TITLE = "title";
    private static final String JSON_DESCRIPTION = "description";
    private static final String JSON_IDEA = "idea";
    private static final String JSON_TODO = "todo";
    private static final String JSON_IMPORTANT = "important";

    //Constructor base vacío
    public Note(){

    }

    //Constructor para crear una nota a partir de un objeto JSON
    public Note(JSONObject jo) throws JSONException {
        mTitle = jo.getString(JSON_TITLE);
        mDescription = jo.getString(JSON_DESCRIPTION);
        mIdea = jo.getBoolean(JSON_IDEA);
        mToDo = jo.getBoolean(JSON_TODO);
        mImportant = jo.getBoolean(JSON_IMPORTANT);
    }

    //El método toma las 5 variables de la nota y las serializa en un objeto tipo JSON
    public JSONObject convertNoteToJSON() throws JSONException{

        JSONObject jo = new JSONObject();

        jo.put(JSON_TITLE, mTitle);
        jo.put(JSON_DESCRIPTION, mDescription);
        jo.put(JSON_IDEA, mIdea);
        jo.put(JSON_TODO, mToDo);
        jo.put(JSON_IMPORTANT, mImportant);

        return jo;
    }

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
