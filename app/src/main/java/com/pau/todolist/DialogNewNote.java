package com.pau.todolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogNewNote extends DialogFragment {     //Create a dialog

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());   //getActivity gives the context.
        LayoutInflater inflater = getActivity().getLayoutInflater();            //To inflate a Layout from the function getActivity().

        View dialogView = inflater.inflate(R.layout.dialog_new_note, null);     //Calling the desired layout.

        final EditText edTitle = (EditText) dialogView.findViewById(R.id.editTitle);      //Remember, with final we make references.
        final EditText edDescription = (EditText) dialogView.findViewById(R.id.editDescription);

        final CheckBox cbIdea = (CheckBox) dialogView.findViewById(R.id.checkBoxIdea);
        final CheckBox cbImportant = (CheckBox) dialogView.findViewById(R.id.checkImportant);
        final CheckBox cbToDo = (CheckBox) dialogView.findViewById(R.id.checkBoxToDo);

        Button btCancel = (Button) dialogView.findViewById(R.id.buttonCancel);
        Button btOk = (Button) dialogView.findViewById(R.id.buttonOk);


        builder.setView(dialogView)
                .setMessage("Create a new note");   //Setting the message requires these 2 lines.

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();                      //Automatically closes the menu.
            }
        });

        btOk.setOnClickListener(new View.OnClickListener() {        //IMPORTANT: LISTENERS WAIT FOR SOMETHING TO BE PRESSED.
                                                                        // CHECKERS ONLY CHECK THE STATUS OF A RADIO BUTTON (FE)
            @Override
            public void onClick(View view) {
                Note newNote = new Note();       //Class we have created.

                newNote.setTitle(edTitle.getText().toString());
                newNote.setDescription(edDescription.getText().toString());

                newNote.setIdea(cbIdea.isChecked());
                newNote.setImportant(cbImportant.isChecked());
                newNote.setToDo(cbToDo.isChecked());


                MainActivity callingActivity = (MainActivity) getActivity(); //Main Activity is in fact the main one.

                callingActivity.createNewNote(newNote);

                dismiss();      //It returns to the same point as the cancel button but saving the note.
            }
        });

        return builder.create()
    }


}
