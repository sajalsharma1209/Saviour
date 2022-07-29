package com.example.saviour.Main_Activity.Menu_View_Members;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saviour.Conn;
import com.example.saviour.Main_Activity.Menu;
import com.example.saviour.R;

public class Edit_Members extends AppCompatActivity {
    EditText name, mobile;
    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_members);
        setTitle("Edit Members");

        Conn conn = new Conn(this);

        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        edit = findViewById(R.id.editbutton);

        name.setText(getIntent().getStringExtra("name"));
        mobile.setText(getIntent().getStringExtra("mobile"));
        String id = getIntent().getStringExtra("id");

        edit.setOnClickListener(view -> {
            String editname = name.getText().toString().trim().toUpperCase();
            String editmobile = mobile.getText().toString().trim();

            conn.update_member(id, editname, editmobile);

            Toast.makeText(this, "Successfully updated", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Edit_Members.this, View_Members.class);
            startActivity(intent);

        });
    }
}