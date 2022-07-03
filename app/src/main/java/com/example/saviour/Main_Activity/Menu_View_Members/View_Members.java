package com.example.saviour.Main_Activity.Menu_View_Members;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.saviour.Conn;
import com.example.saviour.R;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class View_Members extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<View_Members_Model> datalist;
    View_Members_Adapter adapter;
    Conn conn=new Conn(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_members);
        setTitle("Members List");

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        datalist=new ArrayList<>();

        Cursor cursor=conn.get_members();

        if (cursor != null && cursor.getCount() > 0){
            while (cursor.moveToNext()) {
                View_Members_Model obj = new View_Members_Model(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                datalist.add(obj);
            }
            adapter=new View_Members_Adapter(datalist);
            recyclerView.setAdapter(adapter);
        }


    }
}