package com.example.saviour.Main_Activity.Menu_Add_Members;

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

        String count =""+ conn.get_members().getCount();

        no_count.setText(count + " numbers added");

        if ( Integer.parseInt(count) >=10) {
            name.setEnabled(false);
            mobile.setEnabled(false);
            submit.setEnabled(false);
        }

        submit.setOnClickListener(view -> {

            String mem_name = name.getText().toString().trim().toUpperCase();
            String mem_mobile = mobile.getText().toString().trim();


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
                        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        mobile.setText("");

                        no_count.setText(Integer.parseInt(""+conn.get_members().getCount())+ " numbers added");

                        if ( Integer.parseInt(""+conn.get_members().getCount()) >=10) {
                            name.setEnabled(false);
                            mobile.setEnabled(false);
                            submit.setEnabled(false);
                        }
                    }
                }


        });

    }
}