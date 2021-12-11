package edu.csustan.cs3810.gacha_helper;
//Written By Corey Edh
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// written by:
// tested by:
// debugged by:
// etc.

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {

    private ArrayList<String> artifactStatList;
    private RecyclerViewClickListener listener;


    public recyclerAdapter(ArrayList<String> artifactStatList, RecyclerViewClickListener listener) {
        this.artifactStatList = artifactStatList;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView artifactStatTxt;


        public MyViewHolder(final View view) {
            super(view);
            artifactStatTxt = view.findViewById(R.id.textView7);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)  {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String stat = artifactStatList.get(position).toString();
        holder.artifactStatTxt.setText(stat);

    }

    //How many items it needs to show in the RecyclerView
    @Override
    public int getItemCount() {
        return artifactStatList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View view, int position);
    }

}
