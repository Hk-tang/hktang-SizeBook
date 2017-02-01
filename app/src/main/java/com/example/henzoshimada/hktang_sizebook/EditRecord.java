package com.example.henzoshimada.hktang_sizebook;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditRecord extends AppCompatActivity {

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
    }

    public void saveData(View view) {
        Intent intent = new Intent(this, EditRecord.class);
        startActivity(intent);

        nameField = (EditText) findViewById(R.id.NameField);
        dateField = (EditText) findViewById(R.id.DateField);
        neckField = (EditText) findViewById(R.id.NeckField);
        bustField = (EditText) findViewById(R.id.BustField);
        chestField = (EditText) findViewById(R.id.ChestField);
        waistField = (EditText) findViewById(R.id.WaistField);
        hipField = (EditText) findViewById(R.id.HipField);
        inseamField = (EditText) findViewById(R.id.InseamField);
        commentField = (EditText) findViewById(R.id.CommentField);

        String name = nameField.getText().toString();

        Record person = new Record(name);

        person.setDate(dateField.getText().toString());
        person.setNeck(neckField.getText().toString());
        person.setBust(bustField.getText().toString());
        person.setChest(chestField.getText().toString());
        person.setWaist(waistField.getText().toString());
        person.setHip(hipField.getText().toString());
        person.setInseam(inseamField.getText().toString());
        person.setComment(commentField.getText().toString());


    }


}
