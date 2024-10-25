package com.example.afati3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText, surnameEditText, brutoEditText;
    private Button calculateButton, showListView;
    private TextView resultTextView;
    private DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_relative);

        initializeComponents();

        calculateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                doCalculations();
            }


        });

        showListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecordsActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initializeComponents() {
        usernameEditText = findViewById(R.id.usernameEditText);
        surnameEditText = findViewById(R.id.surnameEditText);
        brutoEditText = findViewById(R.id.brutoEditText);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);
        showListView = findViewById(R.id.showListViewButton);
        databaseManager = new DatabaseManager(this);

    }

    private void doCalculations() {
        String name = usernameEditText.getText().toString();
        String surname = surnameEditText.getText().toString();
        double brutoWage = Double.parseDouble(brutoEditText.getText().toString());
        double employeeContribution = brutoWage * 0.05;
        double employerContribution = brutoWage * 0.05;
        double pensionalContribution = employeeContribution + employerContribution;
        double taxedWage = brutoWage - pensionalContribution;

        String result = name + " " + surname + " paga juaj bruto eshte: " + brutoWage
                + "\nNga kjo page, kontributi juaj pensional eshte " + pensionalContribution
                + "ku " + employeeContribution + " jane nga ju dhe " + employerContribution + " jane nga punedhenesi."
                + "\nPaga e tatueshme eshte: " + taxedWage + ".";

        UserDataModel userDataModel = new UserDataModel(name, surname, brutoWage, pensionalContribution, taxedWage);
        databaseManager.insertUser(userDataModel);


        resultTextView.setText(result);
    }

}