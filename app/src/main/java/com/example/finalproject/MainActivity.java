package com.example.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String firstIngridient, secondIngridient, thirdIngridient;

    EditText firstIngridientInput, secondIngridientInput, thirdIngridientInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set the Input Boxes.
        firstIngridientInput = (EditText) findViewById(R.id.add_First);
        secondIngridientInput = (EditText) findViewById(R.id.add_Second);
        thirdIngridientInput = (EditText) findViewById(R.id.add_Third);

        //Get the Search Button
        Button searchButton = findViewById(R.id.searching);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Wait for the Cook", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
