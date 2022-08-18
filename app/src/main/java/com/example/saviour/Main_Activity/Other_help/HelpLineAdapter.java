package com.example.saviour.Main_Activity.Other_help;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saviour.R;

import java.util.List;

public class HelpLineAdapter extends RecyclerView.Adapter<HelpLineAdapter.viewholder> {

    List<HelpLineModel> datalist;

    public HelpLineAdapter(List<HelpLineModel> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.other_helpline_single, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        holder.name.setText(datalist.get(position).getName());
        holder.mobile.setText(datalist.get(position).getMobile());

        holder.itemView.setOnClickListener(view -> {

            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + datalist.get(position).getMobile()));
           // intent.setData(Uri.parse("tel:" + "9411270551"));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            holder.itemView.getContext().startActivity(intent);


        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {

        TextView name, mobile;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            mobile = itemView.findViewById(R.id.mobile);
        }
    }
}
