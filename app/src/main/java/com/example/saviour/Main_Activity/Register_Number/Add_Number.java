package com.example.saviour.Main_Activity.Register_Number;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.saviour.Conn;
import com.example.saviour.R;

import java.util.Objects;

public class Add_Number extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText name,mobile;
        Button submit;
        Conn conn=new Conn(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_number);
        setTitle("Add Members");
        name=findViewById(R.id.name);
        mobile=findViewById(R.id.mobile);
        submit=findViewById(R.id.submit);

        submit.setOnClickListener(view ->{

            String mem_name=name.getText().toString().trim().toUpperCase();
            String mem_mobile=mobile.getText().toString().trim();
            if(mem_name.isEmpty() && mem_mobile.isEmpty())
            {
                name.setError("Field Required");
                mobile.setError("Field Required");
            }
            else if(mem_name.isEmpty())
            {
                name.setError("Field Required");
            }
            else if(mem_mobile.isEmpty())
            {
                mobile.setError("Field Required");
            }
            else {

                boolean check = conn.insert_members(mem_name, "+91-" + mem_mobile);
                if (check) {
                    Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show();
                    name.setText("");
                    mobile.setText("");
                }
            }

        });

    }
}