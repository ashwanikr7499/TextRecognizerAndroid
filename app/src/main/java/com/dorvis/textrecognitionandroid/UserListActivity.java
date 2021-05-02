package com.dorvis.textrecognitionandroid;

import android.os.Bundle;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {
    ArrayList<Contact> contacts;
    Button delete_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ...
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        // Lookup the recyclerview in activity layout
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

        // Initialize contacts
        contacts = Contact.createContactsList(0);
        // Create adapter passing in the sample user data
        ContactsAdapter adapter = new ContactsAdapter(contacts);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!

        rvContacts.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                findViewById(R.id.childScroll).getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });

        // Add a new contact
//        contacts.add(0, new Contact("Barney", "Hi "));
        // Notify the adapter that an item was inserted at position 0

        delete_all = findViewById(R.id.delete_all);
        final DBHelper dbHelper = new DBHelper(getApplicationContext());
//        Toast.makeText(this, ""+dbHelper.getAllContacts().toString(), Toast.LENGTH_SHORT).show();
        adapter.notifyItemInserted(0);
        for (Pair<String, String> p : dbHelper.getAllContacts()) {
            contacts.add(new Contact(p.first, p.second));
        }
        delete_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteAll();
            }
        });
    }
}
