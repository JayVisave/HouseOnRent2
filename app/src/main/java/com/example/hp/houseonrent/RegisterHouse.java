package com.example.hp.houseonrent;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterHouse extends AppCompatActivity  {
    private Spinner Size_list,Requirement_list, Locality_list;
    private ArrayAdapter<CharSequence> adapter;
    String size,requirement,locality;
    private EditText price,capacity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_house);
        Size_list =(Spinner) findViewById(R.id.size_list);
        Requirement_list = (Spinner) findViewById(R.id.req_list);
        Locality_list = (Spinner) findViewById(R.id.local_list);
        adapter = ArrayAdapter.createFromResource(this,R.array.Locality, R.layout.support_simple_spinner_dropdown_item);
        Locality_list.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(this,R.array.Size, R.layout.support_simple_spinner_dropdown_item);
        Size_list.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(this,R.array.Requirements, R.layout.support_simple_spinner_dropdown_item);
        Requirement_list.setAdapter(adapter);
        Size_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                size = parent.getItemAtPosition(position).toString();
               
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Locality_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                locality = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Requirement_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                requirement = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    @Override
    public void onBackPressed() {

        ShowAlert s = new ShowAlert();
        s.Alert(this);



    }



   
}
