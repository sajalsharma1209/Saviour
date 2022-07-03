package com.example.saviour.Main_Activity.Menu_View_Members;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saviour.R;

import java.util.ArrayList;

public class View_Members_Adapter extends RecyclerView.Adapter<View_Members_Adapter.myviewholder>{
    ArrayList<View_Members_Model> dataList;

    public View_Members_Adapter(ArrayList<View_Members_Model> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_members_single,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.id.setText(dataList.get(position).getId());
        holder.name.setText(dataList.get(position).getName());
        holder.mobile.setText(dataList.get(position).getMobile());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class myviewholder extends RecyclerView.ViewHolder {
        TextView id,name,mobile;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            id=itemView.findViewById(R.id.id);
            name=itemView.findViewById(R.id.name);
            mobile=itemView.findViewById(R.id.mobile);
        }
    }


}
