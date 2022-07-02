package com.example.saviour.Main_Activity.Register_Number;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saviour.Conn;
import com.example.saviour.R;

public class Add_Number extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView no_count;
        EditText name, mobile;
        Button submit;
        Conn conn = new Conn(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_number);
        setTitle("Add Members");

        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        submit = findViewById(R.id.submit);
        no_count = findViewById(R.id.txtnumbercount);

        String count = conn.get_row();

        no_count.setText(count + " numbers added");

        if (Integer.parseInt(count) + 1 > 10 && Integer.parseInt(count) + 1 >= 11) {
            name.setEnabled(false);
            mobile.setEnabled(false);
            submit.setEnabled(false);
        }

        submit.setOnClickListener(view -> {

            String mem_name = name.getText().toString().trim().toUpperCase();
            String mem_mobile = mobile.getText().toString().trim();
            String count1 = conn.get_row();
            if(Integer.parseInt(count)+1 <= 10 && Integer.parseInt(count)+1 < 11)
            {
                if (mem_name.isEmpty() && mem_mobile.isEmpty()) {
                    name.setError("Field Required");
                    mobile.setError("Field Required");
                } else if (mem_name.isEmpty()) {
                    name.setError("Field Required");
                } else if (mem_mobile.isEmpty()) {
                    mobile.setError("Field Required");
                } else {
                    boolean check = conn.insert_members(mem_name, "+91-" + mem_mobile);
                    if (check) {
                        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show();
                        name.setText("");
                        mobile.setText("");

                        no_count.setText(Integer.parseInt( count1)+1+ " numbers added");
                    }
                }
            }
            else
            {
                name.setEnabled(false);
                mobile.setEnabled(false);
                submit.setEnabled(false);

            }

        });

    }
}