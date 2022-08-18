package com.example.saviour.Main_Activity.Other_help;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saviour.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Others_Helpline extends AppCompatActivity {

    List datalist;
    HelpLineAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_helpline);
        setTitle("HelpLine Numbers");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        datalist = new ArrayList<>();
        datalist = getdata();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new HelpLineAdapter(datalist);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @NonNull
    private List getdata() {

        List<HelpLineModel> list = new ArrayList<>();
        list.add(new HelpLineModel("Senior Citizen Helpline",
                "1091"));
        list.add(new HelpLineModel("Railway Accident Emergency Service",
                "1072"));
        list.add(new HelpLineModel("Children Helpline number",
                "1098"));
        return list;
    }
}