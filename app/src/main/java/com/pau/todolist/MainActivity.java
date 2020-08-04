package com.pau.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private Note mTempNote = new Note();  WHEN WE ONLY CREATED ONE NOTE.

    private NoteAdapter mNoteAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNoteAdd = new NoteAdapter();
        ListView listNotes = (ListView) findViewById(R.id.list_view);

        listNotes.setAdapter(mNoteAdd);

        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemPos, long l) {
                Note tempNote = mNoteAdd.getItem(itemPos);      //getItem is a method that involves the list with all the notes.
                                                                //It gives the item of the index, a note.
                DialogShowNote shower = new DialogShowNote();
                shower.sendNoteSelected(tempNote);
                shower.show(getSupportFragmentManager(), "");        //Calling the showNote dialog.
            }
        });
        /*                                                                  WHEN WE ONLY HAD ONE NOTE.
        Button showNote = (Button) findViewById(R.id.bt_show_note);

        showNote.setOnClickListener(new View.OnClickListener() {            //To show the note
            @Override
            public void onClick(View view) {
                DialogShowNote showNote = new DialogShowNote();     //Calling the class.
                showNote.sendNoteSelected(mTempNote);
                showNote.show(getSupportFragmentManager(),"1234");       //Line that allows the dialog to show.
            }
        });
          */          //See that the code necessary to call the showNote dialog and the newNote dialog is the same.
    }

    public void createNewNote(Note note){   //DialogNewNote calls it and stores the new note and sendNoteSelected uses it
       mNoteAdd.addNote(note);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {     //Calling the menu which isn't located in the activity_main layout.
        getMenuInflater().inflate(R.menu.menu, menu);   //onCreateOptionsMenu is used for a menu layout.
        return super.onCreateOptionsMenu(menu);
    }

    @Override                                                           //Writting onOptions... and onCreate automatically completes the code.
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add){
            DialogNewNote openNew = new DialogNewNote();
            openNew.show(getSupportFragmentManager(),"3456");   //Necessary to open the dialog.
        }
        //Here we would implement the code of the other options of the menu. FE: Button to open the settings.
        return false;
    }

    @Override
    protected void onPause() {
        super.onPause();

        mNoteAdd.saveNotes();
    }


    public class NoteAdapter extends BaseAdapter {      //It would be the same as having everything put i NoteAdapter class.
                                                        //We make an inner class because we don't inflate any dialog. It isn't necessary to make such a distribution.
        List<Note> list = new ArrayList<Note>();   //The purpose of all this is to make the code more readable.
        private JSONSerializer mSerializer;

        public NoteAdapter(){
            mSerializer = new JSONSerializer("ToDoListsJB.json", MainActivity.this.getApplicationContext());

            try{
                list = mSerializer.load();      //Converting a JSONArray object into a note one.
            }catch (Exception e){
                e.printStackTrace();            //We can see that load method is called in the constructor.
            }                                   //That means that the list is automatically refreshed when the app is started.
        }

        public void saveNotes(){
            try{
                mSerializer.save(list);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Note getItem(int itemPos) {
            return list.get(itemPos);
        }

        @Override
        public long getItemId(int itemPos) {
            return itemPos;
        }

        @Override
        public View getView(int itemPos, View view, ViewGroup viewGroup) {

            if(view == null){       //null -> Indicates that there's no view at the moment. -> In this if we are initializing it.
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.list_item, viewGroup, false);
            }

            TextView textTitle = (TextView) findViewById(R.id.textTitle);
            TextView textDescription = (TextView) findViewById(R.id.textDescription);

            ImageView imImportant = (ImageView) findViewById(R.id.imageImportant);
            ImageView imToDo = (ImageView) findViewById(R.id.imageToDo);
            ImageView imIdea = (ImageView) findViewById(R.id.imageIdea);

            Note currentNote = list.get(itemPos);

            if(!currentNote.isImportant()){
                imImportant.setVisibility(View.INVISIBLE);
            }
            if(!currentNote.isToDo()){
                imToDo.setVisibility(View.INVISIBLE);
            }
            if(!currentNote.isIdea()){
                imIdea.setVisibility(View.INVISIBLE);
            }

            textTitle.setText(currentNote.getTitle());
            textDescription.setText(currentNote.getDescription());

            return view;
        }

        public void addNote(Note n){
            list.add(n);
            notifyDataSetChanged(); //Notifies the list that one note has been added.
                                    //The listView must be reseted.
        }

    }


//See that from every activity we can only open dialogs. Different screens are ran in different activities.
}