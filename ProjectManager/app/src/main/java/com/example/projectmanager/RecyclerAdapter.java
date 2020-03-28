package com.example.projectmanager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.eventViewHolder>{

    private Context context;
    private List<Event> eventList;

    public RecyclerAdapter(Context context, List<Event> eventList){
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public eventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.event_list, null);
        return new eventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull eventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.eventName.setText(event.getName());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class eventViewHolder extends RecyclerView.ViewHolder{
        TextView eventName;
        public eventViewHolder(@NonNull View itemView) {

            super(itemView);

            eventName = itemView.findViewById(R.id.eventTitle);

        }
    }

}
