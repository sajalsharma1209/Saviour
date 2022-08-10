package com.example.saviour.Main_Activity.Menu_Add_Members;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.saviour.Conn;
import com.example.saviour.R;

import java.util.Objects;

public class Add_Number extends AppCompatActivity {

    TextView no_count;
    EditText name, mobile;
    Button submit;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Conn conn = new Conn(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_number);
        setTitle("Add Members");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        submit = findViewById(R.id.submit);
        no_count = findViewById(R.id.txtnumbercount);

        String count = "" + conn.get_members().getCount();

        no_count.setText(count + " numbers added");

        if (Integer.parseInt(count) >= 10) {
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

                    no_count.setText(Integer.parseInt("" + conn.get_members().getCount()) + " numbers added");

                    if (Integer.parseInt("" + conn.get_members().getCount()) >= 10) {
                        name.setEnabled(false);
                        mobile.setEnabled(false);
                        submit.setEnabled(false);
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_contact, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        if (item.getItemId() == R.id.contact) {
            startActivityForResult(new Intent("android.intent.action.PICK", ContactsContract.Contacts.CONTENT_URI), 1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*

    for pick the contact in contact list

     */
    @SuppressLint("Range")
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (reqCode == 1) {
                Cursor cursor1, cursor2 = null;

                Uri uri = data.getData();
                cursor1 = getContentResolver().query(uri, null, null, null, null);
                if (cursor1.moveToFirst()) {
                    String contactid = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts._ID));
                    String contactname = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    int idresulthold = Integer.parseInt(cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                    name.setText(contactname);

                    if (idresulthold == 1) {
                        cursor2 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactid,
                                null,
                                null);
                        while (cursor2.moveToNext()) {
                            String contactnumber = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            mobile.setText(formatNumber(contactnumber));
                            //mobile.setText(contactnumber);
                        }
                    }
                }

            }
        } 

    }

    private String formatNumber(String number) {
        StringBuilder s = new StringBuilder();
        char c;
        for (int i = 0; i < number.length(); i++) {
            c = number.charAt(i);
            if (c != ' ') {

                s.append(c);
            }
        }

        if (s.length() == 14)

            return "" + s.substring(4);

        else if (s.length() == 13)
            return "" + s.substring(3);
        else
            return "" + s;


    }
}