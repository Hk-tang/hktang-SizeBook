package com.example.henzoshimada.hktang_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private ListView oldRecordList;
    private ListView recordList;
    private ArrayList<Record> RecordList;
    private ArrayAdapter<Record> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oldRecordList = (ListView) findViewById(R.id.oldRecordList);
    }
    public void EditButton(View view) {
        Intent intent = new Intent(this, EditRecord.class);
        startActivity(intent);
    }

    /*Taken from lab3 LonelyTwitter
    https://github.com/Hk-tang/lonelyTwitter/tree/lab3/app/src/main/java/ca/ualberta/cs/lonelytwitter
    Taken on January 26, 2017 15:05*/
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();

        adapter = new ArrayAdapter<Record>(this, android.R.layout.simple_list_item_1, RecordList);
        oldRecordList.setAdapter(adapter);

        // Taken From: https://teamtreehouse.com/community/how-can-i-open-a-new-activity-when-an-item-is-clicked-on-in-the-listview
        // 2017-02-02 15:33
        oldRecordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent myIntent = new Intent(view.getContext(), EditRecord.class);
                    startActivityForResult(myIntent, 0);
                }
            }
        );
    }

    private void displayRecords(ArrayList<Record> RecordList){
        recordList = (ListView) findViewById(R.id.oldRecordList);

        ArrayAdapter<Record> displayAdapter = new ArrayAdapter<Record>(this, android.R.layout.simple_list_item_1, RecordList);
        recordList.setAdapter(displayAdapter);
        displayAdapter.notifyDataSetChanged();
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Taken from http://stackovtions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-01-24 18:19
            Type listType = new TypeToken<ArrayList<Record>>() {
            }.getType();
            RecordList = gson.fromJson(in, listType);

            displayRecords(RecordList);

        } catch (FileNotFoundException e) {
            RecordList = new ArrayList<Record>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
