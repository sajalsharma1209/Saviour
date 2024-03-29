package com.example.saviour.Main_Activity.Menu_Edit_Sos_Message;

import android.database.Cursor;
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

public class Edit_Sos_Message extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        EditText message;
        Button savebtn;
        Conn conn = new Conn(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sos_message);
        setTitle("Sos Message");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        message = findViewById(R.id.msgtxt);
        savebtn = findViewById(R.id.savebutton);
        Cursor cursor = conn.get_message();
        if (cursor.moveToFirst()) {

            message.setHint(cursor.getString(1));
        }

        savebtn.setOnClickListener(vi ->
        {

            String sosmessage = message.getText().toString().trim();

            boolean check = conn.update_message(sosmessage);
            if (check) {
                Toast.makeText(this, "Now, sos will sent this update message to your family members", Toast.LENGTH_SHORT).show();
                Cursor cursor1 = conn.get_message();
                if (cursor1.moveToFirst()) {
                    message.setText("");
                    message.setHint(cursor1.getString(1));
                }
            }

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