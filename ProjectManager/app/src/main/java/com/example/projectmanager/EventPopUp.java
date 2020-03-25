package com.example.projectmanager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

public class EventPopUp extends AppCompatDialogFragment {
    private EditText editName;
    private EditText editDesc;
    private TextView date;
    private DialogListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.eventpopup, null);
        Bundle args = getArguments();
        String day = args.getString("day");
        String month = args.getString("month");
        String year = args.getString("year");
        //String date1 = day + "/" + month + "/" + year;
        date = view.findViewById(R.id.DialogTitle);
        date.setText("Date: " + day + "/" + month +"/" + year);

        builder.setView(view)
                .setTitle("New Event")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //DialogFragment.this.getDialog().cancel();
                    }
                })
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        String name = editName.getText().toString();
                        String desc = editDesc.getText().toString();
                        listener.applyText(name, desc);
                    }

        });
        editName = view.findViewById(R.id.edit_name);
        editDesc = view.findViewById(R.id.edit_desc);

        return builder.create();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement DialogListener");
        }
    }

    public interface DialogListener{
        void applyText(String name, String desc);
    }
}
