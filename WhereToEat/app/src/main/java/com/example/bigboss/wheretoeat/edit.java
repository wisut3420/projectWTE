package com.example.bigboss.wheretoeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.ErrnoException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class edit extends AppCompatActivity {

    myDbAdapter helper;
    private Spinner spinner;
    private ArrayList<String> type = new ArrayList<String>();
    EditText name,time,recommend,price,lat,longt;
    int sSpinner;
    long idSpinner;
    String strSpinner;
    String idMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        helper = new myDbAdapter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        name = (EditText) findViewById(R.id.wresname);
        time = (EditText) findViewById(R.id.wresopen);
        recommend = (EditText) findViewById(R.id.wresmenu);
        price = (EditText) findViewById(R.id.wprincerate);
        lat = (EditText) findViewById(R.id.wreslati);
        longt = (EditText) findViewById(R.id.wresloti);

        type.add("ชาบู");
        type.add("หมูกระทะ");
        type.add("อาหารตามสั่ง");
        type.add("ก๋วยเตี๋ยว");

        spinner = (Spinner) findViewById(R.id.wrestype);
        ArrayAdapter<String> menu = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, type);
        spinner.setAdapter(menu);

        Bundle bundle = getIntent().getExtras();
        String nameEdit;
        if(bundle != null){
            nameEdit = bundle.getString("name");
            String rest = helper.showEdit(nameEdit);
            String[] restSplit = rest.split("__");
            if(restSplit[1].equals("ชาบู")){
                spinner.setSelection(0);
            } else if(restSplit[1].equals("หมูกระทะ")){
                spinner.setSelection(1);
            } else if(restSplit[1].equals("อาหารตามสั่ง")){
                spinner.setSelection(2);
            } else if(restSplit[1].equals("ก๋วยเตี๋ยว")){
                spinner.setSelection(3);
            }

            idMain = restSplit[0];

            try {
                name.setText(restSplit[2]);
                time.setText(restSplit[3]);
                recommend.setText(restSplit[4]);
                price.setText(restSplit[5]);
                lat.setText(restSplit[6]);
                longt.setText(restSplit[7]);
            }catch (Exception e){
                Message.message(getApplicationContext(), "Something wrong...\n"+e);
            }

        }
    }


    public void edit(View view){
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

        String name2 = name.getText().toString();
        String time2 = time.getText().toString();
        String recommend2 = recommend.getText().toString();
        String price2 = price.getText().toString();
        String lat2 = lat.getText().toString();
        String longt2 = longt.getText().toString();
        helper.editRestaurant(idMain,name2,strSpinner,time2,recommend2,price2,lat2,longt2);
        Message.message(getApplicationContext(),"Edit Successful... Maybe");

        Intent intent = new Intent(edit.this,adminctr.class);
        startActivity(intent);
        finish();
    }

    public void delete(View view){
        helper.deleteRestaurant(idMain);
        Message.message(getApplicationContext(),"Delete Successful... Maybe");

        Intent intent = new Intent(edit.this,adminctr.class);
        startActivity(intent);
        finish();
    }

    public void backControl(View view){
        Intent intent = new Intent(edit.this,adminctr.class);
        startActivity(intent);
        finish();
    }

}
