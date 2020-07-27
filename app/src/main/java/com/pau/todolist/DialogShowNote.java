package com.pau.todolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogShowNote extends DialogFragment {
    //THIS CLASS CONTAINS SOME EXPLANATIONS TO UNDERSTAND HOW A DIALOG WORKS.
    private Note mNote;

    public void sendNoteSelected(Note noteSelected) {
        this.mNote = noteSelected;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Alert Dialog allow us to instantiate .PositiveButton and NegativeButton methods. 
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());   //getActivity: Parameter. The dialog must be built in the activity that has called it --> Context.
        //In most cases, this activity will be MainActivity.
        LayoutInflater inflater = getActivity().getLayoutInflater();        //The activity is who inflates the dialog layout.

        View dialogView = inflater.inflate(R.layout.show_note, null);           //inflater.inflate returns a view.

        TextView textTitle = (TextView) dialogView.findViewById(R.id.view_Title);
        TextView textDescription = (TextView) dialogView.findViewById(R.id.view_Description);

        textTitle.setText(mNote.getTitle());
        textDescription.setText(mNote.getDescription());

        ImageView imImportant = (ImageView) dialogView.findViewById(R.id.imageImportantShow);
        ImageView imToDo = (ImageView) dialogView.findViewById(R.id.imageToDoShow);
        ImageView imIdea = (ImageView) dialogView.findViewById(R.id.imageIdeaShow);

        if (!mNote.isIdea()) {
            imIdea.setVisibility(View.INVISIBLE);
        }
        if (!mNote.isImportant()) {
            imImportant.setVisibility(View.INVISIBLE);
        }
        if (!mNote.isToDo()) {
            imToDo.setVisibility(View.INVISIBLE);
        }

        Button btOk = (Button) dialogView.findViewById(R.id.btOk);

        builder.setView(dialogView)
                .setMessage("Your note");

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return builder.create();
    }
}


