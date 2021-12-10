package edu.csustan.cs3810.gacha_helper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {

    private ArrayList<String> artifactStatList;

    public recyclerAdapter(ArrayList<String> artifactStatList) {
        this.artifactStatList = artifactStatList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView artifactStatTxt;

        public MyViewHolder(final View view) {
            super(view);
            artifactStatTxt = view.findViewById(R.id.textView7);
        }

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
}
