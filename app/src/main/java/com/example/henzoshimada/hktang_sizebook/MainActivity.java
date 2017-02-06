package com.example.henzoshimada.hktang_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

/**
 * The Main activity.
 * This class is the main view class of the project. 
 * In this class we provide a view of the records and a counter for how many there are
 * All files are in the form of "json" files that are stored in Emulator's accessible from Android Device Monitor
 */
public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private ListView oldRecordList;
    private ListView recordList;
    private ArrayList<Record> RecordList;
    private TextView counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
            Creates a listview instance to hold the old records
            creates a counter for the number of records
        */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = (TextView) findViewById(R.id.counter);
        oldRecordList = (ListView) findViewById(R.id.oldRecordList);
    }

    /**
     * Edit button.
     *
     * When the edit button is selected then go to the second screen
     * 
     */
    public void EditButton(View view) {
        Intent intent = new Intent(this, EditRecord.class);
        intent.putExtra("position", -1);
        startActivity(intent);
    }

    /*Taken from lab3 LonelyTwitter
    https://github.com/Hk-tang/lonelyTwitter/tree/lab3/app/src/main/java/ca/ualberta/cs/lonelytwitter
    Taken on January 26, 2017 15:05*/
    @Override
    protected void onStart() {
        // Loads the old records from file
        super.onStart();
        loadFromFile();

        Integer numRecords = RecordList.size();
        counter.setText(String.format("%d",numRecords));

        Log.d("tag","Main on Start");

        Log.d("tag","record list: "+RecordList.toString());
        displayRecords(RecordList);

        // Taken From: https://teamtreehouse.com/community/how-can-i-open-a-new-activity-when-an-item-is-clicked-on-in-the-listview
        // 2017-02-02 15:33
        // If a record is selected then send this specific record to the second screen
        oldRecordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent myIntent = new Intent(view.getContext(), EditRecord.class);
                    myIntent.putExtra("position", position);
                    startActivityForResult(myIntent, 0);
                }
            }
        );
    }

    /**
     * Displays the records on the main screen in a list view
    */
    private void displayRecords(ArrayList<Record> RecordList){
        Log.d("tag","Display Records");
        recordList = (ListView) findViewById(R.id.oldRecordList);

        ArrayAdapter<Record> displayAdapter = new ArrayAdapter<Record>(this, android.R.layout.simple_list_item_1, RecordList);
        recordList.setAdapter(displayAdapter);
        displayAdapter.notifyDataSetChanged();
        Log.d("tag","done Display Records");
    }

    // Load the old records
    private void loadFromFile() {
        Log.d("tag","MainLoadFile");
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Taken from http://stackovtions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-01-24 18:19
            Type listType = new TypeToken<ArrayList<Record>>() {
            }.getType();
            RecordList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            Log.d("tag","file not found exception");
            RecordList = new ArrayList<Record>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
