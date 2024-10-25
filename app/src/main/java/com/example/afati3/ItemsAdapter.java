package com.example.afati3;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;

public class ItemsAdapter extends CursorAdapter {

    private TextView usernameTextView, surnameTextView, brutoTextView, pensionTextView, realWageTextView;


    public ItemsAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.record_items, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
            usernameTextView = view.findViewById(R.id.usernameTextView);
            surnameTextView = view.findViewById(R.id.surnameTextView);
            brutoTextView = view.findViewById(R.id.brutoTextView);
            pensionTextView = view.findViewById(R.id.pensionTextView);
            realWageTextView = view.findViewById(R.id.realWageTextView);

            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseManager.COLUMN_NAME));
            String surname = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseManager.COLUMN_SURNAME));
            double bruto = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseManager.COLUMN_BRUTO));
            double pension = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseManager.COLUMN_PENSION_CONTRIBUTION));
            double realWage = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseManager.COLUMN_WAGE_WITHOUT_TAX));

            usernameTextView.setText(name);
            surnameTextView.setText(surname);
            brutoTextView.setText(String.valueOf(bruto));
            pensionTextView.setText(String.valueOf(pension));
            realWageTextView.setText(String.valueOf(realWage));


            int recordPosition = cursor.getPosition() + 1; // let's start the record from 1 not 0;
            System.out.println("The positino is: " + cursor.getPosition() + " and the record is: " + recordPosition);

            if(recordPosition % 2 == 0 ) {
                view.setBackgroundColor(Color.LTGRAY);
            }


    }
}
