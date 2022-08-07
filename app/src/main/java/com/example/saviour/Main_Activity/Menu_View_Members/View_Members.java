package com.example.saviour.Main_Activity.Menu_View_Members;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saviour.Conn;
import com.example.saviour.R;

import java.util.ArrayList;
import java.util.Objects;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class View_Members extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<View_Members_Model> datalist;
    View_Members_Adapter adapter;
    Conn conn = new Conn(this);
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(View_Members.this, R.color.red))
                    .addSwipeRightActionIcon(R.drawable.delete_icon)
                    .setActionIconTint(ContextCompat.getColor(View_Members.this, R.color.black))
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(View_Members.this, R.color.blue))
                    .addSwipeLeftActionIcon(R.drawable.edit_icon)
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getLayoutPosition();

            switch (direction) {
                case ItemTouchHelper.LEFT:

                    Intent intent = new Intent(viewHolder.itemView.getContext(), Edit_Members.class);

                    intent.putExtra("id", datalist.get(position).getId());
                    intent.putExtra("name", datalist.get(position).getName());
                    intent.putExtra("mobile", datalist.get(position).getMobile());

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    viewHolder.itemView.getContext().startActivity(intent);
                    adapter.notifyDataSetChanged();
                    break;

                case ItemTouchHelper.RIGHT:
                    String id = datalist.get(position).getId();
                    new Conn(getApplicationContext()).delete_member(id);

                    datalist.remove(position);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_members);
        setTitle("Members List");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        datalist = new ArrayList<>();

        Cursor cursor = conn.get_members();

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                View_Members_Model obj = new View_Members_Model(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                datalist.add(obj);
            }
            adapter = new View_Members_Adapter(datalist);
            recyclerView.setAdapter(adapter);
        }
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}