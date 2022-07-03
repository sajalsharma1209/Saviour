package com.example.saviour.Main_Activity.Menu_Edit_Sos_Message;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saviour.Conn;
import com.example.saviour.R;

public class Edit_Sos_Message extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        EditText message;
        Button savebtn;
        Conn conn = new Conn(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sos_message);
        setTitle("Sos Message");

        message = findViewById(R.id.msgtxt);
        savebtn = findViewById(R.id.savebutton);
        Cursor cursor = conn.message_get_value();
        if (cursor.moveToFirst()) {

            message.setHint(cursor.getString(1));
        }

        savebtn.setOnClickListener(vi ->
        {

            String sosmessage = message.getText().toString().trim();

            boolean check = conn.uupdate_message(sosmessage);
            if (check) {
                Toast.makeText(this, "Now, sos will sent this update message to your family members", Toast.LENGTH_SHORT).show();
                Cursor cursor1 = conn.message_get_value();
                if (cursor1.moveToFirst()) {
                    message.setText("");
                    message.setHint(cursor1.getString(1));
                }
            }

        });

    }
}