package com.example.saviour.Main_Activity.Menu_View_Members;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.saviour.Conn;
import com.example.saviour.R;

import java.util.Objects;

public class Edit_Members extends AppCompatActivity {
    EditText name, mobile;
    Button edit;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_members);
        setTitle("Edit Members");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Conn conn = new Conn(this);

        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        edit = findViewById(R.id.editbutton);

        String mobilefullno = getIntent().getStringExtra("mobile");
        String mobileno = mobilefullno.substring(4);

        name.setText(getIntent().getStringExtra("name"));
        mobile.setText(mobileno);
        String id = getIntent().getStringExtra("id");

        edit.setOnClickListener(view -> {
            String editname = name.getText().toString().trim().toUpperCase();
            String editmobile = mobile.getText().toString().trim();

            conn.update_member(id, editname, "+91-" + editmobile);

            Toast.makeText(this, "Successfully updated", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Edit_Members.this, View_Members.class);
            startActivity(intent);
            finish();

        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}