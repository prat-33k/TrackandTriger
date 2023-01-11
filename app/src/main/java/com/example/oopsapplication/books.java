package com.example.oopsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oopsapplication.models.Grocery;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class books extends AppCompatActivity {
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayList<String> listKeys = new ArrayList<String>();
    ArrayList<String> listDetails = new ArrayList<String>();

    ArrayAdapter<String> adapter;
    Button todo;
    private ListView dataListView;
    private EditText itemText;
    public EditText notes;
    private Button findButton;
    private Button deleteButton;
    Button addButton;
    Button calendar;
    private Boolean searchMode = false;
    private Boolean itemSelected = false;
    private int selectedPosition = 0;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    private DatabaseReference dbRef = database.getReference("books-to-read/"+uid);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        calendar =(Button) findViewById(R.id.button9);
        dataListView = (ListView) findViewById(R.id.dataListView);
        itemText = (EditText) findViewById(R.id.itemText);
        notes = (EditText) findViewById(R.id.editTextTextMultiLine4);
        Button addButton = (Button) findViewById(R.id.addButton);
        findButton = (Button) findViewById(R.id.findButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        todo=(Button) findViewById(R.id.shareid);

        deleteButton.setEnabled(true);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice,
                listItems);
        dataListView.setAdapter(adapter);
        dataListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        dataListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {


                    @Override
                    public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                        selectedPosition = position;
                        itemSelected = true;
                        deleteButton.setEnabled(true);
                        notes.setText(listDetails.get(position));

                    }
                    /*public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        selectedPosition = position;
                        itemSelected = true;
                        deleteButton.setEnabled(true);
                    }*/


                });

        addChildEventListener();
        calendar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent2 = new Intent(books.this,thecalendar.class);
                startActivity(intent2);
            }
        });
    }
    private void addChildEventListener() {
        ChildEventListener childListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adapter.add(
                        (String) dataSnapshot.child("description").getValue());
                listDetails.add((String) dataSnapshot.child("details").getValue());

                listKeys.add(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                int index = listKeys.indexOf(key);

                if (index != -1) {
                    listItems.remove(index);
                    listKeys.remove(index);
                    adapter.notifyDataSetChanged();
                    listDetails.remove(index);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        dbRef.addChildEventListener(childListener);
    }



    ValueEventListener queryValueListener = new ValueEventListener() {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
            Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

            adapter.clear();
            listKeys.clear();

            while (iterator.hasNext()) {
                DataSnapshot next = (DataSnapshot) iterator.next();

                String match = (String) next.child("description").getValue();
                String key = next.getKey();
                listKeys.add(key);
                adapter.add(match);
            }

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    public void  share(android.view.View view){

        Intent shareintent=new Intent();
        shareintent.setAction(Intent.ACTION_SEND);
        String selectedFromList = (String) dataListView.getItemAtPosition(selectedPosition);
        shareintent.putExtra(Intent.EXTRA_TEXT,"The Note is:  "+ selectedFromList);
        shareintent.setType("text/plain");
        startActivity(shareintent);
    }



    public void deleteItem(android.view.View view) {

        if(dataListView.getAdapter().getCount()<=selectedPosition)
        {selectedPosition=0;}
        dataListView.setItemChecked(selectedPosition,false);
        dbRef.child(listKeys.get(selectedPosition)).removeValue();
    }

    public void findItems(android.view.View view) {
        Query query;

        if (!searchMode) {
            findButton.setText("Clear");
            query = dbRef.orderByChild("description").
                    equalTo(itemText.getText().toString());
            searchMode = true;
        } else {
            searchMode = false;
            findButton.setText("Find");
            query = dbRef.orderByKey();
        }

        if (itemSelected) {
            dataListView.setItemChecked(selectedPosition, false);
            itemSelected = false;
            deleteButton.setEnabled(false);
        }

        query.addListenerForSingleValueEvent(queryValueListener);
    }

    public void addItem(android.view.View view) {


        String note = notes.getText().toString();
        String item = itemText.getText().toString();
        String key = dbRef.push().getKey();

        itemText.setText("");
        notes.setText("");

        dbRef.child(key).setValue(new Grocery(note, item));

        adapter.notifyDataSetChanged();
    }
}