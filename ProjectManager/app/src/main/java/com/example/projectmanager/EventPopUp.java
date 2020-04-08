package com.example.projectmanager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;


import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

public class EventPopUp extends AppCompatDialogFragment {
    private EditText editName;
    private EditText editDesc;
    private TimePicker timePicker1;
    private TextView date;
    private DialogListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.eventpopup, null);
        Bundle args = getArguments();
        String Date = args.getString("date");
        //String date1 = day + "/" + month + "/" + year;
        date = view.findViewById(R.id.DialogTitle);
        date.setText("Date: " + Date);


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
                        int hour = timePicker1.getCurrentHour();
                        int minute = timePicker1.getCurrentMinute();
                        String desc = editDesc.getText().toString();
                        listener.applyText(name, hour, minute, desc);
                    }

        });
        editName = view.findViewById(R.id.edit_name);
        timePicker1 = view.findViewById(R.id.edit_time);
        timePicker1.setIs24HourView(true);
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
        void applyText(String name, int hour, int minute, String desc);
    }
}
