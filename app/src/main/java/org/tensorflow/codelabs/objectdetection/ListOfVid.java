package org.tensorflow.codelabs.objectdetection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListOfVid extends RecyclerView.Adapter<ListOfVid.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<String> vis;

    ListOfVid(Context context, List<String> vis){
        this.vis = vis;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull

    @Override
    public ListOfVid.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_vid, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String vi = vis.get(position);
        holder.nameVi.setText(vi);
    }

    @Override
    public int getItemCount() {
        return vis.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameVi;
        final Button copy;
        ViewHolder(View view){
            super(view);
            nameVi = view.findViewById(R.id.videoName);
            copy = view.findViewById(R.id.cp);
        }
    }
}
