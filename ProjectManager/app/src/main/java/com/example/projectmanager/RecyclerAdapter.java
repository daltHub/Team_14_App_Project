package com.example.projectmanager;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.eventViewHolder>{

    private Context context;
    private List<Event> eventList;
    private Dialog myDialog;
    private boolean multiSelect = false;
    private ArrayList<Integer> selectedItems = new ArrayList<Integer>();
    private OnItemLongClick Callback;

    public RecyclerAdapter(Context context, List<Event> eventList, OnItemLongClick listener){
        this.context = context;
        this.eventList = eventList;
        this.Callback = listener;
    }

    @NonNull
    @Override
    public eventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.event_list, null);
        final eventViewHolder vHolder = new eventViewHolder(view);


        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.event_details);


        vHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView name = myDialog.findViewById(R.id.event_name);
                TextView time = myDialog.findViewById(R.id.event_time);
                TextView desc = myDialog.findViewById(R.id.event_desc);
                name.setText(eventList.get(vHolder.getAdapterPosition()).getName());
                time.setText(eventList.get(vHolder.getAdapterPosition()).getHour() + ":" + eventList.get(vHolder.getAdapterPosition()).getMinute());
                desc.setText(eventList.get(vHolder.getAdapterPosition()).getDesc());
                myDialog.show();
            }
        });
        vHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(context, "Event Deleted", Toast.LENGTH_SHORT).show();
                Callback.onLongClick(eventList.get(vHolder.getAdapterPosition()).getId());
               return true;
            }
        });
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull eventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.eventName.setText(event.getName());
        holder.eventTime.setText(event.getHour() + ":" + event.getMinute());

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class eventViewHolder extends RecyclerView.ViewHolder{
        TextView eventName, eventTime;
        public eventViewHolder(@NonNull View itemView) {

            super(itemView);

            eventName = itemView.findViewById(R.id.eventTitle);
            eventTime = itemView.findViewById(R.id.eventTime);

        }
    }
    public interface OnItemLongClick{
        void onLongClick(String value);
    }

}
