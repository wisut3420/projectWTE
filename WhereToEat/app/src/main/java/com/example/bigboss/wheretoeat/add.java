package com.example.bigboss.wheretoeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class add extends AppCompatActivity {

    private Spinner spinner;
    private ArrayList<String> type = new ArrayList<String>();

    myDbAdapter helper;
    int sSpinner;
    long idSpinner;
    String strSpinner;
    EditText wresname,wresopen,wresmenu,wprincerate,wreslati,wresloti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        helper = new myDbAdapter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        type.add("ชาบู");
        type.add("หมูกระทะ");
        type.add("อาหารตามสั่ง");
        type.add("ก๋วยเตี๋ยว");

        spinner = (Spinner) findViewById(R.id.wrestype);

        wresname = (EditText) findViewById(R.id.wresname);
        wresopen = (EditText) findViewById(R.id.wresopen);
        wresmenu = (EditText) findViewById(R.id.wresmenu);
        wprincerate = (EditText) findViewById(R.id.wprincerate);
        wreslati = (EditText) findViewById(R.id.wreslati);
        wresloti = (EditText) findViewById(R.id.wresloti);

        ArrayAdapter<String> adapterThai = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, type);
        spinner.setAdapter(adapterThai);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sSpinner = position;
                idSpinner = id;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btnAddres = findViewById(R.id.btnAddres);
        btnAddres.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if(sSpinner==0){
                    strSpinner="ชาบู";
                }
                else if(sSpinner==1){
                    strSpinner="หมูกระทะ";
                }
                else if(sSpinner==2){
                    strSpinner="อาหารตามสั่ง";
                }
                else if(sSpinner==3){
                    strSpinner="ก๋วยเตี๋ยว";
                }

                String name = wresname.getText().toString();
                String time = wresopen.getText().toString();
                String recommend = wresmenu.getText().toString();
                String price = wprincerate.getText().toString();
                String lat = wreslati.getText().toString();
                String longt = wresloti.getText().toString();
                long result = helper.addRestaurant(name,strSpinner,time,recommend,price,lat,longt);
                if(result==0){
                    Message.message(getApplicationContext(),"Add Unsuccessful, Restaurant already in database");
                }
                else {
                    Message.message(getApplicationContext(),"Add Successful");
                }

                Intent intent = new Intent(add.this,adminctr.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void test(View view){
        String allData = helper.getDataRest();
        Message.message(getApplicationContext(),allData);
    }

    public void rst(View view){
        wresname.setText("");
        wresopen.setText("");
        wresmenu.setText("");
        wprincerate.setText("");
        wreslati.setText("");
        wresloti.setText("");
    }

}
