package com.example.henzoshimada.hktang_sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class EditRecord extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private ArrayList<Record> recordList;
    private ArrayAdapter<Record> adapter;
    private Integer position;
    private Record person;

    private EditText nameField;
    private EditText dateField;
    private EditText neckField;
    private EditText bustField;
    private EditText chestField;
    private EditText waistField;
    private EditText hipField;
    private EditText inseamField;
    private EditText commentField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);

        loadFromFile();

        nameField = (EditText) findViewById(R.id.NameField);
        dateField = (EditText) findViewById(R.id.DateField);
        neckField = (EditText) findViewById(R.id.NeckField);
        bustField = (EditText) findViewById(R.id.BustField);
        chestField = (EditText) findViewById(R.id.ChestField);
        waistField = (EditText) findViewById(R.id.WaistField);
        hipField = (EditText) findViewById(R.id.HipField);
        inseamField = (EditText) findViewById(R.id.InseamField);
        commentField = (EditText) findViewById(R.id.CommentField);


        if (position != -1){
            Log.d("tag","Old Entry");
            nameField.setText(recordList.get(position).getName());

            if (recordList.get(position).getDate() != null){
                dateField.setText(recordList.get(position).getDate());
            }
            if (recordList.get(position).getNeck() != null){
                neckField.setText(recordList.get(position).getNeck().toString());
            }
            if (recordList.get(position).getBust() != null){
                bustField.setText(recordList.get(position).getBust().toString());
            }
            if (recordList.get(position).getChest() != null){
                chestField.setText(recordList.get(position).getChest().toString());
            }
            if (recordList.get(position).getWaist() != null){
                waistField.setText(recordList.get(position).getWaist().toString());
            }
            if (recordList.get(position).getHip() != null){
                hipField.setText(recordList.get(position).getHip().toString());
            }
            if (recordList.get(position).getInseam() != null){
                inseamField.setText(recordList.get(position).getInseam().toString());
            }
            if (recordList.get(position).getComment() != null){
                commentField.setText(recordList.get(position).getComment());
            }
        }

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();

        adapter = new ArrayAdapter<Record>(this,
                R.layout.list_item, recordList);

    }

    public void deleteData(View view){
        recordList.remove(position.intValue());
        saveInFile();
        finish();
    }

    public void saveData(View view) {

        try {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            String name = nameField.getText().toString();
            Log.d("tag", "New Entry");
            if(name.length() == 0){
                Toast.makeText(context, "Must have a name", duration).show();
            }
            else {
                person = new Record(name);

                person.setDate(dateField.getText().toString());
                person.setNeck(neckField.getText().toString());
                person.setBust(bustField.getText().toString());
                person.setChest(chestField.getText().toString());
                person.setWaist(waistField.getText().toString());
                person.setHip(hipField.getText().toString());
                person.setInseam(inseamField.getText().toString());
                person.setComment(commentField.getText().toString());

                if (position == -1) {
                    recordList.add(person);
                } else {
                    Log.d("tag", "Old Entry");
                    recordList.set(position, person);
                    Log.d("tag", "after editing: " + recordList.toString());
                }
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        }
        catch (IndexOutOfBoundsException e){
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, "Error Invalid Input", duration).show();
            return;

        }
        finish();
    }
    private void loadFromFile() {
        Log.d("tag","EditLoadFile");
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-01-24 18:19
            Type listType = new TypeToken<ArrayList<Record>>() {
            }.getType();
            recordList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            recordList = new ArrayList<Record>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
    private void saveInFile() {
        Log.d("tag","SaveFile");
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Log.d("tag","save: "+recordList.toString());
            Gson gson = new Gson();
            gson.toJson(recordList, out);
            out.flush();

        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
